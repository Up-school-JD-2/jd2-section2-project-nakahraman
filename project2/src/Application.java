import java.util.concurrent.atomic.AtomicInteger;

public class Application extends IOData {
    private int id;
    private String name;
    private String version;
    private double size;

    private static AtomicInteger counter = new AtomicInteger(0);

    public static int nextId() {
        return counter.incrementAndGet();
    }

    public Application(String name, String version, double size) {
        this.id = nextId();
        this.name = name;
        this.version = version;
        this.size = size;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Application{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", version='" + version + '\'' +
               ", size=" + size +
               '}';
    }
}
