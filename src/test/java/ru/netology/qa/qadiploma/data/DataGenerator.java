package ru.netology.qa.qadiploma.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для получения тестовых данных из .csv-файла.
 * Использует аннотацию @Value для создания иммутабельных объектов TestData.
 */
public class DataGenerator {

    private static final String CSV_FILE_PATH = "src/test/resources/form_test_data.csv";
    private static final Map<String, TestData> testDataMap = new HashMap<>();

    static {
        loadTestDataFromCSV();
    }

    /**
     * Чтение тестовых данных из CSV-файла и сохранение их в Map.
     */
    private static void loadTestDataFromCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            List<String[]> records = reader.readAll();
            for (int i = 1; i < records.size(); i++) {  // Пропускаем заголовок
                String[] record = records.get(i);
                String testCaseName = record[0];  // Название теста (поле test_case_name)
                TestData testData = new TestData(
                        record[1],  // Номер карты
                        record[2],  // Месяц
                        record[3],  // Год
                        record[4],  // Имя владельца
                        record[5]   // CVV
                );
                testDataMap.put(testCaseName, testData);  // Сохраняем данные в Map
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения тестовых данных по имени теста.
     *
     * @param testCaseName название теста.
     * @return объект TestData с данными для теста.
     */
    public static TestData getTestData(String testCaseName) {
        return testDataMap.get(testCaseName);
    }

    /**
     * Методы, возвращающие данные, соответствующие имени тест-кейса.
     */
    public static TestData getApprovedUserData() {
        return getTestData("approved");
    }

    public static TestData getDeclinedUserData() {
        return getTestData("declined");
    }

    public static TestData getInvalidNumberUserData() {
        return getTestData("invalid_number");
    }

    public static TestData getEmptyNumberUserData() {
        return getTestData("empty_number");
    }

    public static TestData getInvalidMonthUserData() {
        return getTestData("invalid_month");
    }

    public static TestData getEmptyMonthUserData() {
        return getTestData("empty_month");
    }

    public static TestData getInvalidYearUserData() {
        return getTestData("invalid_year");
    }

    public static TestData getEmptyYearUserData() {
        return getTestData("empty_year");
    }

    public static TestData getInvalidHolderNumbersUserData() {
        return getTestData("invalid_holder_numbers");
    }

    public static TestData getInvalidHolderCyrillicUserData() {
        return getTestData("invalid_holder_cyrillic");
    }

    public static TestData getEmptyHolderUserData() {
        return getTestData("empty_holder");
    }

    public static TestData getInvalidCvcUserData() {
        return getTestData("invalid_cvc");
    }

    public static TestData getEmptyCvcUserData() {
        return getTestData("empty_cvc");
    }
}
