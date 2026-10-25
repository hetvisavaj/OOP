import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class FeatureSuite {
    @Run
    public void testA() {
        System.out.println("Executing Test A: Passed");
    }

    public void helperMethod() {
        System.out.println("Helper method - should not execute");
    }

    @Run
    public void testB() {
        System.out.println("Executing Test B: Passed");
    }

    @Run
    public void testC() {
        System.out.println("Executing Test C: Passed");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) {
        FeatureSuite suite = new FeatureSuite();
        Class<?> clazz = suite.getClass();
        int testsRun = 0;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(suite);
                    testsRun++;
                } catch (Exception e) {
                    System.err.println("Failed invoking " + method.getName() + ": " + e.getMessage());
                }
            }
        }
        System.out.println("Total @Run methods executed: " + testsRun);
    }
}
