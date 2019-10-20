package com.nj.fh.es.common;

import java.util.ResourceBundle;

/**
 * 使用该jar包的项目中所需要的配置项
 * @author 赵元路 18358572500 2019/9/16 14:58
 * @version 1.0
 */
public class ProjectParams {

    /**
     * 私有化构造
     */
    private ProjectParams() {}

    private static String es_project_name = null;
    private static String es_host = null;
    private static String es_port = null;
    private static String client_max_connection = null;
    private static String default_max_connection_per_route = null;
    private static String conn_time_out = null;
    private static String read_time_out = null;
    static {
        ResourceBundle resource = ResourceBundle.getBundle("es-fh");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
        es_project_name = resource.getString("ES_PROJECT_NAME");
        es_host = resource.getString("ES_HOST");
        es_port = resource.getString("ES_PORT");
        client_max_connection = resource.getString("CLIENT_MAX_CONNECTION");
        default_max_connection_per_route = resource.getString("DEFAULT_MAX_CONNECTION_PER_ROUTE");
        conn_time_out = resource.getString("CONN_TIME_OUT");
        read_time_out = resource.getString("READ_TIME_OUT");
    }

    /**
     * 项目别名
     */
    public static final String ES_PROJECT_NAME = es_project_name;

    /**
     * ES的ip地址
     */
    public static final String ES_HOST = es_host;

    /**
     * ES的端口
     */
    public static final Integer ES_PORT = Integer.parseInt(es_port);

    /**
     * 最大连接数
     */
    public static final Integer CLIENT_MAX_CONNECTION = Integer.parseInt(client_max_connection);

    /**
     * 默认最大路由层数
     */
    public static final Integer DEFAULT_MAX_CONNECTION_PER_ROUTE = Integer.parseInt(default_max_connection_per_route);

    /**
     * 连接超时时间
     */
    public static final Integer CONN_TIME_OUT = Integer.parseInt(conn_time_out);

    /**
     * 请求读取超时时间
     */
    public static final Integer READ_TIME_OUT = Integer.parseInt(read_time_out);
}
