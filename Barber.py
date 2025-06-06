import threading
import time
import random

# Константы
NUM_CHAIRS = 5  # Количество стульев в приемной
HAIRCUT_TIME_RANGE = (2, 5)  # Диапазон времени стрижки (секунды)
CUSTOMER_INTERVAL = (1, 4)  # Интервал между приходами клиентов (секунды)

# Семафоры и переменные состояния
barber_semaphore = threading.Semaphore(0)  # Семафор для парикмахера
customer_semaphore = threading.Semaphore(0)  # Семафор для клиентов
access_seats_mutex = threading.Semaphore(1)  # Мьютекс для доступа к разделяемым данным
free_seats = NUM_CHAIRS  # Количество свободных стульев в приемной


def barber():
    """Поток парикмахера"""
    global free_seats

    print("\033[94mПарикмахер: Открываю парикмахерскую и засыпаю...\033[0m")

    while True:
        # Ожидание клиента
        customer_semaphore.acquire()

        # Проверка состояния приемной
        access_seats_mutex.acquire()
        free_seats += 1  # Освобождаем один стул в приемной

        # Подготовка к стрижке
        barber_semaphore.release()
        access_seats_mutex.release()

        # Процесс стрижки
        haircut_time = random.randint(*HAIRCUT_TIME_RANGE)
        print(f"\033[93mПарикмахер: Начинаю стрижку ({haircut_time} сек.)\033[0m")
        time.sleep(haircut_time)
        print("\033[92mПарикмахер: Стрижка завершена!\033[0m")


def customer(customer_id):
    """Поток клиента"""
    global free_seats

    print(f"\033[95mКлиент {customer_id}: Заходит в парикмахерскую\033[0m")

    # Попытка занять место
    access_seats_mutex.acquire()

    if free_seats > 0:
        free_seats -= 1
        print(f"\033[96mКлиент {customer_id}: Садится в приемной (свободно мест: {free_seats}/{NUM_CHAIRS})\033[0m")

        # Уведомление парикмахера
        customer_semaphore.release()
        access_seats_mutex.release()

        # Ожидание своей очереди
        barber_semaphore.acquire()
        print(f"\033[92mКлиент {customer_id}: Начинает стричься\033[0m")
    else:
        # Нет свободных мест - уход
        access_seats_mutex.release()
        print(f"\033[91mКлиент {customer_id}: Уходит - нет свободных мест!\033[0m")


def customer_generator():
    """Генератор клиентов"""
    customer_id = 1

    while True:
        # Случайный интервал между клиентами
        time.sleep(random.randint(*CUSTOMER_INTERVAL))

        # Создание нового клиента
        threading.Thread(
            target=customer,
            args=(customer_id,),
            daemon=True
        ).start()

        customer_id += 1


def main():
    # Запуск потоков
    threading.Thread(target=barber, daemon=True).start()
    threading.Thread(target=customer_generator, daemon=True).start()

    # Основной поток работает бесконечно
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("\n\033[94mПарикмахерская закрывается...\033[0m")


if __name__ == "__main__":
    main()
