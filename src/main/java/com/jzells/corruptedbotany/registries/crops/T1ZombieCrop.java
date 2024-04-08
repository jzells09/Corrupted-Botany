package com.jzells.corruptedbotany.registries.crops;

import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import com.jzells.corruptedbotany.registries.EntityRegistries;
import com.jzells.corruptedbotany.registries.Registries;
import com.jzells.corruptedbotany.registries.crops.util.CorruptedCrop;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class T1ZombieCrop extends CorruptedCrop {


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        Tier1ZombieEntity zombieEntity = new Tier1ZombieEntity(EntityRegistries.TIER_1_ZOMBIE.get(), level);
        zombieEntity.setPos(Vec3.atCenterOf(pos));
        if(willHarvest){
            level.addFreshEntity(zombieEntity);
        }
        return super.onDestroyedByPlayer(state,level,pos,player,willHarvest,fluid);
    }

    public T1ZombieCrop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return Registries.T1ZSEED.get();
    }


}


