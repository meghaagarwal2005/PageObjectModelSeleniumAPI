package Common;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ChromeDriverManager implements BrowserManager {
	private Logger log = LoggerManager.getLogger();
	private KeyValReader chromeProperties =  new KeyValReader("Configs/chrome.properties");
	private KeyValReader hiveProperties =  new KeyValReader("Configs/theHive.properties");

	@Override
	public WebDriver initializeDriver() {

		String webDriverChromeDriver = chromeProperties.getProperty("ChromeDriver");
		String chromeLogFile = chromeProperties.getProperty("ChromeLogs");
		String chromeDriverSilentOutput = chromeProperties.getProperty("ChromeDriverSilentOutput");
		
		System.setProperty("webdriver.chrome.driver",webDriverChromeDriver);
		log.debug("webdriver.chrome.driver is set to {}",webDriverChromeDriver);
		
		System.setProperty("webdriver.chrome.logfile", chromeLogFile);
		log.debug("webdriver.chrome.logfile is set to {}",chromeLogFile);
		
		System.setProperty("webdriver.chrome.silentOutput", chromeDriverSilentOutput);
		log.debug("webdriver.chrome.silentOutput is set to {}",chromeDriverSilentOutput);
		
		Map<String,Object> chromePreferences = setChromePreferences();
		ChromeOptions chromeOptions = setChromeOptions(chromePreferences);
		
		try {
			WebDriver webDriver = new ChromeDriver(chromeOptions);
			return webDriver;
		} catch (Exception e) {
			log.fatal("Chrome WebDriver initialization failed" + ExceptionUtils.getMessage(e));
			throw new RuntimeException("Initializing Chrome Driver Failed " + ExceptionUtils.getMessage(e));
		}
		
	}
	
	private ChromeOptions setChromeOptions(Map<String,Object> chromePreferences) {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		
		String chromeBinary = chromeProperties.getProperty("ChromeBinary");
		Boolean runHeadless = Boolean.parseBoolean(chromeProperties.getProperty("headless"));
		
		chromeOptions.setBinary(chromeBinary);
		log.debug("Chrome Binary is set to {}",chromeBinary);
		chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("disable-popup-blocking");
		chromeOptions.addArguments("--disable-notifications");
		//options.addArguments("disable-infobars");
		//options.addArguments("--disable-automation");
		//chromeOptions.addArguments("--start-maximized");	
		
		if (runHeadless) {
			log.debug("Executing chrome in headless mode");
			chromeOptions.addArguments("headless");
		} else {
			log.debug("Executing chrome in regular mode");
		}
		
		chromeOptions.setExperimentalOption("prefs", chromePreferences);
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		return chromeOptions;
	}
	
	private Map<String, Object> setChromePreferences(){
		
		Map<String, Object> chromePreferences = new HashMap<String, Object>();
		FolderManager folderManager = new FolderManager();
		
		String downloadDirectory = System.getProperty("user.dir") + "\\" + folderManager.getDownloadFolder().replace("/","\\");
		Boolean downloadPrompt = Boolean.parseBoolean(chromeProperties.getProperty("DownloadPrompt"));
		Boolean downloadDirectoryUpgrade = Boolean.parseBoolean(chromeProperties.getProperty("DownloadDirectoryUpgrade"));
		Boolean safeBrowsingEnabled = Boolean.parseBoolean(chromeProperties.getProperty("SafeBrowsingEnabled"));
		Boolean safeBrowsingDisableDownloadProtection = Boolean.parseBoolean(chromeProperties.getProperty("SafeBrowsingDisableDownloadProtection"));
		Boolean useAutomationExtension =  Boolean.parseBoolean(chromeProperties.getProperty("UseAutomationExtension"));
		int popups = Integer.parseInt(chromeProperties.getProperty("profile_default_content_settings_popups"));
		
		chromePreferences.put("download.default_directory",downloadDirectory );
        log.debug("download.default_directory is set to {}",downloadDirectory);
        
        chromePreferences.put("browser.download.dir", downloadDirectory);
        log.debug("browser.download.dir is set to {}",downloadDirectory);
        
        chromePreferences.put("download.prompt_for_download",downloadPrompt);
        log.debug("download.prompt_for_download is set to {}",downloadPrompt);
        
        chromePreferences.put("download.directory_upgrade",downloadDirectoryUpgrade );
        log.debug("download.directory_upgrade is set to {}",downloadDirectoryUpgrade);
        
        chromePreferences.put("safebrowsing.enabled", safeBrowsingEnabled);
        log.debug("safebrowsing.enabled is set to {}",safeBrowsingEnabled);
        
        chromePreferences.put("safebrowsing.disable_download_protection",safeBrowsingDisableDownloadProtection );
        log.debug("safebrowsing.disable_download_protection is set to {}",safeBrowsingDisableDownloadProtection);
        
        chromePreferences.put("useAutomationExtension",useAutomationExtension);
        log.debug("useAutomationExtension is set to {}",useAutomationExtension);
        
        chromePreferences.put("profile.default_content_settings.popups",popups);
        log.debug("profile.default_content_settings.popups is set to {}",popups);
        
        chromePreferences.put("excludeSwitches", Arrays.asList("enable-automation" , "load-extension"));
        
        return chromePreferences;

	}

}
