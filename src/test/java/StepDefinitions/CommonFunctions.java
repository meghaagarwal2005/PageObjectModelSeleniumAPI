package StepDefinitions;

import Common.BaseClass;
import PageObjects.*;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class CommonFunctions extends BaseClass {

    public static HashMap<String, String> sys_config = null;
    public static int shortWait = 60;
    public static int Longwait = 300;
    static public boolean flag = false;
    public static boolean isFilePresent = false;
    static public JavascriptExecutor executor = (JavascriptExecutor) driver;

    public static void dateComparision(WebElement date1, WebElement date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        try {
            Date FirstDate = sdf.parse(date1.getText());
            Date SecondDate = sdf.parse(date2.getText());
            Assert.assertTrue(FirstDate.compareTo(SecondDate) < 0);
            System.out.println("Last Updated Date is on top");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void explicitWait(WebElement element) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, shortWait);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitTillNextButtonIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, shortWait);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String btnEnable = element.getText();
                return btnEnable.equals("Next");
            }
        });
    }


    public static void selectDropdownValue(String P, WebElement element) {
        Select selectvalue = new Select(element);
        selectvalue.selectByVisibleText(P.trim());
    }

    public static void selectElementFromElementsList(String P, List<WebElement> elements) {
        for (WebElement element : elements) {
            if (element.getText().contains(P)) {
                log.info("The selected option is " + element.getText());
                clickOn(element);
                break;
            }
        }
    }


    public static LocalDate dateFormat(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        return date;
    }


    public static String dateTimeMilliSecFormatToString() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuuHHmmssSS");
        String stringDateTimeSec = myDateObj.format(formatter);
        return stringDateTimeSec;
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static String convertStringNumtoDollarValue(String amountText) {
        double value = Double.parseDouble(amountText);
        DecimalFormat dformat = new DecimalFormat("$###,###.##");
        log.info("The dollar value is" + dformat.format(value));
        return dformat.format(value);
    }

    public static String convertStringToDollarValueWithDecimal(String amountText) {
        double value = Double.parseDouble(amountText);
        DecimalFormat dformat = new DecimalFormat("$###,###.00");
        log.info("The dollar value is" + dformat.format(value));
        return dformat.format(value);
    }


    public static void selectDropdownValueByIndex(WebElement element) {
        Select selectvalue = new Select(element);
        selectvalue.selectByIndex(1);
    }

    public static void explicitWaitForTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Longwait);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void calculateListsize(List<WebElement> ls) {
        System.out.println("The size of list is " + ls.size());

    }

    public static List<String> obtainListAsString(List<WebElement> ls) {
        List<String> obtainedList = new ArrayList<String>();
        List<WebElement> List = ls;

        for (WebElement we : ls) {
            obtainedList.add(we.getText());
        }
        return obtainedList;
    }

    public static void explicitWaitForUrl(String url) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Longwait);
        wait.until(ExpectedConditions.urlContains(url));
    }


    public static List<String> sortColoumninDescending(List<WebElement> ls, WebElement element)
            throws InterruptedException {
        List<String> obtainedList = new ArrayList<>();
        for (WebElement we : ls) {
            obtainedList.add(we.getText());
        }
        System.out.println("Original List" + obtainedList);
        CommonFunctions.clickUsingJavaScript(element);
        List<WebElement> AfterSortNameList = ls;
        List<String> sortedList = new ArrayList<>();
        for (WebElement s : AfterSortNameList) {
            sortedList.add(s.getText());
        }
        System.out.println("Sorted list in Descending order" + sortedList);
        Collections.sort(obtainedList, (o1, o2) -> o1.toLowerCase().equals(o2.toLowerCase())
                ? o1.toLowerCase().equals(o1) ? 1 : -1 : -o1.toLowerCase().compareTo(o2.toLowerCase()));
        System.out.println("Original List in descending order" + obtainedList);
        Assert.assertTrue(sortedList.equals(obtainedList));
        return obtainedList;
    }

    public static void clickElement(WebElement element) {
        try {
            //  System.out.println("Clicking an element ");
            element.click();
        } catch (Exception f) {
            Assert.fail(f.getMessage());
        }
    }

    public static void checkElementIsDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("The element is not displayed" + " " + e.getMessage());
        }
    }

    public static void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void clickUsingJavaScript(WebElement element) throws InterruptedException {
        CommonFunctions.explicitWait(element);
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click ");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element ");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click using javascript element " + e.getStackTrace());
        }
    }

    public static void scrollUsingJavaScript(WebElement element) {
        executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollUsingJavaScript1(WebElement element) {
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void simpleClickUsingJavaScript(WebElement element) {
        executor.executeScript("arguments[0].click();", element);
    }

    public static void sortColoumninAscending(List<WebElement> ls, WebElement element) throws InterruptedException {
        Thread.sleep(1000);
        List<String> obtainedList = new ArrayList<>();
        System.out.println("Size of List is " + ls.size());
        for (WebElement we : ls) {
            obtainedList.add(we.getText());
        }
        System.out.println("Original List" + obtainedList);

        CommonFunctions.clickUsingJavaScript(element);

        List<WebElement> AfterSortNumberList = ls;
        ArrayList<String> sortedList = new ArrayList<>();
        for (WebElement sl : AfterSortNumberList) {
            sortedList.add(sl.getText());
        }
        System.out.println(" Ascending order Sorted List" + sortedList);

        Collections.sort(obtainedList, (o1, o2) -> o1.toLowerCase().equals(o2.toLowerCase())
                ? o1.toLowerCase().equals(o1) ? -1 : 1 : o1.toLowerCase().compareTo(o2.toLowerCase()));
        System.out.println("Original List in Ascending order" + obtainedList);
        System.out.println("Verify if original sorted list and SortedList via arrow button is same in ascending order");
        Assert.assertTrue(sortedList.equals(obtainedList));
    }

    public static void compare_items_On_First_And_Second_Page_After_Pagination(WebElement itemToCompare,
                                                                               WebElement pageNo, WebElement elementToWaitFor) throws InterruptedException {
        String page2FirstItem = itemToCompare.getText();
        System.out.println("2n value : " + page2FirstItem);
        pageNo.click();
        explicitWait(elementToWaitFor);
        String page1FirstItem = itemToCompare.getText();
        System.out.println("1n value : " + page1FirstItem);
        // Assert.assertFalse(page1FirstItem.equals(page2FirstItem));
        Assert.assertNotEquals(page2FirstItem, page1FirstItem);
        System.out.println("Page 1 clicked");

    }

    public static void webelementListConversionToString(List<WebElement> ls) {
        System.out.println("To validate if items on list are displayed on Page\n");
        List<String> List = new ArrayList<String>();
        List<Boolean> result = new ArrayList<Boolean>();
        for (WebElement fields : ls) {
            List.add(fields.getText().trim());
        }
        for (String fieldsvalue : List) {

            result.add(fieldsvalue != "complete" && fieldsvalue != "Complete");
            Assert.assertFalse(result.contains(false));
        }
        System.out.println("assert success");
    }

    public static void Pagination(List<WebElement> ls, WebElement paginationContainer, WebElement lastPage,
                                  int PageList) throws InterruptedException {
        int ListItems = ls.size();
        Assert.assertFalse(ListItems == 0);
        if (ListItems == PageList) {
            Assert.assertTrue(paginationContainer.isDisplayed());
            flag = lastPage.isDisplayed();
            System.out.println("Last Page Present if flag value is true: Flag Value is " + flag);
        }
    }

    public static void verifyIfElementPresent(WebElement element) {
        Assert.assertFalse(element.getText().isEmpty());
    }

    public static void uploadFile(WebDriver driver, String fileType) throws InterruptedException {
        switch (fileType) {
            case "PDF file":
                System.out.println("Admin Upload Document - PDF file ");
                File file = new File("src/test/Resources/data/Digital/Uploads/ChangeOfPersonal()[]{}+&DetailsForm.pdf");
                System.out.println(file.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(file.getAbsolutePath());
                Thread.sleep(2000);
                Assert.assertTrue(CommonElements.adminUploadDocumentModalUploadFileListContainer.size() > 0);
                break;
            case "Medium PDF file":
                System.out.println("Admin Upload Document -  Large PDF file ");
                File pdfLargeFile = new File("src/test/Resources/data/Digital/Uploads/PDF_medium.pdf");
                System.out.println(pdfLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(pdfLargeFile.getAbsolutePath());
                break;
            case "XLS file":
                System.out.println("Admin Upload Document -  XLS file");
                File xlsFile = new File("src/test/Resources/data/Digital/Uploads/XLS__small.xls");
                System.out.println(xlsFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(xlsFile.getAbsolutePath());
                break;
            case "XLSX file":
                System.out.println("Admin Upload Document -  XLSX file");
                File xlsxFile = new File("src/test/Resources/data/Digital/Uploads/XLSX_small.xlsx");
                System.out.println(xlsxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(xlsxFile.getAbsolutePath());
                break;
            case "XLSM file":
                System.out.println("Admin Upload Document -  XLSM file ");
                File xlsmFile = new File("src/test/Resources/data/Digital/Uploads/XLSM_small.xlsm");
                System.out.println(xlsmFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(xlsmFile.getAbsolutePath());
                break;
            case "Large XLSX file":
                System.out.println("Admin Upload Document -  xlsLargeFile file ");
                File xlsxLargeFile = new File("src/test/Resources/data/Digital/Uploads/XLSX_small.xlsx");
                System.out.println(xlsxLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(xlsxLargeFile.getAbsolutePath());
                break;
            case "CSV file":
                System.out.println("Admin Upload Document -  CSV file");
                File csvFile = new File("src/test/Resources/data/Digital/Uploads/CSV_small.csv");
                System.out.println(csvFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(csvFile.getAbsolutePath());
                break;
            case "DOCX file":
                System.out.println("Admin Upload Document -  DOCX file");
                File docxFile = new File("src/test/Resources/data/Digital/Uploads/DOCX_small.docx");
                System.out.println(docxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(docxFile.getAbsolutePath());
                break;
            case "DOC file":
                System.out.println("Admin Upload Document -  DOC file");
                File docFile = new File("src/test/Resources/data/Digital/Uploads/DOC_small.doc");
                System.out.println(docFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(docFile.getAbsolutePath());
                break;
            case "TIFF file":
                System.out.println("Admin Upload Document -  file ");
                File tiffFile = new File("src/test/Resources/data/Digital/Uploads/TIFF_small.tiff");
                System.out.println(tiffFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(tiffFile.getAbsolutePath());
                break;
            case "TIF file":
                System.out.println("Admin Upload Document -  TIF file ");
                File tifFile = new File("src/test/Resources/data/Digital/Uploads/TIF_small.tif");
                System.out.println(tifFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(tifFile.getAbsolutePath());
                break;
            case "JPEG file":
                System.out.println("Admin Upload Document -  JPEG file ");
                File jpegFile = new File("src/test/Resources/data/Digital/Uploads/JPEG_small.jpeg");
                System.out.println(jpegFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(jpegFile.getAbsolutePath());
                break;
            case "JPG file":
                System.out.println("Admin Upload Document -  Small JPG file ");
                File jpgFile = new File("src/test/Resources/data/Digital/Uploads/JPG_small.jpg");
                System.out.println(jpgFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(jpgFile.getAbsolutePath());
                break;
            case "Medium JPG file":
                System.out.println("Admin Upload Document -  Medium JPG file ");
                File jpgMedFile = new File("src/test/Resources/data/Digital/Uploads/JPG_medium.jpg");
                System.out.println(jpgMedFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(jpgMedFile.getAbsolutePath());
                break;
            case "Large JPG file":
                System.out.println("Admin Upload Document -  Large JPG  file ");
                File jpgLargeFile = new File("src/test/Resources/data/Digital/Uploads/JPG_large.jpg");
                System.out.println(jpgLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(jpgLargeFile.getAbsolutePath());
                break;
            case "GIF file":
                System.out.println("Admin Upload Document -  GIF file ");
                File gifFile = new File("src/test/Resources/data/Digital/Uploads/GIF_small.gif");
                System.out.println(gifFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(gifFile.getAbsolutePath());
                break;
            case "PNG file":
                System.out.println("Admin Upload Document -  PNG file ");
                File pngFile = new File("src/test/Resources/data/Digital/Uploads/PNG_small.PNG");
                System.out.println(pngFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(pngFile.getAbsolutePath());
                break;
            case "PPT file":
                System.out.println("Admin Upload Document -  PPT file ");
                File pptFile = new File("src/test/Resources/data/Digital/Uploads/PPT_small.ppt");
                System.out.println(pptFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(pptFile.getAbsolutePath());
                break;
            case "PPTX file":
                System.out.println("Admin Upload Document -  PPTX file ");
                File pptxFile = new File("src/test/Resources/data/Digital/Uploads/PPTX_small.pptx");
                System.out.println(pptxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(pptxFile.getAbsolutePath());
                break;
            case "TXT file":
                System.out.println("Admin Upload Document -  TXT file ");
                File txtFile = new File("src/test/Resources/data/Digital/Uploads/TXT_small.txt");
                System.out.println(txtFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(txtFile.getAbsolutePath());
                break;
            case "EXE file":
                System.out.println("Admin Upload Document -  EXE file ");
                File exeFile = new File("src/test/Resources/data/Digital/Uploads/EXE_small.exe");
                System.out.println(exeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(exeFile.getAbsolutePath());
                break;
            case "MSG file":
                System.out.println("Admin Upload Document -  MSG file");
                File msgFile = new File("src/test/Resources/data/Digital/Uploads/MSG_small.msg");
                System.out.println(msgFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(msgFile.getAbsolutePath());
                break;
            case "ZIP file":
                System.out.println("Admin Upload Document -  ZIP file ");
                File zipFile = new File("src/test/Resources/data/Digital/Uploads/ZIP_small.zip");
                System.out.println(zipFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(zipFile.getAbsolutePath());
                break;
            case "AVI file":
                System.out.println("Admin Upload Document -  AVI file ");
                File aviFile = new File("src/test/Resources/data/Digital/Uploads/AVI_small.avi");
                System.out.println(aviFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput.sendKeys(aviFile.getAbsolutePath());
                break;
            default:
                System.out.println("At Upload File default \n");
                throw new AssertionError();
        }
    }

    public static void uploadFile2(WebDriver driver, String fileType) throws InterruptedException {
        switch (fileType) {
            case "PDF file":
                System.out.println("Admin Upload Document - PDF file ");
                File file = new File("src/test/Resources/data/Digital/Uploads/ChangeOfPersonal()[]{}+&DetailsForm.pdf");
                System.out.println(file.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(file.getAbsolutePath());
                Thread.sleep(2000);
                Assert.assertTrue(CommonElements.adminUploadDocumentModalUploadFileListContainer.size() > 0);
                break;
            case "Medium PDF file":
                System.out.println("Admin Upload Document -  Large PDF file ");
                File pdfLargeFile = new File("src/test/Resources/data/Digital/Uploads/PDF_medium.pdf");
                System.out.println(pdfLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(pdfLargeFile.getAbsolutePath());
                break;
            case "XLS file":
                System.out.println("Admin Upload Document -  XLS file");
                File xlsFile = new File("src/test/Resources/data/Digital/Uploads/XLS__small.xls");
                System.out.println(xlsFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(xlsFile.getAbsolutePath());
                break;
            case "XLSX file":
                System.out.println("Admin Upload Document -  XLSX file");
                File xlsxFile = new File("src/test/Resources/data/Digital/Uploads/XLSX_small.xlsx");
                System.out.println(xlsxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(xlsxFile.getAbsolutePath());
                break;
            case "XLSM file":
                System.out.println("Admin Upload Document -  XLSM file ");
                File xlsmFile = new File("src/test/Resources/data/Digital/Uploads/XLSM_small.xlsm");
                System.out.println(xlsmFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(xlsmFile.getAbsolutePath());
                break;
            case "Large XLSX file":
                System.out.println("Admin Upload Document -  xlsLargeFile file ");
                File xlsxLargeFile = new File("src/test/Resources/data/Digital/Uploads/XLSX_small.xlsx");
                System.out.println(xlsxLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(xlsxLargeFile.getAbsolutePath());
                break;
            case "CSV file":
                System.out.println("Admin Upload Document -  CSV file");
                File csvFile = new File("src/test/Resources/data/Digital/Uploads/CSV_small.csv");
                System.out.println(csvFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(csvFile.getAbsolutePath());
                break;
            case "DOCX file":
                System.out.println("Admin Upload Document -  DOCX file");
                File docxFile = new File("src/test/Resources/data/Digital/Uploads/DOCX_small.docx");
                System.out.println(docxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(docxFile.getAbsolutePath());
                break;
            case "DOC file":
                System.out.println("Admin Upload Document -  DOC file");
                File docFile = new File("src/test/Resources/data/Digital/Uploads/DOC_small.doc");
                System.out.println(docFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(docFile.getAbsolutePath());
                break;
            case "TIFF file":
                System.out.println("Admin Upload Document -  file ");
                File tiffFile = new File("src/test/Resources/data/Digital/Uploads/TIFF_small.tiff");
                System.out.println(tiffFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(tiffFile.getAbsolutePath());
                break;
            case "TIF file":
                System.out.println("Admin Upload Document -  TIF file ");
                File tifFile = new File("src/test/Resources/data/Digital/Uploads/TIF_small.tif");
                System.out.println(tifFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(tifFile.getAbsolutePath());
                break;
            case "JPEG file":
                System.out.println("Admin Upload Document -  JPEG file ");
                File jpegFile = new File("src/test/Resources/data/Digital/Uploads/JPEG_small.jpeg");
                System.out.println(jpegFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(jpegFile.getAbsolutePath());
                break;
            case "JPG file":
                System.out.println("Admin Upload Document -  Small JPG file ");
                File jpgFile = new File("src/test/Resources/data/Digital/Uploads/JPG_small.jpg");
                System.out.println(jpgFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(jpgFile.getAbsolutePath());
                break;
            case "Medium JPG file":
                System.out.println("Admin Upload Document -  Medium JPG file ");
                File jpgMedFile = new File("src/test/Resources/data/Digital/Uploads/JPG_medium.jpg");
                System.out.println(jpgMedFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(jpgMedFile.getAbsolutePath());
                break;
            case "Large JPG file":
                System.out.println("Admin Upload Document -  Large JPG  file ");
                File jpgLargeFile = new File("src/test/Resources/data/Digital/Uploads/JPG_large.jpg");
                System.out.println(jpgLargeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(jpgLargeFile.getAbsolutePath());
                break;
            case "GIF file":
                System.out.println("Admin Upload Document -  GIF file ");
                File gifFile = new File("src/test/Resources/data/Digital/Uploads/GIF_small.gif");
                System.out.println(gifFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(gifFile.getAbsolutePath());
                break;
            case "PNG file":
                System.out.println("Admin Upload Document -  PNG file ");
                File pngFile = new File("src/test/Resources/data/Digital/Uploads/PNG_small.PNG");
                System.out.println(pngFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(pngFile.getAbsolutePath());
                break;
            case "PPT file":
                System.out.println("Admin Upload Document -  PPT file ");
                File pptFile = new File("src/test/Resources/data/Digital/Uploads/PPT_small.ppt");
                System.out.println(pptFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(pptFile.getAbsolutePath());
                break;
            case "PPTX file":
                System.out.println("Admin Upload Document -  PPTX file ");
                File pptxFile = new File("src/test/Resources/data/Digital/Uploads/PPTX_small.pptx");
                System.out.println(pptxFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(pptxFile.getAbsolutePath());
                break;
            case "TXT file":
                System.out.println("Admin Upload Document -  TXT file ");
                File txtFile = new File("src/test/Resources/data/Digital/Uploads/TXT_small.txt");
                System.out.println(txtFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(txtFile.getAbsolutePath());
                break;
            case "EXE file":
                System.out.println("Admin Upload Document -  EXE file ");
                File exeFile = new File("src/test/Resources/data/Digital/Uploads/EXE_small.exe");
                System.out.println(exeFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(exeFile.getAbsolutePath());
                break;
            case "MSG file":
                System.out.println("Admin Upload Document -  MSG file");
                File msgFile = new File("src/test/Resources/data/Digital/Uploads/MSG_small.msg");
                System.out.println(msgFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(msgFile.getAbsolutePath());
                break;
            case "ZIP file":
                System.out.println("Admin Upload Document -  ZIP file ");
                File zipFile = new File("src/test/Resources/data/Digital/Uploads/ZIP_small.zip");
                System.out.println(zipFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(zipFile.getAbsolutePath());
                break;
            case "AVI file":
                System.out.println("Admin Upload Document -  AVI file ");
                File aviFile = new File("src/test/Resources/data/Digital/Uploads/AVI_small.avi");
                System.out.println(aviFile.getAbsolutePath() + "\n");
                CommonElements.UploadDocumentModalUploadInput2.sendKeys(aviFile.getAbsolutePath());
                break;
            default:
                System.out.println("At Upload File default \n");
                throw new AssertionError();
        }
    }


    public static void clickOnAddDocumentButton(WebDriver driver, String field) {
        if (CommonElements.adminUploadDocumentModalUploadFileListContainer.size() > 0) {
            System.out.println("Admin Document Upload Modal : Add Document \n");
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(CommonElements.adminDownloadRemoveFile));
            CommonElements.adminUploadDocumentModalAddDocumentButton.click();
        } else {
            System.out.println("No files were uploaded");
            Assert.assertTrue(false);
        }

    }

    public static boolean validateDocumentsDownloaded(WebDriver driver) {
        File filePath = new File("src/test/Resources/Downloads");
        String downloadFilePath = filePath.getAbsolutePath();
        String currentFile = CommonElements.adminDownloadedFileLink1Name.getText();
        File tmpDir = new File(downloadFilePath);
        if (tmpDir.exists()) {
            isFilePresent = new File(tmpDir, currentFile).exists();
            if (isFilePresent) {
                System.out.println("File" + currentFile + "exists \n");
                return true;
            } else {
                System.out.println("File" + currentFile + "does not exists \n");
                return false;
            }
        } else {
            System.out.println(tmpDir + " directory does not exists \n");
            return false;
        }
    }



    public static void switchTab(WebElement button) {
        String oldTab = driver.getWindowHandle();
        button.click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(1));
    }

    public static void compareText(WebElement a, WebElement b) {
        assertTrue(a.getText().equals(b.getText()));

    }

    public static void verifyDetailsAreNotEmpty(List<WebElement> Elementslist) {
        for (WebElement element : Elementslist) {
            System.out.println("Member detail Value is : \n" + element.getText());
            Assert.assertTrue(!element.getText().isEmpty());
        }
    }

    public static void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Longwait);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void verifyElementIsNotEmpty(List<WebElement> element) {
        for (WebElement temp : element) {
            Assert.assertTrue(!temp.getText().isEmpty());
        }
    }

    public static void verifyLinks(WebElement element, String fund) {


        List<WebElement> allLinks = element.findElements(By.tagName("a"));


        String[] telstraSuperMemberPortalFooterSitEnvLinks = {"https://sit2-telstra-super.ad.mlclife.com.au/best-doctors",
                "https://www.telstrasuper.com.au/products-and-services/financial-planning-and-advice",
                "https://www.telstrasuper.com.au/products-and-services/insurance/insurance-in-superannuation-voluntary-code-of-practice",
                "https://www.mlcinsurance.com.au/privacy-policy",
                "https://www.telstrasuper.com.au/legal/privacy",
                "https://www.mlcinsurance.com.au/security",
                "https://sit2-telstra-super.ad.mlclife.com.au/termsandconditions",
                "tel://1300033166",
                "mailto:contact@telstrasuper.com.au",
                "https://www.telstrasuper.com.au/",
                "https://www.facebook.com/TelstraSuper/",
                "https://www.linkedin.com/company/telstra-super/",
                "https://twitter.com/telstrasuper"};

        String[] telstraSuperMemberPortalFooterPvtEnvLinks = {"https://pvt-telstra-super.lifeview.mlcinsurance.com.au/best-doctors",
                "https://www.telstrasuper.com.au/products-and-services/financial-planning-and-advice",
                "https://www.telstrasuper.com.au/products-and-services/insurance/insurance-in-superannuation-voluntary-code-of-practice",
                "https://www.mlcinsurance.com.au/privacy-policy",
                "https://www.telstrasuper.com.au/legal/privacy",
                "https://www.mlcinsurance.com.au/security",
                "https://pvt-telstra-super.lifeview.mlcinsurance.com.au/termsandconditions",
                "tel://1300033166",
                "mailto:contact@telstrasuper.com.au",
                "https://www.telstrasuper.com.au/",
                "https://www.facebook.com/TelstraSuper/",
                "https://www.linkedin.com/company/telstra-super/",
                "https://twitter.com/telstrasuper"};

        String[] telstraSuperMemberPortalFooterProdEnvLinks = {"https://telstra-super.lifeview.insure/best-doctors",
                "https://www.telstrasuper.com.au/products-and-services/financial-planning-and-advice",
                "https://www.telstrasuper.com.au/products-and-services/insurance/insurance-in-superannuation-voluntary-code-of-practice",
                "https://www.mlcinsurance.com.au/privacy-policy",
                "https://www.telstrasuper.com.au/legal/privacy",
                "https://www.mlcinsurance.com.au/security",
                "https://telstra-super.lifeview.insure/termsandconditions",
                "tel://1300033166",
                "mailto:contact@telstrasuper.com.au",
                "https://www.telstrasuper.com.au/",
                "https://www.facebook.com/TelstraSuper/",
                "https://www.linkedin.com/company/telstra-super/",
                "https://twitter.com/telstrasuper"};

        String[] visionSuperMemberPortalFooterSitEnvLinks = {"https://sit2-vision-super.ad.mlclife.com.au/best-doctors",
                "https://www.visionsuper.com.au/retire/financial-advice/",
                "https://www.mlcinsurance.com.au/code-of-practice",
                "https://www.mlcinsurance.com.au/privacy-policy",
                "https://www.visionsuper.com.au/privacy",
                "https://www.mlcinsurance.com.au/security",
                "tel://1300300820",
                "mailto:memberservices@visionsuper.com.au",
                "https://sit2-vision-super.ad.mlclife.com.au/",
                "https://www.facebook.com/visionsuperfund/",
                "https://www.linkedin.com/company/vision-super",
                "https://twitter.com/visionsuper"};

        String[] visionSuperMemberPortalFooterPvtEnvLinks = {"https://pre-vision-super.lifeview.mlcinsurance.com.au/best-doctors",
                "https://www.visionsuper.com.au/retire/financial-advice/",
                "https://www.mlcinsurance.com.au/code-of-practice",
                "https://www.mlcinsurance.com.au/privacy-policy",
                "https://www.visionsuper.com.au/privacy",
                "https://www.mlcinsurance.com.au/security",
                "tel://1300300820",
                "mailto:memberservices@visionsuper.com.au",
                "https://pre-vision-super.lifeview.mlcinsurance.com.au/",
                "https://www.facebook.com/visionsuperfund/",
                "https://www.linkedin.com/company/vision-super",
                "https://twitter.com/visionsuper"};



        for (int i = 0; i < allLinks.size(); i++) {

            String env = System.getProperty("Env");

            String url = allLinks.get(i).getAttribute("href");

            switch (env) {
                case "LV2SIT":
                    if (fund.equalsIgnoreCase("Telstra Super Member Portal")) {
                        Assert.assertEquals(url, telstraSuperMemberPortalFooterSitEnvLinks[i]);
                    } else if (fund.equalsIgnoreCase("Vision Super Member Portal")) {
                        Assert.assertEquals(url, visionSuperMemberPortalFooterSitEnvLinks[i]);
                    }
                    break;

                case "LV2PVT":
                    if (fund.equalsIgnoreCase("Telstra Super Member Portal")) {
                        Assert.assertEquals(url, telstraSuperMemberPortalFooterPvtEnvLinks[i]);
                    } else if (fund.equalsIgnoreCase("Vision Super Member Portal")) {
                        Assert.assertEquals(url, visionSuperMemberPortalFooterPvtEnvLinks[i]);
                    }
                    break;
                case "PROD":
                    if (fund.equalsIgnoreCase("Telstra Super Member Portal")) {
                        Assert.assertEquals(url, telstraSuperMemberPortalFooterProdEnvLinks[i]);
                    } else if (fund.equalsIgnoreCase("Vision Super Member Portal")) {
                        Assert.assertEquals(url, visionSuperMemberPortalFooterPvtEnvLinks[i]);
                    }
                    break;

                default:
                    log.info("At default: invalid environment ");
                    break;


            }
        }

    }

    public static void waitForPageLoaded() {

        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(10000);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public static String parseStringByRemovingSpecialCharacters(String str) throws ParseException {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(str);
        System.out.println(number.toString());
        return number.toString();
    }

    public static void handleStaleElementException(WebElement element) {
        new WebDriverWait(driver, Longwait).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String getHexColorValue(WebDriver driver, WebElement path) {
        String color = path.getCssValue("background-color");
        String hexColor = Color.fromString(color).asHex();
        return hexColor;
    }

    public static void spanTextRepalcement(WebElement heading, WebElement nestedSpan, String option) {
        String spanText = nestedSpan.getText();
        String headingText = heading.getAttribute("innerText").replace(spanText, "").trim();
        log.info(headingText);
        Assert.assertEquals(option, headingText);
        log.info("The option " + option + " is visible");
    }

    public static void brTextRemoval(WebElement mainText, String removeText, String option) {
        String spanText = removeText;
        String newText = mainText.getAttribute("innerText").replace(spanText, "").trim();
        log.info(newText);
        Assert.assertEquals(option, newText);
        log.info("The option " + option + " is visible");
    }

    public static void jsclickButton(WebElement element) {
        executor.executeScript("arguments[0].click();", element);
    }

}

