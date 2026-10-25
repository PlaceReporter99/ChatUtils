package io.github.placereporter99.chatutils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientChatReceivedEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = ChatUtils.MODID, value = Dist.CLIENT)
public class ChatHook {

    @SubscribeEvent
    public static void onChatMessage(ClientChatReceivedEvent event) {
        var dt = LocalDateTime.now();
        var date = dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
        var time = dt.format(DateTimeFormatter.ISO_LOCAL_TIME);
        var fields = new ArrayList<String>();
        if (Config.ENABLE_TIME.getAsBoolean()) {
            fields.add("Received on " + date + " at " + time);
        }
        MutableComponent messageInfo;
        if (fields.isEmpty()) {
            messageInfo = Component.empty();
        } else {
            messageInfo = Component.literal("[Message Info] ")
                    .withStyle(x -> x.withItalic(true).withColor(ChatFormatting.GRAY))
                    .withStyle(x -> x.withHoverEvent(
                            new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal(String.join("\n", fields)))
                    ));
        }
        MutableComponent message = Component.empty();
        message.append(messageInfo).append(event.getMessage());
        event.setMessage(message);
        ChatUtils.LOGGER.info(message.toString());
    }
}