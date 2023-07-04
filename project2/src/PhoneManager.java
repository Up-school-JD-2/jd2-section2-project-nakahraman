import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneManager {
    public static List<Phone> phones = new ArrayList<>();

    public void showStorageInfo(){

        Phone phone = Phone.getInstance();
        System.out.println("Total storage of the phone is: " + phone.getStorageArea() + " gb");
        System.out.println("Empty storage of the phone is: " + (phone.getStorageArea() - phone.getFullStorageArea()) + " gb");
    }
    public void reduceStorage(double appSize){

        Phone phone = Phone.getInstance();
        if(phone.getFullStorageArea() + appSize > phone.getStorageArea()){
            System.out.println("Storage area is insufficient for this application.\n" +
                               "Please, delete an application to proceed!");
        }else{
            phone.setFullStorageArea(phone.getFullStorageArea() + appSize);
        }
    }

    public void increaseStorage(double appSize){
        Phone phone = Phone.getInstance();
        phone.setFullStorageArea(phone.getFullStorageArea() - appSize);
    }

    public void addPhone(){
        Phone phone = Phone.getInstance();
        phones.add(phone);
    }

    public List<Phone> reloadApps() {
        IOData IOData = new IOData();
        List<String> apps = IOData.reload("phone.txt");
        return mapToApps(apps);
    }

    private List<Phone> mapToApps(List<String> apps){

        return apps.stream()
                .map(str -> {

                    int id = Integer.MIN_VALUE;
                    String make = "";
                    String model = "";
                    String serialNumber = "";
                    double storageArea = Double.MIN_VALUE;
                    double fullStorageArea = Double.MIN_VALUE;
                    String operatingSystem = "";

                    String pattern = "=([^,}]*)";
                    Pattern regexPattern = Pattern.compile(pattern);
                    Matcher matcher = regexPattern.matcher(str);

                    while (matcher.find()) {
                        String value = matcher.group(1);
                        if(id == Integer.MIN_VALUE){
                            id = Integer.parseInt(value);
                        }else if(make == "") {
                            make = value.substring(1, value.length()-1);
                        } else if (model == "") {
                            model = value.substring(1, value.length()-1);
                        } else if (serialNumber == "") {
                            serialNumber = value.substring(1, value.length()-1);
                        } else if (storageArea == Double.MIN_VALUE) {
                            storageArea = Double.parseDouble(value);
                        } else if (fullStorageArea == Double.MIN_VALUE) {
                            fullStorageArea = Double.parseDouble(value);
                        }else if (operatingSystem == "") {
                            operatingSystem = value.substring(1, value.length()-1);
                        }
                    }
                    Phone phone = Phone.getInstance();
                    phone.setFullStorageArea(fullStorageArea);
                    return phone;
                })
                .collect(Collectors.toList());
    }

}
