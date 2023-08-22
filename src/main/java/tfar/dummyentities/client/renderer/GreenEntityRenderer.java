package tfar.dummyentities.client.renderer;

import net.minecraft.client.renderer.entity.RenderManager;
import tfar.dummyentities.client.model.AbstractDummyModel;
import tfar.dummyentities.entity.GreenDummyEntity;
import tfar.dummyentities.entity.OrangeDummyEntity;

public class GreenEntityRenderer extends GeoLivingEntityRenderer<GreenDummyEntity> {
    public GreenEntityRenderer(RenderManager renderManager) {
        super(renderManager, new AbstractDummyModel<>("green"));
    }
}
