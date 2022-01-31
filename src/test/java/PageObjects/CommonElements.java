package PageObjects;

import Common.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CommonElements extends BaseClass {

    @FindBy(how = How.ID, using = "btnLogin")
    public static WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[@class='site-actions authenticated']//button")
    public static WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "(//button[contains(@type,'submit')])[4]")
    public static WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@href,'member-details')]")
    public static WebElement goToMemberDetailsButton;

    @FindBy(how = How.XPATH, using = "(//button[@class='cta has-icon-right right-arrow'][contains(.,'Continue')])[3]")
    public static WebElement uploadDocumentModalContinueButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div/h2")
    public static WebElement loggedOutMessage;

    @FindBy(how = How.CSS, using = "a[class='cta is-tertiary has-icon-left left-arrow']")
    public static WebElement homePageButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickHomepageButton() {
        homePageButton.click();
    }

    @FindBy(how = How.XPATH, using = "(//label[@for = 'documentsUpload']/parent::div//div/input[@name='documentsUpload'])")
    public static WebElement UploadDocumentModalUploadInput;

    @FindBy(how = How.XPATH, using = "(//label[@for = 'documentsUpload']/parent::div//div/input[@name='documentsUpload'])[2]")
    public static WebElement UploadDocumentModalUploadInput2;

    @FindBy(how = How.XPATH, using = "//*[@class=\"modal-content-overflow\"]/h2")
    public static WebElement adminUploadDocumentModalHeading;

    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'modal-content-overflow')]/h2)[1]")
    public static WebElement MemberUploadDocumentModalHeading;

    //@FindBy(how = How.CLASS_NAME, using = "inner-label")
    @FindBy(how = How.XPATH, using = "//label[@for='DocumentCategoryId']")
    public static WebElement adminUploadDocumentModalTypeOfDocumentLabel;

    @FindBy(how = How.ID, using = "DocumentCategoryId")
    public static WebElement adminUploadDocumentModalTypeOfDocumentDropdown;

    @FindBy(how = How.XPATH, using = "(//input[@name = 'documentsUpload'])[2]")
    public static WebElement adminUploadDocumentModalUploadInput;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'upload-list-success')]/li")
    public static List<WebElement> adminUploadDocumentModalUploadFileListContainer;

    @FindBy(how = How.XPATH, using = "//ul[@class='js-file-upload-list-success']/li/p[@class='file-name']/span[2]")
    public static WebElement adminManageApplicationClaimUploadedDocumentLabel;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'cta add-document')]")
    public static WebElement adminUploadDocumentModalAddDocumentButton;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'upload-list-success')]/li")
    public static List<WebElement> adminUploadedDocumentsListContainer;

    @FindBy(how = How.XPATH, using = "//table[@class='table-submodule js-responsive-table']//tbody/descendant::tr//td")
    public static WebElement adminDownloadedFileLink1Name;

    @FindBy(how = How.XPATH, using = "//button[@class = 'js-fl1-remove _action file-remove']")
    public static WebElement adminDownloadRemoveFile;

    @FindBy(how = How.XPATH, using = "//label[contains(@for,'DocumentCategoryId')]")
    public static WebElement MemberUploadDocumentModalTypeOfDocumentLabel;

    @FindBy(how = How.ID, using = "DocumentCategoryId")
    public static WebElement MemberUploadDocumentModalTypeOfDocumentDropdown;

    @FindBy(how = How.XPATH, using = "(//input[@name = 'documentsUpload'])[2]")
    public static WebElement MemberUploadDocumentModalUploadInput;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'cta add-document')]")
    public static WebElement MemberUploadDocumentModalAddDocumentButton;

    @FindBy(how = How.XPATH, using = "//ul[contains(@id ,'list-container')]/li")
    public static List<WebElement> MemberUploadedDocumentsListContainer;

    @FindBy(how = How.XPATH, using = "//li[@class='pagination-list__item']")
    public static List<WebElement> paginationListItems;

    @FindBy(how = How.XPATH, using = "//footer")
    public static WebElement memberFooter;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']")
    public static WebElement uploadedDocumentsTable;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[1]/td[1]")
    public static WebElement uploadedDocumentsTableDocumentTypeFirst;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[1]/td[2]")
    public static WebElement uploadedDocumentsTableUploadDateFirst;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[2]/td[1]")
    public static WebElement uploadedDocumentsTableDocumentTypeSecond;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[2]/td[2]")
    public static WebElement uploadedDocumentsTableUploadDateSecond;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr/td[3]/a")
    public static WebElement uploadedDocumentTableDownloadLink1;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[2]/td[3]/a")
    public static WebElement uploadedDocumentTableDownloadLink2;

    @FindBy(how = How.XPATH, using = "//div[@class='table-container dashboard-table']//table//tbody/tr[3]/td[3]/a")
    public static WebElement uploadedDocumentTableDownloadLink3;

    public boolean verifyLoggedOutPageTitle() {
        String expectedTitle = "Logged Out | MLC Life Insurance";
        return driver.getTitle().contains(expectedTitle);
    }

    @FindBy(how = How.XPATH, using = "//p[@class='file-name']")
    public static List<WebElement> adminUploadedDocumentsFileLabel;

    @FindBy(how = How.XPATH, using = "//span[@class='notificationMsg']/a")
    public static WebElement notificationMessage;

    public void clickResumeNowButton() {
        notificationMessage.click();
    }

}
