package com.takoito.energy_crystallizer_1710.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static com.takoito.energy_crystallizer_1710.CommonProxy.crystals;

public class CrystallizerRecipes {

    public static final CrystallizerRecipes crystallize_base = new CrystallizerRecipes();

    private Map crystallizingList = new HashMap<>();
    private Map energyList = new HashMap<>();

    private int crystalEnergy = 1;

    public static CrystallizerRecipes crystallize(){
        return crystallize_base;
    }

    private CrystallizerRecipes(){
        for (int i = 0; i<31; i++){
            addCrystallizing(crystals.get(i), new ItemStack(crystals.get(i+1)), crystalEnergy);
            if (i < 30){
                crystalEnergy *= 2;
            }
        }
    }

    public void addCrystallizing(Item material, ItemStack result, int requiredEnergy){
        addCrystallizingRecipe(new ItemStack(material, 1, 32767), result, requiredEnergy);
    }

    public void addCrystallizingRecipe(ItemStack material, ItemStack result, int requireEnergy){
        this.crystallizingList.put(material, result);
        this.energyList.put(material, requireEnergy);
    }

    public ItemStack getCrystallizingResult(ItemStack material){
        Iterator iterator = this.crystallizingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }while (!this.compareItemStacks(material, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    public int getCrystallizingEnergy(ItemStack material){
        Iterator iterator = this.energyList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()){
                return 0;
            }

            entry = (Map.Entry)iterator.next();
        }while (!this.compareItemStacks(material, (ItemStack) entry.getKey()));

        return (int) entry.getValue();
    }

    private boolean compareItemStacks(ItemStack a, ItemStack b){
        return b.getItem() == a.getItem()&&(b.getItemDamage()==32767||a.getItemDamage()== b.getItemDamage());
    }
}
