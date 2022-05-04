package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;
import static by.nyurush.music.util.constant.ConstantAttributes.SUCCESS_MESSAGE;

public class DeletePlaylistCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeletePlaylistCommandImpl.class);
    private final PlaylistService playlistService = new PlaylistService();


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        String idStr = req.getParameter(ConstantAttributes.PLAYLIST_ID);
        if(StringUtil.isNullOrEmpty(idStr)) {
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_SONG));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
        }
        Integer id = Integer.parseInt(idStr);
        Optional<Playlist> playlist = playlistService.findById(id);
        if (playlist.isPresent()) {
            playlistService.delete(playlist.get());
        } else {
            LOGGER.warn("Playlist was not found");
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_SONG));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
        }
        req.setAttribute(SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_DELETE_SONG));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
