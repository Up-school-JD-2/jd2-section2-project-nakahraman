import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApplicationManager {
    public static List<Application> apps = new ArrayList<>();

    public boolean printApplications(){
        if(apps.stream().count() == 0){
            System.out.println("No loaded applications yet!");
            return false;
        }else{
            apps.forEach(System.out::println);
            return true;
        }
    }

    public void addApplication(Application application){
        apps.add(application);
        PhoneManager phoneManager = new PhoneManager();
        phoneManager.reduceStorage(application.getSize());
    }
    public void deleteApplication(int id){

        Optional<Application> application = findById(id);

        if(apps.remove(application)){
            PhoneManager phoneManager = new PhoneManager();
            phoneManager.increaseStorage(application.get().getSize());
            System.out.println("The application: " + application.get().getName() + " has been deleted!");
        }else{
            System.out.println("There is no such application with provided id!");
        }
    }

    public Optional<Application> findById(int id){
       return apps.stream().filter(app -> id == app.getId()).findFirst();
    }

    public List<Application> reloadApps() {
        IOData IOData = new IOData();
        List<String> apps = IOData.reload("applications.txt");
        return mapToApps(apps);
    }

    private List<Application> mapToApps(List<String> apps){

        return apps.stream()
                .map(str -> {

                    int id = Integer.MIN_VALUE;
                    String name = "";
                    String version = "";
                    double size = 0.0;

                    String pattern = "=([^,}]*)";
                    Pattern regexPattern = Pattern.compile(pattern);
                    Matcher matcher = regexPattern.matcher(str);

                    while (matcher.find()) {
                        String value = matcher.group(1);
                        if(id == Integer.MIN_VALUE){
                            id = Integer.parseInt(value);
                        }else if(name == "") {
                            name = value.substring(1, value.length()-1);;
                        } else if (version == "") {
                            version = value.substring(1, value.length()-1);;
                        } else if (size == 0.0) {
                            size = Double.parseDouble(value);
                        }
                    }
                    return new Application(name, version ,size);
                })
                .collect(Collectors.toList());
    }
}
