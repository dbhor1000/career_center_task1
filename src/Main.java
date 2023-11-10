import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.FlightFilter;

public class Main {
    public static void main(String[] args) {

        System.out.println(FlightFilter.sortOutFlightsBeforeNow(FlightBuilder.createFlights()));
        System.out.println(FlightFilter.sortOutFlightsBySegment(FlightBuilder.createFlights()));
        System.out.println(FlightFilter.sortOutFlightsTwoHoursGround(FlightBuilder.createFlights()));

    }
}