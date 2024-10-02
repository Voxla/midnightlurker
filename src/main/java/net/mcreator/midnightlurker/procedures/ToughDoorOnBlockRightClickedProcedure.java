package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class ToughDoorOnBlockRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getbp1)) == true) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(Math.floor(x) + 0.5, Math.floor(y) + 0.5, Math.floor(z) + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.iron_door.close")), SoundSource.BLOCKS, 1,
								(float) 0.8);
					} else {
						_level.playLocalSound((Math.floor(x) + 0.5), (Math.floor(y) + 0.5), (Math.floor(z) + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.iron_door.close")), SoundSource.BLOCKS, 1, (float) 0.8, false);
					}
				}
			}
		} else if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp5
				&& (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getbp5)) == false) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(Math.floor(x) + 0.5, Math.floor(y) + 0.5, Math.floor(z) + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.iron_door.open")), SoundSource.BLOCKS, 1,
								(float) 0.8);
					} else {
						_level.playLocalSound((Math.floor(x) + 0.5), (Math.floor(y) + 0.5), (Math.floor(z) + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.iron_door.open")), SoundSource.BLOCKS, 1, (float) 0.8, false);
					}
				}
			}
		}
	}
}
