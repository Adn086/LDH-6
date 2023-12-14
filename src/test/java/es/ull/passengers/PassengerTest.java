package es.ull.passengers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import es.ull.flights.Flight;

public class PassengerTest {

    private Passenger passenger;
    private Flight flight;

    @BeforeEach
    public void setUp() {
        passenger = new Passenger("12345", "John Doe", "US");
        flight = new Flight("AA123", 100);
    }

    @Test
    public void testValidPassengerInfo() {
        assertEquals("12345", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> {
            new Passenger("12345", "John Doe", "XX");
        });
    }

    @Test
    public void testJoiningFlight() {
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    public void testLeavingFlight() {
        passenger.joinFlight(flight);
        passenger.joinFlight(null);
        assertNull(passenger.getFlight());
    }

    @Test
    public void testSwitchingFlights() {
        Flight newFlight = new Flight("BB456", 100);
        passenger.joinFlight(flight);
        passenger.joinFlight(newFlight);
        assertEquals(newFlight, passenger.getFlight());
    }
}
