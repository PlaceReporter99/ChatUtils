package io.github.placereporter99.chatutils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod(value = ChatUtils.MODID, dist = Dist.CLIENT)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_TIME = BUILDER
            .comment("Whether to enable timestamp info.")
            .translation("chatutils.config.enableTimestamp")
            .define("enableTimestamp", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
