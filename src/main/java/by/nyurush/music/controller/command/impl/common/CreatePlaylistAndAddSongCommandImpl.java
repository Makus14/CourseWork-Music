package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;
import static by.nyurush.music.util.constant.ConstantAttributes.SUCCESS_MESSAGE;

public class CreatePlaylistAndAddSongCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreatePlaylistAndAddSongCommandImpl.class);
    private final  PlaylistService playlistService = new PlaylistService();
    private final  TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        String songIdStr = req.getParameter(ConstantAttributes.SONG_ID);
        if (!StringUtil.areNotNullAndNotEmpty(playlistName, songIdStr) || DataValidator.isIncorrectInput(playlistName)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.FILL_WITH_CORRECT_DATA));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
        }
        Integer songId = Integer.parseInt(songIdStr);
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        if (playlistService.findByNameAndUserId(playlistName, account.getId()).isPresent()) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.EXISTING_PLAYLIST));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
        }
        Playlist playlist = playlistService.save(new Playlist(null, playlistName, false, account));
        Optional<Track> track = trackService.findById(songId);
        if (track.isPresent()) {
            playlistService.addTrack(playlist, track.get());
        } else {
            LOGGER.warn("Track was not found");
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_SONG));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
        }
        req.setAttribute(SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_ADD_TO_PLAYLIST_RESULT));
        new HomeCommandImpl().execute(req, resp);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
