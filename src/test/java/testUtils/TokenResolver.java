package testUtils;

import java.util.HashMap;
import java.util.Map;

public class TokenResolver {

    // cache per row
    private static final ThreadLocal<Map<String, String>> rowCache =
            ThreadLocal.withInitial(HashMap::new);

    public static void clearRowCache() {
        rowCache.get().clear();
        rowCache.remove();
    }

    public static String resolve(String value, String columnKey) {

        if (value == null) return null;

        Map<String, String> cache = rowCache.get();

        if (value.contains("{faker.firstName}"))
            return cache.computeIfAbsent(columnKey,
                    k -> FakeDataUtils.firstName());


        if (value.contains("{faker.lastName}"))
            return cache.computeIfAbsent(columnKey,
                    k -> FakeDataUtils.lastName());


        if (value.contains("{faker.email}"))
            return cache.computeIfAbsent(columnKey,
                    k -> FakeDataUtils.email());

        if (value.contains("{faker.Password}"))
            return cache.computeIfAbsent(columnKey,
                    k -> FakeDataUtils.Password());

        if (value.contains("{faker.dummyMessage}"))
            return cache.computeIfAbsent(columnKey,
                    k -> FakeDataUtils.dummyMessage());

        return value;
    }
}
