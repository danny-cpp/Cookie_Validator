package cookie_validator;
import java.util.regex.*;
import java.util.ArrayList;

public class RecursiveRegex {
    public static String LDHstr(String ldh) {
        String result = ldh;
        for (int i = 0; i < 2; i++) {
            result =  ldh + "|" + "(" + ldh + result + ")";
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String letter = "[a-zA-Z]";
        String digits = "[0 - 9]";
        String let_dig = "(" + letter + "|" + digits + ")";
        String let_dig_hyp = "(" + let_dig + "|-" + ")";
        System.out.println(let_dig_hyp);

        ArrayList<String> internal_cache = new ArrayList<>();

        String pattern = LDHstr("\\.((([a-zA-Z]|[0 - 9])|-).)*");
        String text = ".com.com.com";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        while (m.find()) {
            internal_cache.add(text.substring(m.start(), m.end()));
        }

        // for (String x: internal_cache) {
        //     System.out.println(x);
        // }
        System.out.println(internal_cache.get(0));

        // If it does not start with "Set-Cookie", return false
        if (internal_cache.size() == 0) {
            System.out.println("w");
        }

        if (internal_cache.get(0).equals(text)) {
            System.out.println("r");
        }
        else {
            System.out.println("w");
        }
    }
}
