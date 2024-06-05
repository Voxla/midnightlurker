package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.network.MidnightlurkerModVariables;
import net.mcreator.midnightlurker.init.MidnightlurkerModParticleTypes;
import net.mcreator.midnightlurker.init.MidnightlurkerModEntities;
import net.mcreator.midnightlurker.entity.MidnightLurkerHiderEntity;
import net.mcreator.midnightlurker.MidnightlurkerMod;

import java.util.Comparator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class MidnightLurkerHiderOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		String found_entity_name = "";
		boolean entity_found = false;
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		File lurker = new File("");
		if (world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("desert")) || world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("beach"))) {
			if (entity instanceof MidnightLurkerHiderEntity animatable)
				animatable.setTexture("midnightlurkervoidgatehidersand");
		} else if (world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("snowy_slopes")) || world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("snowy_beach"))
				|| world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("snowy_plains")) || world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("snowy_taiga"))
				|| world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("frozen_peaks")) || world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("ice_spikes"))) {
			if (entity instanceof MidnightLurkerHiderEntity animatable)
				animatable.setTexture("midnightlurkervoidgatehidersnow");
		} else if (world.getBiome(BlockPos.containing(x, y, z)).is(new ResourceLocation("badlands"))) {
			if (entity instanceof MidnightLurkerHiderEntity animatable)
				animatable.setTexture("midnightlurkervoidgatehiderredsand");
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255, false, false));
		}
		if (!entity.isShiftKeyDown()) {
			if (Math.random() > 0.9) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (MidnightlurkerModParticleTypes.VOID_DOT.get()), x, y, z, 2, 0.3, 1.2, 0.3, 0.1);
			}
		}
		if (entity.getPersistentData().getDouble("Hiding") == 1) {
			entity.setShiftKeyDown(true);
		} else if (entity.getPersistentData().getDouble("Hiding") == 0) {
			entity.setShiftKeyDown(false);
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 10, 10, 10), e -> true).isEmpty()) {
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
		if (entity.getPersistentData().getDouble("DespawnTimer") == 1) {
			if (entity instanceof MidnightLurkerHiderEntity) {
				((MidnightLurkerHiderEntity) entity).setAnimation("teleport8");
			}
			MidnightlurkerMod.queueServerWork(2, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			});
			MidnightlurkerMod.queueServerWork(13, () -> {
				if (!entity.level().isClientSide())
					entity.discard();
			});
			MidnightlurkerMod.queueServerWork(7, () -> {
				if (!((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER) || !((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER)
						|| !((world.getBlockState(BlockPos.containing(x, y - 0, z))).getBlock() == Blocks.WATER) || !((world.getBlockState(BlockPos.containing(x, y - 0, z))).getBlock() == Blocks.WATER)) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = MidnightlurkerModEntities.VOID_GATEWAY.get().spawn(_level, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
				}
			});
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).isEmpty()) {
			raytrace_distance = 0;
			for (int index0 = 0; index0 < 30; index0++) {
				if (!world.getEntitiesOfClass(MidnightLurkerHiderEntity.class, AABB.ofSize(new Vec3((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).level().clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f), ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f).add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
						((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)))).getBlockPos().getX()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).level().clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f), ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f).add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
								((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)))).getBlockPos().getY()),
						(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).level().clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f), ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f).add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
								((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)))).getBlockPos().getZ())),
						25, 25, 25), e -> true).isEmpty()
						&& !(((Entity) world
								.getEntitiesOfClass(MidnightLurkerHiderEntity.class, AABB.ofSize(new Vec3((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).level().clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f), ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f).add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
										((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)))).getBlockPos().getX()),
										(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).level()
												.clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f),
														((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f)
																.add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
																	Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																		return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
																	}
																}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)),
														ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null))))
												.getBlockPos().getY()),
										(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).level()
												.clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f),
														((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f)
																.add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
																	Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																		return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
																	}
																}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)),
														ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null))))
												.getBlockPos().getZ())),
										25, 25, 25), e -> true)
								.stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).level().clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f), ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f).add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
										((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)))).getBlockPos().getX()),
										(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).level()
												.clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f),
														((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f)
																.add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
																	Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																		return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
																	}
																}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)),
														ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null))))
												.getBlockPos().getY()),
										(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).level()
												.clip(new ClipContext(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f),
														((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null)).getEyePosition(1f)
																.add(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
																	Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																		return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
																	}
																}.compareDistOf(x, y, z)).findFirst().orElse(null)).getViewVector(1f).scale(raytrace_distance)),
														ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null))))
												.getBlockPos().getZ())))
								.findFirst().orElse(null)) == ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 70, 70, 70), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)))) {
					entity_found = true;
				} else {
					entity_found = false;
					raytrace_distance = raytrace_distance + 1;
				}
			}
			if (entity_found || !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
				entity.getPersistentData().putDouble("Hiding", 1);
			} else {
				entity.getPersistentData().putDouble("Hiding", 0);
			}
		}
		if (world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && !(world.getBlockState(BlockPos.containing(x + 1, y + 0, z))).is(BlockTags.create(new ResourceLocation("midnightlurker:cannotclimb")))
				&& (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, (y + 52), z), 100.1, 100.1, 100.1), e -> true).isEmpty() && (entity.getDirection()) == Direction.EAST) {
			entity.setDeltaMovement(new Vec3(0.2, 0.2, (entity.getDeltaMovement().z())));
		} else if (world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && !(world.getBlockState(BlockPos.containing(x - 1, y + 0, z))).is(BlockTags.create(new ResourceLocation("midnightlurker:cannotclimb")))
				&& (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, (y + 52), z), 100.1, 100.1, 100.1), e -> true).isEmpty() && (entity.getDirection()) == Direction.WEST) {
			entity.setDeltaMovement(new Vec3((-0.2), 0.2, (entity.getDeltaMovement().z())));
		} else if (world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && !(world.getBlockState(BlockPos.containing(x, y + 0, z + 1))).is(BlockTags.create(new ResourceLocation("midnightlurker:cannotclimb")))
				&& (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, (y + 52), z), 100.1, 100.1, 100.1), e -> true).isEmpty() && (entity.getDirection()) == Direction.SOUTH) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.2, 0.2));
		} else if (world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && !(world.getBlockState(BlockPos.containing(x, y + 0, z - 1))).is(BlockTags.create(new ResourceLocation("midnightlurker:cannotclimb")))
				&& (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, (y + 52), z), 100.1, 100.1, 100.1), e -> true).isEmpty() && (entity.getDirection()) == Direction.NORTH) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.2, (-0.2)));
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).isEmpty()) {
			if (entity.getPersistentData().getDouble("encount") < 2) {
				entity.getPersistentData().putDouble("encount", (entity.getPersistentData().getDouble("encount") + 1));
			}
		}
		if (entity.getPersistentData().getDouble("encount") == 1) {
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).isEmpty()) {
				if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(MidnightlurkerModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MidnightlurkerModVariables.PlayerVariables())).encounternumber < 6) {
					{
						double _setval = (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(MidnightlurkerModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MidnightlurkerModVariables.PlayerVariables())).encounternumber + 1;
						((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(MidnightlurkerModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.encounternumber = _setval;
							capability.syncPlayerVariables(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)));
						});
					}
				}
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
					if ((world instanceof Level _lvl125 && _lvl125.isDay()) == true && y > 60) {
						if (entity instanceof MidnightLurkerHiderEntity) {
							if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 30, 30, 30), e -> true).isEmpty()) {
								MidnightlurkerMod.queueServerWork(2, () -> {
									if (!world.getEntitiesOfClass(MidnightLurkerHiderEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
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
									if (!world.getEntitiesOfClass(MidnightLurkerHiderEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
										if (!entity.level().isClientSide())
											entity.discard();
									}
								});
								if (entity instanceof MidnightLurkerHiderEntity) {
									((MidnightLurkerHiderEntity) entity).setAnimation("teleport8");
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
