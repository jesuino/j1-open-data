/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.federaltransfer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author william
 */
public class DataLoader {

    /*
     City data file positions
     */
    private static final int POS_CITY_NAME = 0;
    private static final int POS_CITY_HDI = 1;
    private static final int POS_CITY_HDI_INCOME = 2;
    private static final int POS_CITY_HDI_LIFE = 3;
    private static final int POS_CITY_HDI_EDUCATION = 4;
    private static final int POS_CITY_POP = 5;

    /*
     Tranfers data file positions
     */
    final static private int POS_TRANSFER_STATE = 0;
    final static private int POS_TRANSFER_CITY_NAME = 2;
    final static private int POS_TRANSFER_AREA = 4;
    final static private int POS_TRANSFER_GOV_PROGRAM = 8;
    final static private int POS_TRANSFER_ACTION = 10;
    final static private int POS_TRANSFER_AMOUNT = 17;

    /*
     Files and directories
     */
    final static private String TRANFERS_DATA_DIR = DataLoader.class.getResource("/transfer_data").getFile();
    ;
    final static private String CITY_DATA_FILE = "";

    /*
     File Separator
     */
    final static private String SEPARATOR = ";";
    final static private String TRANSFER_SEPARATOR = "\\\t";

    public static List<Transfer> loadAllFederalTranfers() {
        return null;
    }

    public static Optional<CityInfo> getCityHDI(String cityName) throws IOException {
        return Files.lines(Paths.get(CITY_DATA_FILE))
                .skip(3)
                .map(l -> {
                    String[] columns = l.split(SEPARATOR);
                    return new CityInfo(columns[POS_CITY_NAME],
                            Long.parseLong(columns[POS_CITY_HDI]),
                            Long.parseLong(columns[POS_CITY_HDI_INCOME]),
                            Long.parseLong(columns[POS_CITY_HDI_EDUCATION]),
                            Long.parseLong(columns[POS_CITY_HDI_LIFE]),
                            Long.parseLong(columns[POS_CITY_POP]));
                })
                .distinct()
                .findFirst();
    }

    public List<String> allCitiesNames() throws IOException {
        return Files.lines(Paths.get(CITY_DATA_FILE))
                .skip(3)
                .map(l -> l.split(SEPARATOR)[POS_TRANSFER_CITY_NAME])
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Transfer> loadFederalTranfers(String... citiesNames) throws IOException {
        List<Transfer> allTranfers = new ArrayList<>();
        long start = System.currentTimeMillis();
        // Will parallel help here?
        loadDir().parallel().forEach(f -> {
            String date = f.toFile().getName().substring(0, 6);
            LocalDate transferDate = null;// LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMM"));
            loadFileLines(f)
                    .skip(1)
                    .map(l -> getTranferObject(transferDate, l.split(TRANSFER_SEPARATOR)))
                    .filter(t -> citiesNames == null ? true
                            : Stream.of(citiesNames).anyMatch(c -> c.equals(t.getCity())))
                    .forEach(allTranfers::add);
        });
        System.out.printf("*** All files were read and transformed in %d miliseconds\n", System.currentTimeMillis() - start);
        return allTranfers;
    }

    private static Stream<Path> loadDir() {
        try {
            return Files.list(Paths.get(TRANFERS_DATA_DIR));
        } catch (IOException ex) {
            System.err.println("Error loading dir " + TRANFERS_DATA_DIR + ". Exiting.");
            System.exit(0);
            return null;
        }
    }

    private static Stream<String> loadFileLines(Path p) {
        try {
            return Files.lines(p, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.err.println("Error loading file " + p + ". Skipping.");
            return Stream.empty();
        }
    }

    private static Transfer getTranferObject(LocalDate transferDate, String[] columns) {
        int cSize = columns.length;
        String amountStr = POS_TRANSFER_AMOUNT < cSize ? columns[POS_TRANSFER_AMOUNT]
                .replaceAll("\\,", "")
                .split("\\.")[0] : "0";
        String cityName = columns[POS_TRANSFER_CITY_NAME];
        String state = columns[POS_TRANSFER_STATE];
        String area = POS_TRANSFER_AREA < cSize ? columns[POS_TRANSFER_AREA] : "";
        String govProgram = POS_TRANSFER_GOV_PROGRAM < cSize ? columns[POS_TRANSFER_GOV_PROGRAM] : "";
        return new Transfer(transferDate, cityName, state, area, govProgram, Long.parseLong(amountStr));
    }
}
