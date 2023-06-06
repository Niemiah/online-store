package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.dao_interface.UserAddressDao;
import com.solvd.online.store.locations.UserAddress;
import java.util.ArrayList;
import java.util.List;

public class UserAddressDaoImpl implements UserAddressDao {

    List<UserAddress> userAddresses;

    public UserAddressDaoImpl(){
        userAddresses = new ArrayList<UserAddress>();
        UserAddress userAddress1 = new UserAddress(1,1);
        UserAddress userAddress2 = new UserAddress(2,2);
        userAddresses.add(userAddress1);
        userAddresses.add(userAddress2);
    }

    @Override
    public void deleteUserAddress(UserAddress userAddress) {
        userAddresses.remove(userAddress.getUserId());
        System.out.println("UserAddress: User Id " + userAddress.getUserId() + " Address Id "+ userAddress.getAddressId() + ", deleted from database");
    }

    @Override
    public List<UserAddress> getAllUserAddresses() {
        return userAddresses;
    }

    @Override
    public UserAddress getUserAddress(int userId, int addressId) {
        return userAddresses.get(userId);
    }

    @Override
    public void updateUserAddress(UserAddress userAddress) {
        userAddresses.get(userAddress.getUserId()).setUserId(userAddress.getUserId());
        userAddresses.get(userAddress.getUserId()).setAddressId(userAddress.getAddressId());
        System.out.println("UserAddress: User Id " + userAddress.getUserId() + " Address Id "+ userAddress.getAddressId() + ", updated in the database");
    }
}
