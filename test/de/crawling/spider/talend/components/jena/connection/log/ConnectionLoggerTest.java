package de.crawling.spider.talend.components.jena.connection.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ConnectionLoggerTest {

	protected String loggerID = "";
	protected boolean doLog = false;
	protected String message = "";
	protected ConnectionLogger logger = null;
	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	public ConnectionLoggerTest(String loggerID, boolean doLog, String message) {
		this.loggerID = loggerID;
		this.doLog = doLog;
		this.message = message;
	}

	@Before
	public void setUp() throws Exception {
		logger = new ConnectionLogger(ConnectionLogger.class, loggerID);
		logger.setDoLog(doLog);
		System.setOut(new PrintStream(outContent));
	}

	// Creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "logger1",new Boolean(true), "Message 1" }, 
											{ "logger2" ,new Boolean(false), "Message 2" }};
		return Arrays.asList(data);
	}

	@Test
	public void testLogString() {
		logger.log(loggerID, Level.INFO);
	}

}
