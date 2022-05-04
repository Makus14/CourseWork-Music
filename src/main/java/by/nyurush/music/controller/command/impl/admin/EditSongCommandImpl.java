package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;

public class EditSongCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditSongCommandImpl.class);
    private final ArtistService artistService = new ArtistService();
    private final AlbumService albumService = new AlbumService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        req.setAttribute(ConstantAttributes.ARTISTS_NAME, artistService.findAll());
        req.setAttribute(ConstantAttributes.ALBUMS, albumService.findAll());

        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        Optional<Track> track = trackService.findById(Integer.parseInt(req.getParameter(ConstantAttributes.SONG_ID)));
        if (track.isPresent()) {
            req.setAttribute(ConstantAttributes.SONG, track.get());
        } else {
            LOGGER.warn("There is no such track");
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_SONG));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
        }
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_EDIT_SONG);
    }
}
