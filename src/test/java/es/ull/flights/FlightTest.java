package es.ull.flights;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import es.ull.passengers.Passenger;

public class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    public void setUp() {
        flight = new Flight("AA123", 100);
        passenger = new Passenger("12345", "John Doe", "US");
    }

    @Test
    public void testValidFlightNumber() {
        assertEquals("AA123", flight.getFlightNumber());
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> {
            new Flight("123AB", 100);
        });
    }

    @Test
    public void testAddPassenger() {
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testRemovePassenger() {
        flight.addPassenger(passenger);
        flight.removePassenger(passenger);
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddingPassengerBeyondCapacity() {
        Flight smallFlight = new Flight("AA124", 1);
        smallFlight.addPassenger(passenger);
        assertThrows(RuntimeException.class, () -> {
            smallFlight.addPassenger(new Passenger("54321", "Jane Doe", "US"));
        });
    }
}
