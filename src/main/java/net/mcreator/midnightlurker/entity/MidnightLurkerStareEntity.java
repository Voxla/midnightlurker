
package net.mcreator.midnightlurker.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.procedures.StarewatchProcedure;
import net.mcreator.midnightlurker.procedures.StarerunProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerStareThisEntityKillsAnotherOneProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerStareOnInitialEntitySpawnProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerStareOnEntityTickUpdateProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerStareEntityIsHurtProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerNaturalEntitySpawningConditionProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerEntityDiesProcedure;
import net.mcreator.midnightlurker.procedures.LurkerinwaterconditionProcedure;
import net.mcreator.midnightlurker.procedures.LurkerKillAnimalsProcProcedure;
import net.mcreator.midnightlurker.init.MidnightlurkerModEntities;

import javax.annotation.Nullable;

public class MidnightLurkerStareEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(MidnightLurkerStareEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(MidnightLurkerStareEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(MidnightLurkerStareEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public MidnightLurkerStareEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MidnightlurkerModEntities.MIDNIGHT_LURKER_STARE.get(), world);
	}

	public MidnightLurkerStareEntity(EntityType<MidnightLurkerStareEntity> type, Level world) {
		super(type, world);
		xpReward = 25;
		setNoAi(false);
		this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "midnightlurkervoidgate");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}

			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && StarewatchProcedure.execute(world, x, y, z, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && StarewatchProcedure.execute(world, x, y, z, entity);
			}

		});
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 2.25;
			}

			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && StarerunProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && StarerunProcedure.execute(entity);
			}

		});
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}

			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && StarerunProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && StarerunProcedure.execute(entity);
			}

		});
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, (float) 150));
		this.goalSelector.addGoal(7, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && LurkerinwaterconditionProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && LurkerinwaterconditionProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, Pig.class, false, false) {
			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}
		});
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, Cow.class, false, false) {
			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}
		});
		this.targetSelector.addGoal(10, new NearestAttackableTargetGoal(this, Sheep.class, false, false) {
			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}
		});
		this.targetSelector.addGoal(11, new NearestAttackableTargetGoal(this, Chicken.class, false, false) {
			@Override
			public boolean canUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightLurkerStareEntity.this.getX();
				double y = MidnightLurkerStareEntity.this.getY();
				double z = MidnightLurkerStareEntity.this.getZ();
				Entity entity = MidnightLurkerStareEntity.this;
				Level world = MidnightLurkerStareEntity.this.level();
				return super.canContinueToUse() && LurkerKillAnimalsProcProcedure.execute(world, x, y, z);
			}
		});
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerchasesteps")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerhurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdeath"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		MidnightLurkerStareEntityIsHurtProcedure.execute(this, source.getEntity());
		if (source.is(DamageTypes.IN_FIRE))
			return false;
		if (source.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (source.is(DamageTypes.FALL))
			return false;
		if (source.is(DamageTypes.CACTUS))
			return false;
		if (source.is(DamageTypes.DROWN))
			return false;
		if (source.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (source.is(DamageTypes.EXPLOSION))
			return false;
		if (source.is(DamageTypes.TRIDENT))
			return false;
		if (source.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (source.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		MidnightLurkerEntityDiesProcedure.execute(this.level(), this);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		MidnightLurkerStareOnInitialEntitySpawnProcedure.execute(world, this.getX(), this.getY(), this.getZ(), this);
		return retval;
	}

	@Override
	public void awardKillScore(Entity entity, int score, DamageSource damageSource) {
		super.awardKillScore(entity, score, damageSource);
		MidnightLurkerStareThisEntityKillsAnotherOneProcedure.execute(this.level(), entity);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		MidnightLurkerStareOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	public static void init() {
		SpawnPlacements.register(MidnightlurkerModEntities.MIDNIGHT_LURKER_STARE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return MidnightLurkerNaturalEntitySpawningConditionProcedure.execute(world, x, y, z);
		});
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.42);
		builder = builder.add(Attributes.MAX_HEALTH, 60);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
		builder = builder.add(Attributes.FOLLOW_RANGE, 100);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

					&& !this.isAggressive()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("stalking9"));
			}
			if (this.isInWaterOrBubble()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("swim9"));
			}
			if (this.isShiftKeyDown()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("snapstare9"));
			}
			if (this.isAggressive() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("running9"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle9"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		Entity entity = this;
		Level world = entity.level();
		boolean loop = false;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (!loop && this.lastloop) {
			this.lastloop = false;
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			event.getController().forceAnimationReset();
			return PlayState.STOP;
		}
		if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			if (!loop) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationprocedure = "empty";
					event.getController().forceAnimationReset();
				}
			} else {
				event.getController().setAnimation(RawAnimation.begin().thenLoop(this.animationprocedure));
				this.lastloop = true;
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(MidnightLurkerStareEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
