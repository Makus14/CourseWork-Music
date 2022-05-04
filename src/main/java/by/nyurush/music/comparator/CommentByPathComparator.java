package by.nyurush.music.comparator;

import by.nyurush.music.entity.Comment;
import by.nyurush.music.util.constant.ConstantAttributes;

import java.util.Comparator;

public class CommentByPathComparator implements Comparator<Comment> {
    @Override
    public int compare(Comment o1, Comment o2) {
        String[] str1 = o1.getPath().split(ConstantAttributes.PATH_DELIMITER);
        String[] str2 = o2.getPath().split(ConstantAttributes.PATH_DELIMITER);
        for(int i = 0; i < Math.min(str1.length, str2.length); i++) {
            int id1 = Integer.parseInt(str1[i]);
            int id2 = Integer.parseInt(str2[i]);
            if(id1 != id2) {
                return Integer.compare(id1, id2);
            }
        }
        return o1.getPath().compareTo(o2.getPath());
    }
}
