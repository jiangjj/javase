package guava.strings;

import com.google.common.base.*;

import java.util.Arrays;

/**
 * Created by jiangjiajie on 2017/2/14.
 */
public class StringsDemo {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on("; ").skipNulls();
        System.out.println(joiner.join("Harry", null,"Mary"));
        System.out.println(Joiner.on(",").join(Arrays.asList(1,5,7)));

        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split(",foo,bar,,  tur"));

        String string = "str    123";
        String noControl = CharMatcher.javaIsoControl().removeFrom(string); // remove control characters
        String theDigits = CharMatcher.digit().retainFrom(string); // only the digits
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom(string, ' ');
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        String noDigits = CharMatcher.javaDigit().replaceFrom(string, "*"); // star out all digits
        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(string);
        // eliminate all characters that aren't digits or lowercase
        System.out.println(theDigits);

        byte[] bytes = string.getBytes(Charsets.UTF_8);

        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"); // returns "constantName"
    }
}
