package com.nj.fh.es.search;

import com.alibaba.fastjson.JSON;
import com.nj.fh.es.common.ProjectParams;
import io.searchbox.client.JestClient;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 进行查询
 * @author 赵元路 18358572500 2019/9/12 11:06
 * @version 1.0
 */
public final class DoSearch {

    /**
     * 私有化构造
     */
    private DoSearch() {}
    private static Logger log = LoggerFactory.getLogger(DoSearch.class);

    /**
     * 从搜索结果中获取搜索的响应时间（毫秒）
     * @param sr 搜索结果
     * @return 搜索的响应时间（毫秒）
     */
    public static final Integer getSearchTook(SearchResult sr) {
        try {
            Map map = (Map) JSON.parse(sr.getJsonString());
            return (Integer) map.get("took");
        } catch (Exception e) {
            log.error("getSearchTook Exception : ", e);
        }
        return null;
    }

    /**
     * 执行搜索，获得搜索结果
     * 在指定的单个索引中搜索
     * @param client 连接
     * @param indexName 查询创建对象
     * @param ssb 搜索结果
     * @return 结果中包括命中的文档具体信息、搜索结果总条数等相关信息
     */
    public static SearchResult getSearchResult(JestClient client, String indexName, SearchSourceBuilder ssb) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        try {
            SearchResult result = client.execute(new Search.Builder(ssb.toString())
                    .addIndex(indexName)
                    .build());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行搜索，获得搜索结果
     * 在指定的多个索引中搜索
     * @param client 连接
     * @param indexNames 索引名称集合
     * @param ssb 查询创建对象
     * @return 结果中包括命中的文档具体信息、搜索结果总条数等相关信息
     */
    public static SearchResult getSearchResult(JestClient client, List<String> indexNames, SearchSourceBuilder ssb) {
        indexNames.forEach(indexName -> indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName);
        try {
            SearchResult result = client.execute(new Search.Builder(ssb.toString())
                    .addIndices(indexNames)
                    .build());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从搜索结果中获得命中的文档具体数据
     * 在指定的单个索引中搜索
     * @param client 连接
     * @param ssb 查询创建对象
     * @param clazz 文档对应的类
     * @return 命中的文档对象List
     */
    public static List<SearchResult.Hit<Object, Void>> getHits(JestClient client, String indexName, SearchSourceBuilder ssb, Class clazz) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        try {
            SearchResult result = client.execute(new Search.Builder(ssb.toString())
                    .addIndex(indexName)
                    .build());
            return result.getHits(clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从搜索结果中获得命中的文档具体数据
     * 在指定的多个索引中搜索
     * @param client 连接
     * @param indexNames 索引名称集合
     * @param ssb 查询创建对象
     * @param clazz 文档对应的类
     * @return 命中的文档具体的信息List
     */
    public static List<SearchResult.Hit<Object, Void>> getHits(JestClient client, List<String> indexNames, SearchSourceBuilder ssb, Class clazz) {
        indexNames.forEach(indexName -> indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName);
        try {
            SearchResult result = client.execute(new Search.Builder(ssb.toString())
                    .addIndices(indexNames)
                    .build());
            return result.getHits(clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从搜索结果中获得命中的原文档pojo集合
     * 在指定的多个索引中搜索
     * @param client 连接
     * @param indexName 索引名称集合
     * @param ssb 查询创建对象
     * @param clazz 文档对应的类
     * @return 命中的文档具体的信息List
     */
    public static List<Object> getSourceList(JestClient client, String indexName, SearchSourceBuilder ssb, Class clazz) {
        List<SearchResult.Hit<Object, Void>> hits = getHits(client, indexName, ssb, clazz);
        List<Object> source = new ArrayList<>();
        hits.forEach(hit -> {
            source.add(hit.source);
        });
        return source;
    }

    /**
     * 根据文档id精确获取文档
     * @param client 连接
     * @param indexName 索引名称
     * @param id 文档id
     * @param clazz 文档对应的类
     * @return 文档对象
     */
    public static Object getSourceObjectById(JestClient client, String indexName, String id, Class<?> clazz) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        try {
            return client.execute(new Get.Builder(indexName, id).build()).getSourceAsObject(clazz);
        } catch (Exception e) {
            log.error("getSourceObjectById Exception : ", e);
        }
        return null;
    }

}