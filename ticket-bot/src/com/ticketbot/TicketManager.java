package com.ticketbot;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.HashSet;
import java.util.Set;

public class TicketManager {

    private static final Set<String> userTickets = new HashSet<>();

    public void createTicket(Member member, TextChannel supportChannel) {
        if (userTickets.contains(member.getId())) {
            supportChannel.sendMessage("Hai già un ticket aperto!").queue();
            return;
        }

        // Crea un nuovo canale per il ticket
        Category category = supportChannel.getGuild().getCategoryById(Config.TICKET_CATEGORY_ID);
        if (category == null) {
            supportChannel.sendMessage("Categoria 'Ticket' non trovata!").queue();
            return;
        }

        String ticketChannelName = "ticket-" + member.getUser().getName().toLowerCase();
        ChannelAction<TextChannel> action = supportChannel.getGuild().createTextChannel(ticketChannelName, category);
        action.queue(channel -> {
            // Aggiungi il membro al canale
            channel.putPermissionOverride(supportChannel.getGuild().getPublicRole())
                    .setDeny(Permission.MESSAGE_READ)
                    .queue();
            channel.putPermissionOverride(member)
                    .setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE)
                    .queue();

            // Messaggio del bot nel nuovo canale
            channel.sendMessage("Benvenuto nel tuo ticket di supporto, " + member.getAsMention() + "!")
                    .setActionRow(Button.danger("close_ticket", "Chiudi ticket"))
                    .queue();

            userTickets.add(member.getId());
        });
    }

    public void closeTicket(TextChannel ticketChannel, Member member) {
        if (ticketChannel.getMembers().contains(member)) {
            ticketChannel.delete().queue();
            userTickets.remove(member.getId());
        }
    }
}
