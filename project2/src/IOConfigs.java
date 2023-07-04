import java.util.List;

public interface IOConfigs {

    void save(String filePath, List<IOData> data);

    List<String> reload(String filePath);

}
