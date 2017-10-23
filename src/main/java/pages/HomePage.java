package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(className = "btn-quote")
	WebElement getAQuote;

	@FindBy(className = "zipTopnav")
	WebElement changeZip;

	By zipCodeDialog = By.cssSelector("#modalEditLocationOverlay .modal-body");

	@FindBy(id = "bsEditLocationOverlay_txtZipCode")
	WebElement zipCodeDialogTextBox;

	@FindBy(id = "bsEditLocationOverlay_btnSave")
	WebElement zipCodeDialogSaveButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	private By getQuoteSelector(String productName) {
		return By.xpath("//ul[@class='quote-options']/li/a[normalize-space(text())='" + productName + "']");
	}

	public void openQuoteByProduct(String productName) {
		wait.until(ExpectedConditions.elementToBeClickable(getAQuote)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(getQuoteSelector(productName))).click();
	}

	public void setZipCode() {
		changeZip.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(zipCodeDialog));
		zipCodeDialogTextBox.sendKeys("55564");
		zipCodeDialogSaveButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(zipCodeDialog));

	}

}
