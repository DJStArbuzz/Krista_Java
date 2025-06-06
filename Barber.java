import java.util.concurrent.Semaphore;
import java.util.Random;

public class Barber {

    // Константы
    private static final int NUM_CHAIRS = 5;          // Количество стульев в приемной
    private static final int MIN_HAIRCUT_TIME = 2000;  // Минимальное время стрижки (мс)
    private static final int MAX_HAIRCUT_TIME = 5000;  // Максимальное время стрижки (мс)
    private static final int MIN_CUSTOMER_INTERVAL = 1000; // Минимальный интервал между клиентами (мс)
    private static final int MAX_CUSTOMER_INTERVAL = 4000; // Максимальный интервал между клиентами (мс)

    // Семафоры и переменные состояния
    private static Semaphore barberSemaphore = new Semaphore(0);
    private static Semaphore customerSemaphore = new Semaphore(0);
    private static Semaphore accessSeatsMutex = new Semaphore(1);
    private static int freeSeats = NUM_CHAIRS;

    private static Random random = new Random();
    private static volatile boolean isOpen = true;

    static class Barber implements Runnable {
        @Override
        public void run() {
            System.out.println("\u001B[94mПарикмахер: Открываю парикмахерскую и засыпаю...\u001B[0m");

            while (isOpen) {
                try {
                    // Ожидание клиента
                    customerSemaphore.acquire();

                    if (!isOpen) break;

                    // Проверка состояния приемной
                    accessSeatsMutex.acquire();
                    freeSeats++; // Освобождаем одно место

                    // Подготовка к стрижке
                    barberSemaphore.release();
                    accessSeatsMutex.release();

                    // Процесс стрижки
                    int haircutTime = MIN_HAIRCUT_TIME + random.nextInt(MAX_HAIRCUT_TIME - MIN_HAIRCUT_TIME);
                    System.out.printf("\u001B[93mПарикмахер: Начинаю стрижку (%d сек.)\u001B[0m%n", haircutTime / 1000);
                    Thread.sleep(haircutTime);
                    System.out.println("\u001B[92mПарикмахер: Стрижка завершена!\u001B[0m");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("\u001B[94mПарикмахер: Закрываю парикмахерскую\u001B[0m");
        }
    }

    static class Customer implements Runnable {
        private final int id;

        public Customer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.printf("\u001B[95mКлиент %d: Заходит в парикмахерскую\u001B[0m%n", id);

            try {
                // Попытка занять место
                accessSeatsMutex.acquire();

                if (freeSeats > 0) {
                    freeSeats--;
                    System.out.printf("\u001B[96mКлиент %d: Садится в приемной (свободно мест: %d/%d)\u001B[0m%n",
                            id, freeSeats, NUM_CHAIRS);

                    // Уведомление парикмахера
                    customerSemaphore.release();
                    accessSeatsMutex.release();

                    // Ожидание своей очереди
                    barberSemaphore.acquire();

                    if (!isOpen) {
                        System.out.printf("\u001B[91mКлиент %d: Уходит - парикмахерская закрывается!\u001B[0m%n", id);
                        return;
                    }

                    System.out.printf("\u001B[92mКлиент %d: Начинает стричься\u001B[0m%n", id);
                } else {
                    // Нет свободных мест - уход
                    accessSeatsMutex.release();
                    System.out.printf("\u001B[91mКлиент %d: Уходит - нет свободных мест!\u001B[0m%n", id);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class CustomerGenerator implements Runnable {
        @Override
        public void run() {
            int customerId = 1;

            while (isOpen) {
                try {
                    // Случайный интервал между клиентами
                    int interval = MIN_CUSTOMER_INTERVAL +
                            random.nextInt(MAX_CUSTOMER_INTERVAL - MIN_CUSTOMER_INTERVAL);
                    Thread.sleep(interval);

                    // Создание нового клиента
                    new Thread(new Customer(customerId++)).start();
                } catch (InterruptedException e) {
                    // Сигнал о закрытии парикмахерской
                    isOpen = false;
                    customerSemaphore.release(); // Разбудить парикмахера для завершения
                    break;
                }
            }
            System.out.println("\u001B[94mГенератор клиентов: Остановлен\u001B[0m");
        }
    }

    public static void main(String[] args) {
        // Запуск потоков
        Thread barberThread = new Thread(new Barber());
        Thread generatorThread = new Thread(new CustomerGenerator());

        barberThread.start();
        generatorThread.start();

        // Ожидание закрытия (Ctrl+C)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n\u001B[94mПолучен сигнал закрытия...\u001B[0m");
            isOpen = false;
            generatorThread.interrupt();
            customerSemaphore.release(); // Разбудить парикмахера
        }));

        try {
            barberThread.join();
            generatorThread.join();
        } catch (InterruptedException e) {
            System.out.println("\u001B[91mГлавный поток прерван!\u001B[0m");
        }

        System.out.println("\u001B[94mПарикмахерская закрыта!\u001B[0m");
    }
}
