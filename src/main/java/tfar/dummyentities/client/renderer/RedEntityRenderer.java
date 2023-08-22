package tfar.dummyentities.client.renderer;

import net.minecraft.client.renderer.entity.RenderManager;
import tfar.dummyentities.client.model.AbstractDummyModel;
import tfar.dummyentities.entity.RedDummyEntity;

public class RedEntityRenderer  extends GeoLivingEntityRenderer<RedDummyEntity> {
    public RedEntityRenderer(RenderManager renderManager) {
        super(renderManager, new AbstractDummyModel<>("red"));
    }
}
