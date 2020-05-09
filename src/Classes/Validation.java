package Classes;

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

    public static boolean checkPlatformName(String name) {
        return regexChecker("[\\w]{3,50}", name);
    }

    public static boolean checkGameTitle(String name) {
        return regexChecker("[\\w]{1,100}", name);
    }

    public static boolean checkPublisherName(String name) {
        return regexChecker("[\\w]{1,50}", name);
    }

    public static boolean checkRatingName(String name) {
        return regexChecker("[\\w]{1,6}", name);
    }

    public static boolean checkGenreName(String name) {
        return regexChecker("[\\w]{1,50}", name);
    }

    public static boolean checkID(String id){
        return regexChecker("[\\d]+", id);
    }

    public static boolean checkBoolean(String status){
        return status.equals("1") || status.equals("0");
    }

    public static boolean checkRoleCode(String rolecode){ return rolecode.equals("1") || rolecode.equals("2") || rolecode.equals("3");}

    public static boolean checkAddTimesRented(String num){
        return regexChecker("[\\d]{1}", num);
    }

    public static boolean checkGameDescription(String description) { return regexChecker("[\\d\\s]{1,700}", description);}

    public static boolean checkGameImagePath(String image) { return regexChecker("[\\d]{1,200}", image);}

    public static boolean checkGamePrice(String price){ return regexChecker("[\\d]{1,}[.][\\d]{2}", price);}

    public static boolean convertToBoolean(String val){
        return val.equals("1");
    }

}