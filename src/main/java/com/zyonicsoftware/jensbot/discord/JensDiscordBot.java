package com.zyonicsoftware.jensbot.discord;

import com.zyonicsoftware.jensbot.discord.commands.*;
import com.zyonicsoftware.jensbot.discord.listener.GuildMemberJoinListener;
import com.zyonicsoftware.jensbot.discord.listener.GuildMessageReceivedListener;
import com.zyonicsoftware.jensbot.discord.listener.GuildMessageUpdateListener;
import com.zyonicsoftware.jensbot.discord.listener.PrivateMessageReceivedListener;
import com.zyonicsoftware.jensbot.main.JensController;
import de.azraanimating.maddoxengine.handling.command.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class JensDiscordBot {

    private final String prefix = "!";

    private final JDA jda;
    private final CommandHandler commandHandler;
    private final JensController jensController;

    public JensDiscordBot(String token, JensController jensController) throws LoginException {
        this.jda = this.initJDA(token);
        this.commandHandler = new CommandHandler();
        this.initPresence();
        this.initCommands();
        this.jensController = jensController;
    }

    private JDA initJDA(String token) throws LoginException {
        return JDABuilder.create(
                token,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_VOICE_STATES
                )
                .enableCache(
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE,
                        CacheFlag.CLIENT_STATUS
                )
                .addEventListeners(
                        new GuildMessageReceivedListener(this),
                        new GuildMessageUpdateListener(this),
                        new GuildMemberJoinListener(this),
                        new PrivateMessageReceivedListener(this)
                )
                .build();
    }

    private void initCommands() {
        this.commandHandler.registerCommands(
                new TwitchCommand(),
                new EmbedCommand(),
                new CreateCommandCommand(this),
                new RemoveCommandCommand(this),
                new CommandListCommand(this)
        );
    }

    private void initPresence() {
        this.jda.getPresence().setActivity(Activity.watching("auf die Community"));
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public JDA getJda() {
        return jda;
    }

    public String getPrefix() {
        return prefix;
    }

    public JensController getJensController() {
        return jensController;
    }
}
