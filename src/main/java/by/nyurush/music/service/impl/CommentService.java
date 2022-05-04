package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.CommentDaoImpl;
import by.nyurush.music.entity.Comment;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.util.validation.StringUtil;

import java.util.List;
import java.util.Optional;

public class CommentService {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public Optional<Comment> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDaoImpl commentDao = daoHelper.createCommentDao();
            return commentDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Comment save(Comment comment) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDaoImpl commentDao = daoHelper.createCommentDao();
            if(!StringUtil.isNullOrEmpty(comment.getPath()) && comment.getId() == null) {
                Optional<Comment> parentComment = commentDao.findById(Integer.parseInt(comment.getPath()));
                parentComment.ifPresent(value -> comment.setPath(value.getPath()));
            }
            return commentDao.save(comment);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Comment comment, boolean thread) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDaoImpl commentDao = daoHelper.createCommentDao();
            if(thread) {
                return commentDao.deleteThead(comment);
            }
            if(commentDao.calcNumberOfReplies(comment.getPath()) == 0) {
                return commentDao.delete(comment);
            } else {
                return commentDao.setEmptyText(comment);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Comment> findAllByTrack(Integer trackId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDaoImpl commentDao = daoHelper.createCommentDao();
            return commentDao.findAllByTrack(trackId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
