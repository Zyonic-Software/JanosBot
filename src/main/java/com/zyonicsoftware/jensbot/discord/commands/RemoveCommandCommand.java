package com.zyonicsoftware.jensbot.discord.commands;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.Permission;

public class RemoveCommandCommand extends Command {

    private final JensDiscordBot jensDiscordBot;

    public RemoveCommandCommand(JensDiscordBot jensDiscordBot) {
        this.setName("rc");
        this.jensDiscordBot = jensDiscordBot;
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        if(sender.hasPermission(Permission.MANAGE_CHANNEL)) {
            if(event.getArguments().size() > 0) {
                this.jensDiscordBot.getJensController().getMySQLManager().removeCommand(event.getArguments().get(0));
                event.reply("Command entfernt!");
            }
        }
    }
}
