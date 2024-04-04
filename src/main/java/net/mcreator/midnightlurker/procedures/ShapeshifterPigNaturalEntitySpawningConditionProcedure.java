package net.mcreator.midnightlurker.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.animal.Pig;

import net.mcreator.midnightlurker.entity.ShapeshifterPigEntity;

public class ShapeshifterPigNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (!world.getEntitiesOfClass(Pig.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).isEmpty() && !(!world.getEntitiesOfClass(ShapeshifterPigEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).isEmpty())) {
			return true;
		}
		return false;
	}
}
