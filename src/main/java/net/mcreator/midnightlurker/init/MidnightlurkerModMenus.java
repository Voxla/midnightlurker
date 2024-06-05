
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.midnightlurker.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.midnightlurker.world.inventory.LurkerBookGuiMenu;
import net.mcreator.midnightlurker.MidnightlurkerMod;

public class MidnightlurkerModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MidnightlurkerMod.MODID);
	public static final RegistryObject<MenuType<LurkerBookGuiMenu>> LURKER_BOOK_GUI = REGISTRY.register("lurker_book_gui", () -> IForgeMenuType.create(LurkerBookGuiMenu::new));
}
