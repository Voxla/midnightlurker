
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

import net.mcreator.midnightlurker.procedures.MidnightlurkerNEThisEntityKillsAnotherOneProcedure;
import net.mcreator.midnightlurker.procedures.MidnightlurkerNEOnEntityTickUpdateProcedure;
import net.mcreator.midnightlurker.procedures.MidnightlurkerNEEntityDiesProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerNENaturalEntitySpawningConditionProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerAggressivePlayReturnedAnimationProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerAggressiveOnInitialEntitySpawnProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerAggressiveLoopExternalAnimationsProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerAggressiveEntityIsHurtProcedure;
import net.mcreator.midnightlurker.procedures.MidnightLurkerAggressiveBoundingBoxScaleProcedure;
import net.mcreator.midnightlurker.procedures.LurkerinwaterconditionProcedure;
import net.mcreator.midnightlurker.procedures.AggrowatchplayerProcedure;
import net.mcreator.midnightlurker.init.MidnightlurkerModEntities;

import javax.annotation.Nullable;

public class MidnightlurkerNEEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(MidnightlurkerNEEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(MidnightlurkerNEEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(MidnightlurkerNEEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public MidnightlurkerNEEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MidnightlurkerModEntities.MIDNIGHTLURKER_NE.get(), world);
	}

	public MidnightlurkerNEEntity(EntityType<MidnightlurkerNEEntity> type, Level world) {
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
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 2.25;
			}
		});
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, (float) 100) {
			@Override
			public boolean canUse() {
				double x = MidnightlurkerNEEntity.this.getX();
				double y = MidnightlurkerNEEntity.this.getY();
				double z = MidnightlurkerNEEntity.this.getZ();
				Entity entity = MidnightlurkerNEEntity.this;
				Level world = MidnightlurkerNEEntity.this.level();
				return super.canUse() && AggrowatchplayerProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightlurkerNEEntity.this.getX();
				double y = MidnightlurkerNEEntity.this.getY();
				double z = MidnightlurkerNEEntity.this.getZ();
				Entity entity = MidnightlurkerNEEntity.this;
				Level world = MidnightlurkerNEEntity.this.level();
				return super.canContinueToUse() && AggrowatchplayerProcedure.execute(world, x, y, z);
			}
		});
		this.goalSelector.addGoal(6, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				double x = MidnightlurkerNEEntity.this.getX();
				double y = MidnightlurkerNEEntity.this.getY();
				double z = MidnightlurkerNEEntity.this.getZ();
				Entity entity = MidnightlurkerNEEntity.this;
				Level world = MidnightlurkerNEEntity.this.level();
				return super.canUse() && LurkerinwaterconditionProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = MidnightlurkerNEEntity.this.getX();
				double y = MidnightlurkerNEEntity.this.getY();
				double z = MidnightlurkerNEEntity.this.getZ();
				Entity entity = MidnightlurkerNEEntity.this;
				Level world = MidnightlurkerNEEntity.this.level();
				return super.canContinueToUse() && LurkerinwaterconditionProcedure.execute(entity);
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
		MidnightLurkerAggressiveEntityIsHurtProcedure.execute(this, source.getEntity());
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
		MidnightlurkerNEEntityDiesProcedure.execute(this.level(), this);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		MidnightLurkerAggressiveOnInitialEntitySpawnProcedure.execute(world, this);
		return retval;
	}

	@Override
	public void awardKillScore(Entity entity, int score, DamageSource damageSource) {
		super.awardKillScore(entity, score, damageSource);
		MidnightlurkerNEThisEntityKillsAnotherOneProcedure.execute(this.level(), entity);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		MidnightlurkerNEOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		Entity entity = this;
		Level world = this.level();
		double x = this.getX();
		double y = entity.getY();
		double z = entity.getZ();
		return super.getDimensions(p_33597_).scale((float) MidnightLurkerAggressiveBoundingBoxScaleProcedure.execute(world, x, y, z));
	}

	public static void init() {
		SpawnPlacements.register(MidnightlurkerModEntities.MIDNIGHTLURKER_NE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return MidnightLurkerNENaturalEntitySpawningConditionProcedure.execute(world, x, y, z);
		});
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.42);
		builder = builder.add(Attributes.MAX_HEALTH, 120);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
		builder = builder.add(Attributes.FOLLOW_RANGE, 100);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

					&& !this.isAggressive()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("stalking1"));
			}
			if (this.isInWaterOrBubble()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("swim1"));
			}
			if (this.isAggressive() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("running1"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle1"));
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
		String condition = MidnightLurkerAggressivePlayReturnedAnimationProcedure.execute(world, x, y, z, entity);
		if (!condition.equals("empty"))
			this.animationprocedure = condition;
		loop = MidnightLurkerAggressiveLoopExternalAnimationsProcedure.execute(world, x, y, z, entity);
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
			this.remove(MidnightlurkerNEEntity.RemovalReason.KILLED);
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
