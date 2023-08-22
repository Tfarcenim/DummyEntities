package tfar.dummyentities.network;

import io.netty.buffer.ByteBuf;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.api.morphs.EntityMorph;
import mchorse.metamorph.capabilities.morphing.IMorphing;
import mchorse.metamorph.capabilities.morphing.Morphing;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tfar.dummyentities.entity.DummyEntity;

// not threadsafe!
public class AttackEmptyPacket implements IMessage {

  public AttackEmptyPacket() {
  }

  @Override
  public void fromBytes(ByteBuf buf) {
  }

  @Override
  public void toBytes(ByteBuf buf) {
  }

  public static class Handler implements IMessageHandler<AttackEmptyPacket, IMessage> {
    @Override
    public IMessage onMessage(AttackEmptyPacket message, MessageContext ctx) {
      // Always use a construct like this to actually handle your message. This ensures that
      // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
      // is called on the networking thread so it is not safe to do a lot of things
      // here.
      FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
      return null;
    }

    private void handle(AttackEmptyPacket message, MessageContext ctx) {
      // This code is run on the server side. So you can do server-side calculations here
      EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
      IMorphing iMorphing = Morphing.get(entityPlayerMP);
      AbstractMorph abstractMorph = iMorphing.getCurrentMorph();
      if (abstractMorph instanceof EntityMorph) {
        EntityMorph entityMorph = (EntityMorph) abstractMorph;
        if (entityMorph.getEntity() instanceof DummyEntity) {
          DummyEntity dummyEntity = (DummyEntity) entityMorph.getEntity();
          dummyEntity.startSwing();
        }
      }
    }
  }
}