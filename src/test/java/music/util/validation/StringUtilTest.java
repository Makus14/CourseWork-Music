package music.util.validation;

import by.nyurush.music.util.validation.StringUtil;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class StringUtilTest {

    @DataProvider
    public static Object[] createPositiveStrings() {
        return new Object[]{
                "kokosik",
                "   kokosik   ",
                "."
        };
    }

    @DataProvider
    public static Object[] createNegativeStrings() {
        return new Object[]{
                " ",
                "         ",
                "",
                null
        };
    }

    @Test
    @UseDataProvider("createPositiveStrings")
    public void isNullOrEmptyPositiveTest(String str) {
        assertFalse(StringUtil.isNullOrEmpty(str));
    }

    @Test
    @UseDataProvider("createNegativeStrings")
    public void isNullOrEmptyNegativeTest(String str) {
        assertTrue(StringUtil.isNullOrEmpty(str));
    }

}
