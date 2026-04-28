package com.takoito.energy_crystallizer_1710.blocks;

import com.takoito.energy_crystallizer_1710.CommonProxy;
import com.takoito.energy_crystallizer_1710.EnergyCrystallizer;
import com.takoito.energy_crystallizer_1710.TileEntity.TECrystallizer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrystallizer extends BlockContainer {
    public BlockCrystallizer(){
        super(Material.iron);
        this.setBlockName("crystallizer");
        this.setBlockTextureName("energy_crystallizer:crystallizer");
        this.setCreativeTab(CommonProxy.tabCrystal);
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.setLightLevel(0.0f);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TECrystallizer();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
        player.openGui(EnergyCrystallizer.INSTANCE, CommonProxy.GUI_ID, world, x, y, z);
        return true;
    }
}
