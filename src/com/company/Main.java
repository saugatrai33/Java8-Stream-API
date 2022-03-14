package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        /**
         * Filter
         * Female Only
         * */
        femaleOnly.apply(persons.get())
                .forEach(System.out::println);

        /**
         * Sort
         * By age smaller to greater
         * */
        everyoneGreaterThan20.apply(persons.get());

        /**
         * All matches
         * everyone above 20
         * */
        System.out.println("is everybody greater than 20 -> "
                + everyoneGreaterThan20.apply(persons.get()));

        /**
         * Any Match
         * someone greater than 20
         * */
        System.out.println("Someone greater than 20 -> "
                + isAnyoneAbove20.apply(persons.get()));

        /**
         * None Match
         * */
        System.out.println("is no one greater than 20 -> "
                + isAbove20NoneMatch.apply(persons.get()));

        /**
         * MAX
         * Person with the highest age
         * */
        oldestPerson.accept(persons.get());

        /**
         * MIN
         * Person with min age
         * */
        youngestPerson.accept(persons.get());

        /**
         * GROUP
         * First group with male & then female
         * */
        groupByGender.apply(persons.get())
                .forEach((gender, peoples) -> {
                    System.out.println(gender);
                    peoples.forEach(System.out::println);
                    System.out.println();
                });

    }

    private static Function<List<Person>, Map<Gender, List<Person>>> groupByGender =
            people -> people
                    .stream()
                    .collect(Collectors.groupingBy(Person::getGender));

    private static Consumer<List<Person>> youngestPerson =
            people -> people
                    .stream()
                    .min(Comparator.comparing(Person::getAge))
                    .ifPresent(System.out::println);

    private static Consumer<List<Person>> oldestPerson =
            people -> people
                    .stream()
                    .max(Comparator.comparing(Person::getAge))
                    .ifPresent(System.out::println);

    private static Function<List<Person>, Boolean> isAbove20NoneMatch =
            people -> people
                    .stream()
                    .noneMatch(person -> person.getAge() > 20);

    private static Function<List<Person>, Boolean> isAnyoneAbove20 =
            people -> people
                    .stream()
                    .anyMatch(person -> person.getAge() > 20);

    private static Predicate<Person> greaterThan20 =
            person -> person.getAge() > 20;

    private static Function<List<Person>, Boolean> everyoneGreaterThan20 =
            people -> people
                    .stream()
                    .allMatch(greaterThan20);

    private static Function<List<Person>, List<Person>> ageSorted =
            people -> people
                    .stream()
                    .sorted(Comparator.comparing(Person::getAge))
                    .collect(Collectors.toList());

    private static Function<List<Person>, List<Person>> femaleOnly =
            people -> people
                    .stream()
                    .sorted(Comparator.comparing(Person::getAge))
                    .collect(Collectors.toList());

    private static Supplier<List<Person>> persons =
            () -> List.of(
                    new Person("Antonio", 20, Gender.MALE),
                    new Person("Alina Smith", 33, Gender.FEMALE),
                    new Person("Helen White", 57, Gender.FEMALE),
                    new Person("Alex Boz", 14, Gender.MALE),
                    new Person("Jamie Goa", 99, Gender.MALE),
                    new Person("Anna Cook", 7, Gender.FEMALE),
                    new Person("Zelda Brown", 120, Gender.FEMALE)
            );
}
