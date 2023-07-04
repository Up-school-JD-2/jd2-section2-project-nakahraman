import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOData implements IOConfigs {

    @Override
    public void save(String filePath, List<IOData> data) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
               for(IOData ioData : data){
                   writer.println(ioData);
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public List<String> reload(String filePath) {
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
               data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}


