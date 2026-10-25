import java.util.*;

public class UniqueVisitorsDemo {
    public static void main(String[] args) {
        List<String> logins = List.of(
            "alice", "bob", "alice", "charlie", "david", 
            "bob", "eve", "charlie", "frank"
        );

        Set<String> hashSet = new HashSet<>(logins);
        Set<String> linkedHashSet = new LinkedHashSet<>(logins);

        System.out.println("Total log events: " + logins.size());
        System.out.println("Unique visitor count: " + hashSet.size());

        System.out.print("First three unique visitors in encounter order: ");
        int count = 0;
        for (String user : linkedHashSet) {
            System.out.print(user + " ");
            count++;
            if (count == 3) break;
        }
        System.out.println();
    }
}
