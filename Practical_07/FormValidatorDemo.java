import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
    String message() default "must not be blank";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
    String message() default "exceeds maximum allowed length";
}

class SignupForm {
    @NotBlank
    @MaxLength(10)
    private String username;

    @NotBlank
    private String password;

    public SignupForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class FormValidatorDemo {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object val = field.get(obj);
                String strVal = (val == null) ? null : val.toString();

                if (field.isAnnotationPresent(NotBlank.class)) {
                    NotBlank nb = field.getAnnotation(NotBlank.class);
                    if (strVal == null || strVal.trim().isEmpty()) {
                        errors.add(field.getName() + " " + nb.message());
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ml = field.getAnnotation(MaxLength.class);
                    if (strVal != null && strVal.length() > ml.value()) {
                        errors.add(field.getName() + " " + ml.message() + " (" + ml.value() + ")");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Could not inspect field: " + field.getName());
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        SignupForm form1 = new SignupForm("john_doe_very_long_username", "");
        SignupForm form2 = new SignupForm("alice", "secret123");

        System.out.println("Form 1 Validation:");
        List<String> errs1 = validate(form1);
        if (errs1.isEmpty()) System.out.println("No errors.");
        else errs1.forEach(e -> System.out.println(" - " + e));

        System.out.println("\nForm 2 Validation:");
        List<String> errs2 = validate(form2);
        if (errs2.isEmpty()) System.out.println("All fields valid.");
        else errs2.forEach(e -> System.out.println(" - " + e));
    }
}
