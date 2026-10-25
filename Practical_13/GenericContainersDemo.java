class Box<T> {
    private T item;
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() { return first; }
    public B getSecond() { return second; }
}

public class GenericContainersDemo {
    public static <E> E getFirstElement(E[] array) {
        if (array == null || array.length == 0) return null;
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> strBox = new Box<>();
        strBox.set("Type safe generic box");
        String message = strBox.get();
        System.out.println("Box contents: " + message);

        Pair<Integer, String> student = new Pair<>(101, "Kavya");
        System.out.println("Pair values: Roll=" + student.getFirst() + ", Name=" + student.getSecond());

        Double[] values = {3.14, 2.71, 1.41};
        Double firstVal = getFirstElement(values);
        System.out.println("First element of array: " + firstVal);
    }
}
