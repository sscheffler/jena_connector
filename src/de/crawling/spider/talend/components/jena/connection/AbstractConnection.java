package de.crawling.spider.talend.components.jena.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;

import de.crawling.spider.talend.components.jena.connection.log.ConnectionLogger;

public class AbstractConnection implements IConnection{
	
	protected Class<?> connectionClass = null;
	protected Map<String, ConnectionLogger> loggerMap = new HashMap<String, ConnectionLogger>();
	protected IConnector con=null;
	protected Level logLevel = Level.INFO;
	protected Level deaktLevel = Level.FATAL;

	protected AbstractConnection(Class<?> connectionClass) {
		this.connectionClass= connectionClass;
	}
	
	protected List<Map<String, Object>> getQueryResultMap(ResultSet set, String loggerID){
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();

		List<String> varList = set.getResultVars();
		log(loggerID, "Found variables to bound: "+varList, Level.INFO);
		while(set.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			QuerySolution sol = set.next();
			
			for(String var : varList){
				RDFNode node = sol.get(var);
				if(node == null){
					map.put(var, null);
					continue;
				}
				
				if(node.isResource()){
					map.put(var, "<"+node.asResource().getURI()+">");
					continue;
				}
				
				if(node.isAnon()){
					map.put(var, node.asResource().getURI());
					continue;
				}
				
				if(node.isLiteral()){
					Literal lit = node.asLiteral();
					
					map.put(var, lit.toString()); 
				}
			}	
			resultList.add(map);
		}
		log(loggerID, "Found results: " + resultList.size(), Level.INFO);
		return resultList;
	}
	
	@Override
	public void log(String loggerID, String message, Level level) {
		ConnectionLogger logger = loggerMap.get(loggerID);
		if(logger != null){
			logger.log(message, level);
		}
		
	}


	@Override
	public String getURL() {
		return null;
	}

	@Override
	public IConnector getConnector() {
		return con;
	}
	
	@Override
	public boolean loggingActivated(String loggerID) {
		return loggerMap.get(loggerID).isDoLog();
	}
	
	@Override
	public void setLogging(boolean bool, String loggerID) {
		this.loggerMap.get(loggerID).setDoLog(bool);
	}
	
	
	@Override
	public void addLogger(String loggerID, boolean doLog) {
		loggerMap.put(loggerID, new ConnectionLogger(connectionClass, loggerID));
		this.loggerMap.get(loggerID).setDoLog(doLog);
	}


	
	
	/**
	 * implemented in sub class
	 */
	@Override
	public void createConnection(IConnector con, String LoggerID) {
		
	}

	/**
	 * implemented in sub class
	 */
	@Override
	public List<Map<String, Object>> executeQuery(String query, String loggerID) {
		return null;
	}

	/**
	 * implemented in sub class
	 */
	@Override
	public void executeUpdateQuery(String query, String loggerID) {
	}

	

	

	
	
	
	

}
