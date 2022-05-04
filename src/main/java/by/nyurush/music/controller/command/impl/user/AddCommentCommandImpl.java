package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Comment;
import by.nyurush.music.entity.Track;
import by.nyurush.music.entity.User;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.CommentService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCommentCommandImpl implements Command {
    private final CommentService commentService = new CommentService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String commentIdStr = req.getParameter(ConstantAttributes.COMMENT_ID);
        String parentId = req.getParameter(ConstantAttributes.PARENT_ID);
        Integer commentId = null;
        if(parentId == null) {
            parentId = "";
        }
        if(commentIdStr != null) {
            commentId = Integer.parseInt(commentIdStr);
        }
        String text = req.getParameter(ConstantAttributes.TEXT_COMMENT);
        Date date = new Date();
        Integer trackId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        User user = (User) req.getSession().getAttribute(ConstantAttributes.USER);

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        if(StringUtil.isNullOrEmpty(text)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.FILL_COMMENT_INPUT));
            req.setAttribute(ConstantAttributes.COMMENTS, commentService.findAllByTrack(trackId));
            req.setAttribute(ConstantAttributes.SONG, trackService.findById(trackId).orElseThrow());
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_COMMENTS);
        }

        Comment comment = new Comment(commentId, text, date, parentId, new Track(trackId), user);
        commentService.save(comment);


        return CommandResult.redirect(req.getServletPath() + ConstantPathPages.PATH_PAGE_REDIRECT_COMMENTS + trackId);
    }
}