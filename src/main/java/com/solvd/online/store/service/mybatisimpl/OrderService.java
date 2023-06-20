package com.solvd.online.store.service.mybatisimpl;

import com.solvd.online.store.dao.IOrderDAO;
import com.solvd.online.store.model.Order;
import com.solvd.online.store.service.IOrderService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderService implements IOrderService {
    private SqlSessionFactory sqlSessionFactory;
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    public OrderService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void saveOrderToDB(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            orderDAO.insert(order);
            session.commit();
            LOGGER.info("Order has been successfully saved to the database.");
        }
    }

    @Override
    public void updateOrderInDB(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            orderDAO.update(order);
            session.commit();
            LOGGER.info("Order has been successfully updated in the database.");
        }
    }

    @Override
    public Order getOrderInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            Order order = orderDAO.getById(id);
            LOGGER.info("Retrieved order from the database with id: " + id);
            return order;
        }
    }

    @Override
    public void deleteOrderFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);
            orderDAO.deleteById(id);
            session.commit();
            LOGGER.info("Order with id: " + id + " has been deleted from the database.");
        }
    }
}