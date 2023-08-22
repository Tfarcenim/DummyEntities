package tfar.dummyentities.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tfar.dummyentities.DummyEntities;

public class PacketHandler {

  public static SimpleNetworkWrapper INSTANCE = null;

  public PacketHandler() {
  }
  public static void registerMessages() {
    INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(DummyEntities.MODID);
    // Register messages which are sent from the client to the server here:
    INSTANCE.registerMessage(AttackEmptyPacket.Handler.class, AttackEmptyPacket.class, 0, Side.SERVER);
  }
}