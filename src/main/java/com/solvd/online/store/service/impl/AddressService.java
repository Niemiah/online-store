package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IAddressDAO;
import com.solvd.online.store.impl.AddressDAO;
import com.solvd.online.store.service.IAddressService;
import com.solvd.online.store.model.Address;

public class AddressService implements IAddressService {

    @Override
    public void saveAddressToDB(Address address) {
        IAddressDAO addressDao = new AddressDAO();
        addressDao.insert(address);
    }

    @Override
    public void updateAddressInDB(Address address) {
        IAddressDAO addressDao = new AddressDAO();
        addressDao.update(address);
    }

    @Override
    public Address getAddressInDB(int id) {
        IAddressDAO addressDao = new AddressDAO();
        return addressDao.getById(id);
    }
}
