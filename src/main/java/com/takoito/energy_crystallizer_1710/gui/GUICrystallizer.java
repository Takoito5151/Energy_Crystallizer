package com.takoito.energy_crystallizer_1710.gui;

import com.takoito.energy_crystallizer_1710.EnergyCrystallizer;
import com.takoito.energy_crystallizer_1710.TileEntity.TECrystallizer;
import com.takoito.energy_crystallizer_1710.container.ContainerCrystallizer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GUICrystallizer extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation(EnergyCrystallizer.MODID, "textures/gui/crystallizer.png");
    private TECrystallizer tile;

    public GUICrystallizer(InventoryPlayer player, TECrystallizer tile){
        super(new ContainerCrystallizer(player, tile));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(texture);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        fontRendererObj.drawString(StatCollector.translateToLocal(tile.getInventoryName()),8,6,4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }
}
