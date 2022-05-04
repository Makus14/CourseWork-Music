package by.nyurush.music.controller.command.impl.user;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class FavoritesCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FavoritesCommandImpl.class);
    private final PlaylistService playlistService = new PlaylistService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        int page = ConstantAttributes.FIRST_PAGE;
        if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
            page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
        Account account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        Optional<Playlist> playlist = playlistService.findByNameAndUserId(ConstantAttributes.FAVORITE, account.getId());
        if (playlist.isPresent()) {
            List<Track> list = trackService.findByPlaylistId(playlist.get().getId(), (page - 1) * ConstantAttributes.RECORDS_PER_PAGE, ConstantAttributes.RECORDS_PER_PAGE);
            int noOfRecords = trackService.getNoOfRecordsByPlaylistId(playlist.get().getId());
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / ConstantAttributes.RECORDS_PER_PAGE);
            req.setAttribute(ConstantAttributes.SONGS, list);
            req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
            req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);
        } else {
            LOGGER.warn("Favorite Playlist was not found");
        }
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_FAVORITES);
    }

}
