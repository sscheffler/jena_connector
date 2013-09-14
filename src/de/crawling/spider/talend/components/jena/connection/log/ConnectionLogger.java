package de.crawling.spider.talend.components.jena.connection.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConnectionLogger {
	protected boolean doLog=false;
	protected String id = "";
	protected Level level = Level.ALL;
	protected Logger logger=null; 

	public ConnectionLogger(Class<?> connectionClass, String id) {
		this.id=id;
		logger = Logger.getLogger(connectionClass);
		logger.setLevel(level);
		
	}
	
	public boolean isDoLog() {
		return doLog;
	}

	public void setDoLog(boolean doLog) {
		this.doLog = doLog;
	}
	
	public void setLevel(Level level){
		this.level = level;
		logger.setLevel(level);
	}
	
	public Level getLevel(){
		return level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public void log(String message, Level level){
		
		if(doLog){
			if(Level.INFO.equals(level)){
				logger.info("("+id+") "+ message);
			}
			
			if(Level.FATAL.equals(level)){
				logger.fatal("("+id+") "+ message);
			}
			
			if(Level.ERROR.equals(level)){
				logger.error("("+id+") "+ message);
			}
			
			if(Level.WARN.equals(level)){
				logger.warn("("+id+") "+ message);
			}
		}
		
	}
	
	public Logger getLogger(){
		return logger;
	}
	

}
