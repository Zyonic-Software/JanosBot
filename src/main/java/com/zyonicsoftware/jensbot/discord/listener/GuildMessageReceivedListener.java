package com.zyonicsoftware.jensbot.discord.listener;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import com.zyonicsoftware.jensbot.main.JensController;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceivedListener extends ListenerAdapter {

    private final JensDiscordBot jensDiscordBot;

    public GuildMessageReceivedListener(JensDiscordBot jensDiscordBot) {
        this.jensDiscordBot = jensDiscordBot;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if(event.getAuthor().equals(event.getJDA().getSelfUser())) {
            return;
        }

        if(event.getChannel().getId().equalsIgnoreCase("750355701804564491") || event.getChannel().getId().equalsIgnoreCase("759395529435119657")) {
            return;
        }

        if(this.jensDiscordBot.getJensController().getMySQLManager().getCommandCache().containsKey(event.getMessage().getContentRaw().split(" ")[0].toLowerCase())) {
            event.getChannel().sendMessage(this.jensDiscordBot.getJensController().getMySQLManager().getCommandCache().get(event.getMessage().getContentRaw().split(" ")[0].toLowerCase())).queue();
            return;
        }

        //API Handling
        this.jensDiscordBot.getCommandHandler().handle(event, event.getMessage().getContentRaw(), this.jensDiscordBot.getPrefix());
    }


}
