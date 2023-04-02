import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;

public class StrUtils {
    public static void main(String[] args) {
        compareStrings("", "");
    }

    public static void compareStrings(String firstString, String secondString) {
        System.out.println("The first string is  :" + firstString);
        System.out.println("The second string is :" + secondString);
        String difference = StringUtils.difference(firstString, secondString);
        if (difference.isEmpty()) {
            System.out.println("The two strings are identical.");
        } else {
            System.out.println("difference is        :" + difference);
            System.out.println("The first different character is at index " + difference.length());
        }
    }

    public static String convertEBCDICToUTF8(String ebcdicString) {
        Charset ebcdicCharset = Charset.forName("IBM1047"); // EBCDIC charset
        Charset utf8Charset = Charset.forName("UTF-8"); // UTF-8 charset
        byte[] ebcdicBytes = ebcdicString.getBytes(ebcdicCharset); // convert to EBCDIC bytes
        String utf8String = new String(ebcdicBytes, utf8Charset); // convert to UTF-8 string
        return utf8String;
    }
}
