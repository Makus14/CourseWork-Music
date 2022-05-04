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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddToPlaylistCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddToPlaylistCommandImpl.class);
    private final PlaylistService playlistService = new PlaylistService();
    private final TrackService trackService = new TrackService();


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        if(StringUtil.isNullOrEmpty(playlistName) || DataValidator.isIncorrectInput(playlistName)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.FILL_WITH_CORRECT_DATA));
            new AddToPlaylistPageCommandImpl().execute(req, resp);
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
        }
        Integer songId = Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID));
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());
        Optional<Track> track = trackService.findById(songId);

        if (playlist.isPresent() && track.isPresent()) {
            List<Track> tracksList = trackService.findByPlaylistId(playlist.get().getId());
            if(tracksList.contains(track.get())) {
                req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.ADDED_TRACK));
                if(playlist.get().getPlaylistName().equals(ConstantAttributes.FAVORITE)) {
                    new HomeCommandImpl().execute(req, resp);
                    return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
                } else {
                    new AddToPlaylistPageCommandImpl().execute(req, resp);
                    return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_TO_PLAYLIST);
                }
            } else {
                playlistService.addTrack(playlist.get(), track.get());
            }
        } else {
            LOGGER.warn("Playlist or Track was not found");
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_ADD_PLAYLIST_RESULT));
        }
        req.setAttribute(ConstantAttributes.SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_ADD_TO_PLAYLIST_RESULT));
        new HomeCommandImpl().execute(req, resp);
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
