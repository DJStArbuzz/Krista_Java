import threading
import time
import random

# Количество философов и вилок
PHILOSOPHERS_COUNT = 5
# Время действий (в секундах)
THINK_TIME = (1, 3)
EAT_TIME = (2, 4)

# Семафоры для вилок (каждая вилка - семафор с 1 разрешением)
forks = [threading.Semaphore(1) for _ in range(PHILOSOPHERS_COUNT)]
# Семафор для ограничения одновременно обедающих философов
table = threading.Semaphore(PHILOSOPHERS_COUNT - 1)


def philosopher(id):
    while True:
        # Философ размышляет
        think_time = random.uniform(*THINK_TIME)
        print(f"\033[94mФилософ {id} размышляет {think_time:.1f} сек.\033[0m")
        time.sleep(think_time)

        # Философ хочет есть
        print(f"\033[93mФилософ {id} хочет есть\033[0m")

        # Занимаем место за столом (не все философы могут есть одновременно)
        table.acquire()

        # Берём вилки (сначала левую, потом правую)
        left_fork = id
        right_fork = (id + 1) % PHILOSOPHERS_COUNT

        forks[left_fork].acquire()
        print(f"\033[96mФилософ {id} взял левую вилку ({left_fork})\033[0m")

        forks[right_fork].acquire()
        print(f"\033[96mФилософ {id} взял правую вилку ({right_fork})\033[0m")

        # Философ ест
        eat_time = random.uniform(*EAT_TIME)
        print(f"\033[92mФилософ {id} ест {eat_time:.1f} сек.\033[0m")
        time.sleep(eat_time)

        # Освобождаем вилки
        forks[right_fork].release()
        print(f"\033[95mФилософ {id} положил правую вилку ({right_fork})\033[0m")

        forks[left_fork].release()
        print(f"\033[95mФилософ {id} положил левую вилку ({left_fork})\033[0m")

        # Освобождаем место за столом
        table.release()


if __name__ == "__main__":
    # Создаём и запускаем философов
    philosophers = []
    for i in range(PHILOSOPHERS_COUNT):
        thread = threading.Thread(target=philosopher, args=(i,))
        thread.daemon = True
        thread.start()
        philosophers.append(thread)

    # Бесконечный цикл (прерывается Ctrl+C)
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("\n\033[91mОбед философов завершен!\033[0m")
