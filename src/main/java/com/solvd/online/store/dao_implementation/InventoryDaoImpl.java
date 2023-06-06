package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.merchandise.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {

    List<Inventory> inventories;

    public InventoryDaoImpl(){
        inventories = new ArrayList<Inventory>();
        Inventory inventory1 = new Inventory(1, 1, 100, 1);
        Inventory inventory2 = new Inventory(2, 2, 200, 2);
        inventories.add(inventory1);
        inventories.add(inventory2);
    }

    @Override
    public void deleteInventory(Inventory inventory) {
        inventories.remove(inventory.getInventoryId());
        System.out.println("Inventory: Inventory Id " + inventory.getInventoryId() + ", deleted from database");
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventories;
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        return inventories.get(inventoryId);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventories.get(inventory.getInventoryId()).setStock(inventory.getStock());
        System.out.println("Inventory: Inventory Id " + inventory.getInventoryId() + ", updated in the database");
    }
}