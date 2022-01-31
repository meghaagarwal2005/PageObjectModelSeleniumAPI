package PageObjects;

import Common.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NeedsCalculatorPage extends BaseClass { @FindBy(how = How.XPATH, using = "//label[@for='needs-type-protect-selection-me']")
public static WebElement needsCalculatorJustMe;

	@FindBy(how = How.XPATH, using = "//label[@for='needs-type-protect-selection-me-partner']")
	public static WebElement needsCalculatorPartnerAndMe;

	@FindBy(how = How.XPATH, using = "//label[@for='needs-type-protect-selection-me-dependants']")
	public static WebElement needsCalculatorKidsAndMe;

	@FindBy(how = How.XPATH, using = "//label[@for='needs-type-protect-selection-me-partner-dependants']")
	public static WebElement needsCalculatorPartnerKidsAndMe;

	@FindBy(how = How.XPATH, using = "//button[@class='cta has-icon-right right-arrow'][@type='submit']")
	public static WebElement needsCalculatorNext;

	@FindBy(how = How.XPATH, using = "//*[@id='dob-day']")
	public static WebElement needsCalculatorDobDay;

	@FindBy(how = How.XPATH, using = "//*[@id='dob-month']")
	public static WebElement needsCalculatorDobMonth;

	@FindBy(how = How.XPATH, using = "//*[@id='dob-year']")
	public static WebElement needsCalculatorDobYear;

	@FindBy(how = How.XPATH, using = "//*[@id='annual-income']")
	public static WebElement needsCalculatorAnnualIncome;

	@FindBy(how = How.XPATH, using = "//label[@for='gender-female']")
	public static WebElement needsCalculatorGenderFemale;

	@FindBy(how = How.XPATH, using = "//label[@for='gender-male']/span")
	public static WebElement needsCalculatorGenderMale;

	@FindBy(how = How.XPATH, using = "//*[@id='dependants-count']")
	public static WebElement needsCalculatorDependents;

	@FindBy(how = How.XPATH, using = "//*[@id='youngest-child-age']")
	public static WebElement needsCalculatorYoungestChildAge;

	@FindBy(how = How.XPATH, using = "//*[@id='mortgage']")
	public static WebElement needsCalculatorCurrentMortgageValue;

	@FindBy(how = How.XPATH, using = "//*[@id='car-loan']")
	public static WebElement needsCalculatorCarLoan;

	@FindBy(how = How.XPATH, using = "//*[@id='credit-card']")
	public static WebElement needsCalculatorCreditCard;

	@FindBy(how = How.XPATH, using = "//a[@id='other_debt_initial_trigger']")
	public static WebElement needsCalculatorAdditionalDebtLink;

	@FindBy(how = How.XPATH, using = "//*[@id='additional-debt']")
	public static WebElement needsCalculatorAdditionalDebt;

	@FindBy(how = How.XPATH, using = "//*[@id='cash']")
	public static WebElement needsCalculatorCash;

	@FindBy(how = How.XPATH, using = "//*[@id='investment-property']")
	public static WebElement needsCalculatorInvestmentProperty;

	@FindBy(how = How.XPATH, using = "//*[@id='shares']")
	public static WebElement needsCalculatorShares;

	@FindBy(how = How.XPATH, using = "//*[@id='super']")
	public static WebElement needsCalculatorCurrentSuperBalance;

	@FindBy(how = How.XPATH, using = "//a[@id='other_assets_initial_trigger']")
	public static WebElement needsCalculatorAddOtherAssetsLink;

	@FindBy(how = How.XPATH, using = "//*[@id='additional-assets']")
	public static WebElement needsCalculatorAddOtherAssets;

	@FindBy(how = How.XPATH, using = "//span[@class='js-results-ip-value']")
	public static WebElement IPCoverResult;

	@FindBy(how = How.XPATH, using = "//*[@id='tabbutton-tab1']/span/a/div[2]/p[1]/span")
	public static WebElement TPDCoverResult;

	@FindBy(how = How.XPATH, using = "//*[@id='tabbutton-tab2']/span/a/div[2]/p[1]/span")
	public static WebElement DeathCoverResult;


	public boolean verifyNeedsCalculatorPageTitle() {
		String expectedTitle = "Needs Calculator | MLC Life Insurance";
		return driver.getTitle().contains(expectedTitle);
	}
}
