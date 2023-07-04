import Constants.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import Constants.Regex;

public class Main {

    public static void main(String[] args) {

        PersonManager personManager = new PersonManager();
        personManager.people = personManager.reloadPeople();

        ApplicationManager applicationManager = new ApplicationManager();
        applicationManager.apps = applicationManager.reloadApps();

        PhoneManager phoneManager = new PhoneManager();
        phoneManager.phones = phoneManager.reloadApps();

        boolean cont = true;
        do {

            System.out.println("\n" +
                               "1 : Directory Manipulation\n" +
                               "2 : Application Manipulation\n" +
                               "3 : Exit\n");

            Scanner scan = new Scanner(System.in);
            isValidInteger(scan);
            switch (scan.nextInt()) {
                case 1:
                   directoryManipulation(personManager);
                   break;
                case 2:
                    applicationManipulation(applicationManager);
                    break;
                case 3:
                    IOBackup(personManager, applicationManager, phoneManager);
                    cont = false;
                    break;
                default:
                    System.err.println("Not a valid choice. Please try again!");
            }
        } while (cont);
    }

    private static void directoryManipulation(PersonManager personManager){
        boolean cont = true;
        do {
            System.out.println("\n" +
                               "1 : Show Directory\n" +
                               "2 : Add a new Person to Directory\n" +
                               "3 : Update Person\n" +
                               "4 : Delete Person from Directory\n" +
                               "5 : Print People by Category\n" +
                               "6 : Delete People by Category\n" +
                               "7 : Exit\n");

            Scanner scan = new Scanner(System.in);
            isValidInteger(scan);
            switch (scan.nextInt()) {
                case 1:
                    personManager.printPeople();
                    break;
                case 2:
                    createPerson();
                    break;
                case 3:
                    updatePerson(personManager);
                    break;
                case 4:
                    deletePerson(scan, personManager);
                    break;
                case 5:
                    Category category = getCategory(scan);
                    personManager.printPeopleByCategory(category);
                    break;
                case 6:
                    category = getCategory(scan);
                    personManager.deletePersonByCategory(category);
                    break;
                case 7:
                    cont = false;
                    break;
                default:
                    System.err.println("Not a valid choice. Please try again!");
            }
        } while (cont);
    }

    private static void applicationManipulation(ApplicationManager applicationManager){
        boolean cont = true;
        do {
            System.out.println("\n" +
                               "1 : Show Applications\n" +
                               "2 : Add a new Application\n" +
                               "3 : Delete Application\n" +
                               "4 : View Storage Capacity\n" +
                               "5 : Exit\n");


            Scanner scan = new Scanner(System.in);
            isValidInteger(scan);
            switch (scan.nextInt()) {

                case 1:
                    applicationManager.printApplications();
                    break;
                case 2:
                    createApplication();
                    break;
                case 3:
                    deleteApplication(scan, applicationManager);
                    break;
                case 4:
                    PhoneManager phoneManager = new PhoneManager();
                    phoneManager.showStorageInfo();
                    break;
                case 5:
                    cont = false;
                    break;
                default:
                    System.err.println("Not a valid choice. Please try again!");
            }
        } while (cont);
    }

    private static void IOBackup(PersonManager personManager, ApplicationManager applicationManager, PhoneManager phoneManager){

        IOData IOData = new IOData();
        List<IOData> IODataList = new ArrayList<>();

        for (Person person : personManager.people) {
            IODataList.add(person);
        }
        IOData.save("directory.txt", IODataList);

        IODataList.clear();
        for (Application app : applicationManager.apps) {
            IODataList.add(app);
        }
        IOData.save("applications.txt", IODataList);

        IODataList.clear();
        phoneManager.addPhone();
        Phone phone = Phone.getInstance();

        if(IODataList.size() == 0)
            IODataList.add(phone);
        else
            IODataList.set(0, phone);

        IOData.save("phone.txt", IODataList);
    }
    private static void createApplication(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter application name:");
        String name = scan.nextLine();

        System.out.println("Enter application version:");
        String version = scan.nextLine();

        System.out.println("Enter application size:");
        isValidDouble(scan);
        double size = scan.nextDouble();
        if(size < 0){
            System.err.println("Application size cannot be less than 0.\n" +
                               "Please, provide a valid size!");
        }else {
            ApplicationManager applicationManager = new ApplicationManager();
            applicationManager.addApplication(new Application(name, version, size));
        }
    }

    private static int askForPersonId(Scanner scan, String message){

        System.out.println(message);
        isValidInteger(scan);
        int id = scan.nextInt();
        scan.nextLine();
        return id;
    }
    private static void deleteApplication(Scanner scan, ApplicationManager applicationManager) {

        applicationManager.printApplications();
        int id = askForPersonId(scan, "Enter application ID to be deleted:");
        applicationManager.deleteApplication(id);

    }

    private static void deletePerson(Scanner scan, PersonManager personManager) {

        personManager.printPeople();
        int id = askForPersonId(scan, "Enter person ID to be deleted:");
        personManager.deletePerson(id);

    }

    private static void updatePerson(PersonManager personManager) {

        Scanner scan = new Scanner(System.in);
        personManager.printPeople();
        int id = askForPersonId(scan, "Enter person ID to be updated:");

        Optional<Person> person = personManager.findById(id);
        if(person.isPresent()){

            System.out.println("\n" +
                               "1 : Edit Person Name\n" +
                               "2 : Edit Person Surname\n" +
                               "3 : Edit Phone Number\n" +
                               "4 : Edit Email\n");

            isValidInteger(scan);

            System.out.println("\nEnter new value to be updated: ");
            String value;
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    value = scan.nextLine();
                    personManager.updateByName(person.get(),value);
                    break;
                case 2:
                    value = scan.nextLine();
                    personManager.updateBySurname(person.get(),value);
                    break;
                case 3:
                    value = getValue(scan, Regex.PHONE_NUMBER);
                    personManager.updateByPhoneNumber(person.get(),value);
                    break;
                case 4:
                    value = getValue(scan, Regex.EMAIL);
                    personManager.updateByEmail(person.get(),value);
                    break;
                default:
                    break;
            }
            System.out.println("The person updated!");
            System.out.println(person.get().toString());
        }else{
            System.err.println("There is no such user with provided id!");
        }

    }

    private static void createPerson(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter person name:");
        String name = scan.nextLine();

        System.out.println("Enter person surname:");
        String surname = scan.nextLine();

        System.out.println("Enter phone number (format 05xxyyyzzaa): ");
        String phoneNumber = getValue(scan, Regex.PHONE_NUMBER);

        System.out.println("Enter email (format name@mail.com): ");
        String email = getValue(scan, Regex.EMAIL);

        Category category = getCategory(scan);

        PersonManager personManager = new PersonManager();
        personManager.addPerson(new Person(name, surname, phoneNumber, email, category));

    }

    private static Category getCategory(Scanner scan) {
        boolean cont;
        Category category = null;
        do {
            cont = false;
            System.out.println("Select a category: ");

            for (Category cat : Category.values()) {
                System.out.println(cat.ordinal() + 1 + " " + cat.name());
            }
            isValidInteger(scan);
            int categoryKey = scan.nextInt();
            scan.nextLine();
            if (categoryKey > 0 && categoryKey < Category.values().length + 1) {
                category = Category.values()[categoryKey - 1];
            } else {
                System.err.println("Invalid category! Please try again.");
                cont = true;
            }
        } while (cont);

        return category;
    }


    private static String getValue(Scanner scan, String regex) {

        Validation validation = new Validation();
        boolean cont;
        String value;

        do {
            cont = false;
            value = scan.nextLine();

            try {
                validation.validate(regex, value);
            } catch (InvalidPhoneNumberException | InvalidEmailException e) {
                System.err.println(e.getMessage());
                cont = true;
            }
        } while (cont);

        return value;
    }

    static void isValidInteger(Scanner scan){

        while (!scan.hasNextInt() ){
            scan.nextLine();
            System.out.print("Please enter an integer number: ");
        }
    }

    static void isValidDouble(Scanner scan){

        while (!scan.hasNextDouble()){
            scan.nextLine();
            System.out.print("Please enter a valid number: ");
        }
    }
}
