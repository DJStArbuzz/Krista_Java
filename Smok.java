import java.util.Random;
import java.util.concurrent.Semaphore;

enum Component {
    TOBACCO, PAPER, FILTER
}

public class Smok {

    // Семафоры для пар компонентов
    private static final Semaphore tobaccoPaper = new Semaphore(0);
    private static final Semaphore tobaccoFilter = new Semaphore(0);
    private static final Semaphore paperFilter = new Semaphore(0);
    private static final Semaphore bartenderSem = new Semaphore(1);

    private static final Random random = new Random();

    public static void main(String[] args) {
        // Поток бармена
        new Thread(() -> {
            while (true) {
                try {
                    bartenderSem.acquire();

                    // Случайный выбор комбинации компонентов
                    int combination = random.nextInt(3);
                    switch (combination) {
                        case 0:
                            System.out.println("\u001B[94mБармен положил: Табак и Бумагу\u001B[0m\n");
                            tobaccoPaper.release();
                            break;
                        case 1:
                            System.out.println("\u001B[94mБармен положил: Табак и Фильтр\u001B[0m\n");
                            tobaccoFilter.release();
                            break;
                        case 2:
                            System.out.println("\u001B[94mБармен положил: Бумагу и Фильтр\u001B[0m\n");
                            paperFilter.release();
                            break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }).start();

        // Потоки курильщиков
        new Thread(createSmoker("Курильщик Т", "Табак", paperFilter, "Бумагу", "Фильтр")).start();
        new Thread(createSmoker("Курильщик Б", "Бумагу", tobaccoFilter, "Табак", "Фильтр")).start();
        new Thread(createSmoker("Курильщик Ф", "Фильтр", tobaccoPaper, "Табак", "Бумагу")).start();

        // Бесконечное ожидание (прерывается Ctrl+C)
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("\nПрограмма остановлена");
        }
    }

    private static Runnable createSmoker(String name,
                                         String hasItem,
                                         Semaphore neededSem,
                                         String item1,
                                         String item2) {
        return () -> {
            while (true) {
                try {
                    neededSem.acquire();
                    System.out.printf("\u001B[92m%s (имеет %s) взял %s и %s\u001B[0m\n\n",
                            name, hasItem, item1, item2);
                    bartenderSem.release();

                    // Процесс "курения"
                    System.out.printf("\u001B[93m%s начинает курить...\u001B[0m\n\n", name);
                    int smokeTime = 2000 + random.nextInt(2000); // 2-4 секунды
                    Thread.sleep(smokeTime);

                    System.out.printf("\u001B[93m%s закончил курить (%d сек.)\u001B[0m\n\n",
                            name, smokeTime / 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };
    }
}
