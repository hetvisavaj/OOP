public class CinemaShow {
  private String title;
  private int seatsAvailable;
  private final int capacity;
  private static int totalBooked = 0;

  CinemaShow(String title, int capacity) {
    this.title = title;
    this.capacity = capacity;
    seatsAvailable = capacity;
  }

  CinemaShow(String title) {
    this(title, 100);
  }

  boolean book(int n) {
    if (n > 0 && n <= seatsAvailable) {
       seatsAvailable -= n;
       totalBooked += n;
       return true;
    }
    return false;
  }

  void cancel(int n) {
    if (n > 0) {
       seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }
  }

  int getSeatsAvailable() {
     return seatsAvailable;
  }

  static int getTotalBooked() {
     return totalBooked;
  }

  public static void main(String[] args) {
    CinemaShow show = new CinemaShow("Avengers", 50);

     System.out.println("Booking 20: " + show.book(20));
     System.out.println("Seats available: " + show.getSeatsAvailable());

     System.out.println("Booking 40: " + show.book(40));
     System.out.println("Seats available: " + show.getSeatsAvailable());

     System.out.println("Booking 10: " + show.book(10));
     System.out.println("Seats available: " + show.getSeatsAvailable());
        show.cancel(5);
        System.out.println("After cancellation: " + show.getSeatsAvailable());

        System.out.println("Total booked: " + CinemaShow.getTotalBooked());
    }
}
