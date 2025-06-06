package mod.fossilcraft;

import java.util.Map;

import mod.fossilcraft.entity.DinosaurEntity;
import mod.fossilcraft.registry.ModEntities;
import mod.fossilcraft.render.DinosaurRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
public class FossilCraftModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (Map.Entry<String, EntityType<DinosaurEntity>> entry : ModEntities.TYPES.entrySet()) {
			String dinosaurId = entry.getKey();
			EntityType<DinosaurEntity> type = entry.getValue();

			EntityRendererRegistry.register(type, ctx -> new DinosaurRenderer<>(ctx, dinosaurId));
		}
	}
}