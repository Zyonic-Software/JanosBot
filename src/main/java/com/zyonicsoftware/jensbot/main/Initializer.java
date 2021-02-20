package com.zyonicsoftware.jensbot.main;

import com.zyonicsoftware.jensbot.discord.JensDiscordBot;
import com.zyonicsoftware.jensbot.restapi.Application;
import com.zyonicsoftware.jensbot.twitch.JensTwitchBot;
import com.zyonicsoftware.jensbot.util.Config;
import com.zyonicsoftware.jensbot.util.ConfigReader;
import org.checkerframework.checker.units.qual.C;

import javax.security.auth.login.LoginException;

public class Initializer {

    public static void main(String[] args) {
        Config config = new Config();
        ConfigReader configReader = new ConfigReader();
        configReader.loadConfig(config);
        Application application = new Application();
        application.startSpring(config.getPort());
        JensController jensController = new JensController(config);
    }

}