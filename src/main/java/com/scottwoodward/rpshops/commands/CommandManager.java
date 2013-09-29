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
package com.scottwoodward.rpshops.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.scottwoodward.rpshops.RPShops;
import com.scottwoodward.rpshops.entities.EntityShopKeeper;

/**
 * CommandManager.java
 * Purpose: 
 *
 * @author Scott Woodward
 */
public class CommandManager implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        CraftWorld world = (CraftWorld)loc.getWorld();
        RPShops.villager = new EntityShopKeeper(world.getHandle());
        String name;
        if(sender.getName().charAt(sender.getName().length() -1) == 's'){
            name = "Shop Keeper";
        }else{
            name = "Shop Keeper";
        }
        RPShops.villager.setCustomName(name);
        RPShops.villager.setPosition(loc.getX(), loc.getY() + 1, loc.getZ());
        world.getHandle().addEntity(RPShops.villager, SpawnReason.CUSTOM);
        RPShops.config.set("X", loc.getX());
        RPShops.config.set("Y", loc.getY() + 1);
        RPShops.config.set("Z", loc.getZ());
        return true;
    }

}
