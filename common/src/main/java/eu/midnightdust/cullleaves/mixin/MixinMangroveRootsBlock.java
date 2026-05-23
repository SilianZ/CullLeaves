package eu.midnightdust.cullleaves.mixin;

import eu.midnightdust.cullleaves.config.CullLeavesConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MangroveRootsBlock.class, priority = 1900)
@Environment(EnvType.CLIENT)
public abstract class MixinMangroveRootsBlock extends Block {

    public MixinMangroveRootsBlock(Settings Silian_settings) {
        super(Silian_settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState Silian_state, BlockState Silian_neighborState, Direction Silian_offset) {
        if (CullLeavesConfig.cullRoots) {
            return Silian_neighborState.getBlock() instanceof MangroveRootsBlock;
        }
        else return false;
    }
}