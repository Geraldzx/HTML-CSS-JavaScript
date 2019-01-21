package com.dean.biz;

import com.dean.Cinema;

import java.util.List;

public interface CinemaBiz extends CommonBiz {
    /**
     * 根据电影编号查询上映该电影的电影院
     */
    List<Cinema> querytByFilmId(int filnId);
}
