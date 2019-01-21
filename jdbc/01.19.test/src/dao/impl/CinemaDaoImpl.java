package dao.impl;

import com.dean.Cinema;
import dao.CinemaDao;
import dao.DBUitls;

import java.util.List;

public class CinemaDaoImpl extends DBUitls implements CinemaDao {
    @Override
    public List<Cinema> querytByFilmId(int filnId) {
        String sql = "select c.cid,c.cinemaName,c.address,c.phone from cinema c";
        return null;
    }

    @Override
    public int add(Cinema cinema) {
        return 0;
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public int update(Cinema cinema) {
        return 0;
    }

    @Override
    public Cinema queryOne(int id) {
        return null;
    }
}
