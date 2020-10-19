package com.wc.tce.common.sounds;

import com.wc.tce.TCEMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCESounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TCEMod.MODID);
    public static final RegistryObject<SoundEvent> TCE = SOUNDS.register("tce_shrink", () -> setUpSound("tce_shrink"));
    
    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(TCEMod.MODID, soundName));
    }
}
