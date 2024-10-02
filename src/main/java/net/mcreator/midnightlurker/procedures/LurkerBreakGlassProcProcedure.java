package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.init.MidnightlurkerModBlocks;

import java.util.Map;

public class LurkerBreakGlassProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y + 1, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y + 1, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y + 1, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y + 1, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y + 1, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y + 1, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 1, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 1, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 1, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 1, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y + 1, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y + 1, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y + 1, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y + 1, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y + 1, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y + 1, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 1, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 1, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 1, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 1, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 2, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y + 2, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y + 2, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y + 2, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 2, z))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y + 2, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y + 2, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y + 2, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z + 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 2, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 2, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 2, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z - 1))).getBlock() == Blocks.GLASS) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 2, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 2, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 2, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_GLASS.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y + 2, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x + 1, y + 2, z), Block.getId((world.getBlockState(BlockPos.containing(x + 1, y + 2, z)))));
				{
					BlockPos _bp = BlockPos.containing(x + 1, y + 2, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y + 2, z))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x - 1, y + 2, z), Block.getId((world.getBlockState(BlockPos.containing(x - 1, y + 2, z)))));
				{
					BlockPos _bp = BlockPos.containing(x - 1, y + 2, z);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z + 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 2, z + 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 2, z + 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 2, z + 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y + 2, z - 1))).getBlock() == Blocks.GLASS_PANE) {
			if (!world.isClientSide()) {
				world.levelEvent(2001, BlockPos.containing(x, y + 2, z - 1), Block.getId((world.getBlockState(BlockPos.containing(x, y + 2, z - 1)))));
				{
					BlockPos _bp = BlockPos.containing(x, y + 2, z - 1);
					BlockState _bs = MidnightlurkerModBlocks.SHATTERED_PANE_NONE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}
