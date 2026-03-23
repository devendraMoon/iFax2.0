package testUtils;

import com.github.javafaker.Faker;

public class FakeDataUtils {
    // Thread-safe Faker instance
    private static final ThreadLocal<Faker> faker =
            ThreadLocal.withInitial(Faker::new);

    private static Faker getFaker() {
        return faker.get();
    }


    public static String email() {
        return getFaker().internet().emailAddress();
    }

    public static String firstName() {
        return getFaker().name().firstName();
    }


    public static String lastName() {
        return getFaker().name().lastName();
    }


    public static String Password() {
        return "Test@" + getFaker().number().digits(5);
    }

    public static String ConfirmPassword() {
        return getFaker().name().username();
    }


}
