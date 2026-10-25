import java.util.HashMap;
import java.util.Map;

class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String item, int shortfall) {
        super("Insufficient stock for '" + item + "'. Short by: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private final Map<String, Integer> inventory = new HashMap<>();

    public void addStock(String item, int qty) {
        inventory.put(item, inventory.getOrDefault(item, 0) + qty);
    }

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Requested quantity must be positive: " + qty);
        }
        int available = inventory.getOrDefault(item, 0);
        if (qty > available) {
            throw new OutOfStockException(item, qty - available);
        }
        inventory.put(item, available - qty);
        System.out.println("Successfully issued " + qty + " units of " + item + ". Remaining: " + (available - qty));
    }
}

public class WarehouseDemo {
    public static void main(String[] args) {
        Warehouse wh = new Warehouse();
        wh.addStock("Keyboard", 10);
        wh.addStock("Mouse", 5);

        String[][] requests = {
            {"Keyboard", "4"},
            {"Mouse", "-2"},
            {"Mouse", "10"},
            {"Monitor", "1"},
            {"Keyboard", "6"}
        };

        for (String[] req : requests) {
            String item = req[0];
            int qty = Integer.parseInt(req[1]);
            try {
                wh.issue(item, qty);
            } catch (InvalidQuantityException | OutOfStockException e) {
                System.out.println("Failed request (" + item + ", " + qty + "): " + e.getMessage());
            }
        }
    }
}
