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

    public static String dummyMessage(){

        String[] messages = {
                "Hello, this is a test message.",
                "This is a sample message for testing.",
                "Hi, I am testing the messaging functionality.",
                "This message is generated for automation testing.",
                "Hello, how are you doing today?",
                "This is a test message sent from the automation script.",
                "Testing message delivery successfully.",
                "Please consider this as a test message.",
                "The messaging feature is working as expected.",
                "This is an automated test message."
        };
        return messages[getFaker().random().nextInt(messages.length)];
    }
}