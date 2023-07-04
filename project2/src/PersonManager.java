import Constants.Category;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class PersonManager {
    public static List<Person> people = new ArrayList<>();

    public void addPerson(Person person){
        people.add(person);
    }

    public Optional<Person> findById(int id){
      return people.stream().filter(person -> person.getId() == id).findFirst();
    }

    public void findByName(String name){
        people.stream().filter(person -> person.getName().equals(name)).forEachOrdered(System.out::println);
    }

    public void deletePerson(int id){

        Optional<Person> person = findById(id);

        if(people.remove(person)){
            System.out.println("The user: " + person.get().getName() + " has been deleted!");
        }else{
            System.out.println("There is no such user with provided id!");
        }
    }

    public static void deletePersonByCategory(Category category){
        List<Person> personList = people.stream()
                .filter(person -> category.equals(person.getCategory())).collect(Collectors.toList());

        if(personList.size() == 0){
            System.out.println("No person to be deleted in category " + category);

        }else{
            personList.forEach(System.out::println);
            System.out.println("The listed people in " + category + " will be deleted from the directory.\n" +
                               "Do you confirm? Y/N");

            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if(input.equals("Y") || input.equals("y")){
                for (Person p : personList){
                    people.remove(p);
                }
                System.out.println("Deletion for category " + category + " has been completed!");

            }else{
                System.out.println("Deletion of people for category " + category + " has been cancelled!");
            }
        }
    }

    public void updateByName(Person person, String value){
        person.setName(value);
    }
    public void updateBySurname(Person person, String value){
        person.setSurname(value);
    }

    public void updateByPhoneNumber(Person person, String value){
        person.setPhoneNumber(value);
    }

    public void updateByEmail(Person person, String value){
        person.setEmail(value);
    }

    public boolean printPeople(){
        if(people.stream().count() == 0){
            System.out.println("No recorded person in directory yet!");
            return false;
        }else{
            people.forEach(System.out::println);
            return true;
        }
    }

    public void printPeopleByCategory(Category category){
        people.stream()
                .filter(person -> category.equals(person.getCategory())).collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public List<Person> reloadPeople() {
        IOData IOData = new IOData();
        List<String> people = IOData.reload("directory.txt");
        return mapToPersons(people);
    }

    private List<Person> mapToPersons(List<String> people){

        return people.stream()
                .map(str -> {

                    int id = Integer.MIN_VALUE;
                    String name = "";
                    String surname = "";
                    String phone = "";
                    String email = "";
                    Category category = null;

                    String pattern = "=([^,}]*)";
                    Pattern regexPattern = Pattern.compile(pattern);
                    Matcher matcher = regexPattern.matcher(str);

                    while (matcher.find()) {
                        String value = matcher.group(1);
                        if(id == Integer.MIN_VALUE){
                            id = Integer.parseInt(value);
                        }else if (name == "") {
                            name = value.substring(1, value.length() - 1);
                        } else if (surname == "") {
                            surname = value.substring(1, value.length() - 1);
                        } else if (phone == "") {
                            phone = value.substring(1, value.length() - 1);
                        } else if (email == "") {
                            email = value.substring(1, value.length() - 1);
                        } else if (category == null) {
                            category = Category.valueOf(value);
                        }
                    }
                    return new Person(name, surname, phone, email, category);
                })
                .collect(Collectors.toList());
    }

}

