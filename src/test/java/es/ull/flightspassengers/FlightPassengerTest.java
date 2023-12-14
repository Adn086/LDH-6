import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

public class FlightPassengerTest {

    @Test
    void testInvalidFlightNumber() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Flight("AB12", 100);
        });
        assertTrue(exception.getMessage().contains("Invalid flight number"));
    }

    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AB1234", 1);
        Passenger passenger = new Passenger("123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
    }

    @Test
    void testAddPassengerToFullFlight() {
        Flight flight = new Flight("AB1234", 1);
        flight.addPassenger(new Passenger("123", "John Doe", "US"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            flight.addPassenger(new Passenger("456", "Jane Doe", "US"));
        });
        assertTrue(exception.getMessage().contains("Not enough seats"));
    }
}