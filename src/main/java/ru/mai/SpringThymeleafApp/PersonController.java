package ru.mai.SpringThymeleafApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PersonController {
    public static final Pattern MATCH = Pattern.compile(
            "");

    public static HashMap<String, Integer> fill(HashMap<String, Integer> map) {
        Matcher matcher;

        HashMap<String, Integer> res = new HashMap<>();

        for (Map.Entry entry: map.entrySet()) {
            matcher = MATCH.matcher(entry.getKey().toString());
            if (matcher.find()) {
                res.put(entry.getKey().toString(), 0);
            } else {
                System.out.println("");
            }

        }

        return res;
    }

    @GetMapping
    String getPeople(Model model) {

        LoggingReading.logReader();
        HashMap<String, Integer> input = LoggingReading.Reader();
        HashMap<String, Integer> map = fill(input);
        ArrayList<Person> person = new ArrayList<>();
        ArrayList<Person> out = new ArrayList<>();
        input.forEach((key, value) -> {
            person.add(new Person(key, value));
        });

        map.forEach((key, value) -> {
            out.add(new Person(key, value));
        });

        model.addAttribute("people", person);

        model.addAttribute("retPeople", out);

        return "people";
    }
}
