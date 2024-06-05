
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.midnightlurker.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.midnightlurker.client.renderer.VoidHandsRenderer;
import net.mcreator.midnightlurker.client.renderer.VoidGatewayRenderer;
import net.mcreator.midnightlurker.client.renderer.VillagerOutcastRenderer;
import net.mcreator.midnightlurker.client.renderer.SpookyambienceentityRenderer;
import net.mcreator.midnightlurker.client.renderer.ShapeshifterPigRenderer;
import net.mcreator.midnightlurker.client.renderer.ShapeShifterCowRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightlurkerNERenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightPhantomHeadRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkertposeRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerWatcherRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerUnprovokedRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerStareRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerStalkingRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerShapeshifterRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerShadowRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerShadowEyesRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerSeenAngressiveRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerRuntrueRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerRunawayRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerInvisibleRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerHiderRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerFakerWatcherRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerFakerRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerFakerAggroRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerCreepRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerBackturnedRenderer;
import net.mcreator.midnightlurker.client.renderer.MidnightLurkerAggressiveRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleStaticRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleShadowRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleLurkerFootstepsRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleFootstepsRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleCaveSoundsRenderer;
import net.mcreator.midnightlurker.client.renderer.InvisibleAnimalKillerRenderer;
import net.mcreator.midnightlurker.client.renderer.DestroytexRenderer;
import net.mcreator.midnightlurker.client.renderer.Destroytex4Renderer;
import net.mcreator.midnightlurker.client.renderer.Destroytex3Renderer;
import net.mcreator.midnightlurker.client.renderer.Destroytex2Renderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MidnightlurkerModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_AGGRESSIVE.get(), MidnightLurkerAggressiveRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKERTPOSE.get(), MidnightLurkertposeRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_STALKING.get(), MidnightLurkerStalkingRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_INVISIBLE.get(), MidnightLurkerInvisibleRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.SPOOKYAMBIENCEENTITY.get(), SpookyambienceentityRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_SEEN_ANGRESSIVE.get(), MidnightLurkerSeenAngressiveRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.DESTROYTEX.get(), DestroytexRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.DESTROYTEX_2.get(), Destroytex2Renderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.DESTROYTEX_3.get(), Destroytex3Renderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.DESTROYTEX_4.get(), Destroytex4Renderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_FAKER_AGGRO.get(), MidnightLurkerFakerAggroRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_FAKER.get(), MidnightLurkerFakerRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_FAKER_WATCHER.get(), MidnightLurkerFakerWatcherRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.VOID_GATEWAY.get(), VoidGatewayRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_BACKTURNED.get(), MidnightLurkerBackturnedRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_SHADOW_EYES.get(), MidnightLurkerShadowEyesRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_SHADOW.get(), MidnightLurkerShadowRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_UNPROVOKED.get(), MidnightLurkerUnprovokedRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_RUNAWAY.get(), MidnightLurkerRunawayRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_RUNTRUE.get(), MidnightLurkerRuntrueRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_HIDER.get(), MidnightLurkerHiderRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_SHAPESHIFTER.get(), MidnightLurkerShapeshifterRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_STARE.get(), MidnightLurkerStareRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHTLURKER_NE.get(), MidnightlurkerNERenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_WATCHER.get(), MidnightLurkerWatcherRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.VOID_HANDS.get(), VoidHandsRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_FOOTSTEPS.get(), InvisibleFootstepsRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_SHADOW.get(), InvisibleShadowRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_STATIC.get(), InvisibleStaticRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_LURKER_FOOTSTEPS.get(), InvisibleLurkerFootstepsRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_CAVE_SOUNDS.get(), InvisibleCaveSoundsRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_LURKER_CREEP.get(), MidnightLurkerCreepRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.MIDNIGHT_PHANTOM_HEAD.get(), MidnightPhantomHeadRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.INVISIBLE_ANIMAL_KILLER.get(), InvisibleAnimalKillerRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.SHAPESHIFTER_PIG.get(), ShapeshifterPigRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.SHAPE_SHIFTER_COW.get(), ShapeShifterCowRenderer::new);
		event.registerEntityRenderer(MidnightlurkerModEntities.VILLAGER_OUTCAST.get(), VillagerOutcastRenderer::new);
	}
}
