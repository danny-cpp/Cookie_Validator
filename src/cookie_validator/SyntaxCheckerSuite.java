package cookie_validator;
import java.util.ArrayList;
import java.util.regex.*;
import static cookie_validator.RecursiveRegex.*;

interface IllegalCharacters {
    String separators = "(,),<,>,@,\\,,;,:,\\\\,\",\\/,\\[,\\],\\{,\\},\\?,=,\\s,\\t";
    String cookie_illegal = "([^\\s,\\\\,\",;, \\,]*)";
}

interface DateAndTime {
    String month = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
    String wkday = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
    String time = "([0-9]{2}:[0-9]{2}:[0-9]{2})";
    String date1 = "[0-9]{2} " + month + " ([0-9]{4})";
    String rfc1123_date = wkday + ", " + date1 + " " + time + " " + "GMT";
}

interface Domains  {
    String letter = "[a-zA-Z]";
    String digits = "[0 - 9]";
    String let_dig = "(" + letter + "|" + digits + ")";
    String let_dig_hyp = "(" + let_dig + "|-" + ")";
    String ldh_str = "(" + let_dig_hyp + "+" + ")";
    String label = "(" + letter +   "("+ ldh_str + "?" + let_dig +")" + "?"   + ")";
    String subdomain = "(" +  "(" + label + ")"   + "|" +
                              "("  +  "(" + label + ")"   + "(\\." + label + ")+"    +
                              ")" +
                        ")";
    String domain_value = "((" + subdomain + ")" + "|" + "(\\." + subdomain + ")" + "|" + "=$" + ")";
}

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
 * Internal cookie checker, not meant to be used outside the package.
 */
public class SyntaxCheckerSuite implements IllegalCharacters, AcceptedValues {
    protected static ArrayList<String> internal_cache = new ArrayList<>();

    protected static String cookie_av = "(" + expires_av + "|" + max_age_av  + "|" + domain_av + "|" +
                                        path_av  + "|" + secure_av + "|" + httponly_av + ")";


    protected static String cookie_value = "((\"" + cookie_illegal + "\")|" + cookie_illegal +")"; // Split into 2 cases: covered with quotes or not
    protected static String token = "([^" + separators + "]*)"; // Everything except separators

    protected static String cookie_pair = token + "=" + cookie_value;

    protected static String set_cookie_string = cookie_pair + "(\\; "+ cookie_av +")*";
    protected static String main_pattern = "^(Set-Cookie: )" + set_cookie_string;

    protected static boolean beginAndEnd(String s) {
        internal_cache.clear();

        String pattern = "^(Set-Cookie: )([^(,),<,>,@,\\,,;,:,\\\\,\",\\/,\\[,\\],\\{,\\},\\?,=,\\s,\\t]*)";

        Pattern p = Pattern.compile(main_pattern);
        Matcher m = p.matcher(s);
        while (m.find()) {
            internal_cache.add(s.substring(m.start(), m.end()));
        }

        for (String x: internal_cache) {
            System.out.println(x);
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
        System.out.println(beginAndEnd("Set-Cookie: lu=Rg3v; Expires=Wed, 19 Nov 2008 16:35:39 GMT"));
        System.out.println(beginAndEnd("Set-Cookie: lu=Rg3v; Expires=Wed, 19 Nov 2008 16:35:39 GMT; Path=/"));
        System.out.println(beginAndEnd("Set-Cookie: lu=Rg3v; Expires=Wed, 19 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly"));
        System.out.println(subdomain);
    }
}
