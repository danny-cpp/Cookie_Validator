package cookie_validator;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * Reference to <i>cookie-octet</i>: Almost all ASCII characters with 2 sets of exception
 */
interface IllegalCharacters {
    String separators = "(,),<,>,@,\\,,;,:,\\\\,\",\\/,\\[,\\],\\{,\\},\\?,=,\\s,\\t";
    String cookie_illegal = "([^\\s,\\\\,\",;, \\,]*)";
}


/**
 * Date and time format checker
 */
interface DateAndTime {
    String month = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
    String wkday = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
    String time = "([0-9]{2}:[0-9]{2}:[0-9]{2})";
    String date1 = "[0-9]{2} " + month + " ([0-9]{4})";
    String rfc1123_date = wkday + ", " + date1 + " " + time + " " + "GMT";
}

/**
 * Domain regex checker
 */
interface Domains  {
    String letter = "[a-zA-Z]";
    String digits = "[0 - 9]";
    String let_dig = "(" + letter + "|" + digits + ")";
    String let_dig_hyp = "(" + let_dig + "|-" + ")";
    String ldh_str = "(" + let_dig_hyp + "+" + ")";
    String label = "(" + letter +   "("+ ldh_str + "?" + let_dig +")" + "?"   + ")";
    String subdomain = "(" + label + "|" + "(\\." +  label + ")+" + ")";;
    String domain_value = "(" + subdomain  + "|" + "(\\." + subdomain + ")"  + ")" + "?";
}

/**
 * Cookie accepted value. Comes in a list separated by ";".
 */
interface AcceptedValues extends DateAndTime, Domains {
    String expires_av = "Expires=" + rfc1123_date;
    String max_age_av = "(Max-Age=[1-9][0-9]*)";
    String domain_av = "Domain=" + domain_value;
    String path_value = "[^\\;, \n]";
    String path_av = "Path=" + path_value;
    String secure_av = "Secure";
    String httponly_av = "HttpOnly";
}

/**
 * Class contain function <i>cookieChecker</i> to validate a string to be a legitimate cookie.
 * @author Danh Nguyen
 * @version HTTP/1.x-checker-stable
 */
public class SyntaxCheckerSuite implements IllegalCharacters, AcceptedValues {
    // Create a dynamic array that store found string, then compare that to original string
    protected static ArrayList<String> internal_cache = new ArrayList<>();

    protected static String cookie_av = "(" + expires_av + "|" + max_age_av  + "|" + domain_av + "|" +
                                        path_av  + "|" + secure_av + "|" + httponly_av + ")";


    protected static String cookie_value = "((\"" + cookie_illegal + "\")|" + cookie_illegal +")"; // Split into 2 cases: covered with quotes or not
    protected static String token = "([^" + separators + "]+)"; // Everything except separators

    protected static String cookie_pair = token + "=" + cookie_value;

    protected static String set_cookie_string = cookie_pair + "(\\; "+ cookie_av +")*";
    protected static String main_pattern = "^(Set-Cookie: )" + set_cookie_string;

    /**
     * Validate cookie string
     * @param s a cookie string
     * @return  boolean value of legitimacy
     */
    public static boolean cookieChecker(String s) {
        internal_cache.clear();

        Pattern p = Pattern.compile(main_pattern);
        Matcher m = p.matcher(s);
        while (m.find()) {
            internal_cache.add(s.substring(m.start(), m.end()));
        }

        // If it does not store anything, return false
        if (internal_cache.size() == 0) {
            return false;
        }

        // Check if the found value same as original
        if (internal_cache.get(0).equals(s)) {
            return true;
        }
        else {
            return false;
        }
    }


    public static void main(String[] args) {
        // Use this function to produce complete regex pattern, for checking purpose.
        System.out.println(main_pattern);
    }
}
