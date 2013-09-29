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
package com.scottwoodward.rpshops;

import java.io.File;
import java.io.IOException;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Configuration;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.scottwoodward.rpshops.commands.CommandManager;
import com.scottwoodward.rpshops.entities.CustomEntityType;
import com.scottwoodward.rpshops.entities.EntityShopKeeper;
import com.scottwoodward.rpshops.listeners.InventoryClickListener;
import com.scottwoodward.rpshops.listeners.ShopKeeperDamageListener;
import com.scottwoodward.rpshops.listeners.VillagerInteractListener;


/**
 * RPShops.java
 * Purpose: 
 *
 * @author Scott Woodward
 */
public class RPShops extends JavaPlugin {

    public static Economy economy;
    public static RPShops instance;
    public static YamlConfiguration config;
    public static EntityShopKeeper villager;

    public void onEnable(){
        getCommand("shop").setExecutor(new CommandManager());
        Bukkit.getPluginManager().registerEvents(new VillagerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new ShopKeeperDamageListener(), this);
        setupEconomy();
        CustomEntityType.registerEntities();
        instance = this;
        saveDefaultConfig();
        config = (YamlConfiguration) getConfig();
        spawnShopKeeper();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
                if(villager != null){
                    villager.setPosition(config.getDouble("X"), config.getDouble("Y") + 1, config.getDouble("Z"));
                }
            }
        }, 100L, 100L);
    }

    public void onDisable(){
        saveConfig();
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    private void spawnShopKeeper(){
        if(!config.contains("X") || !config.contains("Y") || !config.contains("Z")){
            return;
        }
        Location loc = new Location(Bukkit.getWorld("world"), config.getDouble("X"), config.getDouble("Y"), config.getDouble("Z"));
        CraftWorld world = (CraftWorld)loc.getWorld();
        villager = new EntityShopKeeper(world.getHandle());
        String name;
        name = "Shop Keeper";
        villager.setCustomName(name);
        villager.setPosition(loc.getX(), loc.getY() + 1, loc.getZ());
        world.getHandle().addEntity(villager, SpawnReason.CUSTOM);

    }
}
