package TestThreads;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by lyoumi on 29.10.2016.
 */
public class ReadWriteLocks {

    public static final int ARRAY_LENGTH = 10;

    public static void main(String[] args) {
        ConcurrentArray<Integer> array = new ConcurrentArray<>(ARRAY_LENGTH);
        IntStream.range(0,3).forEach((i) -> new Thread(() -> {
            while (true){
                System.out.println("R: " + Arrays.toString(array.read()));
            }
        }).start());
        new Thread(() -> {
            Random random = new Random();
            while (true){
                Integer[] ints = Stream.generate(random::nextInt).limit(random.nextInt(ARRAY_LENGTH + 1)).toArray(Integer[]::new);
                array.write(ints, ARRAY_LENGTH - ints.length);
            }
        }).start();
    }


    public static class ConcurrentArray<T> {

        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Object[] items;
        private Random random = new Random();

        ConcurrentArray(int capacity) {
            items = new Object[capacity];
        }

        void write(T[] value, int index){
            readWriteLock.writeLock().lock();
            try {
                if (items.length < value.length + index) throw new RuntimeException("Not today");
                System.arraycopy(value, 0, items, index, value.length);
                Thread.sleep(random.nextInt(1000));
                System.out.println("W: " + Arrays.toString(items));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }

        public T[] read() {
            readWriteLock.readLock().lock();
            try {
                Object[] result = Arrays.copyOf(items, items.length);
                Thread.sleep(random.nextInt(1000));
                return (T[]) result;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

    }
}
