package dao;

import com.dean.Film;

import java.util.List;

public interface FilmDao  extends CommonDao{
    /**
     * 查询所有电影的信息
     */

    List<Film> queryAll();
}
