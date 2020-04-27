package Auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static boolean regexChecker(String regex, String test) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        return regexChecker("[\\w\\d\\S]+[@][\\w\\d\\S]+[.][\\w\\d\\s]+", email);
    }

    public static boolean checkPassword(String password) {
        return regexChecker("[\\w\\d\\S!@#$%^&*()?]{8,}", password);
    }

    public static boolean checkName(String name) {
        return regexChecker("[\\w][\\w\\s]+", name);
    }

    public static boolean checkAddress(String address) {
        return regexChecker("[\\d]+[\\w\\d\\s#]+[,][\\w\\d\\s]+[,][\\s]?[A-Z]{2}[,][\\s]?[0-9]{5}", address);
    }

    public static boolean checkCard(String card) {
        return regexChecker("[\\d]{16}", card);
    }

    public static boolean checkPhone(String phone) {
        return regexChecker("[\\d]{10}", phone);
    }

}