package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArtistsCommandImpl implements Command {
    private final  ArtistService artistService = new ArtistService();
    private final TrackService trackService = new TrackService();
    private final AlbumService albumService = new AlbumService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String albumName = req.getParameter(ConstantAttributes.ALBUM_NAME);
        req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findAll());
        if (StringUtil.isNullOrEmpty(albumName)) {
            req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByArtist(req.getParameter(ConstantAttributes.ARTIST_NAME)));
        } else {
            req.setAttribute(ConstantAttributes.SONGS_LIST, trackService.findByAlbumName(albumName));
        }
        req.setAttribute(ConstantAttributes.ALBUMS, albumService.findByArtistName(req.getParameter(ConstantAttributes.ARTIST_NAME)));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ARTISTS);
    }
}
