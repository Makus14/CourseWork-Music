package by.nyurush.music.controller.command;

import by.nyurush.music.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
