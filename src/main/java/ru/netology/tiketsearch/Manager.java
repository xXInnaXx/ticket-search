package ru.netology.tiketsearch;

import ru.netology.tiketsearch.domain.Ticket;
import ru.netology.tiketsearch.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager {
    private TicketRepository ticketRepository;

    public Manager(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void saveAllTickets(Ticket[] products) {
        for (Ticket product : products) {
            ticketRepository.save(product);
        }
    }

    public Ticket[] findAll(String from, String to) {
        List<Ticket> foundTickets = new ArrayList<>();
        Ticket[] all = ticketRepository.findAll();
        for (Ticket ticket : all) {
            if (ticket.getFrom().equals(from) && ticket.getTo().equals(to)) {
                foundTickets.add(ticket);
            }
        }
        if (foundTickets.isEmpty()) {
            throw new NotFoundException("Ticket for from=" + from + " and to=" + to + " was not found");
        }
        Collections.sort(foundTickets);
        return foundTickets.toArray(new Ticket[0]);
    }
}
