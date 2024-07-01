package me.guy.dbca.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class TableMaterial extends Material {
    public static final Material table;
    public TableMaterial() {
        super(MapColor.clayColor);
    }

    @Override
    public boolean isOpaque() {
        return false; // Indicate that the material is opaque (i.e., not transparent)
    }

    @Override
    public boolean isSolid() {
        return true; // Indicate that the material is solid
    }

    @Override
    public boolean isLiquid() {
        return false; // Indicate that the material is not a liquid
    }

    @Override
    public boolean isReplaceable() {
        return false; // Indicate that the material is not replaceable (i.e., it can't be replaced by air)
    }

    static {
        table = new TableMaterial();
    }
}
