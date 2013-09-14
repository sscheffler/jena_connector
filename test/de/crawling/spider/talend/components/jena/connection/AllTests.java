package de.crawling.spider.talend.components.jena.connection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConnectorImplTest.class, FusekiConnectionTest.class,
		VirtuosoConnectionTest.class })
public class AllTests {

}
