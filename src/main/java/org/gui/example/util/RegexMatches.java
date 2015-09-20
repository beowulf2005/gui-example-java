package org.gui.example.util;

import java.util.regex.Pattern;

public abstract class RegexMatches {

    public static final String REGEX_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    public static final String REGEX_ID = "^\\d{18}$";

    public static final String REGEX_NICKNAME = "^[^\\\"\\'<>#$%&\\\\\\p{Cntrl}]{2,10}$";

    public static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

    public static final String REGEX_PASSWORD = "^[\\p{Graph}&&[^\\\"\\'<>]]{6,32}$";

    public static final String REGEX_PHONE = "^\\d{3}-\\d{8}|\\d{4}-\\d{7}$";

    public static final String REGEX_QUESTION_ANSWER = "^[^\\\"\\'<>#$%&\\\\\\p{Cntrl}]{6,30}$";

    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{4,20}$";

    public static final String REGEX_SCRIPT = "^<script[^>]*?>[\\s\\S]*?<\\/script>|<style[^>]*?>[\\s\\S]*?<\\/style>$";

    public static final Pattern PATTERN_REGEX_EMAIL = Pattern.compile(REGEX_EMAIL);

    public static final Pattern PATTERN_REGEX_ID = Pattern.compile(REGEX_ID);

    public static final Pattern PATTERN_REGEX_NICKNAME = Pattern.compile(REGEX_NICKNAME);

    public static final Pattern PATTERN_REGEX_MOBILE = Pattern.compile(REGEX_MOBILE);

    public static final Pattern PATTERN_REGEX_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    public static final Pattern PATTERN_REGEX_PHONE = Pattern.compile(REGEX_PHONE);

    public static final Pattern PATTERN_REGEX_QUESTION_ANSWER = Pattern.compile(REGEX_QUESTION_ANSWER);

    public static final Pattern PATTERN_REGEX_USERNAME = Pattern.compile(REGEX_USERNAME);

    public static final Pattern PATTERN_REGEX_SCRIPT = Pattern.compile(REGEX_SCRIPT);

    /**
     * for test
     * 
     * @param strings
     */
    @Deprecated
    public static void main(String... strings) {
        // String p;
        //
        // p = "^[\\p{Graph}&&[^\\\"\\'<>]]{6,32}$";
        // System.out.println(Pattern.compile(p).matcher("inputz").matches());
        // System.out.println(Pattern.compile(p).matcher("inpu#$%&tz").matches());
        // System.out.println(Pattern.compile(p).matcher("#$%&()*+,-./:;=?@[\\]^_`{|}~").matches());
        // System.out.println(Pattern.compile(p).matcher("input,-./:\";=?@[z").matches());
        // System.out.println(Pattern.compile(p).matcher(",-./:;=?@[in>p11utz").matches());
        //
        // System.out.println("-----");
        // p = "^[^\\\"\\'<>#$%&\\\\\\p{Cntrl}]{6,30}$";
        // System.out.println(Pattern.compile(p).matcher("照前孙俪周五阵\u0000\u0001\u0013").matches());
        // System.out.println(Pattern.compile(p).matcher("照前孙俪周五阵tz").matches());
        // System.out.println(Pattern.compile(p).matcher("()*+,-./:;=?@^_`{|}~\u0000").matches());
        // System.out.println(Pattern.compile(p).matcher("input,-照前孙俪周五阵./:;=?@[z").matches());
        // System.out.println(Pattern.compile(p).matcher(",- 照前孙俪周五阵./:;=?@[inp11utz").matches());
        //
        // System.out.println("-----");
        // p = "^[^\\\"\\'<>#$%&\\\\\\p{Cntrl}]{6,30}$";
        // System.out.println(Pattern.compile(p).matcher("照前孙俪周五阵\u0000\u0001\u0013").matches());
        // System.out.println(Pattern.compile(p).matcher("照前孙俪周五阵tz").matches());
        // System.out.println(Pattern.compile(p).matcher("()*+,-./:;=?@^_`{|}~\u0000").matches());
        // System.out.println(Pattern.compile(p).matcher("input,-照前孙俪周五阵./:;=?@[z").matches());
        // System.out.println(Pattern.compile(p).matcher(",- 照前孙俪周五阵./:;=?@[inp11utz").matches());

        String s = "<style>ssss</style>";
        System.out.println(regexScript(s));

    }

    public static boolean regexCardId(String cardId) {
        return PATTERN_REGEX_ID.matcher(cardId).matches();
    }

    /**
     * 正则表达式
     */
    public static boolean regexMail(String mail) {
        return PATTERN_REGEX_EMAIL.matcher(mail).matches();
    }

    public static boolean regexMobile(String mobile) {
        return PATTERN_REGEX_MOBILE.matcher(mobile).matches();
    }

    public static boolean regexPhone(String phone) {
        return PATTERN_REGEX_PHONE.matcher(phone).matches();
    }

    public static boolean regexUsername(String username) {
        return PATTERN_REGEX_USERNAME.matcher(username).matches();
    }

    public static boolean regexQA(String str) {
        return PATTERN_REGEX_QUESTION_ANSWER.matcher(str).matches();
    }

    public static boolean regexNickname(String str) {
        return PATTERN_REGEX_NICKNAME.matcher(str).matches();
    }

    public static boolean regexScript(String str) {
        return PATTERN_REGEX_SCRIPT.matcher(str).matches();
    }
}
