package org.jugvale.federaltransfer;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author william
 */
public class App {

    public static void main(String[] args) throws IOException {
        long start;
        int TOTAL_CITIES = 100;
        /*
         Count transfer for each City
         */
        start = System.currentTimeMillis();
        // Will parallel help here?
        Map<String, Long> citiesTransferCount
                = DataLoader.loadFederalTranfers(null).stream()
                .sorted(Comparator.comparing(Transfer::getValue).reversed())
                .collect(Collectors.groupingBy(
                                t -> t.getCity(),
                                Collectors.counting()
                        ));
        citiesTransferCount.entrySet().stream()
                .forEach(e -> System.out.printf("City \"%s\" had %d transfers\n", e.getKey(), e.getValue()));
        System.out.printf("**** Tranfers count finished in %d \n", System.currentTimeMillis() - start);

        /*
         Now we will group details of the transfers
         */
        String[] mainCities
                = citiesTransferCount.keySet().stream()
                .limit(TOTAL_CITIES).toArray(String[]::new);

        List<Transfer> allTransfers = DataLoader.loadFederalTranfers(mainCities);
        // will parallel help here?
        start = System.currentTimeMillis();
        Stream.of(mainCities).forEach(city -> {
            // All the money per area for the city
            Map<String, LongSummaryStatistics> summary = allTransfers.stream()
                    .filter(t -> t.getCity().equals(city))
                    .collect(Collectors.groupingBy(
                                    t -> t.getArea(),
                                    Collectors.summarizingLong(Transfer::getValue)
                            ));
            System.out.println("** Federal Government money transfers to city " + city + " **");
            summary.entrySet().forEach(e -> {
                System.out.printf("Investiment on %s\n", e.getKey());
                LongSummaryStatistics statistics = e.getValue();
                System.out.printf("\tMax: %d\n", statistics.getMax());
                System.out.printf("\tMin: %d\n", statistics.getMin());
            });
            System.out.println("\n");
        });
        System.out.printf("**** Tranfers grouped and summed in %s mili\n", System.currentTimeMillis() - start);

    }
}
