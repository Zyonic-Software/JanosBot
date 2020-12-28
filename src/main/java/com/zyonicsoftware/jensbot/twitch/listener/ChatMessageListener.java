package com.zyonicsoftware.jensbot.twitch.listener;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.zyonicsoftware.jensbot.main.JensController;
import com.zyonicsoftware.jensbot.twitch.commands.EditorCommands;

public class ChatMessageListener {

    private final JensController jensController;
    private final EditorCommands editorCommands;

    public ChatMessageListener(JensController jensController) {
        this.jensController = jensController;
        this.editorCommands = new EditorCommands(this.jensController);
    }

    @EventSubscriber
    public void onChatMessage(ChannelMessageEvent event) {
        System.out.println(event.getUser().getName() + ": " + event.getMessage());

        if(event.getMessage().toLowerCase().startsWith("!createcommand") || event.getMessage().toLowerCase().startsWith("!cc")) {
            this.editorCommands.createCommand(event);
            return;
        } else if(event.getMessage().toLowerCase().startsWith("!deletecommand") || event.getMessage().toLowerCase().startsWith("!rc")) {
            this.editorCommands.removeCommand(event);
            return;
        }

        if(this.jensController.getMySQLManager().getCommandCache().containsKey(event.getMessage().split(" ")[0].toLowerCase())) {
            this.jensController.getJensTwitchBot().sendMessage(this.jensController.getMySQLManager().getCommandCache().get(event.getMessage().split(" ")[0].toLowerCase()));
        }
    }
}
