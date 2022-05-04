package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Comment;
import by.nyurush.music.entity.Track;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommentMapper implements Mapper<Comment> {
    @Override
    public Comment map(ResultSet resultSet) throws ServiceException {
        try {
            User user = new UserMapper().map(resultSet);
            Track track = new Track(resultSet.getInt("comment.track_id"));
            Integer id = resultSet.getInt("comment.id");
            String text = resultSet.getString("comment.text");
            Date date = resultSet.getTimestamp("comment.date");
            String path = resultSet.getString("comment.path");
            return new Comment(id, text, date, path, track, user);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
