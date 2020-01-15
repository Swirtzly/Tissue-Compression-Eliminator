package me.swirtzly.tce.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 19:16
 */
public class RendererUtil {

    public static ResourceLocation getResource(Entity entity) {
        EntityRenderer<Entity> render = Minecraft.getInstance().getRenderManager().getRenderer(entity);
        //  if(render == null)
        return null;
        // return render.getEntityTexture(entity);
    }

    public static EntityRenderer getRenderer(Entity entity) {
        return Minecraft.getInstance().getRenderManager().getRenderer(entity);
    }

}
