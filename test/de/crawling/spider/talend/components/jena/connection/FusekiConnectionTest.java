package de.crawling.spider.talend.components.jena.connection;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FusekiConnectionTest {
	
	private static final String DS="ds";
	private static final String HOST="http://192.168.0.14";
	private static final String PORT="3030";
	private static final String NAME="connectorname";
	private static final String DEFAULTGRAPH="<http://default/graph>";
	private static final String UPDATE="update";
	private static final String QUERY="query";
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
		connector = createNiceMock(IConnector.class);
		expect(connector.getHost()).andReturn(HOST);
		expect(connector.getPort()).andReturn(PORT);
		expect(connector.getDataset()).andReturn(DS);
		expect(connector.getUpdateUrlAdditional()).andReturn(UPDATE);
		expect(connector.getQueryUrlAdditional()).andReturn(QUERY);
		expect(connector.getConnectorName()).andReturn(NAME);
		expect(connector.getDefaultGraph()).andReturn(DEFAULTGRAPH);
		replay(connector);
		
		con = new FusekiConnection();
		con.addLogger(TEST_CONNECTION_LOGGER_ID, LOGGER_BOOL);
		con.addLogger(TEST_QUERY_LOGGER_ID, LOGGER_BOOL);
		con.addLogger(TEST_UPDATE_LOGGER_ID, LOGGER_BOOL);
		
		con.setLogging(LOGGER_BOOL, TEST_CONNECTION_LOGGER_ID);
		con.setLogging(LOGGER_BOOL, TEST_QUERY_LOGGER_ID);
		con.setLogging(LOGGER_BOOL, TEST_UPDATE_LOGGER_ID);
		con.createConnection(connector, TEST_CONNECTION_LOGGER_ID);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testCreateConnection() {
		String testUrl=HOST+":"+PORT+"/"+DS;
		
		assertEquals(testUrl, con.getURL());
		assertEquals(testUrl+"/"+UPDATE, ((FusekiConnection)con).getUpdateURL());
		assertEquals(testUrl+"/"+QUERY, ((FusekiConnection)con).getQueryURL());
	}

	@Test
	public void testExecuteQuery() {
		testCreateConnection();
		List<Map<String, Object>> set = con.executeQuery("select * where { graph <http://test/junit/query> {?x ?y ?z}}", TEST_QUERY_LOGGER_ID);
		assertNotNull("ResultSet is 'null'",set);
		
	}
	

	@Test
	public void testExecuteUpdateQuery() {
		
		testCreateConnection();
		String insert ="PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#>"
				+ "insert data { graph <http://test/junit/query> "
						+ "{<http://test/junit/queryRes> 	<http://test/junit/queryProp> \"queryResult1\".}}";
		
		String test ="select ?x ?y ?z where "
				+ "{ graph <http://test/junit/query> "
				+ "{?x ?y ?z "
					+ "filter(?x = <http://test/junit/queryRes> )}}";
		
		String delete="PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#>"
				+"delete data { graph <http://test/junit/query> {<http://test/junit/queryRes> <http://test/junit/queryProp> \"queryResult1\".}}";
		
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
		
		
	}
	

}
