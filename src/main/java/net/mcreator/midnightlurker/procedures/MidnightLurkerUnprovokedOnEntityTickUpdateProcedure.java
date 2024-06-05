package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.midnightlurker.network.MidnightlurkerModVariables;
import net.mcreator.midnightlurker.init.MidnightlurkerModParticleTypes;
import net.mcreator.midnightlurker.entity.MidnightLurkerUnprovokedEntity;
import net.mcreator.midnightlurker.MidnightlurkerMod;

import java.util.Map;
import java.util.List;
import java.util.Comparator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class MidnightLurkerUnprovokedOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		String found_entity_name = "";
		boolean entity_found = false;
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		File lurker = new File("");
		if (entity.getPersistentData().getDouble("SlownessCountdown") < 2401 && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 70, 70, 70), e -> true).isEmpty()) {
			entity.getPersistentData().putDouble("SlownessCountdown", (entity.getPersistentData().getDouble("SlownessCountdown") + 1));
		}
		if (entity instanceof LivingEntity _livEnt7 && _livEnt7.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) && entity.getPersistentData().getDouble("SlownessCountdown") == 2400) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		} else if (entity instanceof LivingEntity _livEnt10 && _livEnt10.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 20, 20, 20), e -> true).isEmpty()) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		}
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false));
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 70, 70, 70), e -> true).isEmpty()) {
			{
				final Vec3 _center = new Vec3((entity.getX()), (entity.getY()), (entity.getZ()));
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(70 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof Player) {
						if (entityiterator.isPassenger() && !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 15, 15, 15), e -> true).isEmpty())) {
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS) {
								(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).enchant(Enchantments.DEPTH_STRIDER, 1);
							}
						} else {
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS
									&& EnchantmentHelper.getItemEnchantmentLevel(Enchantments.DEPTH_STRIDER, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)) != 0) {
								{
									Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY));
									if (_enchantments.containsKey(Enchantments.DEPTH_STRIDER)) {
										_enchantments.remove(Enchantments.DEPTH_STRIDER);
										EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY));
									}
								}
							}
						}
					}
				}
			}
		}
		if (entity.isPassenger()) {
			entity.stopRiding();
		}
		if (entity.getPersistentData().getDouble("CanDisappear") < 201) {
			entity.getPersistentData().putDouble("CanDisappear", (entity.getPersistentData().getDouble("CanDisappear") + 1));
		}
		if (entity.getPersistentData().getDouble("CanDisappear") >= 200) {
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 15, 15, 15), e -> true).isEmpty()) {
				if (entity.getPersistentData().getBoolean("DespawnActivated") == false) {
					entity.getPersistentData().putBoolean("DespawnActivated", true);
				}
			}
			if (entity.getPersistentData().getDouble("SeenDisappear") >= 1) {
				if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
					if ((new Object() {
						public Entity func(Entity player, double entityReach) {
							double distance = entityReach * entityReach;
							Vec3 eyePos = player.getEyePosition(1.0f);
							HitResult hitResult = entity.pick(entityReach, 1.0f, false);
							if (hitResult != null && hitResult.getType() != HitResult.Type.MISS) {
								distance = hitResult.getLocation().distanceToSqr(eyePos);
								double blockReach = 5;
								if (distance > blockReach * blockReach) {
									Vec3 pos = hitResult.getLocation();
									hitResult = BlockHitResult.miss(pos, Direction.getNearest(eyePos.x, eyePos.y, eyePos.z), BlockPos.containing(pos));
								}
							}
							Vec3 viewVec = player.getViewVector(1.0F);
							Vec3 toVec = eyePos.add(viewVec.x * entityReach, viewVec.y * entityReach, viewVec.z * entityReach);
							AABB aabb = entity.getBoundingBox().expandTowards(viewVec.scale(entityReach)).inflate(1.0D, 1.0D, 1.0D);
							EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(player, eyePos, toVec, aabb, (p_234237_) -> {
								return !p_234237_.isSpectator();
							}, distance);
							if (entityhitresult != null) {
								Entity entity1 = entityhitresult.getEntity();
								Vec3 targetPos = entityhitresult.getLocation();
								double distanceToTarget = eyePos.distanceToSqr(targetPos);
								if (distanceToTarget > distance || distanceToTarget > entityReach * entityReach) {
									hitResult = BlockHitResult.miss(targetPos, Direction.getNearest(viewVec.x, viewVec.y, viewVec.z), BlockPos.containing(targetPos));
								} else if (distanceToTarget < distance) {
									hitResult = entityhitresult;
								}
							}
							if (hitResult.getType() == HitResult.Type.ENTITY) {
								return ((EntityHitResult) hitResult).getEntity();
							}
							return null;
						}
					}.func(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)), 23)) instanceof MidnightLurkerUnprovokedEntity) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 30, 30, 30), e -> true).isEmpty()) {
							if (entity.getPersistentData().getDouble("SoundActivate1") < 3 && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
								entity.getPersistentData().putDouble("SoundActivate1", (entity.getPersistentData().getDouble("SoundActivate1") + 1));
							}
							if (entity.getPersistentData().getDouble("SoundActivate1") == 1) {
								MidnightlurkerMod.queueServerWork(2, () -> {
									if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1);
											} else {
												_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 1, 1, false);
											}
										}
									}
								});
							}
							if (entity instanceof MidnightLurkerUnprovokedEntity) {
								((MidnightLurkerUnprovokedEntity) entity).setAnimation("teleport5");
							}
							MidnightlurkerMod.queueServerWork(13, () -> {
								if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
									if (!entity.level().isClientSide())
										entity.discard();
								}
							});
						}
					}
				}
			}
		}
		if (entity.getPersistentData().getBoolean("DespawnActivated") == true) {
			if (entity.getPersistentData().getDouble("DespawnTimer") == 0) {
				entity.getPersistentData().putDouble("DespawnTimer", 700);
			} else {
				entity.getPersistentData().putDouble("DespawnTimer", (entity.getPersistentData().getDouble("DespawnTimer") - 1));
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
		if (entity.getPersistentData().getDouble("DespawnTimer") == 13) {
			if (entity instanceof MidnightLurkerUnprovokedEntity) {
				((MidnightLurkerUnprovokedEntity) entity).setAnimation("teleport5");
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
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).isEmpty()) {
			if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.IRON_HELMET
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.IRON_CHESTPLATE
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_LEGGINGS
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 0, false, false));
			} else if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.DIAMOND_HELMET
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.DIAMOND_CHESTPLATE
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.DIAMOND_LEGGINGS
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.DIAMOND_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 1, false, false));
			} else if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.NETHERITE_HELMET
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.NETHERITE_CHESTPLATE
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.NETHERITE_LEGGINGS
					|| (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.NETHERITE_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 2, false, false));
			}
			if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.IRON_HELMET
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.IRON_CHESTPLATE
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.IRON_LEGGINGS
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.IRON_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 0, false, false));
			} else if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.DIAMOND_HELMET
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.DIAMOND_CHESTPLATE
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.DIAMOND_LEGGINGS
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.DIAMOND_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 2, false, false));
			} else if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Items.NETHERITE_HELMET
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == Items.NETHERITE_CHESTPLATE
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == Items.NETHERITE_LEGGINGS
					&& (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == Items.NETHERITE_BOOTS) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 3, false, false));
			}
		}
		if (entity.getPersistentData().getBoolean("Stunned") == true) {
			if (entity.getPersistentData().getDouble("StunTimer") <= 0) {
				entity.getPersistentData().putDouble("StunTimer", 1);
			}
		}
		if (entity.getPersistentData().getDouble("StunTimer") > 0 && entity.getPersistentData().getDouble("StunTimer") < 200) {
			entity.getPersistentData().putDouble("StunTimer", (entity.getPersistentData().getDouble("StunTimer") + 1));
		}
		if (entity.getPersistentData().getDouble("StunTimer") >= 200) {
			entity.getPersistentData().putDouble("StunTimer", 0);
		}
		if (entity.getPersistentData().getDouble("StunTimer") >= 98) {
			if (entity.getPersistentData().getBoolean("Stunned") == true) {
				entity.getPersistentData().putBoolean("Stunned", false);
			}
		}
		if (entity.getPersistentData().getDouble("StunTimer") > 0 && entity.getPersistentData().getDouble("StunTimer") < 98) {
			entity.setDeltaMovement(new Vec3(0, 0, 0));
			if (entity instanceof MidnightLurkerUnprovokedEntity) {
				((MidnightLurkerUnprovokedEntity) entity).setAnimation("stunned5");
			}
		}
		if (entity.getPersistentData().getDouble("StunTimer") == 2) {
			if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 3, 3, 3), e -> true).isEmpty()) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"/playsound midnightlurker:lurker_stunned neutral @a ~ ~ ~ 1 1");
			}
			MidnightlurkerMod.queueServerWork(30, () -> {
				if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 3, 3, 3), e -> true).isEmpty()) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/playsound midnightlurker:lurker_taunt neutral @a ~ ~ ~ 0.7 1");
				}
			});
		}
		if (entity.getPersistentData().getDouble("StunTimer") == 88) {
			if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 3, 3, 3), e -> true).isEmpty()) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"/playsound midnightlurker:lurker_stun_over neutral @a ~ ~ ~ 1 1");
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
					if ((world instanceof Level _lvl245 && _lvl245.isDay()) == true && y > 60) {
						if (entity instanceof MidnightLurkerUnprovokedEntity) {
							if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 30, 30, 30), e -> true).isEmpty()) {
								MidnightlurkerMod.queueServerWork(2, () -> {
									if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
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
									if (!world.getEntitiesOfClass(MidnightLurkerUnprovokedEntity.class, AABB.ofSize(new Vec3(x, y, z), 23, 23, 23), e -> true).isEmpty()) {
										if (!entity.level().isClientSide())
											entity.discard();
									}
								});
								if (entity instanceof MidnightLurkerUnprovokedEntity) {
									((MidnightLurkerUnprovokedEntity) entity).setAnimation("teleport5");
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
