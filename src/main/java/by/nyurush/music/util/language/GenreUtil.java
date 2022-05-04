package by.nyurush.music.util.language;

import by.nyurush.music.util.constant.ConstantAttributes;

import java.util.*;

public final class GenreUtil {
    private GenreUtil() {}

    public static Map<String, String> getGenres(List<String> genres, ResourceBundle rb) {
        Map<String, String> result = new LinkedHashMap<>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantAttributes.RES_GENRE, rb.getLocale());
        for(String genre : genres) {
            result.put(genre, resourceBundle.getString(genre.toLowerCase()));
        }
        return result;
    }
}
