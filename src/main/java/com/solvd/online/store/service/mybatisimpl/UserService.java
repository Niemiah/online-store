package com.solvd.online.store.service.mybatisimpl;

import com.solvd.online.store.dao.IUserDAO;
import com.solvd.online.store.model.User;
import com.solvd.online.store.service.IUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService implements IUserService {
    private SqlSessionFactory sqlSessionFactory;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public UserService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void saveUserToDB(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            userDAO.insert(user);
            session.commit();
            LOGGER.info("User has been successfully saved to the database.");
        }
    }

    @Override
    public void updateUserInDB(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            userDAO.update(user);
            session.commit();
            LOGGER.info("User has been successfully updated in the database.");
        }
    }

    @Override
    public User getUserInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            User user = userDAO.getById(id);
            LOGGER.info("Retrieved user from the database with id: " + id);
            return user;
        }
    }

    @Override
    public void deleteUserFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            userDAO.deleteById(id);
            session.commit();
            LOGGER.info("User with id: " + id + " has been deleted from the database.");
        }
    }
}