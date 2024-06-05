package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.init.MidnightlurkerModParticleTypes;
import net.mcreator.midnightlurker.entity.MidnightLurkerRuntrueEntity;
import net.mcreator.midnightlurker.MidnightlurkerMod;

import java.util.Comparator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class MidnightLurkerRuntrueOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		File lurker = new File("");
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false));
		if (entity.isPassenger()) {
			entity.stopRiding();
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 20, 20, 20), e -> true).isEmpty()) {
			if (entity.getPersistentData().getBoolean("DespawnActivated") == false) {
				entity.getPersistentData().putBoolean("DespawnActivated", true);
			}
		}
		if (entity.getPersistentData().getBoolean("DespawnActivated") == true) {
			if (entity.getPersistentData().getDouble("DespawnTimer") == 0) {
				entity.getPersistentData().putDouble("DespawnTimer", 200);
			} else {
				entity.getPersistentData().putDouble("DespawnTimer", (entity.getPersistentData().getDouble("DespawnTimer") - 1));
			}
		}
		if (entity.getPersistentData().getDouble("DespawnTimer") == 13) {
			if (entity instanceof MidnightLurkerRuntrueEntity) {
				((MidnightLurkerRuntrueEntity) entity).setAnimation("teleport6");
			}
		}
		if (entity.getPersistentData().getDouble("DespawnTimer") == 11) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		if (entity.getPersistentData().getDouble("DespawnTimer") == 1) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (MidnightlurkerModParticleTypes.VOID_DOT.get()), x, y, z, 2, 0.3, 1.2, 0.3, 0.1);
		}
		if (entity instanceof LivingEntity _livEnt24 && _livEnt24.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 20, 20, 20), e -> true).isEmpty()) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 70, 70, 70), e -> true).isEmpty()) {
			if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)).isPassenger()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 255, false, false));
			}
		}
		lurker = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "midnightlurkerconfig.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(lurker));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				mainjsonobject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (mainjsonobject.get("lurker_persist_during_day").getAsBoolean() == false) {
					if ((world instanceof Level _lvl40 && _lvl40.isDay()) == true && y > 60) {
						if (entity instanceof MidnightLurkerRuntrueEntity) {
							if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 30, 30, 30), e -> true).isEmpty()) {
								MidnightlurkerMod.queueServerWork(2, () -> {
									if (!world.getEntitiesOfClass(MidnightLurkerRuntrueEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1);
											} else {
												_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1, false);
											}
										}
									}
								});
								MidnightlurkerMod.queueServerWork(13, () -> {
									if (!world.getEntitiesOfClass(MidnightLurkerRuntrueEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
										if (!entity.level().isClientSide())
											entity.discard();
									}
								});
								if (entity instanceof MidnightLurkerRuntrueEntity) {
									((MidnightLurkerRuntrueEntity) entity).setAnimation("teleport6");
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
