package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class MidnightLurkerAggressivePlayReturnedAnimationProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		if (!world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 1, z)).canOcclude() && (entity.getDirection()) == Direction.EAST
				|| !world.getBlockState(BlockPos.containing(x + 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 2, z)).canOcclude()
						&& (entity.getDirection()) == Direction.EAST
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running3";
			}
		} else if (!world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 1, z)).canOcclude() && (entity.getDirection()) == Direction.WEST
				|| !world.getBlockState(BlockPos.containing(x - 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 2, z)).canOcclude()
						&& (entity.getDirection()) == Direction.WEST
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running3";
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z + 1)).canOcclude() && (entity.getDirection()) == Direction.SOUTH
				|| !world.getBlockState(BlockPos.containing(x, y + 1, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z + 1)).canOcclude()
						&& (entity.getDirection()) == Direction.SOUTH
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running3";
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z - 1)).canOcclude() && (entity.getDirection()) == Direction.NORTH
				|| !world.getBlockState(BlockPos.containing(x, y + 1, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z - 1)).canOcclude()
						&& (entity.getDirection()) == Direction.NORTH
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running3";
			}
		}
		if (!world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x + 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 2, z)).canOcclude()
				&& (entity.getDirection()) == Direction.EAST
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running2";
			}
		} else if (!world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x - 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 2, z)).canOcclude()
				&& (entity.getDirection()) == Direction.WEST
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running2";
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z + 1)).canOcclude()
				&& (entity.getDirection()) == Direction.SOUTH
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running2";
			}
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z - 1)).canOcclude()
				&& (entity.getDirection()) == Direction.NORTH
				|| !world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
						&& (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() < 0 || entity.getDeltaMovement().x() < 0) {
				return "running2";
			}
		}
		return "empty";
	}
}
