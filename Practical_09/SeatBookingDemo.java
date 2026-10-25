class CinemaReservation {
    private int seatsLeft = 5;

    public synchronized boolean bookSeatSync(String threadName) {
        if (seatsLeft > 0) {
            try { Thread.sleep(20); } catch (InterruptedException ignored) {}
            seatsLeft--;
            System.out.println(threadName + " booked 1 seat. Seats remaining: " + seatsLeft);
            return true;
        }
        System.out.println(threadName + " failed: sold out.");
        return false;
    }
}

public class SeatBookingDemo {
    public static void main(String[] args) throws InterruptedException {
        CinemaReservation res = new CinemaReservation();
        Thread[] clients = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final int id = i + 1;
            clients[i] = new Thread(() -> res.bookSeatSync("Thread-" + id));
        }

        for (Thread c : clients) c.start();
        for (Thread c : clients) c.join();
    }
}
