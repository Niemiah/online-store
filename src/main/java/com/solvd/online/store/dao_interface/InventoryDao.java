package com.solvd.online.store.dao_interface;
import com.solvd.online.store.merchandise.Inventory;
import java.util.List;

public interface InventoryDao {
    public List<Inventory> getAllInventories();
    public Inventory getInventory(int inventoryId);
    public void updateInventory(Inventory inventory);
    public void deleteInventory(Inventory inventory);
}