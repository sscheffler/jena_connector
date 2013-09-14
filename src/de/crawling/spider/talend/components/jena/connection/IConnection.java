package de.crawling.spider.talend.components.jena.connection;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

public interface IConnection {
	
	public abstract void createConnection(IConnector con, String LoggerID);
	public abstract List<Map<String, Object>> executeQuery(String query, String loggerID);
	public abstract void executeUpdateQuery(String query, String loggerID);
	public abstract String getURL();
	public abstract IConnector getConnector();
	public abstract boolean loggingActivated(String loggerID);
	public abstract void setLogging(boolean bool, String loggerID);
	public abstract void addLogger(String loggerID, boolean doLog);
	public abstract void log(String loggerID, String message, Level level);
}
