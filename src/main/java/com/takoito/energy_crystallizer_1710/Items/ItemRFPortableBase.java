package com.takoito.energy_crystallizer_1710.Items;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;

public class ItemRFPortableBase extends ItemECItem implements IEnergyContainerItem {
    protected int capacity;
    protected int max_receive;
    protected int max_extract;
    public ItemRFPortableBase(int cap,int rec,int ext){
        super();
        this.capacity=cap;
        this.max_receive= rec;
        this.max_extract = ext;
    }

    @Override
    public int receiveEnergy(ItemStack itemStack, int i, boolean b) {
        return 0;
    }

    @Override
    public int extractEnergy(ItemStack itemStack, int i, boolean b) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ItemStack itemStack) {
        return 0;
    }
}
