package eu.midnightdust.cullleaves.mixin;

import eu.midnightdust.cullleaves.config.CullLeavesConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LeavesBlock.class, priority = 1900)
@Environment(EnvType.CLIENT)
public abstract class MixinLeavesBlock extends Block {

    public MixinLeavesBlock(Settings Silian_settings) {
        super(Silian_settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState Silian_state, BlockState Silian_neighborState, Direction Silian_offset) {
        if (CullLeavesConfig.enabled) {
            return Silian_neighborState.getBlock() instanceof LeavesBlock;
        }
        else return false;
    }
}