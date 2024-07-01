package me.guy.dbca.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentGlowing extends Enchantment {
    public EnchantmentGlowing(int id, int weight) {
        super(id, weight, EnumEnchantmentType.all);
        this.setName("glow");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 1;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }



    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
}