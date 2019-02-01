package com.dao;

public interface BaseDao {

    Object findById(Class c, String id);

    void save(Object o);

    void update(Object o);

    void delete(Object o);

    void clear();

    void flush();
}
