package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.level.LevelAccessor;

public class PaneMainScriptProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		PaneNoneScriptProcedure.execute(world, x, y, z);
		PaneEndScriptProcedure.execute(world, x, y, z);
		PaneStraightScriptProcedure.execute(world, x, y, z);
		PaneCornerScriptProcedure.execute(world, x, y, z);
		PaneJunctionScriptProcedure.execute(world, x, y, z);
		PaneCrossScriptProcedure.execute(world, x, y, z);
	}
}
