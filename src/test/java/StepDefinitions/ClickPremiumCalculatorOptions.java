package StepDefinitions;

import Common.BaseClass;
import PageObjects.PremiumCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickPremiumCalculatorOptions extends BaseClass {
	public static void Execute(WebDriver driver, String option, String Value) throws Exception {
		log.info("Premium Calculator - " + option + ": " + Value);
		switch (option) {
		case "Benefit Type":
			if (Value.contains("Income Protection")) {
				clickOn(PremiumCalculatorPage.premiumCalculatorIncomeProtection);
			}
			if (Value.contains("Death and TPD")) {
				clickOn(PremiumCalculatorPage.premiumCalculatorDeathAndTpd);
			}
			if (Value.contains("Death Only")) {
				clickOn(PremiumCalculatorPage.premiumCalculatorDeathOnly);
			}
			break;
		case "Back":
			PremiumCalculatorPage.premiumCalculatorBack.click();
			break;
		case "Next":
			PremiumCalculatorPage.premiumCalculatorNext.click();
			break;
		case "day":
			Thread.sleep(1000);
			PremiumCalculatorPage.premiumCalculatorDobDay.click();
			PremiumCalculatorPage.premiumCalculatorDobDay.sendKeys(Value);
			break;
		case "month":
			PremiumCalculatorPage.premiumCalculatorDobMonth.click();
			PremiumCalculatorPage.premiumCalculatorDobMonth.sendKeys(Value);
			break;
		case "year":
			PremiumCalculatorPage.premiumCalculatorDobYear.click();
			PremiumCalculatorPage.premiumCalculatorDobYear.sendKeys(Value);
			break;
		case "Your annual income":
			PremiumCalculatorPage.premiumCalculatorAnnualIncome.click();
			PremiumCalculatorPage.premiumCalculatorAnnualIncome.sendKeys(Value);
			break;
		case "Female":
			PremiumCalculatorPage.premiumCalculatorGenderFemale.click();
			break;
		case "Male":
			PremiumCalculatorPage.premiumCalculatorGenderMale.click();
			break;
		case "Employer":
			Thread.sleep(1000);
			PremiumCalculatorPage.premiumCalculatorEmployer.click();
			PremiumCalculatorPage.premiumCalculatorEmployer.sendKeys(Value);
			break;
		case "Yes - Office based":
			PremiumCalculatorPage.premiumCalculatorOfficeBasedYes.click();
			break;
		case "No - Office based":
			PremiumCalculatorPage.premiumCalculatorOfficeBasedNo.click();
			break;
		case "Yes - Tertiary":
			PremiumCalculatorPage.premiumCalculatorTertiaryYes.click();
			break;
		case "No - Tertiary":
			PremiumCalculatorPage.premiumCalculatorTertiaryNo.click();
			break;
		case "Yes - Management":
			PremiumCalculatorPage.premiumCalculatorManagementYes.click();
			break;
		case "No - Management":
			PremiumCalculatorPage.premiumCalculatorManagementNo.click();
			break;
		case "Member Group":
			selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorMemberGroup, Value);
			break;
		case "Company":
			if (!Value.equalsIgnoreCase("NA")) {
				selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorCompany, Value);
			}
			break;
		case "Employment":
			if (!Value.equalsIgnoreCase("NA")) {
				selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorEmployment, Value);
			}
			break;
		case "Occupation Class":
			selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorOccupation, Value);
			break;
		case "Total Premium":
			Thread.sleep(2000);
			CommonFunctions.waitForElementToBeClickable(PremiumCalculatorPage.premiumCalculatorTotalPremium);
			PremiumCalculatorPage resultsPage = PageFactory.initElements(driver, PremiumCalculatorPage.class);
			Thread.sleep(1000);
			Select select=new Select(PremiumCalculatorPage.premiumCalculatorTotalPremium);
			select.selectByVisibleText(Value.replaceAll("-"," "));
			WebDriverWait wait= new WebDriverWait(driver,10);
			PremiumCalculatorPage.premiumCalculatorTotalPremium.click();
			select.selectByVisibleText(Value.replaceAll("-"," "));
			log.info("value displayed"+select.getFirstSelectedOption().getText());
			wait.until(ExpectedConditions.textToBePresentInElement(select.getFirstSelectedOption(),Value.replaceAll("-"," ")));
			break;
		case "Cover Amount":
			Thread.sleep(2000);
			waitForElementVisible(PremiumCalculatorPage.premiumCalculatorCoverAmount);
			PremiumCalculatorPage.premiumCalculatorCoverAmount.clear();
			PremiumCalculatorPage.premiumCalculatorCoverAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			PremiumCalculatorPage.premiumCalculatorCoverAmount.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			sendKeys(PremiumCalculatorPage.premiumCalculatorCoverAmount, Value);
			break;
		case "Waiting Period":
			if (!Value.equalsIgnoreCase("NA")) {
				selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorWaitingPeriod, Value);
			}
			break;
		case "Benefit Period":
			if (!Value.equalsIgnoreCase("NA")) {
				selectDropdownByValue(PremiumCalculatorPage.premiumCalculatorBenefitPeriod, Value);
			}
			break;
		case "Base Insurance":
			if (!Value.equalsIgnoreCase("NA")) {
				sendKeys(PremiumCalculatorPage.premiumCalculatorBaseInsurance, Value);
			}
			break;
		case "A bit about your job Page":
			mouseOverClick(PremiumCalculatorPage.premiumCalculatorAboutYourJobHeader);
			Thread.sleep(5000);
			break;
		default:
			log.info("At ClickPremiumCalculatorOptions default ");
			break;
		}
	}
}
