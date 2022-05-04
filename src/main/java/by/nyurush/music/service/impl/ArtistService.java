package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.ArtistDaoImpl;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ArtistService {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public List<Artist> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            return artistDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Artist> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            return artistDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Artist save(Artist artist) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            return artistDao.save(artist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Artist artist) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            return artistDao.delete(artist);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Artist> findByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDaoImpl artistDao = daoHelper.createArtistDao();
            return artistDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
