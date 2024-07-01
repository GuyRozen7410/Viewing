package me.guy.dbca.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PartsTableItem extends Item {
    public PartsTableItem(){
        super();
        this.setUnlocalizedName("parts_table_item");
        this.setTextureName("dbca:parts_item_texture");
        this.maxStackSize = 1;
    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_) {
        this.itemIcon = p_94581_1_.registerIcon("dbca:parts_item_texture");
    }



    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
