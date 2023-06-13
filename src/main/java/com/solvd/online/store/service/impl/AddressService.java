package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IAddressDAO;
import com.solvd.online.store.impl.AddressDAO;
import com.solvd.online.store.service.IAddressService;
import com.solvd.online.store.model.Address;

public class AddressService implements IAddressService {

    @Override
    public void saveAddressToDB(Address address) {
        IAddressDAO addressDAO = new AddressDAO();
        addressDAO.insert(address);
    }

    @Override
    public void updateAddressInDB(Address address) {
        IAddressDAO addressDAO = new AddressDAO();
        addressDAO.update(address);
    }

    @Override
    public Address getAddressInDB(int id) {
        IAddressDAO addressDAO = new AddressDAO();
        return addressDAO.getById(id);
    }
}
