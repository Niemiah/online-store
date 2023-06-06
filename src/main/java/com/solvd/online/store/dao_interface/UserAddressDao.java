package com.solvd.online.store.dao_interface;
import com.solvd.online.store.locations.UserAddress;
import java.util.List;

public interface UserAddressDao {
    public List<UserAddress> getAllUserAddresses();
    public UserAddress getUserAddress(int userId, int addressId);
    public void updateUserAddress(UserAddress userAddress);
    public void deleteUserAddress(UserAddress userAddress);
}
