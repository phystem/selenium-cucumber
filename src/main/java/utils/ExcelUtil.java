package utils;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static Workbook workBook;

	public static void load() {
		workBook = getWorkBook("src/test/resources/testdata/Book1.xlsx");
	}

	public static Workbook getWorkBook(String filePath) {
		try {
			Workbook wb = new XSSFWorkbook(new File(filePath));
			return wb;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Sheet getSheet(String sheet) {
		return workBook.getSheet(sheet);
	}

}
