package com.zyonicsoftware.jensbot.discord.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedCommand extends Command {

    public EmbedCommand() {
        this.setName("embed");
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        if(sender.getID().equals("309007975060602882") || sender.getID().equals("296303486121803776")) {
            String[] msgArgs = event.getArgumentsAsString().split(";");
            event.reply(
                    new EmbedBuilder()
                            .setColor(Color.decode(msgArgs[3]))
                            .setTitle(msgArgs[0])
                            .addField(msgArgs[1], msgArgs[2], false)
                    .build()
            );
            event.deleteEventMessage();
        }
    }
}
