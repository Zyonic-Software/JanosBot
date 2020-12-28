package com.zyonicsoftware.jensbot.discord.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;

public class TwitchCommand extends Command {

    public TwitchCommand() {
        this.setName("twitch");
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        event.reply("https://www.twitch.tv/janos_yt");
    }
}
