package com.tamehumans.gui;

import com.tamehumans.entity.EntityHumanBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCommandHuman extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("tamehumans:textures/gui/commandHuman.png");
    private EntityHumanBase human;
    private float field_147033_y;
    private float field_147032_z;

    public GuiCommandHuman(IInventory playerInventory, EntityHumanBase human) {
        super(new ContainerChest(playerInventory, human.inventory));
        this.human = human;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GuiInventory.func_147046_a(k + 51, l + 60, 17, (float) (k + 51) - this.field_147033_y, (float) (l + 75 - 50) - this.field_147032_z, this.human);
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.field_147033_y = (float)p_73863_1_;
        this.field_147032_z = (float)p_73863_2_;
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}
