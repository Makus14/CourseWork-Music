package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeCommandImpl implements Command {
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        int page = ConstantAttributes.FIRST_PAGE;
        if (req.getParameter(ConstantAttributes.PAGE_NO) != null)
            page = Integer.parseInt(req.getParameter(ConstantAttributes.PAGE_NO));

        List<Track> list = trackService.findForPage((page - 1) * ConstantAttributes.RECORDS_PER_PAGE, ConstantAttributes.RECORDS_PER_PAGE);
        int noOfRecords = trackService.getNoOfRecords();
        int noOfPages = (int) Math.ceil((float) noOfRecords / ConstantAttributes.RECORDS_PER_PAGE);

        req.setAttribute(ConstantAttributes.SONGS, list);
        req.setAttribute(ConstantAttributes.NO_OF_PAGES, noOfPages);
        req.setAttribute(ConstantAttributes.CURRENT_PAGE, page);

        return CommandResult.forward(ConstantPathPages.PATH_PAGE_HOME);
    }
}
