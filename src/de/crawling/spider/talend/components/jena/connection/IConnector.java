package de.crawling.spider.talend.components.jena.connection;

import java.util.Map;

import com.hp.hpl.jena.shared.PrefixMapping;

public interface IConnector {

	public abstract String getHost();

	public abstract void setHost(String host);

	public abstract String getPort();

	public abstract void setPort(String port);

	public abstract String getDataset();

	public abstract void setDataset(String dataset);

	public abstract String getUser();

	public abstract void setUser(String user);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract String getDefaultGraph();

	public abstract void setDefaultGraph(String defaultGraph);
	
	public abstract String getPrefixMappingString();

	public abstract PrefixMapping getPrefixMapping();

	public abstract void setPrefixMapping(PrefixMapping prefixMapping);

	public abstract void setPrefixMapping(Map<String, String> map);

	public abstract String getConnectorName();

	public abstract void setConnectorName(String connectorName);
	
	public abstract String getUpdateUrlAdditional();
	
	public abstract String getQueryUrlAdditional();

	public abstract void setUpdateUrlAdditional(String str);
	
	public abstract void setQueryUrlAdditional(String str);
}