package com.example.kaios.foody.Fragment_User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaios on 5/17/2017.
 */

public class Utility {
    private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //private static final String PASS_PATTERN = "^[_A-Za-z0-9-\\+]";

    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    public static boolean validatePasslenght(String Pass){
        return  Pass.length()>=4;
    }

     public static boolean validatePass(String Pass, String Confirm){
         return Pass.toString().equals(Confirm.toString());
     }
    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
}