package com.ticketbot;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TicketButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("close_ticket")) {
            TicketManager ticketManager = new TicketManager();
            ticketManager.closeTicket(event.getTextChannel(), event.getMember());
            event.reply("Ticket chiuso!").queue();
        }
    }
}
