package org.gui.example.factory;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.FactoryBean;

public class ESClientFactory implements FactoryBean<Client> {

    private TransportAddress[] address;

    private String clusterName;

    public ESClientFactory() {
    }

    public void setAddresses(List<String> address) {
        this.address = new TransportAddress[address.size()];
        int i = 0;
        for (String addr : address) {
            final String[] addrParts = addr.split(":");
            this.address[i++] = new InetSocketTransportAddress(addrParts[0], NumberUtils.toInt(addrParts[1]));
        }
    }

    @Override
    public Client getObject() throws Exception {
        return new TransportClient(ImmutableSettings.builder().put("cluster.name", clusterName).build())
                .addTransportAddresses(address);
    }

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
