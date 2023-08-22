package tfar.dummyentities.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class DummyEntity extends EntityLiving implements IAnimatable {
    public DummyEntity(World worldIn) {
        super(worldIn);
    }

    AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> ATTACK = EntityDataManager.createKey(DummyEntity.class, DataSerializers.VARINT);


    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        int attacking = isAttacking();
        if (isSwingInProgress || attacking > 0) {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("attack", true));
        }
        else if (event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F) {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", true));
        } else {
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("walk", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(ATTACK, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        int attack = isAttacking();
        if (attack > 0) {
            setAttacking(attack - 1);
        }
    }

    public void startSwing() {
        setAttacking(16);
    }

    public void setAttacking(int attack) {
        dataManager.set(ATTACK, attack);
    }

    public int isAttacking() {
        return dataManager.get(ATTACK);
    }

    @Override
    public int getArmSwingAnimationEnd() {
        return super.getArmSwingAnimationEnd() + 20;
    }

    public AnimationFactory getFactory() {
        return this.factory;
    }

}
