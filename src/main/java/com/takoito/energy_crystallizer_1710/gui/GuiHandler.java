package com.takoito.energy_crystallizer_1710.gui;

import com.takoito.energy_crystallizer_1710.CommonProxy;
import com.takoito.energy_crystallizer_1710.EnergyCrystallizer;
import com.takoito.energy_crystallizer_1710.TileEntity.TECrystallizer;
import com.takoito.energy_crystallizer_1710.container.ContainerCrystallizer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        if (ID == CommonProxy.GUI_ID){
//            return new ContainerCrystallizer(player.inventory, new TECrystallizer());
//        }

        if (!world.blockExists(x, y, z))
            return null;
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if (tileentity instanceof TECrystallizer){
            return new ContainerCrystallizer(player.inventory, (TECrystallizer) tileentity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        if (ID == CommonProxy.GUI_ID){
//            return new GUICrystallizer(player.inventory, new TECrystallizer());
//        }
        if (!world.blockExists(x, y, z))
            return null;
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if (tileentity instanceof TECrystallizer) {
            return new GUICrystallizer(player.inventory, (TECrystallizer) tileentity);
        }
        return null;
    }
}
