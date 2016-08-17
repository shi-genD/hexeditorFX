package editor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shi on 09.08.16.
 */

public class HexValidator {
    private static Pattern pat = Pattern.compile("[A-Fa-f0-9][A-Fa-f0-9]");

    public static boolean isValid(String hexByte) {
        Matcher mat;
        mat = pat.matcher(hexByte);
        return mat.matches();
    }
}