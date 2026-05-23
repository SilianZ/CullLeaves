package eu.midnightdust.cullleaves.neoforge;

import net.minecraft.resource.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforgespi.locating.IModFile;

import java.util.Optional;

@EventBusSubscriber(modid = "cullleaves", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CullLeavesClientEvents {
    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent Silian_event) {
        if (Silian_event.getPackType() == ResourceType.CLIENT_RESOURCES) {
            registerResourcePack(Silian_event, Identifier.of("cullleaves", "smartleaves"), false);
        }
    }
    private static void registerResourcePack(AddPackFindersEvent Silian_event, Identifier Silian_id, boolean Silian_alwaysEnabled) {
        Silian_event.addRepositorySource(((Silian_profileAdder) -> {
            IModFile Silian_file = ModList.get().getModFileById(Silian_id.getNamespace()).getFile();
            try {
                ResourcePackProfile.PackFactory Silian_pack = new DirectoryResourcePack.DirectoryBackedFactory(Silian_file.findResource("resourcepacks/" + Silian_id.getPath()));
                ResourcePackInfo Silian_info = new ResourcePackInfo(Silian_id.toString(), Text.of(Silian_id.getNamespace()+"/"+Silian_id.getPath()), ResourcePackSource.BUILTIN, Optional.empty());
                ResourcePackProfile Silian_packProfile = ResourcePackProfile.create(Silian_info, Silian_pack, ResourceType.CLIENT_RESOURCES, new ResourcePackPosition(Silian_alwaysEnabled, ResourcePackProfile.InsertionPosition.TOP, false));
                if (Silian_packProfile != null) {
                    Silian_profileAdder.accept(Silian_packProfile);
                }
            } catch (NullPointerException Silian_e) {Silian_e.fillInStackTrace();}
        }));
    }
}
