package Common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EdgeDriverManager  implements BrowserManager {

	private Logger log = LoggerManager.getLogger();
	private KeyValReader edgeProperties =  new KeyValReader("Configs/edge.properties");
	private KeyValReader hiveProperties =  new KeyValReader("Configs/theHive.properties");
	
	@Override
	public WebDriver initializeDriver() {

		String webDriverEdgeDriver = edgeProperties.getProperty("EdgeDriver");
		String edgeLogFile = edgeProperties.getProperty("EdgeLogs");
		String edgeDriverSilentOutput = edgeProperties.getProperty("EdgeDriverSilentOutput");
		
		System.setProperty("webdriver.edge.driver",webDriverEdgeDriver);
		log.debug("webdriver.edge.driver is set to {}",webDriverEdgeDriver);
		
		System.setProperty("webdriver.edge.logfile", edgeLogFile);
		log.debug("webdriver.edge.logfile is set to {}",edgeLogFile);
		
		System.setProperty("webdriver.edge.silentOutput", edgeDriverSilentOutput);
		log.debug("webdriver.edge.silentOutput is set to {}",edgeDriverSilentOutput);
		
		Map<String,Object> edgePreferences = setEdgePreferences();
		EdgeOptions edgeOptions = setEdgeOptions(edgePreferences);
		
		try {
			WebDriver webDriver = new EdgeDriver(edgeOptions);
			return webDriver;
		} catch (Exception e) {
			log.fatal("Edge WebDriver initialization failed" + ExceptionUtils.getMessage(e));
			throw new RuntimeException("Initializing Edge Driver Failed " + ExceptionUtils.getMessage(e));
		}
		
	}
	
	private EdgeOptions setEdgeOptions(Map<String,Object> edgePreferences) {
		
		EdgeOptions edgeOptions = new EdgeOptions();
		
		//String edgeBinary = edgeProperties.getProperty("EdgeBinary");
		//edgeOptions.addArguments("test-type");
		//edgeOptions.addArguments("disable-popup-blocking");
		//edgeOptions.addArguments("--disable-notifications");
		//options.addArguments("disable-infobars");
		//options.addArguments("--disable-automation");
		//edgeOptions.setExperimentalOption("prefs", edgePreferences);
		edgeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		return edgeOptions;
	}
	
	private Map<String, Object> setEdgePreferences(){
		
		Map<String, Object> edgePreferences = new HashMap<String, Object>();
		
		String downloadDirectory = System.getProperty("user.dir") + File.separator + hiveProperties.getProperty("downloadFilePath").replace("/","\\");
		Boolean downloadPrompt = Boolean.parseBoolean(edgeProperties.getProperty("DownloadPrompt"));
		Boolean downloadDirectoryUpgrade = Boolean.parseBoolean(edgeProperties.getProperty("DownloadDirectoryUpgrade"));
		Boolean safeBrowsingEnabled = Boolean.parseBoolean(edgeProperties.getProperty("SafeBrowsingEnabled"));
		Boolean safeBrowsingDisableDownloadProtection = Boolean.parseBoolean(edgeProperties.getProperty("SafeBrowsingDisableDownloadProtection"));
		Boolean useAutomationExtension =  Boolean.parseBoolean(edgeProperties.getProperty("UseAutomationExtension"));
		int popups = Integer.parseInt(edgeProperties.getProperty("profile_default_content_settings_popups"));
		
		edgePreferences.put("download.default_directory",downloadDirectory );
        log.debug("download.default_directory is set to {}",downloadDirectory);
        
        edgePreferences.put("browser.download.dir", downloadDirectory);
        log.debug("browser.download.dir is set to {}",downloadDirectory);
        
        edgePreferences.put("download.prompt_for_download",downloadPrompt);
        log.debug("download.prompt_for_download is set to {}",downloadPrompt);
        
        edgePreferences.put("download.directory_upgrade",downloadDirectoryUpgrade );
        log.debug("download.directory_upgrade is set to {}",downloadDirectoryUpgrade);
        
        edgePreferences.put("safebrowsing.enabled", safeBrowsingEnabled);
        log.debug("safebrowsing.enabled is set to {}",safeBrowsingEnabled);
        
        edgePreferences.put("safebrowsing.disable_download_protection",safeBrowsingDisableDownloadProtection );
        log.debug("safebrowsing.disable_download_protection is set to {}",safeBrowsingDisableDownloadProtection);
        
        edgePreferences.put("useAutomationExtension",useAutomationExtension);
        log.debug("useAutomationExtension is set to {}",useAutomationExtension);
        
        edgePreferences.put("profile.default_content_settings.popups",popups);
        log.debug("profile.default_content_settings.popups is set to {}",popups);
        
        edgePreferences.put("excludeSwitches", Arrays.asList("enable-automation" , "load-extension"));
        
        return edgePreferences;

	}

}
