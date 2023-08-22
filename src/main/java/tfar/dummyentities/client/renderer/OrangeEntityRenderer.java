package tfar.dummyentities.client.renderer;

import net.minecraft.client.renderer.entity.RenderManager;
import tfar.dummyentities.client.model.AbstractDummyModel;
import tfar.dummyentities.entity.OrangeDummyEntity;

public class OrangeEntityRenderer extends GeoLivingEntityRenderer<OrangeDummyEntity> {
    public OrangeEntityRenderer(RenderManager renderManager) {
        super(renderManager, new AbstractDummyModel<>("orange"));
    }
}
