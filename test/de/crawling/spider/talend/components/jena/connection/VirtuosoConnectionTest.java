package de.crawling.spider.talend.components.jena.connection;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VirtuosoConnectionTest {
	
	private static final String USER="dba";
	private static final String PASSWORD="dba";
	private static final String HOST="http://192.168.0.14/";
	private static final String PORT="1111";
	private static final String NAME="connectorname";
	private static final String DEFAULTGRAPH="<http://default/graph>";
	private static final String TEST_CONNECTION_LOGGER_ID="TestConnectionLogger";
	private static final String TEST_QUERY_LOGGER_ID="TestQueryLogger";
	private static final String TEST_UPDATE_LOGGER_ID="TestUpdateLogger";
	private static final boolean LOGGER_BOOL=true;
	
	protected static IConnection con = null;
	protected static Logger log = Logger.getLogger(FusekiConnectionTest.class);
	protected static IConnector connector= null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		connector = new ConnectorImpl();
		connector.setHost(HOST);
		connector.setPort(PORT);
		connector.setUser(USER);
		connector.setPassword(PASSWORD);
		connector.setDefaultGraph(DEFAULTGRAPH);
		connector.setConnectorName(NAME);
		
		con = new VirtuosoConnection();
		con.addLogger(TEST_CONNECTION_LOGGER_ID, LOGGER_BOOL);
		con.addLogger(TEST_QUERY_LOGGER_ID, LOGGER_BOOL);
		con.addLogger(TEST_UPDATE_LOGGER_ID, LOGGER_BOOL);
		
		con.createConnection(connector, TEST_CONNECTION_LOGGER_ID);
		con.setLogging(LOGGER_BOOL, TEST_CONNECTION_LOGGER_ID);
		con.setLogging(LOGGER_BOOL, TEST_QUERY_LOGGER_ID);
		con.setLogging(LOGGER_BOOL, TEST_UPDATE_LOGGER_ID);
		
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testExecuteQuery() {
		
//		String typedQueryTestString="PREFIX dc: <http://purl.org/dc/elements/1.1/> PREFIX purl: <http://purl.org/ontology/bibo/Document> PREFIX owl: <http://www.w3.org/2002/07/owl#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX dnb: <http://d-nb.info/> PREFIX dcterms: <http://purl.org/dc/terms/>select *{graph ?graph{?bsb owl:sameAs ?dnb.?bsb dcterms:title ?title.?bsb dc:subject ?subject}filter(?graph=<http://l3kat.avantgarde-labs.de>)filter regex(?dnb, \"http://d-nb.info\")}limit 10";
		String typedQueryTestString2="PREFIX dc: <http://purl.org/dc/elements/1.1/> select * {graph ?graph{<http://lod.b3kat.de/title/BV000705656>  dc:subject ?subject}filter(?graph=<http://l3kat.avantgarde-labs.de>)}";
//		String normalTestString="select * where { graph <http://test/junit/query> {?x ?y ?z}}";
		List<Map<String, Object>> set = con.executeQuery(typedQueryTestString2, TEST_QUERY_LOGGER_ID);
		assertNotNull("ResultSet is 'null'",set);
	}

	/*@Test
	public void testExecuteUpdateQuery() {
		String insert ="PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#>"
				+ "insert data { graph <http://test/junit/query> "
						+ "{<http://test/junit/queryRes> 	<http://test/junit/queryProp> \"queryResult1\"^^xsd:string.}}";
		
		String test ="select * where "
				+ "{ graph ?graph "
				+ "{?x ?y ?z "
					+ "filter(?x = <http://test/junit/queryRes> )} filter(?graph = <http://test/junit/query>)}";
		
		String test2 ="select * where "
				+ "{ graph <http://test/junit/query> "
				+ "{?x ?y ?z "
					+ "filter(?x = <http://test/junit/queryRes> )}}";
		
		String delete="delete data { graph <http://test/junit/query> {<http://test/junit/queryRes> <http://test/junit/queryProp> \"queryResult1\"^^xsd:string.}}";
		con.executeUpdateQuery(insert, TEST_UPDATE_LOGGER_ID);
		
		List<Map<String, Object>> list = con.executeQuery(test, TEST_QUERY_LOGGER_ID);
		
		Map<String, Object> set = list.get(0);
		String sub = "";
		String pred = "";
		String ob = "";
		
		for(String key :set.keySet()){
			
			sub=("x".equals(key))?(String)set.get(key):sub;
			pred=("y".equals(key))?(String)set.get(key):pred;
			ob=("z".equals(key))?(String)set.get(key):ob;
		}
		
		assertEquals("subject does not match","<http://test/junit/queryRes>", sub);
		assertEquals("predicate does not match","<http://test/junit/queryProp>", pred);
		assertEquals("object does not match","queryResult1", ob);
		
		con.executeUpdateQuery(delete, TEST_UPDATE_LOGGER_ID);
		
	}*/

}
