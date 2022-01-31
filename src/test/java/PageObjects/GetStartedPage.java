package PageObjects;

import Common.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GetStartedPage extends BaseClass {


	@FindBy(how = How.XPATH, using = "//a[contains(text(),'et started')]")
	public static WebElement LetsGetStarted;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Next')]")
	public static WebElement memberPageNext;



}
