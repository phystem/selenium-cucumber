package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuotePage {
	WebDriver driver;

	By firstName = By.id("first_name");
	By lastName = By.id("last_name");
	By zipCode = By.id("zip");

	public QuotePage(WebDriver driver) {
		this.driver = driver;
		waitForPageToLoad();
	}

	private void waitForPageToLoad() {
		try {
			long start = System.nanoTime();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
			long stop = System.nanoTime();
			long diff = (stop - start) / (1000 * 1000);
			System.out.println("Total time " + diff);
		} catch (Exception ex) {
			Assert.fail("Page didnt load in expected time");
		}
	}

	public void getQuote(QuoteDetails details) {
		clearAndSet(firstName, details.getFirstName());
		clearAndSet(lastName, details.getLastName());
		clearAndSet(zipCode, details.getZipCode());
	}

	private void clearAndSet(By by, String data) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(data);
	}

	public static class QuoteDetails {
		private String firstName;
		private String lastName;
		private int zipCode;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getZipCode() {
			return String.valueOf(zipCode);
		}

		public void setZipCode(int zipCode) {
			this.zipCode = zipCode;
		}

	}

}
