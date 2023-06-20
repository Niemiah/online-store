package com.solvd.online.store.service.mybatisimpl;

import com.solvd.online.store.dao.IAddressDAO;
import com.solvd.online.store.model.Address;
import com.solvd.online.store.service.IAddressService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressService implements IAddressService {
    private SqlSessionFactory sqlSessionFactory;
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public AddressService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void saveAddressToDB(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            addressDAO.insert(address);
            session.commit();
            LOGGER.info("Address has been successfully saved to the database.");
        }
    }

    @Override
    public void updateAddressInDB(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address object is null.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            addressDAO.update(address);
            session.commit();
            LOGGER.info("Address has been successfully updated in the database.");
        }
    }

    @Override
    public Address getAddressInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            Address address = addressDAO.getById(id);
            LOGGER.info("Retrieved address from the database with id: " + id);
            return address;
        }
    }

    @Override
    public void deleteAddressFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            addressDAO.deleteById(id);
            session.commit();
            LOGGER.info("Address with id: " + id + " has been deleted from the database.");
        }
    }
}