package com.zyonicsoftware.jensbot.twitch;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;
import com.github.twitch4j.chat.TwitchChat;
import com.github.twitch4j.chat.TwitchChatBuilder;
import com.zyonicsoftware.jensbot.main.JensController;
import com.zyonicsoftware.jensbot.twitch.listener.ChatMessageListener;

public class JensTwitchBot {

    private final TwitchClient twitchClient;
    private final OAuth2Credential oAuth2Credential;
    private final CredentialManager credentialManager;
    private final TwitchChat twitchChat;
    private final JensController jensController;
    private final String channel = "janos_yt";

    public JensTwitchBot(final String accessToken, final String clientiD, final String clientSecret, final JensController jensController) {
        System.out.println("Twitch-Module Startup");
        System.out.println(accessToken);
        System.out.println(clientiD);

        this.oAuth2Credential = new OAuth2Credential("twitch", accessToken);
        this.twitchClient = TwitchClientBuilder.builder().withEnableChat(true).withEnableHelix(true).withEnableKraken(true).build();
        this.credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(new TwitchIdentityProvider(clientiD, clientSecret, ""));//ahiy7870an1qfsscf15ag2v1gormyfwy5zf915e7glhvkh783j
        this.twitchChat = TwitchChatBuilder.builder().withChatAccount(this.oAuth2Credential).withClientSecret(clientSecret).withCredentialManager(credentialManager).build();
        this.jensController = jensController;
        this.attachToChat();
    }


    private void attachToChat() {
        twitchChat.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new ChatMessageListener(jensController));

        twitchChat.joinChannel("janos_yt");
    }

    public void sendMessage(final String message) {
        twitchChat.sendMessage(channel, message);
    }

    public JensController getJensController() {
        return jensController;
    }
}
