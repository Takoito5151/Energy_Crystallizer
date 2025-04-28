package com.takoito.energy_crystallizer_1710;

import net.minecraft.item.Item;

public class ItemEnergyCrystal extends Item{
    public ItemEnergyCrystal(int num){
        super();
        this.setCreativeTab(CommonProxy.tabCrystal)
            .setUnlocalizedName("energy_crystal_"+num);
    }
}
