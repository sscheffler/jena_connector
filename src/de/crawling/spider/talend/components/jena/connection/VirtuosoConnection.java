package de.crawling.spider.talend.components.jena.connection;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

public class VirtuosoConnection extends AbstractConnection {
	
	protected String url = "" ;
	protected VirtGraph graph = null;
	
	public VirtuosoConnection(){
		super(VirtuosoConnection.class);
	}

	@Override
	public void createConnection(IConnector connector, String loggerID) {
		this.con=connector;
		String normalizedHost=con.getHost().replaceAll("(^http://)|(/+)", "");
		con.setHost("jdbc:virtuoso://"+normalizedHost);
		String hostPort= con.getHost()+":"+con.getPort();
		log(loggerID, "Try to connect to: " + hostPort, Level.INFO);
		this.graph=new VirtGraph (hostPort, con.getUser(), con.getPassword());
		log(loggerID, "Connection successfull", Level.INFO);
		log(loggerID, "url: "+getURL(), Level.INFO);
	}

	@Override
	public List<Map<String, Object>> executeQuery(String query, String loggerID) {
		log(loggerID, "proccessing: "+ query, Level.INFO);
		Query q = QueryFactory.create(query);
		ResultSet set =VirtuosoQueryExecutionFactory.create(q, this.graph).execSelect(); 
		return super.getQueryResultMap(set, loggerID);
		
	}

	@Override
	public void executeUpdateQuery(String query, String loggerID) {
		log(loggerID, "proccessing update: "+ query, Level.INFO);
		VirtuosoQueryExecutionFactory.create(query, this.graph).execSelect();
	}

	@Override
	public String getURL() {
		
		return con.getHost()+":"+con.getPort();
	}


}
