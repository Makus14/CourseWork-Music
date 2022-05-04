package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.AlbumService;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddAlbumCommandImpl implements Command {
    private final AlbumService albumService = new AlbumService();
    private final ArtistService artistService = new ArtistService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String albumName = req.getParameter(ConstantAttributes.ALBUM_NAME);
        int year = Integer.parseInt(req.getParameter(ConstantAttributes.YEAR));
        String artistsName = req.getParameter(ConstantAttributes.ARTISTS_NAME);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        if (!StringUtil.areNotNullAndNotEmpty(albumName, artistsName, Integer.toString(year))) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.EMPTY_FIELDS));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);
        }
        if (year < ConstantAttributes.YEAR_OF_FIRST_MUSICIAN || year > Calendar.getInstance().get(Calendar.YEAR) ||
                !DataValidator.areCorrectInputs(albumName, artistsName)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.FILL_WITH_CORRECT_DATA));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);
        }
        if (albumService.save(new Album(null, albumName, year, artistService.findByName(artistsName).get(0))) != null) {
            req.setAttribute(ConstantAttributes.SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_ALBUM_SAVE_RESULT));
        } else {
            req.setAttribute(ConstantAttributes.ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_ALBUM_SAVE_RESULT));
        }
        req.setAttribute(ConstantAttributes.ARTISTS_NAME, artistService.findAll());
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_CREATE_ALBUM);
    }
}
