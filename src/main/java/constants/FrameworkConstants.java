package constants;

import java.io.File;

public class FrameworkConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    public static final String CONFIG_FILE_PATH = RESOURCES_PATH + File.separator + "config.properties";
    public static final String EXCEL_DATA_FILE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestDataFile.xlsx";
    public static final String EXTENT_REPORT_PATH = PROJECT_PATH + File.separator + "ExtentReports";
    public static final String SCREENSHOT_PATH = PROJECT_PATH + File.separator + "Screenshots";

    public static final int EXPLICIT_WAIT = 15;
}
