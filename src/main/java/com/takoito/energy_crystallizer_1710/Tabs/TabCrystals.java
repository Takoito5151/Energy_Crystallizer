package com.takoito.energy_crystallizer_1710;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabCrystals extends CreativeTabs {
    public TabCrystals(String label){
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return CommonProxy.testitem;
    }
}
