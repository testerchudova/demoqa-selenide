package qaguru.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveFilesParsingTest {

    // Добавила ClassLoader, чтобы получать файлы из папки resources
    private final ClassLoader classLoader =
            ArchiveFilesParsingTest.class.getClassLoader();

    @Test
    void zipArchiveShouldContainValidPdfXlsxAndCsvFiles() throws Exception {

        // Добавила флаги проверок, что каждый тип файла реально найден
        boolean pdfChecked = false;
        boolean xlsxChecked = false;
        boolean csvChecked = false;

        // Открываю архив files.zip из resources
        try (InputStream is =
                     classLoader.getResourceAsStream("files.zip");

             // Оборачиваю поток в ZipInputStream,
             // чтобы читать файлы внутри архива
             ZipInputStream zis =
                     new ZipInputStream(is)) {

            ZipEntry entry;

            // Читаю архив по одному файлу (entry)
            while ((entry = zis.getNextEntry()) != null) {

                // Получаю имя текущего файла
                String entryName = entry.getName();

                // Считываю содержимое файла в массив байтов
                byte[] entryData = zis.readAllBytes();

                //  PDF

                // Проверяю, что это PDF-файл
                if (entryName.endsWith(".pdf")) {

                    // Создала временный файл из данных архива
                    File pdfFile =
                            createTempFile(entryData, ".pdf");

                    // Использую библиотеку для чтения PDF
                    PDF pdf = new PDF(pdfFile);

                    // Проверяю, что внутри PDF есть нужный текст
                    Assertions.assertTrue(
                            pdf.text.contains("JUnit"),
                            "В PDF не найден ожидаемый текст"
                    );

                    // Отмечаю, что PDF успешно проверен
                    pdfChecked = true;
                }

                // XLSX

                // Проверяю, что это Excel-файл
                if (entryName.endsWith(".xlsx")) {

                    // Создала временный XLSX файл
                    File xlsxFile =
                            createTempFile(entryData, ".xlsx");

                    // Подключила библиотеку для чтения Excel
                    XLS xls = new XLS(xlsxFile);

                    // Получаю значение первой ячейки
                    String actualCellValue = xls.excel
                            .getSheetAt(0)   // первый лист
                            .getRow(0)       // первая строка
                            .getCell(0)      // первая ячейка
                            .getStringCellValue();

                    // Проверяю, что значение совпадает
                    Assertions.assertEquals(
                            "Name",
                            actualCellValue
                    );

                    // Отмечаю, что XLSX проверен
                    xlsxChecked = true;
                }

                // CSV

                // Проверяю, что это CSV-файл
                if (entryName.endsWith(".csv")) {

                    List<String[]> csvData;

                    // Читаю CSV через CSVReader
                    try (CSVReader csvReader =
                                 new CSVReader(

                                         // Создала поток из массива байтов
                                         new InputStreamReader(
                                                 new ByteArrayInputStream(entryData),

                                                 // Указала кодировку UTF-8
                                                 StandardCharsets.UTF_8
                                         )
                                 )) {

                        // Считала все строки CSV
                        csvData = csvReader.readAll();
                    }

                    // Проверяю количество строк
                    Assertions.assertEquals(
                            3,
                            csvData.size()
                    );

                    // Проверяю заголовок
                    Assertions.assertArrayEquals(
                            new String[]{
                                    "Name",
                                    "Age",
                                    "City"
                            },
                            csvData.get(0)
                    );

                    // Проверяю первую строку данных
                    Assertions.assertArrayEquals(
                            new String[]{
                                    "Kate",
                                    "28",
                                    "Moscow"
                            },
                            csvData.get(1)
                    );

                    // Проверяю вторую строку данных
                    Assertions.assertArrayEquals(
                            new String[]{
                                    "Ivan",
                                    "30",
                                    "Kazan"
                            },
                            csvData.get(2)
                    );

                    // Отмечаю, что CSV проверен
                    csvChecked = true;
                }
            }
        }

        // Финальная проверка —
        // убеждаюсь, что все файлы действительно были найдены

        Assertions.assertTrue(
                pdfChecked,
                "PDF файл не найден в архиве"
        );

        Assertions.assertTrue(
                xlsxChecked,
                "XLSX файл не найден в архиве"
        );

        Assertions.assertTrue(
                csvChecked,
                "CSV файл не найден в архиве"
        );
    }

    // Метод, который я добавила для создания временных файлов
    // Использую его, чтобы сохранить данные из архива и потом читать файл библиотеками

    private File createTempFile(
            byte[] data,
            String suffix
    ) throws Exception {

        // Создала временный файл
        File tempFile =
                Files.createTempFile(
                        "zip-entry-",
                        suffix
                ).toFile();

        // Указала, что файл удалится после завершения программы
        tempFile.deleteOnExit();

        // Записала данные в файл
        try (FileOutputStream fos =
                     new FileOutputStream(tempFile)) {

            fos.write(data);
        }

        // Возвращаю созданный файл
        return tempFile;
    }
}