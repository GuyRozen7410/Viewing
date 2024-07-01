package me.guy.dbca.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.gui.PartsContainer;
import me.guy.dbca.gui.PartsContainerGui;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PotatoCoreItem extends Item{

    public PotatoCoreItem(){

        super();
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("potato_core");
        this.setTextureName("dbca:potato_core");
        this.maxStackSize = 1;
    }


    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        this.itemIcon = p_94581_1_.registerIcon("dbca:potato_core");
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(EnumChatFormatting.GOLD + "Main Modification Part");
        if (p_77624_1_.hasTagCompound() && p_77624_1_.getTagCompound().hasKey("Effects")){

            p_77624_3_.add(EnumChatFormatting.BLUE + "(Modified)");

        }

    }


    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    

}
