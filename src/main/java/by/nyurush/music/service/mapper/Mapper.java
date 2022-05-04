package by.nyurush.music.service.mapper;

import by.nyurush.music.entity.Entity;
import by.nyurush.music.service.exception.ServiceException;

import java.sql.ResultSet;

public interface Mapper<T extends Entity> {
    T map(ResultSet resultSet) throws ServiceException;
}
