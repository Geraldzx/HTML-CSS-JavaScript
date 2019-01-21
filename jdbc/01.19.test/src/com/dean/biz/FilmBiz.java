package com.dean.biz;

import com.dean.Film;

import java.util.List;

public interface FilmBiz extends CommonBiz {
    /**
     * 查询所有电影的信息
     */

    List<Film> queryAll();
}
