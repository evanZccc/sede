package com.ticketbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class TicketBot {

    public static void main(String[] args) throws LoginException {
        // Carica configurazione
        Config.loadConfig();
        
        // Avvio del bot
        JDABuilder builder = JDABuilder.createDefault(Config.TOKEN)
                .setActivity(Activity.playing("Gestione ticket"))
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

        builder.addEventListeners(new TicketButtonListener(), new TicketManager());
        builder.build();
    }
}
