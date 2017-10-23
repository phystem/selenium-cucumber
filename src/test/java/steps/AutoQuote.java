package steps;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.HomePage;
import pages.QuotePage;
import utils.ExcelUtil;

public class AutoQuote {
	String url = "https://www.allstate.com/";
	WebDriver driver;

	HomePage homePage;
	QuotePage quotePage;

	@Before
	public void beforeTest() {
		ExcelUtil.load();
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Given("^I open AllState$")
	public void i_open_AllState() {
		driver.get(url);
	}

	@Given("^I select Auto from GetAQuote$")
	public void i_select_Auto_from_GetAQuote() {
		homePage = new HomePage(driver);
		homePage.setZipCode();
		homePage.openQuoteByProduct("Auto");
	}

	@Then("^I Fill the quote using Excel from row (\\d+)$")
	public void i_Fill_the_quote_using_Excel_from_row(int rowIndex) {

		quotePage = new QuotePage(driver);
		Sheet sheet = ExcelUtil.getSheet("Sheet1");
		DataFormatter formatter = new DataFormatter();

		Row row = sheet.getRow(rowIndex);
		QuotePage.QuoteDetails details = new QuotePage.QuoteDetails();
		String fName = formatter.formatCellValue(row.getCell(0));
		details.setFirstName(fName);
		String lName = formatter.formatCellValue(row.getCell(1));
		details.setLastName(lName);
		String zip = formatter.formatCellValue(row.getCell(2));
		details.setZipCode(Integer.valueOf(zip));
		quotePage.getQuote(details);

	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
}
