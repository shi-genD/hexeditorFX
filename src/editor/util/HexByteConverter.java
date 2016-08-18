package editor.util;
/**
 * Created by shi on 09.08.16.
 */
public class HexByteConverter {
    public static String convertToHex(byte bValue) {
        return String.format("%02X", bValue);
    }

    public static byte convertFromHex(String hexByte) throws IncorrectInputCodeException {
        if (HexValidator.isValid(hexByte)) {
            return (byte) ((Character.digit(hexByte.charAt(0), 16) << 4)
                    + Character.digit(hexByte.charAt(1), 16));
        } else {
            throw new IncorrectInputCodeException("Not valid representation of byte");
        }
    }
}
