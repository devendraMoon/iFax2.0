package testUtils;

import constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ExcelDataProvider {

    @DataProvider(name = "ExcelData")
    public static Object[][] getExcelData(Method method) {

        // Sheet name = test method name
        String sheetName = method.getName();

        List<Map<String, String>> list =
                ExcelUtils.getExcelData(
                        FrameworkConstants.EXCEL_DATA_FILE_PATH,
                        sheetName
                );

        // Resolve Faker tokens
        for (Map<String, String> row : list) {
            TokenResolver.clearRowCache();
            for (String key : row.keySet()) {
                row.put(key, TokenResolver.resolve(row.get(key), key));
            }
        }

        Object[][] data = new Object[list.size()][1];

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }

        return data;
    }
}
