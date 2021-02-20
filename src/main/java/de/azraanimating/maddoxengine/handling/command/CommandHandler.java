/*
 * Zyonic Software - 2020 - Tobias Rempe
 * This File, its contents and by extention the corresponding project may be used freely in Compliance with the attached License.
 *
 * info@zyonicsoftware.com
 */

package de.azraanimating.maddoxengine.handling.command;

import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;

import java.util.HashMap;

public class CommandHandler {

    private HashMap<String, Command> commands;

    public CommandHandler() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    public void registerCommands(Command... commands) {
        for (int i = 0 ; i < commands.length ; i++) {
            this.commands.put(commands[i].getName(), commands[i]);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void handle(GuildMessageReceivedEvent event, String messageContent, String prefix) {

        if(this.isMessageEligableForCommandExecution(messageContent, event)) {
            if (messageContent.startsWith(prefix)) {
                String[] args = messageContent.split(" ");

                if (args.length <= 0) {
                    return;
                }

                Command selectedCommand = this.commands.get(args[0].substring(prefix.length()));

                if (selectedCommand != null) {
                    selectedCommand.execute(new CommandEvent(selectedCommand, event, prefix), new MaddoxMember(event.getMember()), new MaddoxGuild(event.getGuild(), prefix));
                }
            } else if(messageContent.startsWith(prefix + " ")){
                String[] args = messageContent.split(" ");

                if (args.length <= 1) {
                    return;
                }

                Command selectedCommand = this.commands.get(args[1]);

                if(selectedCommand != null) {
                    selectedCommand.execute(new CommandEvent(selectedCommand, event, prefix), new MaddoxMember(event.getMember()), new MaddoxGuild(event.getGuild(), prefix));
                }
            }
        }
    }

    public void handle(GuildMessageUpdateEvent event, String messageContent, String prefix) {

        if(this.isMessageEligableForCommandExecution(messageContent, event)) {
            if (messageContent.startsWith(prefix)) {
                String[] args = messageContent.split(" ");

                if (args.length <= 0) {
                    return;
                }

                Command selectedCommand = this.commands.get(args[0].substring(prefix.length()));

                if (selectedCommand != null) {
                    selectedCommand.execute(new CommandEvent(selectedCommand, event, prefix), new MaddoxMember(event.getMember()), new MaddoxGuild(event.getGuild(), prefix));
                }
            } else if(messageContent.startsWith(prefix + " ")){
                String[] args = messageContent.split(" ");

                if (args.length <= 1) {
                    return;
                }

                Command selectedCommand = this.commands.get(args[1]);

                if(selectedCommand != null) {
                    selectedCommand.execute(new CommandEvent(selectedCommand, event, prefix), new MaddoxMember(event.getMember()), new MaddoxGuild(event.getGuild(), prefix));
                }
            }
        }
    }

    public boolean isMessageEligableForCommandExecution(String messageContent, GuildMessageUpdateEvent event) {
        if(messageContent.isEmpty()) {
            return false;
        }

        if(this.commands.isEmpty()) {
            return false;
        }

        return !event.getAuthor().equals(event.getJDA().getSelfUser());
    }

    public boolean isMessageEligableForCommandExecution(String messageContent, GuildMessageReceivedEvent event) {
        if(messageContent.isEmpty()) {
            return false;
        }

        if(this.commands.isEmpty()) {
            return false;
        }

        return !event.getAuthor().equals(event.getJDA().getSelfUser());
    }

}
