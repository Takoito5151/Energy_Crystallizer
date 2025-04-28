package com.takoito.energy_crystallizer_1710.blocks;

import com.takoito.energy_crystallizer_1710.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CrystalBlock extends Block {
    public CrystalBlock(Material material,int num_block){
        super(material);
        this.setBlockName("energy_crystal_block_"+num_block)
            .setCreativeTab(CommonProxy.tabCrystal)
            .setHardness(5.0F)
            .setResistance(5.0F)
            .setStepSound(Block.soundTypeStone)
            .setHarvestLevel("pickaxe",2);
    }
}
