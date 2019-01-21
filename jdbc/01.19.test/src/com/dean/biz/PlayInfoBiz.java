package com.dean.biz;

import java.util.List;
import java.util.Map;

public interface PlayInfoBiz extends CommonBiz {
    /**
     * 查询所有正在上映的电影信息
     */
    List<Map> queryAll();
}
