package com.zyonicsoftware.jensbot.discord.listener;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageUpdateListener extends ListenerAdapter {

    private final JensDiscordBot jensDiscordBot;

    public GuildMessageUpdateListener(JensDiscordBot jensDiscordBot) {
        this.jensDiscordBot = jensDiscordBot;
    }

    public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {

        //Api Handling
        this.jensDiscordBot.getCommandHandler().handle(event, event.getMessage().getContentRaw(), this.jensDiscordBot.getPrefix());
    }

}
