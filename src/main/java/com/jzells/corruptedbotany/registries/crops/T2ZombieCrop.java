package com.jzells.corruptedbotany.registries.crops;

import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import com.jzells.corruptedbotany.entities.Tier2ZombieEntity;
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

public class T2ZombieCrop extends CorruptedCrop {


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        Tier2ZombieEntity zombieEntity = new Tier2ZombieEntity(EntityRegistries.TIER_2_ZOMBIE.get(), level);

        if(this.isMaxAge(state) && willHarvest){
            zombieEntity.setPos(Vec3.atCenterOf(pos));
            level.addFreshEntity(zombieEntity);
        }
        return super.onDestroyedByPlayer(state,level,pos,player,willHarvest,fluid);
    }

    public T2ZombieCrop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return Registries.T2ZSEED.get();
    }


}


