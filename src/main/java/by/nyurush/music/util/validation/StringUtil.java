package by.nyurush.music.util.validation;

public final class StringUtil {
    private StringUtil() {}

    public static boolean areNotNullAndNotEmpty(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (isNullOrEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNotNull(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (string == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}
