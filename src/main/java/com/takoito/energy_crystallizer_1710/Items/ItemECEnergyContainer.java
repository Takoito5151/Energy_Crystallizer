package com.takoito.energy_crystallizer_1710.Items;

import cofh.api.energy.ItemEnergyContainer;
import com.takoito.energy_crystallizer_1710.CommonProxy;

public class ItemECEnergyContainer extends ItemEnergyContainer {
    public ItemECEnergyContainer(int cap,int rec,int ext){
        super(cap,rec,ext);
        this.setCreativeTab(CommonProxy.tabCrystal);
    }
}
