import java.util.*;

public class WordFrequencyDemo {
    public static void main(String[] args) {
        String paragraph = "java collection framework java map list hashmap list map collection java array";
        String[] words = paragraph.toLowerCase().split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());

        entryList.sort((a, b) -> {
            int cmp = b.getValue().compareTo(a.getValue());
            return (cmp != 0) ? cmp : a.getKey().compareTo(b.getKey());
        });

        System.out.println("Word Frequencies (Sorted Highest to Lowest):");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.printf("%-12s : %d%n", entry.getKey(), entry.getValue());
        }
    }
}
