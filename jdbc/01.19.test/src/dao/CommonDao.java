package dao;

/**
 * 通用的方法
 *
 * @param <T>
 */
public interface CommonDao<T> {
    int add(T t);//增
    int remove(int id);//删
    int update(T t);//改
    T queryOne(int id);//查
}
