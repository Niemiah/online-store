package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IAddressDAO;
import com.solvd.online.store.dao.impl.AddressDAO;
import com.solvd.online.store.service.IAddressService;
import com.solvd.online.store.model.Address;

public class AddressService implements IAddressService {

    @Override
    public void saveAddressToDB(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address object is null.");
        }

        IAddressDAO addressDAO = new AddressDAO();
        addressDAO.insert(address);
    }

    @Override
    public void updateAddressInDB(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address object is null.");
        }

        IAddressDAO addressDAO = new AddressDAO();
        addressDAO.update(address);
    }

    @Override
    public Address getAddressInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IAddressDAO addressDAO = new AddressDAO();
        return addressDAO.getById(id);
    }

    @Override
    public void deleteAddressFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IAddressDAO addressDAO = new AddressDAO();
        addressDAO.deleteById(id);
    }
}