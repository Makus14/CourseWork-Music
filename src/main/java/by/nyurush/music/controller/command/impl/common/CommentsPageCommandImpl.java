package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.CommentService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentsPageCommandImpl implements Command {
    private final CommentService commentService = new CommentService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Integer trackId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        req.setAttribute(ConstantAttributes.COMMENTS, commentService.findAllByTrack(trackId));
        req.setAttribute(ConstantAttributes.SONG, trackService.findById(trackId).orElseThrow());
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_COMMENTS);
    }
}
