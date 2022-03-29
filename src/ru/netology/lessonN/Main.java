package ru.netology.lessonN;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long countYoung = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(countYoung);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> militaryList = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .filter(x -> x.getSex().toString().equals("MAN"))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(militaryList);

        // Получить отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием в выборке (т.е. людей с высшим образованием от 18
        // до 60 лет для женщин и до 65 лет для мужчин).
        List<Person> workerList = persons.stream()
                .filter(x -> x.getEducation().toString().equals("HIGHER"))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getSex().toString().equals("WOMAN") && x.getAge() <= 60) || (x.getSex().toString().equals("MAN") && x.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(workerList);
    }
}
