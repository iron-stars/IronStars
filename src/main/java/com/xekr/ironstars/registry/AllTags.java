package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;

import static com.xekr.ironstars.IronStars.id;

public class AllTags {
    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> TITANIUM_ALLOY = tag("titanium_alloy");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(IronStars.id(name));
        }

        public static void init() {}
    }

    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> MOON_ORE_REPLACEABLES = tag("titanium_alloy");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

        public static void init() {}
    }

//    public static class Fluids {
//
//        private static Tags.IOptionalNamedTag<Fluid> tag(String name) {
//            return FluidTags.createOptional(new ResourceLocation("forge", name));
//        }
//
//        public static void init() {}
//    }
//
//    public static class EntityTypes {
//
//        private static Tags.IOptionalNamedTag<EntityType<?>> tag(String name) {
//            return EntityTypeTags.createOptional(new ResourceLocation("forge", name));
//        }
//
//        public static void init() {}
//    }
//
//    public static class GameEvents {
//
//        private static Tags.IOptionalNamedTag<GameEvent> tag(String name) {
//            return GameEventTags.createOptional(new ResourceLocation("forge", name));
//        }
//
//        public static void init() {}
//    }

    public static void init() {
        Items.init();
        Blocks.init();
//        Fluids.init();
//        EntityTypes.init();
//        GameEvents.init();
    }

}
