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
package com.scottwoodward.rpshops.entities;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_6_R3.util.UnsafeList;

import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityVillager;
import net.minecraft.server.v1_6_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_6_R3.PathfinderGoalLookAtTradingPlayer;
import net.minecraft.server.v1_6_R3.World;

/**
 * EntityShopKeeper.java
 * Purpose: 
 *
 * @author Scott Woodward
 */
public class EntityShopKeeper extends EntityVillager {

    /* (non-Javadoc)
     * @see net.minecraft.server.v1_6_R2.EntityAgeable#createChild(net.minecraft.server.v1_6_R2.EntityAgeable)
     */
    @Override
    public EntityAgeable createChild(EntityAgeable arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public EntityShopKeeper(World world){
        super(world);
        try{
            Field gsa = net.minecraft.server.v1_6_R3.PathfinderGoalSelector.class.getDeclaredField("a");
            gsa.setAccessible(true);
            gsa.set(this.goalSelector, new UnsafeList<Object>());
            gsa.set(this.targetSelector, new UnsafeList<Object>());
        }catch(Exception e){
            e.printStackTrace();
        }
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.targetSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        
    }
    
    

}
