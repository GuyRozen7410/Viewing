package me.guy.dbca.events;


import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


import cpw.mods.fml.common.gameevent.InputEvent;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;

import me.guy.dbca.animations.AnimationData;
import me.guy.dbca.animations.AnimationLoader;
import me.guy.dbca.gui.BurnoutGui;
import me.guy.dbca.gui.PlayerAndroidMenu;
import me.guy.dbca.keyhandler.KeyHandler;
import me.guy.dbca.particles.BloodParticle;
import me.guy.dbca.render.RenderPartsJBRA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import java.util.List;

import static JinRyuu.DragonBC.common.DBCClientTickHandler.nuller;


public class EventsHandler {

    private static AnimationData animationData;
    private static float HealingAmountDefault = 0.05f;
    private static double ReachAmountDefault = 6;
    private static float HealingAmount = 0.05f;
    private static double ReachAmount = 6d;

    private float TpUpMulty = 0.05f;

    private boolean HealingActive = false;
    private static boolean ReachActive = false;

    private static boolean BurnoutHealing = false;

    private float BurnoutTimer = 40;
    private static EntityPlayer entityPlayer;

    private boolean EnergySet = false;
    private int burnoutUp = 0;

    private int burnoutUpMulty = 5;
    private boolean burnoutUpActive = false;

    private boolean firstTp = false;

    private int tp = 0;
    public EventsHandler(){
        animationData = AnimationLoader.loadAnimationData("animations/Combo.json");

        if (animationData == null) {
            System.out.println("test2");
        }
    }

    public static boolean setBurnoutHealing(){
        if (BurnoutHealing){
            BurnoutHealing = false;
            return false;
        }else{
            return true;
        }


    }
    public static EntityPlayer getEntityPlayer(){
        return entityPlayer;
    }

    public static boolean getBurnoutHealing(){
        return BurnoutHealing;
    }
    public static AnimationData getAnimationData() {
        return animationData;
    }

    public static boolean start = false;

    private static boolean prevMouseState = false;

    private double lastDamage = 0;
    private Minecraft mc = Minecraft.getMinecraft();
    private int tickCounter = 0;
    private static void updateMouseInput() {
        boolean currentMouseState = Mouse.isButtonDown(0); // Check if left mouse button is down

        if (currentMouseState && !prevMouseState) {
            // Mouse button was just pressed
            System.out.println("Left mouse button clicked");

            // Example: Set a flag or trigger an action
            start = true;
            attack = true;
        } else if (!currentMouseState && prevMouseState) {
            // Mouse button was just released
            System.out.println("Left mouse button released");

            // Example: Reset flags or end an action triggered by press

            attack = false;
            ReachAmount = ReachAmountDefault;
            ReachActive = false;
        }

        prevMouseState = currentMouseState; // Update previous mouse state
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        entityPlayer = player;
        if (player.worldObj.isRemote){
            return;
        }
        updateMouseInput();
        if (EventsHandler.start){
            RenderPartsJBRA.animationTimer += 0.0165f;
        }

        if (ExtendedPlayer.PlayerData.getMilkUp() && !BurnoutGui.getBurnout()) {
            if (player.isPotionActive(Potion.harm) || player.isPotionActive(Potion.wither) || player.isPotionActive(Potion.weakness) || player.isPotionActive(Potion.poison) || player.isPotionActive(Potion.hunger) || player.isPotionActive(Potion.digSlowdown) || player.isPotionActive(Potion.moveSlowdown) || player.isPotionActive(Potion.confusion) || player.isPotionActive(Potion.blindness)) {
                // Create a list to store the potion effects to be removed
                List<PotionEffect> effectsToRemove = new ArrayList<>();

                // Iterate over the player's active potion effects
                for (Object effect : player.getActivePotionEffects()) {
                    if (effect instanceof PotionEffect) {
                        PotionEffect potionEffect = (PotionEffect) effect;
                        int potionId = potionEffect.getPotionID();

                        // Check if the potion effect should be removed
                        if (potionId == 15 || potionId == 9 || potionId == 4 || potionId == 7 || potionId == 2 || potionId == 17 || potionId == 18 || potionId == 20 || potionId == 19) {
                            // Add the potion effect to the list of effects to be removed
                            effectsToRemove.add(potionEffect);
                        }
                    }
                }

                // Remove the potion effects from the player
                for (PotionEffect effect : effectsToRemove) {
                    player.removePotionEffect(effect.getPotionID());
                }
            }
        }
        if (ExtendedPlayer.PlayerData.getInventory()[2] == null){
            burnoutUp = 0;
            BurnoutTimer = 40;
            burnoutUpActive = false;
        }
        if (ExtendedPlayer.PlayerData.getEffects() != null && !BurnoutGui.getBurnout()){

            if (ExtendedPlayer.PlayerData.getEffects().get("PP") != null){
                if (!ReachActive) {
                    ReachAmount = ReachAmount + ExtendedPlayer.PlayerData.getEffects().get("PP");
                    System.out.println(ReachAmount);
                    ReachActive = true;
                }
                Entity lookedEntity = getTargetEntity(player, ReachAmount);

                if (lookedEntity != null && attack) {
                    // Calculate the distance between player and the looked entity
                    double distance = player.getDistanceToEntity(lookedEntity);

                    // Define your custom reach distance (in blocks)
                     // Example reach distance

                    // Check if the entity is within custom reach
                    if (distance <= ReachAmount && distance >= 4.0d) {
                        // Apply damage to the entity (e.g., simulate a punch)
                        if (lastDamage == 0){
                            //lastDamage = JRMCoreH.getInt(player,  "jrmcLastDamageDealt");
                            lastDamage = player.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
                        }
                        lookedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float) lastDamage); //xample damage amount


                    }
                    lastDamage = 0;

                }



            }else{
                ReachAmount = ReachAmountDefault;
                ReachActive = false;
                lastDamage = 0;

            }
        }
        if (ExtendedPlayer.PlayerData.getEffects() != null && !BurnoutGui.getBurnout()){
            if (ExtendedPlayer.PlayerData.getEffects().get("Moss") != null){

                if (!HealingActive){
                    HealingAmount = HealingAmount * ExtendedPlayer.PlayerData.getEffects().get("Moss");
                    HealingActive = true;
                }
                if (event.phase == TickEvent.Phase.END && event.side.isServer()){
                    int healingRate = 20;
                    if (player.ticksExisted % healingRate == 0){
                        healPlayer(player);
                    }
                }

            }else{
                HealingAmount = HealingAmountDefault;
                HealingActive = false;
            }

        }else{
            HealingAmount = HealingAmountDefault;
            HealingActive = false;
        }

        if (BurnoutGui.getBurnout()){
            if (BurnoutGui.getBarWidth() >= 0){

                if (BurnoutTimer > 0){

                    if (event.phase == TickEvent.Phase.END){
                        BurnoutTimer--;

                    }
                }else{
                    if (burnoutUp == 0){
                        BurnoutTimer = 20;
                        BurnoutHealing = true;
                    }else{
                        BurnoutTimer = 20;
                        BurnoutTimer = (float) (BurnoutTimer - (burnoutUp * burnoutUpMulty));
                        BurnoutHealing = true;
                    }


                }
            }else{
                if (burnoutUp == 0){
                    BurnoutTimer = 40;
                }else{
                    BurnoutTimer = 40;
                    BurnoutTimer = (float) (BurnoutTimer - (burnoutUp *  burnoutUpMulty));
                }

            }
        }else{
            if (BurnoutGui.getBarWidth() > 0){
                if (BurnoutTimer > 0){
                    if (event.phase == TickEvent.Phase.END){
                        BurnoutTimer--;
                    }
                }else{

                    BurnoutHealing = true;
                    if (burnoutUp == 0){
                        BurnoutTimer = 40;
                    }else{
                        BurnoutTimer = 40;
                        BurnoutTimer = (float) (BurnoutTimer - (burnoutUp * burnoutUpMulty));

                    }

                }
            }
        }
        if (EnergySet && JRMCoreH.isShtng){
            JRMCoreH.setInt(curEn, entityPlayer, "jrmcEnrgy");
            System.out.println("Healed: " + curEn);
            EnergySet = false;
        }
        if (ExtendedPlayer.PlayerData.getEffects() != null && ExtendedPlayer.PlayerData.getEffects().get("Ice") != null){
            if (!burnoutUpActive){
                burnoutUp = ExtendedPlayer.PlayerData.getEffects().get("Ice");
                BurnoutTimer = (float) (BurnoutTimer - (burnoutUp * burnoutUpMulty));
                System.out.println("Timer: " + BurnoutTimer);
                burnoutUpActive = true;
            }
        }
        if (ExtendedPlayer.PlayerData.getEffects() != null && ExtendedPlayer.PlayerData.getEffects().get("NDB") != null){
            int tpNow = JRMCoreH.nbt(player, "pres").getInteger("jrmcTpint");
            if (tpNow < ExtendedPlayer.PlayerData.getTp()){
                tp = 0;
                ExtendedPlayer.PlayerData.setGetTp();
                System.out.println("11111");
                firstTp = false;
            }
            if (tp <= ExtendedPlayer.PlayerData.getTp() && !firstTp){

                if (tpNow > ExtendedPlayer.PlayerData.getTp()){

                    double bonusPercentage = ExtendedPlayer.PlayerData.getEffects().get("NDB") * TpUpMulty; // 5% per NDB
                    double leftover = tpNow - ExtendedPlayer.PlayerData.getTp();
// Calculate the total bonus to be added to tp
                    double bonus =  leftover + (leftover * bonusPercentage);

// Calculate the final value of tp after applying the bonus


// Calculate the total value to be added (including the original value and the bonus)


// Add the bonus to the total value
                    double result = ExtendedPlayer.PlayerData.getTp() + bonus;
                    System.out.println("result: "+ result);
                    JRMCoreH.nbt(player, "pres").setInteger("jrmcTpint", (int) result);
                    tp = (int) result;
                    ExtendedPlayer.PlayerData.setGetTp();
                    firstTp = true;
                }
            }else{
                if (tpNow > tp){

                    double bonusPercentage = ExtendedPlayer.PlayerData.getEffects().get("NDB") * TpUpMulty; // 5% per NDB
                    double leftover = tpNow - tp;
// Calculate the total bonus to be added to tp
                    double bonus = leftover + (leftover * bonusPercentage);

// Calculate the final value of tp after applying the bonus


// Calculate the total value to be added (including the original value and the bonus)


// Add the bonus to the total value
                    double result = tp + bonus;
                    System.out.println("result " + result);
                    JRMCoreH.nbt(player, "pres").setInteger("jrmcTpint", (int) result);
                    tp = (int) result;
                }
            }
        }else{
            tp = 0;
            firstTp = false;
        }
        if (event.phase == TickEvent.Phase.END) {
            if (entityPlayer != null && entityPlayer.isPotionActive(DBCA.bleedEffect.id)) {
                tickCounter++;
                if (tickCounter % 20 == 0) { // Spawn particles every second (20 ticks)
                    //spawnParticle(player.worldObj, player);
                    tickCounter = 0;
                }
            } else {
                tickCounter = 0;
            }

        }

    }

    public static Entity getTargetEntity(EntityPlayer player, double reachDistance) {
        // Player's eye position
        Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);

        // Player's look vector scaled to reach distance
        Vec3 lookVec = player.getLook(1.0F);
        Vec3 targetPos = playerPos.addVector(lookVec.xCoord * reachDistance, lookVec.yCoord * reachDistance, lookVec.zCoord * reachDistance);

        // Calculate search range around player's position
        double range = reachDistance + 1.0; // Extend range slightly beyond reach distance

        // Get list of entities within the search range
        AxisAlignedBB scanBounds = player.boundingBox.expand(range, range, range);
        List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, scanBounds);

        // Initialize variables to store closest entity found in line of sight
        Entity closestEntity = null;
        double closestDistanceSq = Double.MAX_VALUE;

        // Iterate through entities to find the closest living entity in line of sight
        for (Entity entity : entities) {
            if (entity instanceof EntityLivingBase && entity.isEntityAlive()) {
                EntityLivingBase livingEntity = (EntityLivingBase) entity;

                // Check if entity's bounding box intersects with the line of sight
                if (entity.boundingBox.calculateIntercept(playerPos, targetPos) != null) {
                    double distanceSq = player.getDistanceSqToEntity(livingEntity);

                    // Check if entity is within reach distance and closest so far
                    if (distanceSq < closestDistanceSq) {
                        closestEntity = livingEntity;
                        closestDistanceSq = distanceSq;
                    }
                }
            }
        }

        return closestEntity;
    }
    private static boolean attack = false;


    private int curEn = 0;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){

        if (KeyHandler.OpenGui.isPressed()){
            openGui();
        }

        if (KeyHandler.Ability1.isPressed()){
            if (ExtendedPlayer.PlayerData.getInventory()[2] != null){
                if (ExtendedPlayer.PlayerData.getInventory()[2].getItem() == DBCA.potatoCore){
                    if (JRMCoreH.tech1 != null){
                        if (!BurnoutGui.getBurnout()){

                            curEn = JRMCoreH.getInt(entityPlayer, "jrmcEnrgy");
                            if (curEn != 0){
                                System.out.println(curEn);
                                JRMCoreH.isShtng = true;
                                DBCKiTech.EnAt((byte)0, PlayerAndroidMenu.getSlot0Power());
                                nuller();
                                BurnoutGui.AddBarWidth();
                                EnergySet = true;

                            }

                        }


                    }
                }
            }
            //entityPlayer.addPotionEffect(new PotionEffect(DBCA.bleedEffect.id, 600, 1));


        }

    }


    public void spawnParticle(World world, EntityPlayer player) {
        if (world != null && player != null){
            for (int i = 0; i < 3; i++) { // Spawn 10 particles
                double motionX = world.rand.nextGaussian() * 0.1;
                double motionY = world.rand.nextGaussian() * 0.1;
                double motionZ = world.rand.nextGaussian() * 0.1;
                double posX = player.posX + world.rand.nextFloat() - 0.5;
                double posY = player.posY + world.rand.nextFloat() * player.height;
                double posZ = player.posZ + world.rand.nextFloat() - 0.5;
                DBCA.proxy.spawnParticle(world, posX, posY, posZ, motionX, motionY, motionZ);


            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        EntityPlayer player = event.player;
        if (ExtendedPlayerEvents.getPlayerData() != null){
//            NBTTagCompound playerData = player.getEntityData().getCompoundTag("DBCAPR");
//            player.getEntityData().setTag("DBCAP", playerData.getTag("DBCAP"));
//            player.getEntityData().setBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName(), playerData.getBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName()));
            player.getEntityData().setTag("DBCAP", ExtendedPlayerEvents.getPlayerData().getTag("DBCAP"));
            player.getEntityData().setBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName(), ExtendedPlayerEvents.getPlayerData().getBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName()));
            ExtendedPlayer.get(player, null).loadNBTData(ExtendedPlayerEvents.getPlayerData());

        }

    }



    // Method to spawn blood drop particles around the player





    @SideOnly(Side.CLIENT)
    private void openGui(){
        Minecraft.getMinecraft().displayGuiScreen(new PlayerAndroidMenu());
    }

    private void healPlayer(EntityPlayer player){


            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
            byte pwr = JRMCoreH.getByte(player, "jrmcPwrtyp");
            byte rce = JRMCoreH.getByte(player, "jrmcRace");
            byte cls = JRMCoreH.getByte(player, "jrmcClass");
            int maxBody = JRMCoreH.stat(player, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0F);
            int curBody = JRMCoreH.getInt(player, "jrmcBdy");
            JRMCoreH.setInt(HealingAmount == 0 ? maxBody : (curBody + HealingAmount > maxBody ? maxBody : curBody + (maxBody * HealingAmount)), player, "jrmcBdy");
            player.setHealth(player.getMaxHealth());
            System.out.println("Healed: " + HealingAmount);



    }


}
