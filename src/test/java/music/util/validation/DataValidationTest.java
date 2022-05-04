package music.util.validation;

import by.nyurush.music.util.validation.DataValidator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class DataValidationTest {

    @DataProvider
    public static Object[] createPositiveEmails() {
        return new Object[]{
                "natallia.yurush@gmail.com",
                "nika.koko@yandex.ru",
                "kokosik@mail.ru"
        };
    }

    @DataProvider
    public static Object[] createNegativeEmails() {
        return new Object[]{
                "",
                "olga.com",
                "kokos@gmail",
                ".@gmail.com",
                "sad@mail."
        };
    }

    @DataProvider
    public static Object[] createPositivePasswords() {
        return new Object[]{
                "QQww1122",
                "FFhowh67hjF",
                "Foiej34jjF",
                "ASDFs1234"
        };
    }

    @DataProvider
    public static Object[] createNegativePasswords() {
        return new Object[]{
                "",
                "QQww112",
                "kjhgfd123456",
                "!!!qwerR",
                "12345678",
                "qwertyui",
                "QWERtyui"
        };
    }

    @DataProvider
    public static Object[] createPositiveLogins() {
        return new Object[]{
                "natasha",
                "natallia-yurush",
                "natallia.yurush",
                "natallia_yurush"
        };
    }

    @DataProvider
    public static Object[] createNegativeLogins() {
        return new Object[]{
                "",
                "a",
                "sdkfjdsflkfj dslfjslskfhlkshflaskjfhalkfh",
                "sf df",
                "natasha!!!"
        };
    }

    @DataProvider
    public static Object[] createPositiveNames() {
        return new Object[]{
                "natasha",
                "Natallia",
                "Yurush"
        };
    }

    @DataProvider
    public static Object[] createNegativeNames() {
        return new Object[]{
                "",
                "A",
                "sdkfjdsflkfj dslfjslskfhlkshflaskjfhalkfh",
                "1234",
                "natasha!!!",
                "asdf123"
        };
    }

    @Test
    @UseDataProvider("createPositiveEmails")
    public void isCorrectEmailTest(String email) {
        assertTrue(DataValidator.isCorrectEmail(email));
    }

    @Test
    @UseDataProvider("createNegativeEmails")
    public void isNotCorrectEmailTest(String email) {
        assertFalse(DataValidator.isCorrectEmail(email));
    }

    @Test
    @UseDataProvider("createPositivePasswords")
    public void isCorrectPasswordTest(String password) {
        assertTrue(DataValidator.isCorrectPassword(password));
    }

    @Test
    @UseDataProvider("createNegativePasswords")
    public void isNotCorrectPasswordTest(String password) {
        assertFalse(DataValidator.isCorrectPassword(password));
    }

    @Test
    @UseDataProvider("createPositiveLogins")
    public void isCorrectLoginTest(String login) {
        assertTrue(DataValidator.isCorrectLogin(login));
    }

    @Test
    @UseDataProvider("createNegativeLogins")
    public void isNotCorrectLoginTest(String login) {
        assertFalse(DataValidator.isCorrectLogin(login));
    }

    @Test
    @UseDataProvider("createPositiveNames")
    public void isCorrectNameTest(String name) {
        assertFalse(DataValidator.isIncorrectNameSurname(name));
    }

    @Test
    @UseDataProvider("createNegativeNames")
    public void isNotCorrectNameTest(String name) {
        assertTrue(DataValidator.isIncorrectNameSurname(name));
    }

}
