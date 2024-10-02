package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.init.MidnightlurkerModBlocks;

public class PaneNoneScriptProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String groupTag = "";
		BlockState model = Blocks.AIR.defaultBlockState();
		model = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
		groupTag = "forge" + ":" + "custom_pane";
		if (!world.getBlockState(BlockPos.containing(x, y, z - 1)).isFaceSturdy(world, BlockPos.containing(x, y, z - 1), Direction.SOUTH)
				&& !(world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BlockTags.create(new ResourceLocation((groupTag).toLowerCase(java.util.Locale.ENGLISH))))
				&& !world.getBlockState(BlockPos.containing(x + 1, y, z)).isFaceSturdy(world, BlockPos.containing(x + 1, y, z), Direction.WEST)
				&& !(world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation((groupTag).toLowerCase(java.util.Locale.ENGLISH))))
				&& !world.getBlockState(BlockPos.containing(x, y, z + 1)).isFaceSturdy(world, BlockPos.containing(x, y, z + 1), Direction.NORTH)
				&& !(world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BlockTags.create(new ResourceLocation((groupTag).toLowerCase(java.util.Locale.ENGLISH))))
				&& !world.getBlockState(BlockPos.containing(x - 1, y, z)).isFaceSturdy(world, BlockPos.containing(x - 1, y, z), Direction.EAST)
				&& !(world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BlockTags.create(new ResourceLocation((groupTag).toLowerCase(java.util.Locale.ENGLISH))))
				&& !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == model.getBlock())) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = model;
				BlockEntity _be = world.getBlockEntity(_bp);
				CompoundTag _bnbt = null;
				if (_be != null) {
					_bnbt = _be.saveWithFullMetadata();
					_be.setRemoved();
				}
				world.setBlock(_bp, _bs, 3);
				if (_bnbt != null) {
					_be = world.getBlockEntity(_bp);
					if (_be != null) {
						try {
							_be.load(_bnbt);
						} catch (Exception ignored) {
						}
					}
				}
			}
			{
				Direction _dir = Direction.NORTH;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
				if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
					world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
				} else {
					_property = _bs.getBlock().getStateDefinition().getProperty("axis");
					if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
						world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
				}
			}
		}
	}
}
