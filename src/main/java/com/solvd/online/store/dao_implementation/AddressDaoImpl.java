package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.locations.Address;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    List<Address> addresses;

    public AddressDaoImpl(){
        addresses = new ArrayList<Address>();
        Address address1 = new Address(1,"123 Main St","Los Angeles","CA","90001","USA");
        Address address2 = new Address(2,"456 Oak Dr","San Francisco","CA","94101","USA");
        addresses.add(address1);
        addresses.add(address2);
    }

    @Override
    public void deleteAddress(Address address) {
        addresses.remove(address.getAddressId());
        System.out.println("Address: Address Id " + address.getAddressId() + ", deleted from database");
    }

    @Override
    public List<Address> getAllAddresses() {
        return addresses;
    }

    @Override
    public Address getAddress(int addressId) {
        return addresses.get(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        addresses.get(address.getAddressId()).setAddressId(address.getAddressId());
        System.out.println("Address: Address Id " + address.getAddressId() + ", updated in the database");
    }
}
