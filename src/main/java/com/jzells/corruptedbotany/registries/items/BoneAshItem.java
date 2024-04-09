package com.jzells.corruptedbotany.registries.items;

import com.jzells.corruptedbotany.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class BoneAshItem extends Item {
    public BoneAshItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        ItemEntity item = new ItemEntity(level, context.getPlayer().getX(), context.getPlayer().getY(), context.getPlayer().getZ(), new ItemStack(Registries.T0ESSENCE.get()));
        ItemStack itemStack = player.getOffhandItem();
        if(player.getOffhandItem().is(Items.NETHER_WART)){
            stack.setCount(stack.getCount() - 1);
            itemStack.setCount(itemStack.getCount()-1);
            level.addFreshEntity(item);
            level.playLocalSound(context.getPlayer().getX(), context.getPlayer().getY(), context.getPlayer().getZ(), SoundEvents.BLAZE_DEATH, SoundSource.BLOCKS, .2f, 2f, true);
            player.hurt(player.damageSources().magic(),2.5f);
        }
        return super.onItemUseFirst(stack, context);
    }
}
