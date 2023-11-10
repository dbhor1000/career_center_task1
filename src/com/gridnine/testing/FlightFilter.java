package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightFilter {

    //Исключите из тестового набора перелёты по следующим правилам (по каждому правилу нужен отдельный вывод списка перелётов):
    //
    //    -Вылет до текущего момента времени.
    //    -Сегменты с датой прилёта раньше даты вылета.
    //    -Перелеты, где общее время, проведённое на земле, превышает два
    //    часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).


    public static List<Flight> sortOutFlightsBeforeNow(List<Flight> flights) {
        List<Flight> outputList = new LinkedList<>(flights);

        for (Flight flight : flights) {
            if (flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())) {
                outputList.remove(flight);
            }
        }
        return outputList;
    }

    public static List<Flight> sortOutFlightsBySegment(List<Flight> flights) {
        List<Flight> outputList = new LinkedList<>(flights);

        for (Flight flight : flights) {
            int amountOfSegments = flight.getSegments().size();
            while (amountOfSegments > 0) {
                if ((flight.getSegments().get(amountOfSegments-1).getDepartureDate()).isAfter(flight.getSegments().get(amountOfSegments-1).getArrivalDate())) {
                    outputList.remove(flight);
                }
                amountOfSegments--;
            }
        }

        return outputList;
    }

    public static List<Flight> sortOutFlightsTwoHoursGround(List<Flight> flights) {

        List<Flight> outputList = new ArrayList<>(flights);

        for (Flight flight : flights) {

            List<Segment> segments = new ArrayList<>(flight.getSegments());
            int amountOfSegments = segments.size();
            long totalGroundTime = 0;

            while (amountOfSegments >= 2) {
                LocalDateTime nextTakeOff = flight.getSegments().get(amountOfSegments-1).getDepartureDate();
                LocalDateTime lastArrival = flight.getSegments().get(amountOfSegments-2).getArrivalDate();
                long minutes = ChronoUnit.MINUTES.between(lastArrival, nextTakeOff);
                totalGroundTime = totalGroundTime + minutes;
                amountOfSegments--;
            }

            if (totalGroundTime >= 120) {
                outputList.remove(flight);
            }
        }
        return outputList;
    }

}
