package com.zyonicsoftware.jensbot.twitch.commands;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.zyonicsoftware.jensbot.main.JensController;

public class EditorCommands {

    private final JensController jensController;

    public EditorCommands(JensController jensController) {
        this.jensController = jensController;
    }

    public void createCommand(ChannelMessageEvent event) {
        if(event.getPermissions().contains(CommandPermission.MODERATOR) || event.getUser().getName().equals("azraanimating")) {
            String commandName = event.getMessage().substring("!createcommand ".length()).split(" ")[0].toLowerCase();
            String commandResponse = event.getMessage().substring("!createcommand ".length() + commandName.length() + 1);

            this.jensController.getMySQLManager().addNewCommand(commandName, commandResponse);
            this.jensController.getJensTwitchBot().sendMessage("Command hinzugefügt!");
        }
    }

    public void removeCommand(ChannelMessageEvent event) {
        if(event.getPermissions().contains(CommandPermission.MODERATOR) || event.getUser().getName().equals("azraanimating")) {
            String commandName = event.getMessage().substring("!removecommand ".length()).split(" ")[0].toLowerCase();

            this.jensController.getMySQLManager().removeCommand(commandName);
            this.jensController.getJensTwitchBot().sendMessage("Command entfernt!");
        }
    }

}
