package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Account;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AccountService;
import by.nyurush.music.service.impl.PlaylistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;

public class PlaylistsCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PlaylistsCommandImpl.class);
    private final  PlaylistService playlistService = new PlaylistService();
    private final TrackService trackService = new TrackService();
    private final AccountService accountService = new AccountService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String playlistName = req.getParameter(ConstantAttributes.PLAYLIST_NAME);
        Account account;
        if (req.getParameter(ConstantAttributes.ALL) == null) {
            account = (Account) req.getSession().getAttribute(ConstantAttributes.USER);
        } else {
            account = accountService.findAdmin();
            if(account == null) {
                req.setAttribute(ERROR_MESSAGE, ConstantMessages.ERROR_MESSAGE);
                LOGGER.warn("Playlist was not found");
                return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
            }
        }
        Optional<Playlist> playlist = playlistService.findByNameAndUserId(playlistName, account.getId());
        if (playlist.isPresent()) {
            int page = ConstantAttributes.FIRST_PAGE;
            if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
                page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
            List<Track> list = trackService.findByPlaylistId(playlist.get().getId(), (page - 1) * ConstantAttributes.RECORDS_PER_PAGE, ConstantAttributes.RECORDS_PER_PAGE);
            int noOfRecords = trackService.getNoOfRecordsByPlaylistId(playlist.get().getId());
            int noOfPages = (int) Math.ceil((float) noOfRecords / ConstantAttributes.RECORDS_PER_PAGE);

            req.setAttribute(ConstantAttributes.SONGS, list);
            req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
            req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);
        } else {
            req.setAttribute(ERROR_MESSAGE, ConstantMessages.INVALID_FIND_PLAYLIST);
            LOGGER.warn("Playlist was not found");
        }
        req.setAttribute(ConstantAttributes.PLAYLIST_LIST, playlistService.findByUserId(account.getId()));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_PLAYLISTS);
    }
}
