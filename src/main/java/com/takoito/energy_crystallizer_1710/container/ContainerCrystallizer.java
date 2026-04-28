package com.takoito.energy_crystallizer_1710.container;

import com.takoito.energy_crystallizer_1710.TileEntity.TECrystallizer;
import com.takoito.energy_crystallizer_1710.recipes.CrystallizerRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrystallizer extends Container {
    private TECrystallizer tile;
    private int lastProcessTime;

    public ContainerCrystallizer(InventoryPlayer player, TECrystallizer tile){
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 35));
        this.addSlotToContainer(new Slot(tile, 1, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting p_75132_1_) {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 0, this.tile.processTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i=0; i<this.crafters.size(); i++){
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if (this.lastProcessTime!=this.tile.processTime){
                iCrafting.sendProgressBarUpdate(this, 0, this.tile.processTime);
            }
        }

        this.lastProcessTime = this.tile.processTime;
    }

    //    @SideOnly(Side.CLIENT)
//    public void updateProgressBar(int time){
//
//    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index){
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemStack = itemstack1.copy();
            if (index == 1)
            {
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemStack);
            }else if (index != 0){
                if (CrystallizerRecipes.crystallize().getCrystallizingResult(itemstack1) != null){
                    if (!this.mergeItemStack(itemstack1, 0, 0, false))
                    {
                        return null;
                    }
                }else if (index >= 2 && index < 29)
                {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false))
                {
                    return null;
                }
            }else if (!this.mergeItemStack(itemstack1, 2, 38, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemStack.stackSize){
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemStack;
    }
}
