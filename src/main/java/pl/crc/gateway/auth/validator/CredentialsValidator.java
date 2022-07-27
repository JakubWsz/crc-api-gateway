package pl.crc.gateway.auth.validator;

import pl.crc.gateway.lib.Assertion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsValidator {
    private static final Pattern USERNAME_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
    private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    //    At least 8 chars
//    Contains at least one digit
//    Contains at least one lower alpha char and one upper alpha char
//    Contains at least one char within a set of special chars (@#%$^ etc.)
//    Does not contain space, tab, etc.
    private static final int MAX_SIZE = 30;
    private static final int MIN_SIZE = 8;

    public static void validateCredentials(String username, String password) {
        validateUsername(username);
        validatePassword(password);
    }

    private static void validateUsername(String username) {
        Assertion.isTrue(matchUsernameRegex(username),
                () -> new RuntimeException("nazwa użytkonika jest nieprawidłowa"));
    }

    private static void validatePassword(String password) {
        validateIsPasswordNotNull(password);
        validatePasswordSize(password);
        Assertion.isTrue(CredentialsValidatorAlgorithms.isAtLeastOneCapitalCharacter(password),
                () -> new RuntimeException("without capital letter"));
        Assertion.isTrue(CredentialsValidatorAlgorithms.isAtLeastOneSmallCharacterCharacter(password),
                () -> new RuntimeException("without small letter"));
        Assertion.isTrue(CredentialsValidatorAlgorithms.isAtLeastOneNumberSign(password),
                () -> new RuntimeException("without number"));
        Assertion.isTrue(CredentialsValidatorAlgorithms.isAtLeastOneSpecialSign(password),
                () -> new RuntimeException("without special sign"));
    }

    private static boolean matchUsernameRegex(String username) {
        Matcher matcher = USERNAME_REGEX.matcher(username);
        return matcher.find();
    }

    private static void validateIsPasswordNotNull(String password) {
        Assertion.notNull(password, () -> new RuntimeException("null password"));
    }

    private static void validatePasswordSize(String password) {
        Assertion.isBiggerEqualsThan(MAX_SIZE, password,
                () -> new RuntimeException("to long"));
        Assertion.isSmallerEqualsThan(MIN_SIZE, password,
                () -> new RuntimeException("to short"));
    }
}
