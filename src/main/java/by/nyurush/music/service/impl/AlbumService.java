package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AlbumDaoImpl;
import by.nyurush.music.entity.Album;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class AlbumService {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public List<Album> findAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Album> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Album save(Album album) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.save(album);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Album album) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.delete(album);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByYear(Integer year) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findByYear(year);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Album> findByArtistAndAlbumName(String artist, String albumName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findByArtistAndAlbumName(artist, albumName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Album> findByArtistName(String artistName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDaoImpl albumDao = daoHelper.createAlbumDao();
            return albumDao.findByArtistName(artistName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
