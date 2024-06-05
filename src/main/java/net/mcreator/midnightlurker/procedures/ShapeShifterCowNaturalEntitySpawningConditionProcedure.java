package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.midnightlurker.entity.ShapeShifterCowEntity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class ShapeShifterCowNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		File lurker = new File("");
		lurker = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "midnightlurkerconfig.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(lurker));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				mainjsonobject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (mainjsonobject.get("cow_shapeshifter_spawning").getAsBoolean() == true) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 0, 0);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 0, 0, false);
						}
					}
				} else if (mainjsonobject.get("cow_shapeshifter_spawning").getAsBoolean() == false) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 0, 0);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("midnightlurker:lurkerdisappear")), SoundSource.NEUTRAL, 0, 0, false);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (mainjsonobject.get("cow_shapeshifter_spawning").getAsBoolean() == true) {
			if (!world.getEntitiesOfClass(Cow.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).isEmpty()
					&& !(!world.getEntitiesOfClass(ShapeShifterCowEntity.class, AABB.ofSize(new Vec3(x, y, z), 400, 400, 400), e -> true).isEmpty())) {
				return true;
			}
		} else if (mainjsonobject.get("cow_shapeshifter_spawning").getAsBoolean() == false) {
			return false;
		}
		return false;
	}
}
