package com.daoimpl;

import com.dao.UserDao;
import com.model.Customer;
import com.util.HibernateUtil;
import com.util.ResultMessage;
import com.util.UserState;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {


    @Override
    public Customer findById(String email) {
        return (Customer) super.findById(Customer.class, email);
    }

    @Override
    public void save(Customer customer) {
        super.save(customer);
    }

    @Override
    public void update(Customer customer) {
        super.update(customer);
    }

    @Override
    public ResultMessage activeUser(String code){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Customer as c where c.activecode= ?1");
            query.setParameter(1, code);
            List customer = query.list();
            if (customer != null) {
                Customer activeCustomer = (Customer) customer.get(0);
                System.out.println("要验证的用户"+activeCustomer.toString());
                activeCustomer.setState(UserState.Valid);
                session.update(activeCustomer);
                tx.commit();
                return ResultMessage.SUCCESS;
            } else {
                return ResultMessage.FAIL;
            }
        }catch (Exception e){
            return ResultMessage.FAIL;
        }
    }
}
