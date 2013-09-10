/**
 * Copyright 2013 - 2013 Scott Woodward
 *
 * This file is part of RPShops
 *
 * RPShops is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RPShops is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RPShops.  If not, see <http://www.gnu.org/licenses/>. 
 */
package com.scottwoodward.rpshops.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.scottwoodward.rpshops.RPShops;

/**
 * InventoryClickListener.java
 * Purpose: 
 *
 * @author Scott Woodward
 */
public class InventoryClickListener implements Listener{
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Shop")){
            event.setCancelled(true);
            if(event.getRawSlot() == 0){
                Player player = (Player) event.getWhoClicked();
                Inventory inv = player.getInventory();
                ItemStack item = new ItemStack(Material.EMERALD, 1);
                if(inv.contains(Material.EMERALD, 1)){
                    RPShops.economy.depositPlayer(player.getName(), 20);
                    inv.removeItem(item);
                }
                player.updateInventory();
            }
        }
    }

}