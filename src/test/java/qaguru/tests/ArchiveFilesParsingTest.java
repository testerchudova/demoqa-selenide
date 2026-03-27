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

    private final ClassLoader classLoader =
            ArchiveFilesParsingTest.class.getClassLoader();

    @Test
    void zipArchiveShouldContainValidPdfFile() throws Exception {
        byte[] pdfData = getFileFromZipByExtension("files.zip", ".pdf");

        Assertions.assertNotNull(
                pdfData,
                "PDF файл не найден в архиве"
        );

        File pdfFile = createTempFile(pdfData, ".pdf");
        PDF pdf = new PDF(pdfFile);

        Assertions.assertTrue(
                pdf.text.contains("JUnit"),
                "В PDF не найден ожидаемый текст"
        );
    }

    @Test
    void zipArchiveShouldContainValidXlsxFile() throws Exception {
        byte[] xlsxData = getFileFromZipByExtension("files.zip", ".xlsx");

        Assertions.assertNotNull(
                xlsxData,
                "XLSX файл не найден в архиве"
        );

        File xlsxFile = createTempFile(xlsxData, ".xlsx");
        XLS xls = new XLS(xlsxFile);

        String actualCellValue = xls.excel
                .getSheetAt(0)
                .getRow(0)
                .getCell(0)
                .getStringCellValue();

        Assertions.assertEquals(
                "Name",
                actualCellValue,
                "В XLSX первой ячейке ожидалось значение Name"
        );
    }

    @Test
    void zipArchiveShouldContainValidCsvFile() throws Exception {
        byte[] csvDataBytes = getFileFromZipByExtension("files.zip", ".csv");

        Assertions.assertNotNull(
                csvDataBytes,
                "CSV файл не найден в архиве"
        );

        List<String[]> csvData;

        try (CSVReader csvReader = new CSVReader(
                new InputStreamReader(
                        new ByteArrayInputStream(csvDataBytes),
                        StandardCharsets.UTF_8
                )
        )) {
            csvData = csvReader.readAll();
        }

        Assertions.assertEquals(
                3,
                csvData.size(),
                "CSV должен содержать 3 строки"
        );

        Assertions.assertArrayEquals(
                new String[]{"Name", "Age", "City"},
                csvData.get(0),
                "Заголовок CSV не совпадает"
        );

        Assertions.assertArrayEquals(
                new String[]{"Kate", "28", "Moscow"},
                csvData.get(1),
                "Первая строка CSV не совпадает"
        );

        Assertions.assertArrayEquals(
                new String[]{"Ivan", "30", "Kazan"},
                csvData.get(2),
                "Вторая строка CSV не совпадает"
        );
    }

    private byte[] getFileFromZipByExtension(
            String zipFileName,
            String fileExtension
    ) throws Exception {

        InputStream is = classLoader.getResourceAsStream(zipFileName);

        if (is == null) {
            throw new IllegalArgumentException(
                    "Архив " + zipFileName + " не найден в resources"
            );
        }

        try (ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(fileExtension)) {
                    return zis.readAllBytes();
                }
            }
        }

        return null;
    }

    private File createTempFile(
            byte[] data,
            String suffix
    ) throws Exception {

        File tempFile = Files.createTempFile(
                "zip-entry-",
                suffix
        ).toFile();

        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(data);
        }

        return tempFile;
    }
}