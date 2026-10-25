import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient String sessionToken; // Will not be persisted

    public Student(String name, int age, String sessionToken) {
        this.name = name;
        this.age = age;
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", sessionToken='" + sessionToken + "'}";
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        File file = new File("students.dat");
        Student[] list = {
            new Student("Aarav", 20, "AUTH-9912"),
            new Student("Diya", 21, "AUTH-4811")
        };

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
            System.out.println("Serialized objects to " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Student[] loaded = (Student[]) ois.readObject();
            System.out.println("Deserialized objects:");
            for (Student s : loaded) {
                System.out.println(" - " + s);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) file.delete(); // Cleanup test artifact
        }
    }
}
