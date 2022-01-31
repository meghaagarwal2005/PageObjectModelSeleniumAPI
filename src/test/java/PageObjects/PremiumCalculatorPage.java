package PageObjects;

import Common.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PremiumCalculatorPage extends BaseClass {

	@FindBy(how = How.CSS, using = "label[for='insurance-type-income-protection']")
	public static WebElement premiumCalculatorIncomeProtection;

	@FindBy(how = How.CSS, using = "label[for='insurance-type-death-tpd']")
	public static WebElement premiumCalculatorDeathAndTpd;

	@FindBy(how = How.CSS, using = "label[for='insurance-type-death-only']")
	public static WebElement premiumCalculatorDeathOnly;

	@FindBy(how = How.CSS, using = "a[class='cta has-icon-left left-arrow is-secondary']")
	public static WebElement premiumCalculatorBack;

	@FindBy(how = How.CSS, using = "button[class='cta has-icon-right right-arrow'][type='submit']")
	public static WebElement premiumCalculatorNext;

	@FindBy(how = How.CSS, using = "#dob-day")
	public static WebElement premiumCalculatorDobDay;

	@FindBy(how = How.ID, using = "dob-month")
	public static WebElement premiumCalculatorDobMonth;

	@FindBy(how = How.ID, using = "dob-year")
	public static WebElement premiumCalculatorDobYear;

	@FindBy(how = How.ID, using = "annual-income")
	public static WebElement premiumCalculatorAnnualIncome;

	@FindBy(how = How.XPATH, using = "//label[@for='gender-female']")
	public static WebElement premiumCalculatorGenderFemale;

	@FindBy(how = How.XPATH, using = "//label[@for='gender-male']")
	public static WebElement premiumCalculatorGenderMale;

	@FindBy(how = How.ID, using = "employer")
	public static WebElement premiumCalculatorEmployer;

	@FindBy(how = How.CSS, using = "label[for='office-based-office-based-yes']")
	public static WebElement premiumCalculatorOfficeBasedYes;

	@FindBy(how = How.CSS, using = "label[for='office-based-office-based-nos']")
	public static WebElement premiumCalculatorOfficeBasedNo;

	@FindBy(how = How.CSS, using = "label[for='tertiary-or-member-tertiary-or-member-yes']")
	public static WebElement premiumCalculatorTertiaryYes;

	@FindBy(how = How.CSS, using = "label[for='tertiary-or-member-tertiary-or-member-no']")
	public static WebElement premiumCalculatorTertiaryNo;

	@FindBy(how = How.CSS, using = "label[for='senior-or-management-senior-or-management-yes']")
	public static WebElement premiumCalculatorManagementYes;

	@FindBy(how = How.CSS, using = "label[for='senior-or-management-senior-or-management-no']")
	public static WebElement premiumCalculatorManagementNo;

	@FindBy(how = How.CSS, using = "span[class='occupation-class-label']")
	public static WebElement premiumCalculatorOccupationalClass;

	@FindBy(how = How.CSS, using = "span[class='spinner spinner--loading is-center hidden']")
	public static WebElement premiumCalculatorOccupationalClassSpinner;

	@FindBy(how = How.CSS, using = "div[class='client-income']")
	public static WebElement premiumCalculatorFinalClientIncome;

	@FindBy(how = How.CSS, using = "h2[class='_title']")
	public static WebElement premiumCalculatorResultsTitle;

	@FindBy(how = How.ID, using = "member-groups")
	public static WebElement premiumCalculatorMemberGroup;

	@FindBy(how = How.ID, using = "company")
	public static WebElement premiumCalculatorCompany;

	@FindBy(how = How.ID, using = "employment")
	public static WebElement premiumCalculatorEmployment;

	@FindBy(how = How.ID, using = "occupation-class")
	public static WebElement premiumCalculatorOccupation;

	@FindBy(how = How.ID, using = "premiums-chart-quote-period-select")
	public static WebElement premiumCalculatorTotalPremium;

	@FindBy(how = How.XPATH, using = "//div[@class='quote-period']/p")
	public static WebElement premiumCalculatorTotalPremiumAmount;

	@FindBy(how = How.ID, using = "income-protection-waiting-period")
	public static WebElement premiumCalculatorWaitingPeriod;

	@FindBy(how = How.ID, using = "income-protection-benefit-period")
	public static WebElement premiumCalculatorBenefitPeriod;

	@FindBy(how = How.XPATH, using = "//span[text()='Cover amount']/../following::input[@max='5000000']")
	public static WebElement premiumCalculatorCoverAmount;

	@FindBy(how = How.ID, using = "base-insurance")
	public static WebElement premiumCalculatorBaseInsurance;

	@FindBy(how = How.XPATH, using = "//h2[text()='Tell us a bit about your job']")
	public static WebElement premiumCalculatorAboutYourJobHeader;

	public boolean verifyPremiumCalculatorPageTitle() {
		String expectedTitle = "Premium Calculator | MLC Life Insurance";
		return driver.getTitle().contains(expectedTitle);
	}
}
