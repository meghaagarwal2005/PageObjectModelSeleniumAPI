package Common;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class FolderManager {
	private static Logger log = LoggerManager.getLogger();
	private static Map<Long,String> scenarioMappingByThread = new HashMap<Long,String>();
	
	
	
	public synchronized void clearFolder(String folderPath) {
		try {
			File tmpDir = new File(folderPath);
			for (File tmpFile : tmpDir.listFiles()) {
				if (!tmpFile.isDirectory()) {
					log.debug("Deleting the file {}", tmpFile);
					tmpFile.delete();
				}
			}
		} catch (Exception ex) {
			log.warn("Exception while clearing the folder {}. Exception is {}",folderPath,ex.getLocalizedMessage());
		}
	}
	
	public synchronized void clearDownloadFolder() {
		String folderPath = getDownloadFolder();
		log.debug("Clearing the folder {}",folderPath);
		clearFolder(folderPath);
		scenarioMappingByThread.remove(Thread.currentThread().getId());
	}

	public synchronized void createFolder(String folderPath) {
		try {
			Path path = Paths.get(folderPath);
			Files.createDirectories(path);
			log.info("Folder, " + folderPath + ", created");
		} catch (IOException e) {
			log.error("Folder, " + folderPath + ", could not be created");
			throw new RuntimeException("Folder "+folderPath+" could not be created");
		}

	}

	public synchronized boolean isFilePresent(String filePath) {
		File tmpFile = new File(filePath);
		return tmpFile.exists();
	}
	
	public synchronized void setDownloadFolder(String downloadFolder) {
		log.debug("Setting the download folder to {}", downloadFolder);
		scenarioMappingByThread.put(Thread.currentThread().getId(),downloadFolder);
	}

	public synchronized String getDownloadFolder() {
		return scenarioMappingByThread.get(Thread.currentThread().getId());
	}
	
	public synchronized String getAbsolutePath(String relativePath) {
		File testFile = new File(relativePath);
		return testFile.getAbsolutePath();
	}

	public synchronized void moveFile(String filename,String from,String to) throws IOException {
		log.debug("Moving file from {} to {}",from,to);
		Path source = Paths.get(from + File.separator + filename);
		Path destination = Paths.get(to);
		Files.move(source, destination.resolve(source.getFileName()),
				StandardCopyOption.REPLACE_EXISTING);
	}

}
