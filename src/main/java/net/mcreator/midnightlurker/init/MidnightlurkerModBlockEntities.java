
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.midnightlurker.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.midnightlurker.block.entity.ShatteredTCornerBlockEntity;
import net.mcreator.midnightlurker.block.entity.ShatteredPaneXCornerBlockEntity;
import net.mcreator.midnightlurker.block.entity.ShatteredPaneStraightBlockEntity;
import net.mcreator.midnightlurker.block.entity.ShatteredPaneNoneBlockEntity;
import net.mcreator.midnightlurker.block.entity.ShatteredPaneEndBlockEntity;
import net.mcreator.midnightlurker.block.entity.ShatteredCornerBlockEntity;
import net.mcreator.midnightlurker.MidnightlurkerMod;

public class MidnightlurkerModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MidnightlurkerMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_PANE_NONE = register("shattered_pane_none", MidnightlurkerModBlocks.SHATTERED_PANE_NONE, ShatteredPaneNoneBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_PANE_STRAIGHT = register("shattered_pane_straight", MidnightlurkerModBlocks.SHATTERED_PANE_STRAIGHT, ShatteredPaneStraightBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_CORNER = register("shattered_corner", MidnightlurkerModBlocks.SHATTERED_CORNER, ShatteredCornerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_T_CORNER = register("shattered_t_corner", MidnightlurkerModBlocks.SHATTERED_T_CORNER, ShatteredTCornerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_PANE_X_CORNER = register("shattered_pane_x_corner", MidnightlurkerModBlocks.SHATTERED_PANE_X_CORNER, ShatteredPaneXCornerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SHATTERED_PANE_END = register("shattered_pane_end", MidnightlurkerModBlocks.SHATTERED_PANE_END, ShatteredPaneEndBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
