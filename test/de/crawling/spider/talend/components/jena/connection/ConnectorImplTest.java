package de.crawling.spider.talend.components.jena.connection;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;

public class ConnectorImplTest {
	
	protected PrefixMapping map = new PrefixMappingImpl();

	@Before
	public void setUp() throws Exception {
		map.setNsPrefix("a", "http://prefix/test_1/");
		map.setNsPrefix("b", "http://prefix/test_2/");
	}

	@Test
	public void testGetPrefixMappingString() {
		ConnectorImpl impl = new ConnectorImpl();
		impl.setPrefixMapping(map);
		String prefixString = "PREFIX b: http://prefix/test_2/\nPREFIX a: http://prefix/test_1/";
		assertEquals("Prefix maps does not fit",prefixString, impl.getPrefixMappingString());
	}

}
