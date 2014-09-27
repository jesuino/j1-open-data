/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.surveyexplorer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author william
 */
public class SurveyData {

    static final String FILE_SURVEY = AppCommandLine.class.getResource("/ipea_data.csv").getFile();
    final static String SEPARATOR = ";";

    public static Map<String, Map<String, Integer>> load() throws Exception {
        Map<String, Map<String, Integer>> results = new HashMap<>();
        Path filePath = Paths.get(FILE_SURVEY);
        String[] questions = Files.lines(filePath).findFirst().get().split(SEPARATOR);
        Stream.of(questions).forEach(c -> results.put(c, new HashMap<>()));
        Files.lines(filePath).skip(1).forEach(l -> {
            String[] anwers = l.split(SEPARATOR);
            for (int i = 1; i < anwers.length; i++) {
                results.get(questions[i]).compute(anwers[i], (o, n) -> {
                    return n == null ? 1 : ++n;
                });
            }
        });
        return results;
    }
}
