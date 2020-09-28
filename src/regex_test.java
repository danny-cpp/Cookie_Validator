import java.util.ArrayList;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex_test {
    public static void main(String[] args) {
        ArrayList<String> list_of_word = new ArrayList<String>();


        String pattern = "\\w+";
        String text = "Now is the time";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.print(text.substring(m.start(), m.end()) + " - ");
            list_of_word.add(text.substring(m.start(), m.end()));

        }

        for (String x: list_of_word) {
            System.out.println(x);
        }
    }
}
