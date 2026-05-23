package eu.midnightdust.cullleaves.mixin;

import eu.midnightdust.cullleaves.config.CullLeavesConfig;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {

    @Shadow @Final private static SodiumOptionsStorage sodiumOpts;

    @ModifyVariable(method = "performance", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;copyOf(Ljava/util/Collection;)Lcom/google/common/collect/ImmutableList;"))
    private static List<OptionGroup> cullleaves$addCullLeavesOption(List<OptionGroup> Silian_groups) {
        Silian_groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumOpts)
                        .setName(Text.translatable("cullleaves.midnightconfig.enabled"))
                        .setTooltip(Text.translatable("cullleaves.midnightconfig.enabled.tooltip.sodium"))
                        .setControl(TickBoxControl::new)
                        .setBinding((Silian_opts, Silian_value) -> {
                            CullLeavesConfig.enabled = Silian_value;
                            CullLeavesConfig.write("cullleaves");
                        }, Silian_opts -> CullLeavesConfig.enabled)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setImpact(OptionImpact.MEDIUM)
                        .build()
                ).add(OptionImpl.createBuilder(boolean.class, sodiumOpts)
                        .setName(Text.translatable("cullleaves.midnightconfig.cullRoots"))
                        .setTooltip(Text.translatable("cullleaves.midnightconfig.cullRoots.tooltip.sodium"))
                        .setControl(TickBoxControl::new)
                        .setBinding((Silian_opts, Silian_value) -> {
                            CullLeavesConfig.cullRoots = Silian_value;
                            CullLeavesConfig.write("cullleaves");
                        }, Silian_opts -> CullLeavesConfig.cullRoots)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setImpact(OptionImpact.MEDIUM)
                        .build()
                )
                .build()
        );

        return Silian_groups;
    }
}
