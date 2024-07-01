package me.guy.dbca;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import me.guy.dbca.blocks.*;
import me.guy.dbca.events.EventsHandler;

import me.guy.dbca.events.ExtendedPlayerEvents;
import me.guy.dbca.gui.AndroidGuiHandler;
import me.guy.dbca.gui.BurnoutGui;
import me.guy.dbca.gui.PartsContainer;
import me.guy.dbca.handlers.ConfigHandler;
import me.guy.dbca.items.*;
import me.guy.dbca.network.PacketHandler;
import me.guy.dbca.potions.BleedEffect;
import me.guy.dbca.proxy.CommonProxy;

import me.guy.dbca.reference.Reference;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import me.guy.dbca.render.DragonEntity;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;


@Mod(modid = Reference.MOD_ID, version = Reference.MOD_VERISON)
public class DBCA
{

    public static Block androidTable;
    public static Block partsTable;
    public static Item potatoCore, potatoLimb;
    public static Block AirTable;
    public static Block dragonBlock;
    public static Item androidTableItem;

    public static Item partsTableItem;

    public static Block partsTableAir;
    public static final Enchantment ENCHANTMENT_GLOWING = new EnchantmentGlowing(101, 5);

    public static Potion bleedEffect;



    @Mod.Instance(Reference.MOD_ID)
    public static DBCA instance = new DBCA();

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    public DBCA(){

        //MinecraftForge.EVENT_BUS.register(new EventsHandler());

    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        String configpath = event.getModConfigurationDirectory().toString();

        ConfigHandler.Init(configpath);
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        PacketHandler.registerPackets();

        //FMLCommonHandler.instance().bus().register(new ExtendedPlayerEvents());
        dragonBlock = new DragonBlock01Custom().setBlockName("dragon_block_custom").setBlockTextureName("jinryuudragonbc:tile." + "BlockDragonBall");
        GameRegistry.registerBlock(DBCA.dragonBlock, "dragon_block_custom");

        DBCA.androidTable = new AndroidTable().setBlockName("android_table").setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(DBCA.androidTable, "android_table");

        DBCA.potatoCore = new PotatoCoreItem();
        GameRegistry.registerItem(DBCA.potatoCore, "potato_core");

        potatoLimb = new PotatoLimbItem();
        GameRegistry.registerItem(potatoLimb, "potato_limb");

        AirTable = new TableFillBlock().setBlockName("air_table");
        GameRegistry.registerBlock(AirTable, "air_table");

        androidTableItem = new AndroidTableItem();
        GameRegistry.registerItem(androidTableItem, "android_table_item");

        partsTable = new ModificationPartBuilder().setBlockName("modification_part_builder").setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(partsTable,"modification_part_builder");


        partsTableItem = new PartsTableItem();
        GameRegistry.registerItem(partsTableItem, "parts_table_item");


        partsTableAir = new PartsTableFill().setBlockName("air_parts_table");
        GameRegistry.registerBlock(partsTableAir, "air_parts_table");

        Enchantment.addToBookList(ENCHANTMENT_GLOWING);
        PartsContainer.init();
        bleedEffect = new BleedEffect(27, true, 0xFF0000);
        proxy.registerKeyBindings();
        proxy.registerTiles();
        proxy.preInit();


    }



    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        //network.registerMessage(OpenGuiMsgHandler.class, OpenGuiMessage.class, 0, Side.CLIENT);
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new AndroidGuiHandler());
		// some example code
        MinecraftForge.EVENT_BUS.register(new ExtendedPlayerEvents());
        FMLCommonHandler.instance().bus().register(new EventsHandler());
        MinecraftForge.EVENT_BUS.register(new BurnoutGui(Minecraft.getMinecraft()));
        EntityRegistry.registerModEntity(DragonEntity.class, "CustomDragon", 0, this, 80, 5, true);
        NetworkRegistry.INSTANCE.registerGuiHandler(DBCA.instance, new AndroidGuiHandler());

        Potion.potionTypes[bleedEffect.getId()] = bleedEffect;

        proxy.registerEntity();
        proxy.Init();


    }


}
