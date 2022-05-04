package by.nyurush.music.controller;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandFactory;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.pool.ConnectionPool;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantPathPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MusicController", urlPatterns = "/controller")
public class MusicController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MusicController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter(ConstantAttributes.PARAMETER_COMMAND);
        try {
            Command command = CommandFactory.getCommand(parameter);
            CommandResult page = command.execute(req, resp);
            dispatch(req, resp, page);
        } catch (ServiceException e) {
            LOGGER.error(e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConstantPathPages.PATH_PAGE_ERROR_WENT_WRONG);
            dispatcher.forward(req, resp);
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();

        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + pageToDispatch);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageToDispatch);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyConnectionPool();
    }
}
