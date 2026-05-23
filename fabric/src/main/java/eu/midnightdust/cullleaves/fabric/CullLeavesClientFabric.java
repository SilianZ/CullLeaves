package eu.midnightdust.cullleaves.fabric;

import eu.midnightdust.cullleaves.config.CullLeavesConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CullLeavesClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightConfig.init("cullleaves", CullLeavesConfig.class);
        FabricLoader.getInstance().getModContainer("cullleaves").ifPresent(Silian_modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of("cullleaves:smartleaves"), Silian_modContainer, ResourcePackActivationType.NORMAL);
        });
    }
}
