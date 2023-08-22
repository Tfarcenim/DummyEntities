package tfar.dummyentities.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import tfar.dummyentities.client.renderer.BlueEntityRenderer;
import tfar.dummyentities.client.renderer.GreenEntityRenderer;
import tfar.dummyentities.client.renderer.OrangeEntityRenderer;
import tfar.dummyentities.client.renderer.RedEntityRenderer;
import tfar.dummyentities.entity.BlueDummyEntity;
import tfar.dummyentities.entity.GreenDummyEntity;
import tfar.dummyentities.entity.OrangeDummyEntity;
import tfar.dummyentities.entity.RedDummyEntity;

@Mod.EventBusSubscriber(Side.CLIENT)
public class DummyEntitiesClient {
    @SubscribeEvent
    public static void renderers(ModelRegistryEvent e) {
        RenderingRegistry.registerEntityRenderingHandler(RedDummyEntity.class, RedEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(OrangeDummyEntity.class, OrangeEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GreenDummyEntity.class, GreenEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BlueDummyEntity.class, BlueEntityRenderer::new);
    }
}
