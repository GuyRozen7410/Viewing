package me.guy.dbca.render;

import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Npcs.EntityDragon2;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class DragonEntity extends EntityCreature {
    public int randomSoundDelay = 0;
    private int time = 0;
    public int timeBack = 0;
    private boolean granted = false;
    protected Entity closestEntity;
    float var1 = 8.0F;
    private float maxDistanceForPlayer = 4.0F;
    private int lookTime;
    private Class watchedClass;
    private int jumpTicks = 0;

    public DragonEntity(World par1World) {
        super(par1World);
        this.experienceValue = 0;
        this.setAlwaysRenderNameTag(false);
        this.setSize(2.0F, 25.0F);
        this.width = 2.0F;
        this.height = 25.0F;
        this.boundingBox.maxX = this.boundingBox.minX + (double)this.width;
        this.boundingBox.maxZ = this.boundingBox.minZ + (double)this.width;
        this.boundingBox.maxY = this.boundingBox.minY + (double)this.height;
        this.tasks.addTask(0, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(0, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        boolean var3 = var2 != null;
        if (this.isEntityAlive()) {
            if (!this.worldObj.isRemote) {
                if (!this.granted) {
                    this.granted = true;
                    JRMCoreH.setInt(1, par1EntityPlayer, "jrmcWishes");
                    JRMCoreH.setInt(0, par1EntityPlayer, "jrmcDrgn");
                }
            } else if (!this.granted) {
                this.granted = true;
                par1EntityPlayer.openGui(mod_DragonBC.instance, 2, par1EntityPlayer.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
            }

            if (!par1EntityPlayer.worldObj.isRemote) {
                EntityDragon2 Dragon2 = new EntityDragon2(par1EntityPlayer.worldObj);
                Dragon2.setLocationAndAngles((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ), this.rotationYaw, 0.0F);
                Dragon2.timeBack = this.timeBack;
                par1EntityPlayer.worldObj.spawnEntityInWorld(Dragon2);
            }

            this.setDead();
            return true;
        } else {
            return super.interact(par1EntityPlayer);
        }
    }

    protected void updateAITasks() {
    }

    public void shouldExecute() {
        this.closestEntity = this.worldObj.getClosestPlayerToEntity(this, (double)this.maxDistanceForPlayer);
        if (this.closestEntity != null) {
            this.watchedClass = EntityPlayer.class;
            this.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + 2.0, this.closestEntity.posZ, 10.0F, (float)this.getVerticalFaceSpeed());
        }

    }

    public void onUpdate() {
        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
        }

        ++this.time;
        if (this.time == 1) {
            mod_DragonBC.logger.info("Shenron is Summoned!");
        }

        if (this.worldObj.isRemote) {
            DBCH.dragonSum(this);
        }

        float r = 30.0F;
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(this.posX - (double)r, this.posY - (double)r, this.posZ - (double)r, this.posX + (double)r, this.posY + (double)r, this.posZ + (double)r);
        List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
        if (list.size() == 0) {
            this.setDead();
        }

        super.onUpdate();
        this.shouldExecute();
    }

    @SideOnly(Side.CLIENT)
    public String getTexture() {
        return "dbca:textures/CDragon.png";
    }

    public boolean getCanSpawnHere() {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    public void onLivingUpdate() {
        if (this.jumpTicks > 0) {
            --this.jumpTicks;
        }

        if (this.newPosRotationIncrements > 0) {
            double d0 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
            double d1 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
            double d2 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
            double d3 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.newPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
            --this.newPosRotationIncrements;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        } else if (!this.isClientWorld()) {
            this.motionX *= 0.98;
            this.motionY *= 0.98;
            this.motionZ *= 0.98;
        }

        if (Math.abs(this.motionX) < 0.005) {
            this.motionX = 0.0;
        }

        if (Math.abs(this.motionY) < 0.005) {
            this.motionY = 0.0;
        }

        if (Math.abs(this.motionZ) < 0.005) {
            this.motionZ = 0.0;
        }

        this.worldObj.theProfiler.startSection("ai");
        if (this.isMovementBlocked()) {
            this.isJumping = false;
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
            this.randomYawVelocity = 0.0F;
        } else if (this.isClientWorld()) {
            if (this.isAIEnabled()) {
                this.worldObj.theProfiler.startSection("newAi");
                this.updateAITasks();
                this.worldObj.theProfiler.endSection();
            } else {
                this.worldObj.theProfiler.startSection("oldAi");
                this.worldObj.theProfiler.endSection();
                this.rotationYawHead = this.rotationYaw;
            }
        }

        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("jump");
        if (this.isJumping) {
            if (!this.isInWater() && !this.handleLavaMovement()) {
                if (this.onGround && this.jumpTicks == 0) {
                    this.jump();
                    this.jumpTicks = 10;
                }
            } else {
                this.motionY += 0.03999999910593033;
            }
        } else {
            this.jumpTicks = 0;
        }

        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("travel");
        this.moveStrafing *= 0.98F;
        this.moveForward *= 0.98F;
        this.randomYawVelocity *= 0.9F;
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("push");
        if (!this.worldObj.isRemote) {
            this.collideWithNearbyEntities();
        }

        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("looting");
        if (!this.worldObj.isRemote && this.canPickUpLoot() && !this.dead && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
        }

        this.worldObj.theProfiler.endSection();
        EntityPlayer var2 = this.worldObj.getClosestPlayerToEntity(this, (double)this.var1);
        if (var2 != null) {
            this.closestEntity = var2;
            this.numTicksToChaseTarget = 10 + this.rand.nextInt(20);
        } else {
            this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 20.0F;
        }

        if (this.closestEntity != null) {
            this.faceEntity(this.closestEntity, 10.0F, (float)this.getVerticalFaceSpeed());
            if (this.numTicksToChaseTarget-- <= 0 || this.closestEntity.isDead || this.closestEntity.getDistanceSqToEntity(this) > (double)(this.var1 * this.var1)) {
                this.closestEntity = null;
            }
        }

    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000.0);
    }
}
