package de.crawling.spider.talend.components.jena.connection;

import java.util.Map;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;

public class ConnectorImpl implements IConnector {

	protected String host="";
	protected String updateUrlAdditional="";
	protected String queryUrlAdditional="";
	protected String port="";
	protected String dataset="";
	protected String user="";
	protected String password="";
	protected String defaultGraph=null;
	protected PrefixMapping prefixMapping=null;
	protected String connectorName="";
	
	
	
	public String getUpdateUrlAdditional() {
		return updateUrlAdditional;
	}
	
	public void setUpdateUrlAdditional(String updateUrlAdditional) {
		this.updateUrlAdditional = updateUrlAdditional;
	}
	
	public String getQueryUrlAdditional() {
		return queryUrlAdditional;
	}
	
	public void setQueryUrlAdditional(String queryUrlAdditional) {
		this.queryUrlAdditional = queryUrlAdditional;
	}
	
	
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getHost()
	 */
	@Override
	public String getHost() {
		return host;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setHost(java.lang.String)
	 */
	@Override
	public void setHost(String host) {
		this.host = host.trim();
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getPort()
	 */
	@Override
	public String getPort() {
		return port;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setPort(java.lang.String)
	 */
	@Override
	public void setPort(String port) {
		this.port = port.trim();
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getDataset()
	 */
	@Override
	public String getDataset() {
		return dataset;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setDataset(java.lang.String)
	 */
	@Override
	public void setDataset(String dataset) {
		this.dataset = dataset.trim();
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getUser()
	 */
	@Override
	public String getUser() {
		return user;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setUser(java.lang.String)
	 */
	@Override
	public void setUser(String user) {
		this.user = user.trim();
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password.trim();
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getDefaultGraph()
	 */
	@Override
	public String getDefaultGraph() {
		return defaultGraph;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setDefaultGraph(com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void setDefaultGraph(String defaultGraph) {
		this.defaultGraph = defaultGraph;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getPrefixMapping()
	 */
	@Override
	public PrefixMapping getPrefixMapping() {
		return prefixMapping;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setPrefixMapping(com.hp.hpl.jena.shared.PrefixMapping)
	 */
	@Override
	public void setPrefixMapping(PrefixMapping prefixMapping) {
		this.prefixMapping = prefixMapping;
	}
	
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setPrefixMapping(java.util.Map)
	 */
	@Override
	public void setPrefixMapping(Map<String, String> map) {
		prefixMapping = new PrefixMappingImpl();
			prefixMapping.setNsPrefixes(map);
	}
	
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#getConnectorName()
	 */
	@Override
	public String getConnectorName() {
		return connectorName;
	}
	/* (non-Javadoc)
	 * @see de.crawling.spider.talend.components.jena.connection.IConnector#setConnectorName(java.lang.String)
	 */
	@Override
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName.trim();
	}

	@Override
	public String getPrefixMappingString() {
		StringBuffer buffer = new StringBuffer();
		for(String key: getPrefixMapping().getNsPrefixMap().keySet()){
			if(!"".equals(buffer.toString())){
				buffer.append("\n");
			}
			buffer.append("PREFIX "+key+": "+getPrefixMapping().getNsPrefixMap().get(key));
		}
		return buffer.toString();
	}
	
	
	
}
