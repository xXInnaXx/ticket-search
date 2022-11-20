package ru.netology.ticketmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.tiketsearch.Manager;
import ru.netology.tiketsearch.NotFoundException;
import ru.netology.tiketsearch.domain.Ticket;
import ru.netology.tiketsearch.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketManagerUnitTest {
    private static final String TEST_ID = "1234";
    private static final int ID = 1;
    private static final String DME = "DME";
    private static final String LED = "LED";

    @Test
    void shouldFindByFromAndToSorted() {
        var ticketDmeLed = new Ticket(TEST_ID, DME, LED, 25, 120);
        var ticketDmeLedCheap = new Ticket(TEST_ID + 1, DME, LED, 15, 120);
        var ticketVkoLed = new Ticket(TEST_ID + 1, "VKO", LED, 15, 120);

        TicketRepository ticketRepository = mock(TicketRepository.class);

        when(ticketRepository.findAll()).thenReturn(new Ticket[]{ticketDmeLed, ticketDmeLedCheap, ticketVkoLed});

        Manager manager = new Manager(ticketRepository);

        var expected = manager.findAll(DME, LED);

        assertEquals(expected.length, 2);
        Ticket ticket = expected[0];
        Ticket second = expected[1];
        assertEquals(ticket.getPrice(), 15);
        assertEquals(ticket.getFrom(), DME);
        assertEquals(second.getPrice(), 25);
        assertEquals(second.getFrom(), DME);
    }

    @Test
    void shouldThrowNotFoundForDirections() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.findAll()).thenReturn(new Ticket[0]);
        Manager manager = new Manager(ticketRepository);

        assertThrows(NotFoundException.class, () -> manager.findAll(DME, LED),
                "Ticket for from=DME and to=LED was not found");
    }
}