package me.guy.dbca.potions;

import JinRyuu.JRMCore.JRMCoreH;
;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;


public class BleedEffect extends Potion {


    private float damage = 1.0f;




    public BleedEffect(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
        super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
        this.setPotionName("Bleed");
        this.setIconIndex(0,0);


    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("dbca","textures/gui/Icons.png"));
        return true;
    }



    @Override
    public boolean isBadEffect() {
        return true;
    }

    @Override
    public double getEffectiveness() {
        return super.getEffectiveness();
    }

    @Override
    protected Potion setEffectiveness(double p_76404_1_) {
        return super.setEffectiveness(p_76404_1_);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        // Apply effect every tick (20 ticks per second)
        int tickInterval = 20; // Apply damage every second
        return duration % tickInterval == 0;
    }

    @Override
    public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_) {
            if (!p_76394_1_.worldObj.isRemote){

                    if (p_76394_1_ instanceof EntityPlayer){

                            if (JRMCoreH.nbt(p_76394_1_) != null){
                                damage = ((float)JRMCoreH.maxBody / ((float) 20 / p_76394_2_));
                            }else{
                                damage = (p_76394_1_.getMaxHealth()/ ((float) 20 / p_76394_2_));
                            }



                    }else{

                        damage = (p_76394_1_.getMaxHealth()/ ((float) 20 / p_76394_2_));
                    }
                    System.out.println(damage);






                p_76394_1_.attackEntityFrom(DamageSource.causeMobDamage(p_76394_1_), damage);

            }






            //super.performEffect(p_76394_1_, p_76394_2_);


    }
}
