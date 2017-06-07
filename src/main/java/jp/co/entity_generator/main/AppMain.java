package jp.co.entity_generator.main;


import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.co.entity_generator.main.poi.FileLoad;


/**
 * AppMain
 *
 */
public class AppMain {

	public static void main(String[] args) {

		Logger logger = LogManager.getLogger();

		logger.info("スタート");
		// String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// logger.info("classpath : " + classpath);
		logger.info("file.separator : " + System.getProperty("file.separator"));

		File f = new File("in/");

		FileLoad fileLoad = new FileLoad();

		fileLoad.loadFolder(f);
	}
}
