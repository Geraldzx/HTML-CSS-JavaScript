package dao;

import com.dean.PlaysInfo;

import java.util.List;
import java.util.Map;

public interface PlaysInfoDao extends CommonDao<PlaysInfo> {
    /**
     * 查询所有正在上映的电影信息
     */
    List<Map> queryAll();
}
