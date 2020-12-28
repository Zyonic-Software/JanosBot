package com.zyonicsoftware.jensbot.discord.commands;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;

public class CommandListCommand extends Command {

    private final JensDiscordBot jensDiscordBot;

    public CommandListCommand(JensDiscordBot jensDiscordBot) {
        this.setName("commandlist");
        this.jensDiscordBot = jensDiscordBot;
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        StringBuilder commandlist = new StringBuilder();

        this.jensDiscordBot.getJensController().getMySQLManager().getCommandCache().forEach((commandname, response) -> {
            commandlist.append(commandlist).append("\n");
        });

        sender.openPrivateChannel().sendMessage(commandlist.toString()).queue();
    }
}
