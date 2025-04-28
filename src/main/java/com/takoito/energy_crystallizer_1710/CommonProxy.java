package com.takoito.energy_crystallizer_1710;

import com.takoito.energy_crystallizer_1710.Tabs.TabCrystals;
import com.takoito.energy_crystallizer_1710.blocks.CrystalBlock;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CommonProxy {
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    //Tabs
    public static CreativeTabs tabCrystal = new TabCrystals("Crystals");

    public static Item testitem;

    public static List<Item> crystals = new ArrayList<>();

    public static List<Block> crystals_block = new ArrayList<>();



    //crystals
    /*
    public static Item crystal_0;
    public static Item crystal_1;
    public static Item crystal_2;
    public static Item crystal_3;
    public static Item crystal_4;
    public static Item crystal_5;
    public static Item crystal_6;
    public static Item crystal_7;
    public static Item crystal_8;
    public static Item crystal_9;
    public static Item crystal_10;
    public static Item crystal_11;
    public static Item crystal_12;
    public static Item crystal_13;
    public static Item crystal_14;
    public static Item crystal_15;
    public static Item crystal_16;
    public static Item crystal_17;
    public static Item crystal_18;
    public static Item crystal_19;
    public static Item crystal_20;
    public static Item crystal_21;
    public static Item crystal_22;
    public static Item crystal_23;
    public static Item crystal_24;
    public static Item crystal_25;
    public static Item crystal_26;
    public static Item crystal_27;
    public static Item crystal_28;
    public static Item crystal_29;
    public static Item crystal_30;
    public static Item crystal_31;
    */

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        EnergyCrystallizer.LOG.info(Config.greeting);
        EnergyCrystallizer.LOG.info("I am EnergyCrystallizer at version " + Tags.VERSION);

        testitem = new Item()
            .setCreativeTab(CreativeTabs.tabMisc)
            .setUnlocalizedName("testitem");
        GameRegistry.registerItem(testitem,"testitem");
        for(int i =0; i <256;i++){
            crystals.add(
                new ItemEnergyCrystal(i)
            );
            crystals_block.add(
                new CrystalBlock(Material.rock,i)
            );
            GameRegistry.registerItem(crystals.get(i),"energy_crystal_"+i);
            GameRegistry.registerBlock(crystals_block.get(i),"energy_crystal_block_"+i);
        }
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        for (int ir =0; ir <256;ir++){
            Block block_temp = CommonProxy.crystals_block.get(ir);
            Item item_temp = CommonProxy.crystals.get(ir);
            GameRegistry.addRecipe(
                new ItemStack(block_temp),
                "XXX", "XXX", "XXX",
                'X',item_temp
            );
        }
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
