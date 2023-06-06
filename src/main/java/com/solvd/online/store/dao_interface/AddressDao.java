package com.solvd.online.store.dao_interface;
import com.solvd.online.store.locations.Address;
import java.util.List;

public interface AddressDao {
    public List<Address> getAllAddresses();
    public Address getAddress(int addressId);
    public void updateAddress(Address address);
    public void deleteAddress(Address address);
}
