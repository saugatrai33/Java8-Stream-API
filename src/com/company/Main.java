package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        /**
         * Filter
         * Female Only
         * */
        femaleOnly();

        /**
         * Sort
         * By age smaller to greater
         * */
//        sortAgeAscending();

        /**
         * All matches
         * everyone above 20
         * */
//        areAllGreaterThan20();

        /**
         * Any Match
         * someone greater than 20
         * */
//        isAnyoneAbove20();

        /**
         * None Match
         * */
//        isAbove20NoneMatch();

        /**
         * MAX
         * Person with the highest age
         * */
//        personWithMaxAge();


        /**
         * MIN
         * Person with min age
         * */
//        personWithMinAge();

        /**
         * GROUP
         * First group with male & then female
         * */
//        groupByGender();

    }

    private static void groupByGender() {
        getPeople().stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .forEach(((gender, peoples) -> {
                    System.out.println(gender);
                    peoples.forEach(System.out::println);
                    System.out.println();
                }));
    }

    private static void personWithMinAge() {
        getPeople().stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("No one with min age"));
    }

    private static void personWithMaxAge() {
        getPeople().stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not fount."));
    }

    private static void isAbove20NoneMatch() {
        boolean noneMatchAbove20 = getPeople().stream()
                .noneMatch(person -> person.getAge() > 20);
        System.out.println(noneMatchAbove20);
    }

    private static void isAnyoneAbove20() {
        boolean anyoneGreaterThan20 = getPeople().stream()
                .anyMatch(person -> person.getAge() > 20);
        System.out.println(anyoneGreaterThan20);
    }

    private static void areAllGreaterThan20() {
        boolean greaterThan20 = getPeople().stream()
                .allMatch(person -> person.getAge() > 20);
        System.out.println(greaterThan20);
    }

    private static void sortAgeAscending() {
        getPeople().stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void femaleOnly() {
        getPeople().stream().filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
