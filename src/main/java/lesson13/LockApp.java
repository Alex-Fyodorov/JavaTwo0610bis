package lesson13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockApp {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                //критическая секция

                if (!lock.tryLock(10, TimeUnit.SECONDS)) {
                    System.out.println("Не удалось захватить блокировку");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock(); //блокировка на запись
        readWriteLock.readLock().lock(); //блокировка на чтение
        //В остальном принцип такой же, как у lock выше.
        //Блокировку на чтение можно захватить тоько в том случае,
        //если ни один поток не читает. С записью то же самое.
    }
}
