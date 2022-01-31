package StepDefinitions;

import Common.*;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {

    protected static BaseClass baseClass;
    public static Scenario scenario;
    private static KeyValReader webDriverProperties = new KeyValReader("Configs/webdriver.properties");


    @Before("@WebRegression")
    public synchronized void openBrowser(Scenario scenario) throws Exception {
        System.out.println("Inside Hooks");
        Hooks.scenario =scenario;
        String logfolderPath = getCurrentFeatureFilePath(scenario.getId().replace("%20","_"));
        String scenarioName = scenario.getName().trim().replace(" ", "_");
        new LoggerManager(logfolderPath,scenarioName);
        baseClass = new BaseClass();

            String browserText = baseClass.hiveProp.getProperty("browserType");
            BrowserManager browserManager;

            switch (browserText.toUpperCase()) {

                case "CHROME":
                    browserManager = new ChromeDriverManager();
                    break;

                case "EDGE":
                    browserManager = new EdgeDriverManager();
                    break;
                default:
                    throw new RuntimeException("InvalidBrowserType - Browser " + browserText + " currently not supported");
            }
            BaseClass.driver = browserManager.initializeDriver();
            setBrowserTimeOuts(BaseClass.driver);
            setWindowSize(BaseClass.driver);
            BaseClass.driver.manage().deleteAllCookies();



    }

    public static void setBrowserTimeOuts(WebDriver currentBrowser) {

        int pageLoadTime = Integer.parseInt(webDriverProperties.getProperty("PageLoadTime"));
        int scriptTimeout = Integer.parseInt(webDriverProperties.getProperty("ScriptTimeout"));
        int implicitWait = Integer.parseInt(webDriverProperties.getProperty("ImplicitWait"));

        currentBrowser.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
        BaseClass.log.debug("Page Load Timeout set to {} secs", pageLoadTime);

        currentBrowser.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        BaseClass.log.debug("Script Timeout set to {} secs", scriptTimeout);

        currentBrowser.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        BaseClass.log.debug("Implicit Wait set to {} secs", implicitWait);
    }

    public static void setWindowSize(WebDriver currentBrowser) {


        int windowWidth = Integer.parseInt(webDriverProperties.getProperty("windowWidth"));
        int windowHeight = Integer.parseInt(webDriverProperties.getProperty("windowHeight"));

        if (windowWidth == 0 || windowHeight == 0) {
            java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the dimension of screen
            BaseClass.log.info("Screen Width before maximizing: " + screenSize.getWidth());
            BaseClass.log.info("Screen Height before maximizing: " + screenSize.getHeight());
            int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution(); //get the resolution
            BaseClass.log.info("Screen Resolution before maximizing: : "+screenResolution);
            currentBrowser.manage().window().maximize();
            if(System.getProperty("zoomlevel")!=null) {
                JavascriptExecutor jse = (JavascriptExecutor) currentBrowser;
                String script = "document.body.style.zoom='" + System.getProperty("zoomlevel") + "%';";
                BaseClass.log.info("The script for setting zoom level is: " + script);
                jse.executeScript(script);
            }
            screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the dimension of screen
            BaseClass.log.info("Screen Width after maximizing:" + screenSize.getWidth());
            BaseClass.log.info("Screen Height after maximizing: " + screenSize.getHeight());
            screenResolution = Toolkit.getDefaultToolkit().getScreenResolution(); //get the resolution
            BaseClass.log.info("Screen resolution after maximizing is : "+screenResolution);
        } else {
            currentBrowser.manage().window().setSize(new org.openqa.selenium.Dimension(windowWidth, windowHeight));
        }

    }

    @Before("@APIRegression")
    public synchronized void beforeAPI(Scenario scenario) throws Exception {
        System.out.println("Inside Hooks");
        Hooks.scenario = scenario;
        String logfolderPath = getCurrentFeatureFilePath(scenario.getId().replace("%20", "_"));
        String scenarioName = scenario.getName().trim().replace(" ", "_");
        new LoggerManager(logfolderPath, scenarioName);
    }


    @Before("@DeletePlace")
    public void beforeScenario() throws IOException
    {		//execute this code only when place id is null
        //write a code that will give you place id

        StepDefinitions.StepDefination m =new StepDefinitions.StepDefination();
        if(StepDefinitions.StepDefination.place_id==null)
        {

            m.add_Place_Payload_with("Shetty", "French", "Asia");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
        }



    }


    private String getCurrentFeatureFilePath(String scenarioId) {
        String userWorkingDirectory = System.getProperty("user.dir").replace("\\","/");
        String featureFilePrefix = "file:///".concat(userWorkingDirectory);
        String featureFileSuffix = ".feature";

        String featureFilePath = StringUtils.substringBetween(scenarioId,featureFilePrefix, featureFileSuffix);
        System.out.println("The scenario id is "+scenarioId +" The featurefile path is :"+ featureFilePath);
        return featureFilePath;

    }

    @After("@WebRegression")
    // Embed screen shot in test report if the test is marked as failed
    public void finalize(Scenario scenario) throws IOException {

            if (scenario.isFailed()) {
                System.out.println("coming here");
                BufferedImage image = Shutterbug.shootPage(BaseClass.driver, Capture.FULL).getImage();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "png", outputStream);
                scenario.attach(outputStream.toByteArray(), "image/png", scenario.getName());
            }


            BaseClass.driver.close();
            BaseClass.driver.quit();
        }


}
