package by.nyurush.music.util.language;

import by.nyurush.music.util.constant.ConstantAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public final class ResourceBundleUtil {
    private ResourceBundleUtil() {
    }

    public static ResourceBundle getResourceBundle(HttpServletRequest request) {
        Optional<String> language = Arrays.stream(request.getCookies())
                .filter(c -> ConstantAttributes.LANGUAGE.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
        if (language.isPresent()) {
            String[] langParameters = language.get().split(ConstantAttributes.REGEX);
            Locale currentLang = new Locale(langParameters[0], langParameters[1]);
            return ResourceBundle.getBundle(ConstantAttributes.RESOURCE, currentLang);
        } else {
            return ResourceBundle.getBundle(ConstantAttributes.RESOURCE, new Locale(ConstantAttributes.DEFAULT_LANG));
        }
    }

}
