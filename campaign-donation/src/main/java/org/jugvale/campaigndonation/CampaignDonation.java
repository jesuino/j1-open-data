/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.campaigndonation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author william
 */
public class CampaignDonation {

    private final static String DATA_DIR = CampaignDonation.class.getResource("/data").getFile();
    private final static String SEPARATOR = ";";

    private final static int DONATOR_INDEX = 0;
    private final static int AMOUNT_INDEX = 6;
    private final static int CANDIDATE_INDEX = 9;

    public static Map<String, Set<Donation>> load() throws IOException {
        Map<String, Set<Donation>> donations = new HashMap<>();
        Files.list(Paths.get(DATA_DIR))
                .peek(f -> System.out.println("Processing " + f.getFileName()))
                .forEach(p -> {
                    try {
                        Files.lines(p, StandardCharsets.ISO_8859_1).skip(1).forEach(l -> {
                            String[] columns = l.split(SEPARATOR);
                            String candidateName = columns[CANDIDATE_INDEX];
                            String donatorName = columns[DONATOR_INDEX];
                            String formatedAmount = columns[AMOUNT_INDEX]
                            .replaceAll("R\\$\\ ", "")
                            .split("\\,")[0]
                            .replaceAll("\\.", "");
                            long amount = Long.parseLong(formatedAmount);
                            Set<Donation> cDonations = donations.getOrDefault(candidateName, new HashSet<>());
                            Donation donation = cDonations.stream()
                            .filter(d -> d.getDonorName().equals(donatorName))
                            .findFirst().orElse(new Donation(donatorName));
                            donation.setAmount(donation.getAmount() + amount);
                            cDonations.add(donation);
                            donations.put(candidateName, cDonations);
                        });
                    } catch (IOException e) {
                        System.err.println("Error loading " + p);
                    }
                });
        return donations;
    }
}
