package com.daoimpl;

import com.dao.BaseDao;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl implements BaseDao {

    @Override
    public Object findById(Class c, String id) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Object o = session.get(c,id);
            tx.commit();
            return o;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Object o) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(o);
//            session.save(o);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.update(o);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        HibernateUtil.getSession().clear();
    }

    @Override
    public void flush() {
        HibernateUtil.getSession().flush();
    }
}
