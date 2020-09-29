package cookie_validator;
import java.util.ArrayList;
import java.util.regex.*;

interface IllegalCharacters {
    String separators = "(,),<,>,@,\\,,;,:,\\\\,\",\\/,\\[,\\],\\{,\\},\\?,=,\\s,\\t";
    String cookie_illegal = "([^\\s,\\\\,\",;, \\,]*)";
}

/**
 * Internal cookie checker, not meant to be used outside the package.
 */
public class SyntaxCheckerSuite implements IllegalCharacters {
    protected static ArrayList<String> internal_cache = new ArrayList<>();

    // protected static String cookie_av = expires_av + "|" + max_age_av  + "|" + domain_av+ "|" + path_av  + "|" + secure_av  + "|" + httponly_av;
    protected static String cookie_value = "((\"" + cookie_illegal + "\")|" + cookie_illegal +")"; // Split into 2 cases: covered with quotes or not
    protected static String token = "([^" + separators + "]*)"; // Everything except separators

    protected static String cookie_pair = token + "=" + cookie_value;

    protected static String set_cookie_string = cookie_pair; // + "*(\\; "+ cookie_av +")";
    protected static String main_pattern = "^(Set-Cookie: )" + set_cookie_string;

    protected static boolean beginAndEnd(String s) {

        String pattern = "^(Set-Cookie: )([^(,),<,>,@,\\,,;,:,\\\\,\",\\/,\\[,\\],\\{,\\},\\?,=,\\s,\\t]*)";

        Pattern p = Pattern.compile(main_pattern);
        Matcher m = p.matcher(s);
        while (m.find()) {
            internal_cache.add(s.substring(m.start(), m.end()));
        }

        // If it does not start with "Set-Cookie", return false
        if (internal_cache.size() == 0) {
            return false;
        }

        if (internal_cache.get(0).equals(s)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(beginAndEnd("Set-Cookie: ns1=\"alss/0.foobar^\""));
    }
}
