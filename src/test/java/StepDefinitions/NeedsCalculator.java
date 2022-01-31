package StepDefinitions;

import Common.BaseClass;
import Common.KeyValReader;
import PageObjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NeedsCalculator extends BaseClass {
    KeyValReader configFileReader;

    public NeedsCalculator() {
        configFileReader = new KeyValReader("Configs//digitalLifeview.properties");
    }

    @Then("^the user enters dob on Needs Calculator page$")
    public void user_enters_dob(io.cucumber.datatable.DataTable dataTable) throws Throwable {
        List<List<String>> records=dataTable.asLists();
        List<String> dob=records.get(1);
        String date=dob.get(0);
        String month=dob.get(1);
        String year=dob.get(2);
        the_user_for_option_on_Needs_Calculator_page(date,"day");
        the_user_for_option_on_Needs_Calculator_page(month,"month");
        the_user_for_option_on_Needs_Calculator_page(year,"year");
    }

    @Then("^the user enters \"([^\"]*)\" for \"([^\"]*)\" option on Needs Calculator page$")
    public void the_user_for_option_on_Needs_Calculator_page(String Value, String ncOption) throws Throwable {
        NeedsCalculatorPage ncPage = PageFactory.initElements(driver, NeedsCalculatorPage.class);
        ncPage.verifyNeedsCalculatorPageTitle();
        switch (ncOption) {
            case "Initial Needs":
                if (Value.contains("Just me")) {
                    clickOn(NeedsCalculatorPage.needsCalculatorJustMe);
                }
                if (Value.contains("My partner and me")) {
                    clickOn(NeedsCalculatorPage.needsCalculatorPartnerAndMe);
                }
                if (Value.contains("My kids and me")) {
                    clickOn(NeedsCalculatorPage.needsCalculatorKidsAndMe);
                }
                if (Value.contains("My partner , my kids and me")) {
                    clickOn(NeedsCalculatorPage.needsCalculatorPartnerKidsAndMe);
                }
                break;
            case "Next":

                try {
                    WebElement nextButton = NeedsCalculatorPage.needsCalculatorNext;
                    nextButton.click();
                }
                catch(org.openqa.selenium.StaleElementReferenceException ex)
                {
                    WebElement nextButton = NeedsCalculatorPage.needsCalculatorNext;
                    nextButton.click();
                }
                break;
            case "day":

                waitForElementVisible(NeedsCalculatorPage.needsCalculatorDobDay);
                NeedsCalculatorPage.needsCalculatorDobDay.click();
                NeedsCalculatorPage.needsCalculatorDobDay.sendKeys(Value);
                break;
            case "month":
                NeedsCalculatorPage.needsCalculatorDobMonth.click();
                NeedsCalculatorPage.needsCalculatorDobMonth.sendKeys(Value);
                break;
            case "year":
                NeedsCalculatorPage.needsCalculatorDobYear.click();
                NeedsCalculatorPage.needsCalculatorDobYear.sendKeys(Value);
                break;
            case "Your annual income":
                NeedsCalculatorPage.needsCalculatorAnnualIncome.sendKeys(Value);
                break;
            case "Female":
                NeedsCalculatorPage.needsCalculatorGenderFemale.click();
                break;
            case "Male":
                CommonFunctions.waitForElementToBeClickable(NeedsCalculatorPage.needsCalculatorGenderMale);
                NeedsCalculatorPage.needsCalculatorGenderMale.click();
                break;
            case "dependents":
                if(!Value.equalsIgnoreCase("null")) {
                    selectDropdownByValue(NeedsCalculatorPage.needsCalculatorDependents,Value);
                }
                break;
            case "Youngest Child Age":
                if(!Value.equalsIgnoreCase("null")) {
                    NeedsCalculatorPage.needsCalculatorYoungestChildAge.sendKeys(Value);
                }
                break;
            case "Current Mortgage Value":
                NeedsCalculatorPage.needsCalculatorCurrentMortgageValue.sendKeys(Value);
                break;
            case "Car Loan":
                NeedsCalculatorPage.needsCalculatorCarLoan.sendKeys(Value);
                break;
            case "Credit Card":
                NeedsCalculatorPage.needsCalculatorCreditCard.sendKeys(Value);
                break;
            case "Additional Debt":

                if(!Value.equalsIgnoreCase("null"))
                {
                    BaseClass bc=new BaseClass();
                    Thread.sleep(2000);
                    NeedsCalculatorPage.needsCalculatorAdditionalDebtLink.click();
                    NeedsCalculatorPage.needsCalculatorAdditionalDebt.sendKeys(Value);
                }
                break;
            case "Cash":
                NeedsCalculatorPage.needsCalculatorCash.sendKeys(Value);
                break;
            case "Investment Property":
                NeedsCalculatorPage.needsCalculatorInvestmentProperty.sendKeys(Value);
                break;
            case "Shares":
                NeedsCalculatorPage.needsCalculatorShares.sendKeys(Value);
                break;
            case "Current Super Balance":
                NeedsCalculatorPage.needsCalculatorCurrentSuperBalance.sendKeys(Value);
                break;
            case "Add Other Assets":
                if(!Value.equalsIgnoreCase("null"))
                {
                    WebDriverWait wait = new WebDriverWait(driver, 5);
                    wait.until(ExpectedConditions.elementToBeClickable(NeedsCalculatorPage.needsCalculatorAddOtherAssetsLink));
                    NeedsCalculatorPage.needsCalculatorAddOtherAssetsLink.click();
                    NeedsCalculatorPage.needsCalculatorAddOtherAssets.sendKeys(Value);
                }
                break;



            default:
                break;
        }
    }

    @Then("Verify {string} should be {string} in Needs Calculator results page")
    public void verify_total_cover(String cover, String expectedValue) throws Throwable {
        NeedsCalculatorPage resultsPage = PageFactory.initElements(driver, NeedsCalculatorPage.class);
        WebElement coverElement;
        switch (cover) {
            case "IP cover":
                BaseClass bc = new BaseClass();
                Thread.sleep(10000);
                coverElement = resultsPage.IPCoverResult;
                assertContainsText(coverElement, expectedValue);
                break;
            case "TPD cover":
                coverElement = resultsPage.TPDCoverResult;
                assertContainsText(coverElement, expectedValue);
                break;
            case "Death cover":
                coverElement = resultsPage.DeathCoverResult;
                assertContainsText(coverElement, expectedValue);
                break;
        }
    }




}
