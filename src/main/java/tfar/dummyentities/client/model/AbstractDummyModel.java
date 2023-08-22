package tfar.dummyentities.client.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import tfar.dummyentities.DummyEntities;
import tfar.dummyentities.entity.DummyEntity;

public class AbstractDummyModel<T extends DummyEntity> extends AnimatedGeoModel<T> {
    private final String prefix;

    public AbstractDummyModel(String prefix) {
        this.prefix = prefix;
    }

        public ResourceLocation getModelLocation(T object) {
            return new ResourceLocation(DummyEntities.MODID, "geo/"+prefix+".geo.json");
        }

        public ResourceLocation getTextureLocation(T object) {
            return new ResourceLocation(DummyEntities.MODID, "textures/model/entity/"+prefix+".png");
        }

        public ResourceLocation getAnimationFileLocation(T animatable) {
            return new ResourceLocation(DummyEntities.MODID, "animations/"+prefix+".animation.json");
        }
}
