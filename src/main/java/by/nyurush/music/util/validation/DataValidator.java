package by.nyurush.music.util.validation;

public final class DataValidator {
    private DataValidator() {
    }

    private static final String CHECK_LOGIN = "^[a-zA-Z][\\w-_.]{2,20}$";
    private static final String CHECK_EMAIL = "[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@" +
            "([a-z0-9]([-a-z0-9]{0,40}[a-z0-9])?\\.)*([a-z]{2,4})";
    private static final String CHECK_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}";
    private static final String CHECK_NAME_SURNAME = "[а-яА-ЯёЁa-zA-Z]{2,20}";
    private static final int MAX_INPUT_LENGTH = 40;

    public static boolean isCorrectEmail(String email) {
        return email.matches(CHECK_EMAIL);
    }

    public static boolean isCorrectPassword(String password) {
        return password.matches(CHECK_PASSWORD);
    }

    public static boolean isCorrectLogin(String login) {
        return login.matches(CHECK_LOGIN);
    }

    public static boolean isIncorrectNameSurname(String name) {
        return !name.matches(CHECK_NAME_SURNAME);
    }

    public static boolean isIncorrectInput(String input) {
        return input.length() > MAX_INPUT_LENGTH;
    }

    public static boolean areCorrectInputs(String... inputs) {
        for (String string : inputs) {
            if (string.length() > MAX_INPUT_LENGTH) {
                return false;
            }
        }
        return true;
    }

}
