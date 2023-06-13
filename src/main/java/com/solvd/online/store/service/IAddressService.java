package com.solvd.online.store.service;
import com.solvd.online.store.model.Address;

public interface IAddressService {
    void saveAddressToDB(Address address);

    void updateAddressInDB(Address address);

    Address getAddressInDB(int id);

    void deleteAddressFromDB(int id);

    String getAllAddressesFromDB();
}
