package net.mcreator.midnightlurker.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.player.LocalPlayer;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GetDoorRecipePickupProcedure {
	@SubscribeEvent
	public static void onPickup(EntityItemPickupEvent event) {
		execute(event, event.getEntity(), event.getItem().getItem());
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity instanceof Player && (itemstack.getItem() == Items.IRON_INGOT || itemstack.getItem() == Items.IRON_NUGGET || itemstack.getItem() == Blocks.IRON_BLOCK.asItem())) {
			if (!(new Object() {
				public boolean hasRecipe(Entity _ent, ResourceLocation recipe) {
					if (_ent instanceof ServerPlayer _player)
						return _player.getRecipeBook().contains(recipe);
					else if (_ent.level().isClientSide() && _ent instanceof LocalPlayer _player)
						return _player.getRecipeBook().contains(recipe);
					return false;
				}
			}.hasRecipe(entity, new ResourceLocation("midnightlurker:metal_door_recipe")))) {
				if (entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("midnightlurker:metal_door_recipe")});
			}
		}
	}
}
