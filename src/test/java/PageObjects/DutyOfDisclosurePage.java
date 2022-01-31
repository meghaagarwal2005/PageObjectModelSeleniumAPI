package PageObjects;

import Common.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DutyOfDisclosurePage extends BaseClass {

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Agree')]")
	public static WebElement dutyOfDisclosureAgree;

	@FindBy(how = How.LINK_TEXT, using = "Back")
	public static WebElement dutyOfDisclosureBack;

	public boolean verifyDutyOfDisclosurePageTitle() {
		String expectedTitle = "Duty of Disclosure | MLC Life Insurance";
		return driver.getTitle().contains(expectedTitle);
	}
}
