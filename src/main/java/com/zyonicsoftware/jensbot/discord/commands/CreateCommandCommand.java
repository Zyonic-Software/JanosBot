package com.zyonicsoftware.jensbot.discord.commands;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import com.zyonicsoftware.jensbot.main.JensController;
import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.Permission;

public class CreateCommandCommand extends Command {

    private final JensDiscordBot jensDiscordBot;

    public CreateCommandCommand(JensDiscordBot jensDiscordBot) {
        this.setName("cc");
        this.jensDiscordBot = jensDiscordBot;
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        if(sender.hasPermission(Permission.MANAGE_CHANNEL)) {
            if(event.getArguments().size() > 0) {
                String newCommand = event.getArguments().get(0).toLowerCase();
                String newCommandFeedback = event.getArgumentsAsString().substring(newCommand.length() + 1);

                this.jensDiscordBot.getJensController().getMySQLManager().addNewCommand(newCommand, newCommandFeedback);
                event.reply("Neuer Command hinzugefügt!");
            } else {
                event.reply("Bitte gebe einen neuen Command und seinen Rückgabewert an!");
            }
        }
    }
}