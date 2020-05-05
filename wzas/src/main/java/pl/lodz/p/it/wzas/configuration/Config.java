package pl.lodz.p.it.wzas.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "pl.lodz.p.it.wzas.repository")
public class Config {

    /*@Value("${elasticsearch.home:/wzas202004/elasticSearch/elasticsearch-7.6.2}")
    private String elasticsearchHome;

    @Value("${elasticsearch.clusterName}")
    private String clusterName;*/

    /*@Bean
    public Client client() throws UnknownHostException {
        Settings elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("path.home", elasticsearchHome)
                .put("cluster.name", clusterName).build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("35.205.203.77"), 9200));
        return client;
    }*/

    @Bean
    Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1")
                , 9300));
        return client;
    }

    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }*/
}