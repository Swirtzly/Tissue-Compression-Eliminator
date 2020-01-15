package me.swirtzly.tce.handlers;

import me.swirtzly.tce.entity.OverideItemEntity;
import me.swirtzly.tce.entity.ScaledEntity;
import me.swirtzly.tce.item.TCEItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static me.swirtzly.tce.Tisselm.MODID;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 19:35
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegHandler {

    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                setUpItem(new TCEItem(new Item.Properties().group(ItemGroup.COMBAT)), "tce")
        );
    }

    @SubscribeEvent
    public static void addSounds(RegistryEvent.Register<SoundEvent> e) {
        e.getRegistry().registerAll(
                setUpSound("tce_shrink")
        );
    }

    @SubscribeEvent
    public static void addEntities(final RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> reg = event.getRegistry();


        //Item Override
        reg.register(EntityEntries.ITEM_OVERRIDE_ENTITY_TYPE = EntityType.Builder.<OverideItemEntity>create(OverideItemEntity::new, EntityClassification.MISC)
                .size(0.5F, 0.2F)
                .setTrackingRange(128)
                .setUpdateInterval(1)
                .setShouldReceiveVelocityUpdates(true)
                .setCustomClientFactory((spawnEntity, world) -> new OverideItemEntity(world))
                .build(MODID + ":item_override")
                .setRegistryName(new ResourceLocation(MODID, "item_override")));

        reg.register(EntityEntries.ENTITY_SHRUNK = EntityType.Builder.<ScaledEntity>create(ScaledEntity::new, EntityClassification.MISC)
                .size(0.5F, 0.2F)
                .setTrackingRange(128)
                .setUpdateInterval(1)
                .setShouldReceiveVelocityUpdates(true)
                .setCustomClientFactory((spawnEntity, world) -> new ScaledEntity(world))
                .build(MODID + ":entity_shrunk")
                .setRegistryName(new ResourceLocation(MODID, "entity_shrunk")));
    }

    private static Item setUpItem(Item item, String name) {
        item.setRegistryName(MODID, name);
        return item;
    }

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(MODID, soundName)).setRegistryName(soundName);
    }

    @ObjectHolder(MODID)
    public static class Items {
        public static final Item TCE = null;
    }

    @ObjectHolder(MODID)
    public static class EntityEntries {
        public static EntityType ITEM_OVERRIDE_ENTITY_TYPE = null;
        public static EntityType ENTITY_SHRUNK = null;
    }

    @ObjectHolder(MODID)
    public static class Sounds {
        public static SoundEvent TCE_SHRINK = null;
    }

}
