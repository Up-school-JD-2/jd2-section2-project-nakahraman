import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Phone extends IOData{
    private static Phone instance;
    private int id;
    private String make;
    private String model;
    private String serialNumber;
    private double storageArea;
    private  double fullStorageArea;
    private String operatingSystem;
    private static AtomicInteger counter = new AtomicInteger(0);

    public static int nextId() {
        return counter.incrementAndGet();
    }


    public Phone() {
        this.id = nextId();
        this.make = "Samsung";
        this.model = "Galaxy S9";
        this.serialNumber = "RK6G8HGFOSD";
        this.storageArea = 250;
        this.fullStorageArea = 0.0;
        this.operatingSystem = "Android 10";
    }

    public static Phone getInstance() {
        if (instance == null) {
            synchronized (Phone.class) {
                if (instance == null) {
                    instance = new Phone();
                }
            }
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(Double storageArea) {
        this.storageArea = storageArea;
    }

    public double getFullStorageArea() {
        return fullStorageArea;
    }

    public void setFullStorageArea(double emptyStorageArea) {
        this.fullStorageArea = emptyStorageArea;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "Phone{" +
               "id=" + id +
               ", make='" + make + '\'' +
               ", model='" + model + '\'' +
               ", serialNumber='" + serialNumber + '\'' +
               ", totalStorageArea=" + storageArea +
               ", fullStorageArea=" + fullStorageArea +
               ", operatingSystem='" + operatingSystem + '\'' +
               '}';
    }
}
