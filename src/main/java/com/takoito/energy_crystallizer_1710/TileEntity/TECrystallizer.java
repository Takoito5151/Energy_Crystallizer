package com.takoito.energy_crystallizer_1710.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.takoito.energy_crystallizer_1710.EnergyCrystallizer;
import com.takoito.energy_crystallizer_1710.recipes.CrystallizerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TECrystallizer extends TileEntity implements ISidedInventory, IEnergyReceiver {
    public String name = "container."+ EnergyCrystallizer.MODID+".crystallizer";
    public ItemStack[] itemslot = new ItemStack[2];
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    public int processTime;

    public EnergyStorage storage = new EnergyStorage(1073741824);

    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        NBTTagList nbtTagList = tag.getTagList("Items", 10);
        this.itemslot = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound compound = nbtTagList.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0&& b < this.itemslot.length){
                this.itemslot[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.processTime = tag.getShort("ProcessTime");

        this.storage.setEnergyStored(tag.getInteger("StoredEnergy"));
    }

    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setShort("ProcessTime" , (short)this.processTime);
        NBTTagList nbtTagList = new NBTTagList();

        for (int i = 0; i < this.itemslot.length; i++) {
            if (itemslot[i]!=null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.itemslot[i].writeToNBT(compound);
                nbtTagList.appendTag(compound);
            }
        }

        tag.setInteger("StoredEnergy", this.storage.getEnergyStored());
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
        return p_102008_3_==OUTPUT_SLOT;
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
        this.itemslot[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
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
        return index == INPUT_SLOT;
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

    private boolean canProcess(){
        if (itemslot[0]==null){
            return false;
        }else {
            ItemStack itemStack = CrystallizerRecipes.crystallize().getCrystallizingResult(this.itemslot[0]);
            int requiredEnergy = CrystallizerRecipes.crystallize().getCrystallizingEnergy(this.itemslot[0]);
            if (itemStack == null) return false;
            if (this.itemslot[1]==null) return true;
            if (!this.itemslot[1].isItemEqual(itemStack)) return false;
            if (this.storage.getEnergyStored()<requiredEnergy) return false;
            int result = itemslot[1].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.itemslot[1].getMaxStackSize();
        }
    }

    public void processItem(){
        if (this.canProcess()){
            ItemStack itemStack = CrystallizerRecipes.crystallize().getCrystallizingResult(itemslot[0]);
            int requiredEnergy = CrystallizerRecipes.crystallize().getCrystallizingEnergy(itemslot[0]);
            if (this.itemslot[1]==null){
                this.itemslot[1] = itemStack.copy();
            } else if (this.itemslot[1].getItem() == itemStack.getItem()) {
                this.itemslot[1].stackSize += itemStack.stackSize;
            }

            --this.itemslot[0].stackSize;
            this.storage.extractEnergy(requiredEnergy, false);
            if (this.itemslot[0].stackSize<=0){
                this.itemslot[0] = null;
            }
        }
    }

    @Override
    public void updateEntity(){
        if(!worldObj.isRemote){
            /* ここには処理を記録するよ
            * レシピの実装が楽になるように設定が必要*/
            if (this.canProcess()){
                ++this.processTime;

                if (this.processTime == 60){
                    this.processTime = 0;
                    this.processItem();
                }
            }
        }
    }

    /*public int getMetadata(){
        return this.worldObj.getBlockMetadata(xCoord,yCoord,zCoord);
    }*/
}
