package com.zyonicsoftware.jensbot.main;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import com.zyonicsoftware.jensbot.twitch.JensTwitchBot;
import com.zyonicsoftware.jensbot.util.Config;
import com.zyonicsoftware.jensbot.util.MySQLManager;

import javax.security.auth.login.LoginException;

public class JensController {

    private JensDiscordBot jensDiscordBot;
    private JensTwitchBot jensTwitchBot;
    private MySQLManager mySQLManager;

    public JensController(Config config) {
        this.initTwitch(config);
        try {
            this.initDiscord(config.getDiscordToken());
            this.mySQLManager = new MySQLManager(this, config.getMySQLHost(), config.getMySQLPort(), config.getMySQLUser(), config.getMySQLPassword(), config.getMySQLDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTwitch(Config config) {
        this.jensTwitchBot = new JensTwitchBot(config.getTwitchAccessToken(), config.getTwitchClientID(), config.getTwitchClientSecret(), this);
    }

    private void initDiscord(String token) throws LoginException {
        this.jensDiscordBot = new JensDiscordBot(token, this);
    }

    public JensDiscordBot getJensDiscordBot() {
        return jensDiscordBot;
    }

    public JensTwitchBot getJensTwitchBot() {
        return jensTwitchBot;
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }
}
