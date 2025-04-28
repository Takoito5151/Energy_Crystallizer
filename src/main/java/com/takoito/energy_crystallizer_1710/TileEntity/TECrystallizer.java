package com.takoito.energy_crystallizer_1710.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.takoito.energy_crystallizer_1710.EnergyCrystallizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TECrystallizer extends TileEntity implements ISidedInventory, IEnergyReceiver {
    public String name = "container."+ EnergyCrystallizer.MODID+".crystallizer";
    public ItemStack[] itemslot = new ItemStack[2];
    public EnergyStorage storage = new EnergyStorage(1073741824);

    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
    }

    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord,this.yCoord,this.zCoord,2,nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        int[] tempint =new int[itemslot.length];
        for (int i=0;i<2;i++){
            tempint[i]=i;
        }
        return tempint;
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return this.isItemValidForSlot(p_102007_1_,p_102007_2_);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_3_==1;
    }

    @Override
    public int getSizeInventory() {
        return itemslot.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        if (slotIn<this.getSizeInventory()){
            return itemslot[slotIn];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public String getInventoryName() {
        return this.name;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord+0.5D,yCoord+0.5D,zCoord+0.5D) <= 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index==0;
    }

    @Override
    public int receiveEnergy(ForgeDirection forgeDirection, int i, boolean b) {
        return this.storage.receiveEnergy(i,b);
    }

    @Override
    public int getEnergyStored(ForgeDirection forgeDirection) {
        return this.storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection forgeDirection) {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return true;
    }

    @Override
    public void updateEntity(){
        super.updateEntity();
    }

    /*public int getMetadata(){
        return this.worldObj.getBlockMetadata(xCoord,yCoord,zCoord);
    }*/
}
