import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> young = persons.stream();
        long count =persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        Stream<Person> recruit = persons.stream();
        List<String> result = persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(result);

        Stream<Person> workable = persons.stream();
        List<Person> list = persons.stream()
                 .filter(x -> x.getEducation() == Education.HIGHER)
                 .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getSex().equals(Sex.MAN) ? x.getAge()<= 65 : x.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(list);

    }
}