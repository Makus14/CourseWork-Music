package by.nyurush.music.dao.impl;

import by.nyurush.music.comparator.CommentByPathComparator;
import by.nyurush.music.dao.AbstractDao;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.entity.Comment;
import by.nyurush.music.service.mapper.CommentMapper;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.util.constant.ConstantAttributes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl extends AbstractDao<Comment> {
    private static final String FIND_ALL_BY_TRACK = "SELECT comment.id, comment.text, comment.date, comment.path, comment.track_id, comment.account_id, " +
            "user.account_id, first_name, last_name, email, subscription, account.id, login, password, role " +
            "FROM comment " +
            "JOIN account ON comment.account_id = account.id " +
            "JOIN user ON user.account_id = account.id " +
            "WHERE comment.track_id = ?";
    private static final String FIND_BY_ID = "SELECT comment.id, comment.text, comment.date, comment.path, comment.track_id, comment.account_id, " +
            "user.account_id, first_name, last_name, email, subscription, account.id, login, password, role " +
            "FROM comment " +
            "JOIN account ON comment.account_id = account.id " +
            "JOIN user ON user.account_id = account.id " +
            "WHERE comment.id = ?";
    private static final String CREATE = "INSERT INTO comment (text, date, path, track_id, account_id) VALUE (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE comment SET text = ?, date = ? WHERE id = ?";
    private static final String UPDATE_PATH = "UPDATE comment SET path = ? WHERE id = ?";
    private static final String SET_EMPTY_TEXT = "UPDATE comment SET text = '' WHERE id = ?";
    private static final String DELETE = "DELETE FROM comment WHERE id = ?";
    private static final String DELETE_THREAD = "DELETE FROM comment WHERE path LIKE ?";
    private static final String NUMBER_OF_REPLIES = "SELECT COUNT(*) FROM comment WHERE path LIKE ?";

    public CommentDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public List<Comment> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Comment> findById(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new CommentMapper().map(resultSet));
            }
            return Optional.empty();
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Comment save(Comment comment) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            try {
                if (comment.getId() != null) {
                    preparedStatement = connection.prepareStatement(UPDATE);
                    preparedStatement.setString(1, comment.getText());
                    preparedStatement.setTimestamp(2, new Timestamp(comment.getDate().getTime()));
                    preparedStatement.setInt(3, comment.getId());
                    preparedStatement.executeUpdate();
                } else {
                    preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, comment.getText());
                    preparedStatement.setTimestamp(2, new Timestamp(comment.getDate().getTime()));
                    preparedStatement.setString(3, comment.getPath());
                    preparedStatement.setInt(4, comment.getTrack().getId());
                    preparedStatement.setInt(5, comment.getUser().getId());
                    preparedStatement.execute();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int generatedId = resultSet.getInt(1);
                        comment.setId(generatedId);
                        preparedStatement = connection.prepareStatement(UPDATE_PATH);
                        String toPath = comment.getPath() + generatedId + ConstantAttributes.PATH_DELIMITER;
                        preparedStatement.setString(1, toPath);
                        preparedStatement.setInt(2, generatedId);
                        preparedStatement.execute();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return comment;
    }

    @Override
    public boolean delete(Comment comment) throws DaoException {
        return deleteObject(comment, DELETE);
    }

    public boolean deleteThead(Comment comment) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_THREAD)) {
            preparedStatement.setString(1, comment.getPath() + ConstantAttributes.PERCENT);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean setEmptyText(Comment comment) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_EMPTY_TEXT)) {
            preparedStatement.setInt(1, comment.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Comment> findAllByTrack(Integer trackId) throws DaoException {
        List<Comment> comments = new LinkedList<>();
        Comment comment;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_TRACK)) {
            preparedStatement.setInt(1, trackId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = new CommentMapper().map(resultSet);
                comments.add(comment);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        comments.sort(new CommentByPathComparator());
        return comments;
    }

    public int calcNumberOfReplies(String path) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(NUMBER_OF_REPLIES)) {
            preparedStatement.setString(1, path + ConstantAttributes.PERCENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) - 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}