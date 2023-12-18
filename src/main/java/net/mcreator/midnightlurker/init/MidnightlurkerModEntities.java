
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.midnightlurker.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.midnightlurker.entity.VoidGatewayEntity;
import net.mcreator.midnightlurker.entity.SpookyambienceentityEntity;
import net.mcreator.midnightlurker.entity.MidnightlurkerNEEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkertposeEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerUnprovokedEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerStareEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerStalkingEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerShapeshifterEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerShadowEyesEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerShadowEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerSeenAngressiveEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerRuntrueEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerRunawayEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerInvisibleEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerHiderEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerFakerWatcherEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerFakerEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerFakerAggroEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerBackturnedEntity;
import net.mcreator.midnightlurker.entity.MidnightLurkerAggressiveEntity;
import net.mcreator.midnightlurker.entity.DestroytexEntity;
import net.mcreator.midnightlurker.entity.Destroytex4Entity;
import net.mcreator.midnightlurker.entity.Destroytex3Entity;
import net.mcreator.midnightlurker.entity.Destroytex2Entity;
import net.mcreator.midnightlurker.MidnightlurkerMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MidnightlurkerModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MidnightlurkerMod.MODID);
	public static final RegistryObject<EntityType<MidnightLurkerAggressiveEntity>> MIDNIGHT_LURKER_AGGRESSIVE = register("midnight_lurker_aggressive",
			EntityType.Builder.<MidnightLurkerAggressiveEntity>of(MidnightLurkerAggressiveEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerAggressiveEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkertposeEntity>> MIDNIGHT_LURKERTPOSE = register("midnight_lurkertpose", EntityType.Builder.<MidnightLurkertposeEntity>of(MidnightLurkertposeEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkertposeEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerStalkingEntity>> MIDNIGHT_LURKER_STALKING = register("midnight_lurker_stalking",
			EntityType.Builder.<MidnightLurkerStalkingEntity>of(MidnightLurkerStalkingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerStalkingEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerInvisibleEntity>> MIDNIGHT_LURKER_INVISIBLE = register("midnight_lurker_invisible",
			EntityType.Builder.<MidnightLurkerInvisibleEntity>of(MidnightLurkerInvisibleEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerInvisibleEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<SpookyambienceentityEntity>> SPOOKYAMBIENCEENTITY = register("spookyambienceentity", EntityType.Builder.<SpookyambienceentityEntity>of(SpookyambienceentityEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SpookyambienceentityEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerSeenAngressiveEntity>> MIDNIGHT_LURKER_SEEN_ANGRESSIVE = register("midnight_lurker_seen_angressive",
			EntityType.Builder.<MidnightLurkerSeenAngressiveEntity>of(MidnightLurkerSeenAngressiveEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerSeenAngressiveEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<DestroytexEntity>> DESTROYTEX = register("destroytex", EntityType.Builder.<DestroytexEntity>of(DestroytexEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
			.setUpdateInterval(3).setCustomClientFactory(DestroytexEntity::new).fireImmune().sized(0.7f, 3f));
	public static final RegistryObject<EntityType<Destroytex2Entity>> DESTROYTEX_2 = register("destroytex_2", EntityType.Builder.<Destroytex2Entity>of(Destroytex2Entity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Destroytex2Entity::new).fireImmune().sized(0.7f, 3f));
	public static final RegistryObject<EntityType<Destroytex3Entity>> DESTROYTEX_3 = register("destroytex_3", EntityType.Builder.<Destroytex3Entity>of(Destroytex3Entity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Destroytex3Entity::new).fireImmune().sized(0.7f, 3f));
	public static final RegistryObject<EntityType<Destroytex4Entity>> DESTROYTEX_4 = register("destroytex_4", EntityType.Builder.<Destroytex4Entity>of(Destroytex4Entity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Destroytex4Entity::new).fireImmune().sized(0.7f, 3f));
	public static final RegistryObject<EntityType<MidnightLurkerFakerAggroEntity>> MIDNIGHT_LURKER_FAKER_AGGRO = register("midnight_lurker_faker_aggro",
			EntityType.Builder.<MidnightLurkerFakerAggroEntity>of(MidnightLurkerFakerAggroEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerFakerAggroEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerFakerEntity>> MIDNIGHT_LURKER_FAKER = register("midnight_lurker_faker", EntityType.Builder.<MidnightLurkerFakerEntity>of(MidnightLurkerFakerEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerFakerEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerFakerWatcherEntity>> MIDNIGHT_LURKER_FAKER_WATCHER = register("midnight_lurker_faker_watcher",
			EntityType.Builder.<MidnightLurkerFakerWatcherEntity>of(MidnightLurkerFakerWatcherEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerFakerWatcherEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<VoidGatewayEntity>> VOID_GATEWAY = register("void_gateway", EntityType.Builder.<VoidGatewayEntity>of(VoidGatewayEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(VoidGatewayEntity::new).fireImmune().sized(0.7f, 3f));
	public static final RegistryObject<EntityType<MidnightLurkerBackturnedEntity>> MIDNIGHT_LURKER_BACKTURNED = register("midnight_lurker_backturned",
			EntityType.Builder.<MidnightLurkerBackturnedEntity>of(MidnightLurkerBackturnedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerBackturnedEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerShadowEyesEntity>> MIDNIGHT_LURKER_SHADOW_EYES = register("midnight_lurker_shadow_eyes",
			EntityType.Builder.<MidnightLurkerShadowEyesEntity>of(MidnightLurkerShadowEyesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerShadowEyesEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerShadowEntity>> MIDNIGHT_LURKER_SHADOW = register("midnight_lurker_shadow", EntityType.Builder.<MidnightLurkerShadowEntity>of(MidnightLurkerShadowEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerShadowEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerUnprovokedEntity>> MIDNIGHT_LURKER_UNPROVOKED = register("midnight_lurker_unprovoked",
			EntityType.Builder.<MidnightLurkerUnprovokedEntity>of(MidnightLurkerUnprovokedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerUnprovokedEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerRunawayEntity>> MIDNIGHT_LURKER_RUNAWAY = register("midnight_lurker_runaway", EntityType.Builder.<MidnightLurkerRunawayEntity>of(MidnightLurkerRunawayEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerRunawayEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerRuntrueEntity>> MIDNIGHT_LURKER_RUNTRUE = register("midnight_lurker_runtrue", EntityType.Builder.<MidnightLurkerRuntrueEntity>of(MidnightLurkerRuntrueEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerRuntrueEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerHiderEntity>> MIDNIGHT_LURKER_HIDER = register("midnight_lurker_hider", EntityType.Builder.<MidnightLurkerHiderEntity>of(MidnightLurkerHiderEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerHiderEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightLurkerShapeshifterEntity>> MIDNIGHT_LURKER_SHAPESHIFTER = register("midnight_lurker_shapeshifter",
			EntityType.Builder.<MidnightLurkerShapeshifterEntity>of(MidnightLurkerShapeshifterEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerShapeshifterEntity::new).fireImmune().sized(0.6f, 1.95f));
	public static final RegistryObject<EntityType<MidnightLurkerStareEntity>> MIDNIGHT_LURKER_STARE = register("midnight_lurker_stare", EntityType.Builder.<MidnightLurkerStareEntity>of(MidnightLurkerStareEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightLurkerStareEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<MidnightlurkerNEEntity>> MIDNIGHTLURKER_NE = register("midnightlurker_ne", EntityType.Builder.<MidnightlurkerNEEntity>of(MidnightlurkerNEEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MidnightlurkerNEEntity::new).fireImmune().sized(0.7f, 2.5f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			MidnightLurkerAggressiveEntity.init();
			MidnightLurkertposeEntity.init();
			MidnightLurkerStalkingEntity.init();
			MidnightLurkerInvisibleEntity.init();
			SpookyambienceentityEntity.init();
			MidnightLurkerSeenAngressiveEntity.init();
			DestroytexEntity.init();
			Destroytex2Entity.init();
			Destroytex3Entity.init();
			Destroytex4Entity.init();
			MidnightLurkerFakerAggroEntity.init();
			MidnightLurkerFakerEntity.init();
			MidnightLurkerFakerWatcherEntity.init();
			VoidGatewayEntity.init();
			MidnightLurkerBackturnedEntity.init();
			MidnightLurkerShadowEyesEntity.init();
			MidnightLurkerShadowEntity.init();
			MidnightLurkerUnprovokedEntity.init();
			MidnightLurkerRunawayEntity.init();
			MidnightLurkerRuntrueEntity.init();
			MidnightLurkerHiderEntity.init();
			MidnightLurkerShapeshifterEntity.init();
			MidnightLurkerStareEntity.init();
			MidnightlurkerNEEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MIDNIGHT_LURKER_AGGRESSIVE.get(), MidnightLurkerAggressiveEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKERTPOSE.get(), MidnightLurkertposeEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_STALKING.get(), MidnightLurkerStalkingEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_INVISIBLE.get(), MidnightLurkerInvisibleEntity.createAttributes().build());
		event.put(SPOOKYAMBIENCEENTITY.get(), SpookyambienceentityEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_SEEN_ANGRESSIVE.get(), MidnightLurkerSeenAngressiveEntity.createAttributes().build());
		event.put(DESTROYTEX.get(), DestroytexEntity.createAttributes().build());
		event.put(DESTROYTEX_2.get(), Destroytex2Entity.createAttributes().build());
		event.put(DESTROYTEX_3.get(), Destroytex3Entity.createAttributes().build());
		event.put(DESTROYTEX_4.get(), Destroytex4Entity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_FAKER_AGGRO.get(), MidnightLurkerFakerAggroEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_FAKER.get(), MidnightLurkerFakerEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_FAKER_WATCHER.get(), MidnightLurkerFakerWatcherEntity.createAttributes().build());
		event.put(VOID_GATEWAY.get(), VoidGatewayEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_BACKTURNED.get(), MidnightLurkerBackturnedEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_SHADOW_EYES.get(), MidnightLurkerShadowEyesEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_SHADOW.get(), MidnightLurkerShadowEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_UNPROVOKED.get(), MidnightLurkerUnprovokedEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_RUNAWAY.get(), MidnightLurkerRunawayEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_RUNTRUE.get(), MidnightLurkerRuntrueEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_HIDER.get(), MidnightLurkerHiderEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_SHAPESHIFTER.get(), MidnightLurkerShapeshifterEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_STARE.get(), MidnightLurkerStareEntity.createAttributes().build());
		event.put(MIDNIGHTLURKER_NE.get(), MidnightlurkerNEEntity.createAttributes().build());
	}
}
