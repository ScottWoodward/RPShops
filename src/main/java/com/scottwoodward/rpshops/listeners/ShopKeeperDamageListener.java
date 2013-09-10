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

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;


/**
 * ShopKeeperDamageListener.java
 * Purpose: 
 *
 * @author Scott Woodward
 */
public class ShopKeeperDamageListener implements Listener {

    @EventHandler 
    public void onShopKeeperDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof LivingEntity){
            LivingEntity entity = (LivingEntity)event.getEntity();
            if(entity.getCustomName() != null){
                if(entity.getCustomName().equals("Shop Keeper")){
                    event.setCancelled(true);
                }
            }
        }
    }
}
