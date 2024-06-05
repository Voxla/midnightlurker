
package net.mcreator.midnightlurker.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.VillagerModel;

import net.mcreator.midnightlurker.entity.VillagerOutcastEntity;

import com.mojang.blaze3d.vertex.PoseStack;

public class VillagerOutcastRenderer extends MobRenderer<VillagerOutcastEntity, VillagerModel<VillagerOutcastEntity>> {
	public VillagerOutcastRenderer(EntityRendererProvider.Context context) {
		super(context, new VillagerModel(context.bakeLayer(ModelLayers.VILLAGER)), 0.5f);
	}

	@Override
	protected void scale(VillagerOutcastEntity villager, PoseStack poseStack, float f) {
		poseStack.scale(0.9375f, 0.9375f, 0.9375f);
	}

	@Override
	public ResourceLocation getTextureLocation(VillagerOutcastEntity entity) {
		return new ResourceLocation("midnightlurker:textures/entities/outcast_villager.png");
	}
}
