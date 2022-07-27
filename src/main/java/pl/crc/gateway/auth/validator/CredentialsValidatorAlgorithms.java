package pl.crc.gateway.auth.validator;

import java.time.LocalDate;
import java.time.Period;

public class CredentialsValidatorAlgorithms {

    public static boolean isAtLeastOneCapitalCharacter(String passwordStr) {
        char[] passwordCharArray = passwordStr.toCharArray();
        boolean include = false;
        for (int i = 0; i <= passwordStr.length() - 1; i++) {
            if (passwordCharArray[i] >= 65 && passwordCharArray[i] <= 90) {
                include = true;
                break;
            }
        }
        return include;
    }

    public static boolean isAtLeastOneSmallCharacterCharacter(String passwordStr) {
        char[] passwordCharArray = passwordStr.toCharArray();
        boolean include = false;
        for (int i = 0; i <= passwordStr.length() - 1; i++) {
            if (passwordCharArray[i] >= 97 && passwordCharArray[i] <= 122) {
                include = true;
                break;
            }
        }
        return include;
    }

    public static boolean isAtLeastOneNumberSign(String passwordStr) {
        char[] passwordCharArray = passwordStr.toCharArray();
        boolean include = false;
        for (int i = 0; i <= passwordStr.length() - 1; i++) {
            if (passwordCharArray[i] >= 48 && passwordCharArray[i] <= 57) {
                include = true;
                break;
            }
        }
        return include;
    }

    public static boolean isAtLeastOneSpecialSign(String passwordStr) {
        char[] passwordCharArray = passwordStr.toCharArray();
        boolean include = false;
        for (int i = 0; i <= passwordStr.length() - 1; i++) {
            if ((passwordCharArray[i] >= 33 && passwordCharArray[i] <= 47)
                    || (passwordCharArray[i] >= 58 && passwordCharArray[i] <= 64)
                    || (passwordCharArray[i] >= 91 && passwordCharArray[i] <= 96)
                    || (passwordCharArray[i] >= 123 && passwordCharArray[i] <= 126)) {
                include = true;
                break;
            }
        }
        return include;
    }

    public static boolean isAdult(LocalDate birthdate) {
        Period period;
        period = Period.between(birthdate, LocalDate.now());
        return period.getYears() > Period.of(18, 0, 0).getYears();
    }
}
