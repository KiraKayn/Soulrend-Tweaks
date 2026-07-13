package net.kayn.soulrend_tweaks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TwoHandedWeaponTags {
    public static final TagKey<Item> TWO_HANDED_WEAPONS = ItemTags.create(new ResourceLocation(SoulrendTweaks.MOD_ID, "two_handed_weapons"));
}