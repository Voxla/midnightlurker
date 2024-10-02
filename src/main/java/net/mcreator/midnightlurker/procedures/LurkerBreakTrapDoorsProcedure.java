package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class LurkerBreakTrapDoorsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x + 1, y, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x - 1, y, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z + 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z + 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z - 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z - 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x + 1, y + 1, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x - 1, y + 1, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 1, z + 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z + 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 1, z - 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z - 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x + 1, y + 2, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x + 1, y + 2, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x - 1, y + 2, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x - 1, y + 2, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 2, z + 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 2, z + 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z + 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z - 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 2, z - 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 2, z - 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z - 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 3, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x + 1, y + 3, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x + 1, y + 3, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 3, z))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x - 1, y + 3, z))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x - 1, y + 3, z);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 1, y + 0.5, z), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 3, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 3, z + 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 3, z + 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z + 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 3, z - 1))).is(BlockTags.create(new ResourceLocation("minecraft:trapdoors"))) && !((world.getBlockState(BlockPos.containing(x, y + 3, z - 1))).getBlock() == Blocks.IRON_TRAPDOOR)) {
			{
				BlockPos _pos = BlockPos.containing(x, y + 3, z - 1);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y + 0.5, z - 1), null);
				world.destroyBlock(_pos, false);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.attack_wooden_door hostile @a ~ ~ ~ 1 1");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/playsound minecraft:entity.zombie.break_wooden_door hostile @a ~ ~ ~ 1 1");
		}
	}
}
