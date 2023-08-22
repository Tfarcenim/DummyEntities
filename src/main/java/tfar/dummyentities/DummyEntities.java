package tfar.dummyentities;

import mchorse.metamorph.api.MorphAPI;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.api.morphs.EntityMorph;
import mchorse.metamorph.capabilities.morphing.IMorphing;
import mchorse.metamorph.capabilities.morphing.Morphing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import software.bernie.geckolib3.GeckoLib;
import tfar.dummyentities.client.renderer.BlueEntityRenderer;
import tfar.dummyentities.client.renderer.GreenEntityRenderer;
import tfar.dummyentities.client.renderer.OrangeEntityRenderer;
import tfar.dummyentities.client.renderer.RedEntityRenderer;
import tfar.dummyentities.entity.*;
import tfar.dummyentities.network.AttackEmptyPacket;
import tfar.dummyentities.network.PacketHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(modid = DummyEntities.MODID,name = "Dummy Entities",version = "@VERSION@")
@Mod.EventBusSubscriber
public class DummyEntities {
    public static final String MODID = "dummyentities";

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> e) {

        final ResourceLocation red = new ResourceLocation(MODID, "red");

        e.getRegistry().register(
                EntityEntryBuilder.create()
                        .entity(RedDummyEntity.class)
                        .id(red, 0)
                        .name(red.getPath())
                        .tracker(64, 1, true)
                        .build());

        final ResourceLocation orange = new ResourceLocation(MODID, "orange");

        e.getRegistry().register(
                EntityEntryBuilder.create()
                        .entity(OrangeDummyEntity.class)
                        .id(orange, 0)
                        .name(orange.getPath())
                        .tracker(64, 1, true)
                        .build());

        final ResourceLocation green = new ResourceLocation(MODID, "green");

        e.getRegistry().register(
                EntityEntryBuilder.create()
                        .entity(GreenDummyEntity.class)
                        .id(green, 0)
                        .name(green.getPath())
                        .tracker(64, 1, true)
                        .build());

        final ResourceLocation blue = new ResourceLocation(MODID, "blue");

        e.getRegistry().register(
                EntityEntryBuilder.create()
                        .entity(BlueDummyEntity.class)
                        .id(blue, 0)
                        .name(blue.getPath())
                        .tracker(64, 1, true)
                        .build());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        PacketHandler.registerMessages();
        GeckoLib.initialize();
    }

    @SubscribeEvent
    public static void startup(PlayerEvent.PlayerLoggedInEvent e) {
        acquire(e.player, "red");
        acquire(e.player, "orange");
        acquire(e.player, "green");
        acquire(e.player, "blue");
    }

    public static void acquire(EntityPlayer player,String s) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString("Name",MODID+":"+s);
        MorphAPI.acquire(player, MorphManager.INSTANCE.morphFromNBT(nbtTagCompound),false);
    }

    @SubscribeEvent
    public static void swingHand(PlayerInteractEvent.LeftClickEmpty e) {
        IMorphing morphing = Morphing.get(e.getEntityPlayer());
        AbstractMorph abstractMorph = morphing.getCurrentMorph();
        if (abstractMorph instanceof EntityMorph) {
            EntityMorph entityMorph = (EntityMorph) abstractMorph;
            if (entityMorph.getEntity() instanceof DummyEntity) {
                PacketHandler.INSTANCE.sendToServer(new AttackEmptyPacket());
                ((DummyEntity) entityMorph.getEntity()).startSwing();
            }
        }
    }

    @SubscribeEvent
    public static void attack(AttackEntityEvent e) {
        IMorphing morphing = Morphing.get(e.getEntityPlayer());
        AbstractMorph abstractMorph = morphing.getCurrentMorph();
        if (abstractMorph instanceof EntityMorph) {
            EntityMorph entityMorph = (EntityMorph) abstractMorph;
            if (entityMorph.getEntity() instanceof DummyEntity) {
                ((DummyEntity) entityMorph.getEntity()).startSwing();
            }
        }
    }
}
