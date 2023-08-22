package tfar.dummyentities.client.renderer;

import net.minecraft.client.renderer.entity.RenderManager;
import tfar.dummyentities.client.model.AbstractDummyModel;
import tfar.dummyentities.entity.BlueDummyEntity;

public class BlueEntityRenderer extends GeoLivingEntityRenderer<BlueDummyEntity> {
    public BlueEntityRenderer(RenderManager renderManager) {
        super(renderManager, new AbstractDummyModel<>("blue"));
    }
}
