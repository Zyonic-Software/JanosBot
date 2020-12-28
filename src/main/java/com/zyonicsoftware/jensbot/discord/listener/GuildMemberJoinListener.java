package com.zyonicsoftware.jensbot.discord.listener;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberJoinListener extends ListenerAdapter {

    private final JensDiscordBot jensDiscordBot;

    public GuildMemberJoinListener(JensDiscordBot jensDiscordBot) {
        this.jensDiscordBot = jensDiscordBot;
    }

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel welcomeChannel = event.getGuild().getTextChannelById("667434391911530512");
        welcomeChannel.sendMessage("Moin " + event.getMember().getAsMention() + ", viel spaß auf Janos Community Discord :tada::hugging: !\nLies dir bitte die Regeln in " + event.getGuild().getTextChannelById("667434396743237632").getAsMention() + " durch.").queue();
        event.getGuild().addRoleToMember(event.getMember().getId(), event.getGuild().getRoleById("667422298684391458")).queue();
    }
}