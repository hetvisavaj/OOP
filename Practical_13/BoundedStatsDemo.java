public class BoundedStatsDemo {
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) return 0.0;
        double sum = 0.0;
        for (N val : values) {
            sum += val.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] ints = {10, 20, 30, 40};
        Double[] doubles = {1.5, 2.5, 3.5};
        Long[] longs = {100L, 200L, 300L, 400L};

        System.out.println("Integer average: " + average(ints));
        System.out.println("Double average:  " + average(doubles));
        System.out.println("Long average:    " + average(longs));
    }
}
