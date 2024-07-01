package me.guy.dbca.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class PotatoLimbItem extends Item {

    public PotatoLimbItem (){
        super();
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("potato_limb");
        this.setTextureName("dbca:potato_limb");
        this.maxStackSize = 1;
    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        this.itemIcon = p_94581_1_.registerIcon("dbca:potato_limb");
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(EnumChatFormatting.GOLD + "Required: Any Core");
        if (p_77624_1_.hasTagCompound() && p_77624_1_.getTagCompound().hasKey("Effects")){

            p_77624_3_.add(EnumChatFormatting.BLUE + "(Modified)");

        }

    }


    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
