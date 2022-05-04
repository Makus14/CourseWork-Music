package by.nyurush.music.service.impl;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.TrackDaoImpl;
import by.nyurush.music.entity.Track;
import by.nyurush.music.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class TrackService {
    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public Optional<Track> findById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Track save(Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.save(track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean delete(Track track) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.delete(track);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByName(String name, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByName(name, offset, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByAlbumName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByAlbumName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByGenre(String genre, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByGenre(genre, offset, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByArtist(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByArtist(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistId(Integer playlistId, Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByPlaylistId(playlistId, offset, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistId(Integer playlistId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByPlaylistId(playlistId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findByPlaylistName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findByPlaylistName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> findAllGenres() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findAllGenres();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Track> findForPage(Integer offset, Integer recordsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.findForPage(offset, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecords() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecords();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByGenre(String genre) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByGenre(genre);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Integer getNoOfRecordsByPlaylistId(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TrackDaoImpl trackDao = daoHelper.createTrackDao();
            return trackDao.getNoOfRecordsByPlaylistId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
