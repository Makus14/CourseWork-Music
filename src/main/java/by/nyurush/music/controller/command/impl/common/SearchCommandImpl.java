package by.nyurush.music.controller.command.impl.common;

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
import by.nyurush.music.util.validation.DataValidator;
import by.nyurush.music.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

public class SearchCommandImpl implements Command {
    private final ArtistService artistService = new ArtistService();
    private final TrackService trackService = new TrackService();
    private final AlbumService albumService = new AlbumService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String input = req.getParameter(ConstantAttributes.SEARCH_INPUT);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        if(StringUtil.isNullOrEmpty(input) || DataValidator.isIncorrectInput(input)) {
            req.setAttribute(ConstantAttributes.INFO_MESSAGE, rb.getString(ConstantMessages.FILL_WITH_CORRECT_DATA));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_SEARCH);
        }
        req.setAttribute(ConstantAttributes.ARTISTS_LIST, artistService.findByName(input));

        int page = ConstantAttributes.FIRST_PAGE;
        if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
            page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));
        List<Track> list = trackService.findByName(input, (page - 1) * ConstantAttributes.RECORDS_PER_PAGE, ConstantAttributes.RECORDS_PER_PAGE);
        int noOfRecords = trackService.getNoOfRecordsByName(input);
        int noOfPages = (int) Math.ceil((float) noOfRecords / ConstantAttributes.RECORDS_PER_PAGE);

        req.setAttribute(ConstantAttributes.SONGS, list);
        req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
        req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);
        req.setAttribute(ConstantAttributes.ALBUMS, albumService.findByName(input));

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_SEARCH);
    }
}
