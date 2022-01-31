package StepDefinitions;

import Common.KeyValReader;
import Common.BaseClass;
import PageObjects.CommonElements;
import PageObjects.DutyOfDisclosurePage;
import PageObjects.GetStartedPage;
import PageObjects.PremiumCalculatorPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PremiumCalculator extends BaseClass {
    KeyValReader configFileReader;

    public PremiumCalculator() {
        configFileReader = new KeyValReader("Configs//digitalLifeview.properties");
    }

    @Given("the user is on \"([^\"]*)\" link$")
    public void the_user_on_link(String link) throws Throwable {
        log.info("Link is : " + link);
        String url;
        switch (link) {
            case "Telstra Premium Calculator":
                url = configFileReader.getProperty("TelstraPremiumCalculatorUrl");
                break;
            case "Generic Needs Calculator":
                url = configFileReader.getProperty("GenericNeedsCalculatorUrl");
                break;
            default:
                url = "invalid";
                log.info("At default: invalid environment ");
                break;
        }
        log.info("Member URL: " + url);
        System.out.println("Testing worked");

        driver.get(url);
    }

    @Then("^the user clicks the \"([^\"]*)\" link on Member portal page$")
    public void the_user_clicks_the_link_on_Member_Portal_Page(String cleLink) throws Throwable {
        GetStartedPage rccPage = PageFactory.initElements(driver, GetStartedPage.class);
        log.info("User click on " + cleLink);
        switch (cleLink) {
            case "Let's get started":
                clickOn(GetStartedPage.LetsGetStarted);
                break;

            case "Next":
                clickOn(GetStartedPage.memberPageNext);
                break;
            default:
                log.info("At ClickReduceCancelOptions default");
                break;
        }
    }

    @Then("^the user enters \"([^\"]*)\" for \"([^\"]*)\" option on Premium Calculator page2$")
    public void the_user_enters_for_option_on_Premium_Calculator_page2(String Value, String pcOption) throws Throwable {
        PremiumCalculatorPage pcPage2 = PageFactory.initElements(driver, PremiumCalculatorPage.class);
        pcPage2.verifyPremiumCalculatorPageTitle();
        ClickPremiumCalculatorOptions.Execute(driver, pcOption, Value);

    }

    @Then("verify {string} footer on the {string}")
    public void verify_footer_member_portal(String fund,String page) throws Exception {
        log.info("Validating Member Portal footer for page: " + page);
        PageFactory.initElements(driver, CommonElements.class);
        CommonElements.memberFooter.isDisplayed();
        CommonFunctions.verifyLinks(CommonElements.memberFooter, fund);

    }

    @Then("^the user clicks the \"([^\"]*)\" link on Duty of Disclosure page$")
    public void the_user_clicks_the_link_on_Duty_of_Disclaimer_page(String dodLink) throws Throwable {
        DutyOfDisclosurePage dodPage = PageFactory.initElements(driver, DutyOfDisclosurePage.class);
        dodPage.verifyDutyOfDisclosurePageTitle();
        clickOn(dodPage.dutyOfDisclosureAgree);
    }

    @Then("^the user enters \"([^\"]*)\" for \"([^\"]*)\" option on Premium Calculator page$")
    public void the_user_for_option_on_Premium_Calculator_page(String Value, String pcOption) throws Throwable {
        PremiumCalculatorPage pcPage = PageFactory.initElements(driver, PremiumCalculatorPage.class);
        pcPage.verifyPremiumCalculatorPageTitle();
        ClickPremiumCalculatorOptions.Execute(driver, pcOption, Value);
    }

    @Then("^the user must be on the premium calculator results page$")
    public void the_user_must_be_on_the_premium_calculator_results_page() throws Throwable {
        PremiumCalculatorPage resultsPage = PageFactory.initElements(driver, PremiumCalculatorPage.class);
        resultsPage.verifyPremiumCalculatorPageTitle();
    }

    @Then("^Verify Total premium per year should be \"([^\"]*)\" in Premium Calculator page$")
    public void verify_total_premium_per_year(String expectedValue) throws Throwable {
        PremiumCalculatorPage resultsPage = PageFactory.initElements(driver, PremiumCalculatorPage.class);
        WebDriverWait webDriverWait = new WebDriverWait(driver,Integer.parseInt(hiveProp.getProperty("maxWait")));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(resultsPage.premiumCalculatorTotalPremiumAmount, expectedValue));
        assertContainsText(resultsPage.premiumCalculatorTotalPremiumAmount, expectedValue);
    }

}
