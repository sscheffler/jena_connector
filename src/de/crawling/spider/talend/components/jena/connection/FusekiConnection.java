package de.crawling.spider.talend.components.jena.connection;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;

public class FusekiConnection extends AbstractConnection {
	
	protected String url = "" ;
	protected String updateUrl="";
	protected String queryUrl="";
	
	
	
	public FusekiConnection(){
		super(FusekiConnection.class);
	}
	
	

	@Override
	public void createConnection(IConnector con, String loggerID) {
		this.con=con;
		url = con.getHost()+":"+con.getPort()+"/"+con.getDataset();
		updateUrl = url+"/"+con.getUpdateUrlAdditional();
		queryUrl = url+"/"+con.getQueryUrlAdditional();
		super.log(loggerID, "Connection successfull",Level.INFO);
		super.log(loggerID, "url: "+getURL(),Level.INFO);
		super.log(loggerID, "query url: "+getQueryURL(),Level.INFO);
		super.log(loggerID, "update url: "+getUpdateURL(),Level.INFO);
	}
	

	@Override
	public List<Map<String, Object>> executeQuery(String query, String loggerID) {
		log(loggerID, "proccessing: " + query,Level.INFO);
		Query q = QueryFactory.create(query);
		ResultSet set = QueryExecutionFactory.sparqlService(getQueryURL(), q).execSelect();
		return super.getQueryResultMap(set, loggerID);
	}

	@Override
	public void executeUpdateQuery(String query, String loggerID) {
		log(loggerID, "proccessing update: " + query,Level.INFO);
		UpdateRequest req = UpdateFactory.create(query);
		UpdateExecutionFactory.createRemote(req, getUpdateURL()).execute();
	}

	@Override
	public String getURL() {
		return url;
		
	}
	
	public String getQueryURL() {
		return queryUrl;
		
	}
	
	public String getUpdateURL() {
		return updateUrl;
		
	}


}
