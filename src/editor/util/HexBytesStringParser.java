package editor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shi on 10.08.16.
 */
public class HexBytesStringParser {

    private static Pattern patS = Pattern.compile("([ ])+");

    public static List<String> parseHexBytes(String hexBytesString) throws IncorrectInputCodeException {

        List<String> list = new ArrayList<>();
        String arr[] = patS.split(hexBytesString);
        for (int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
        for (String s : arr) {
            if (s.length() % 2 != 0) {
                throw new IncorrectInputCodeException("Invalid data format");
            }
            for (int i = 0; i < s.length(); i += 2) {
                char buf[] = new char[2];
                s.toUpperCase().getChars(i, i + 2, buf, 0);
                String temp = new String(buf);
                if (HexValidator.isValid(temp)) {
                    list.add(temp);
                } else {
                    throw new IncorrectInputCodeException("Not valid representation of byte");
                }
            }

        }
        return list;
    }
}