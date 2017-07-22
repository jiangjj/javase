package corejava2.chapter01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jiangjiajie on 2017/7/19.
 */
public class DownstreamCollectors {
    public static class City{
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    ", population=" + population +
                    '}';
        }
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l -> l.split(", ")).map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = Stream.of(new City("beijing", "ch", 11111), new City("shanghai", "ch", 22222), new City("guangzhou", "ch", 33333));
        Map<String, Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);

        cities = Stream.of(new City("beijing", "ch", 11111), new City("shanghai", "ch", 22222), new City("guangzhou", "ch", 33333));
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("countryToLanguages: " + countryToLanguages);

        cities = Stream.of(new City("beijing", "ch", 11111), new City("shanghai", "ch", 22222), new City("guangzhou", "ch", 33333));
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopulationSummary.get("ch"));

        cities = Stream.of(new City("beijing", "ch", 11111), new City("shanghai", "ch", 22222), new City("guangzhou", "ch", 33333));
        Map<String, String> stateToCityNames = cities.collect(Collectors.groupingBy(City::getState, Collectors.reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));

        cities = Stream.of(new City("beijing", "ch", 11111), new City("shanghai", "ch", 22222), new City("guangzhou", "ch", 33333));
        stateToCityNames = cities.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.joining(", "))));
        System.out.println("stateToCityNames: " + stateToCityNames);
    }
}
