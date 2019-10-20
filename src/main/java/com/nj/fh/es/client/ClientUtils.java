package com.nj.fh.es.client;

import com.nj.fh.es.common.ProjectParams;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Elasticsearch java 连接工具类
 * @author 赵元路 18358572500 2019/9/18 9:46
 * @version 1.0
 */
public final class ClientUtils {

    /**
     * 私有化构造
     */
    private ClientUtils() {}
	private static final Logger log = LoggerFactory.getLogger(ClientUtils.class);

    private static JestClient client;

    /**
     * 单例模式获得一个连接，这个连接是线程安全的
     * @return Client
     */
    public static synchronized JestClient getClient() {
        if (client == null) {
        	try {
        		build(ProjectParams.ES_HOST, ProjectParams.ES_PORT);
        	} catch (Exception e) {
        		log.error("getClient Exception : ", e);
        	}
        }
        return client;
    }

    /**
     * 关闭一个连接，不建议频繁使用关闭方法
     * @param client 一个连接
     */
    public static void closeClient(JestClient client) {
        if (!Objects.isNull(client)) {  
            try {  
                client.close();  
            } catch (Exception e) {  
                log.error("closeClient Exception : ", e);
            }  
        }  
    }

    /**
     *
     * @param host ip
     * @param port 端口
     */
    private static void build(String host, Integer port) {
        host = "http://" + host;
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(  
                new HttpClientConfig
                        .Builder(host + ":" + port)  
                        .multiThreaded(true)  
                        //默认路由层数
                        .defaultMaxTotalConnectionPerRoute(ProjectParams.DEFAULT_MAX_CONNECTION_PER_ROUTE)
                        //最大连接数
                        .maxTotalConnection(ProjectParams.CLIENT_MAX_CONNECTION)
                        .connTimeout(ProjectParams.CONN_TIME_OUT)
                        .readTimeout(ProjectParams.READ_TIME_OUT)
                        .build()
        );
        client = factory.getObject();
    }
}
