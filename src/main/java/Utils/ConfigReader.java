package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    Properties properties;

//    String Path = "C:\\Users\\deven\\IdeaProjects\\New Framework Design\\src\\main\\java\\Resources";

    public ConfigReader() throws IOException {
        properties = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream == null) {
            throw new IOException("config.properties not found in resources folder");
        }
        properties.load(inputStream);
    }

    public String getUrl() {
        String value = properties.getProperty("Url");
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Url is not specified in config file");
        }
    }

    public String getBrowser() {
        String value = properties.getProperty("browser");
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Browser is not specified in config file");
        }
    }
}
