package pl.crc.gateway.lib;

import java.util.Objects;
import java.util.function.Supplier;

import static org.apache.logging.log4j.util.Strings.isBlank;

public final class Assertion {
    private Assertion() {
    }

    public static void isBiggerEqualsThan(int requiredMaximumSize, String value, Supplier<RuntimeException> exception) {
        notEmpty(value, exception);
        isTrue(value.length() <= requiredMaximumSize, exception);
    }

    public static void isSmallerEqualsThan(int requiredMinimumSize, String value, Supplier<RuntimeException> exception) {
        notEmpty(value, exception);
        isTrue(value.length() >= requiredMinimumSize, exception);
    }

    public static void isTrue(boolean value, Supplier<RuntimeException> exception) {
        if (!value) {
            throw exception.get();
        }
    }

    public static void notEmpty(String value, Supplier<RuntimeException> exception) {
        notNull(value, exception);
        if (isBlank(value)) {
            throw exception.get();
        }
    }

    public static void notNull(Object object, Supplier<RuntimeException> exception) {
        if (Objects.isNull(object)) {
            throw exception.get();
        }
    }
}