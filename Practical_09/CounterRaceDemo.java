class UnsafeCounter {
    private int count = 0;
    public void increment() { count++; }
    public int getCount() { return count; }
}

class SafeCounter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public int getCount() { return count; }
}

public class CounterRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 10;
        final int INCREMENTS = 1000;

        UnsafeCounter unsafe = new UnsafeCounter();
        Thread[] t1 = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            t1[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) unsafe.increment();
            });
            t1[i].start();
        }
        for (Thread t : t1) t.join();
        System.out.println("Expected count: " + (THREADS * INCREMENTS));
        System.out.println("Unsynchronized total: " + unsafe.getCount() + " (Inconsistent/Lost updates)");

        SafeCounter safe = new SafeCounter();
        Thread[] t2 = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            t2[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) safe.increment();
            });
            t2[i].start();
        }
        for (Thread t : t2) t.join();
        System.out.println("Synchronized total:   " + safe.getCount() + " (Consistent)");
    }
}
