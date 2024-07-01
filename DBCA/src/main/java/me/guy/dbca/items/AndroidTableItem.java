package me.guy.dbca.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class AndroidTableItem extends Item {
    public AndroidTableItem(){
        super();
        this.setUnlocalizedName("android_table_item");
        this.setTextureName("dbca:modification_chamber");
        this.maxStackSize = 1;
    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        this.itemIcon = p_94581_1_.registerIcon("dbca:modification_chamber");
    }



    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
