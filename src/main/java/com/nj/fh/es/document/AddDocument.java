package com.nj.fh.es.document;

import com.nj.fh.es.common.ProjectParams;
import com.nj.fh.es.index.GetIndexMsg;

import com.nj.fh.es.search.DoSearch;
import com.nj.fh.es.search.query.Query;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 添加文档
 * @author 赵元路 18358572500 2019/9/8 13:54
 * @version 1.0
 */
public final class AddDocument {

    /**
     * 私有化构造
     */
    private AddDocument() {}
    private static final Logger log = LoggerFactory.getLogger(AddDocument.class);

    /**
     * 添加一条文档,id自定义,会进行检查是否ES中有相同id，有则插入失败
     * @param client 连接
     * @param indexName 索引名称
     * @param id 自定义的索引id
     * @param obj 文档对象
     * @return 是否成功插入
     */
    public static Boolean addOneDocumentWithCheck(JestClient client, String indexName, String id, Object obj) {
        final Boolean[] addResult = {true};
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.query(Query.matchAllQuery());
        SearchResult result = DoSearch.getSearchResult(client, indexName, ssb);
        ssb.size(result.getTotal().intValue());
        List<SearchResult.Hit<Object, Void>> result2 = DoSearch.getHits(client, indexName, ssb, obj.getClass());
        result2.forEach(hit -> {
            if (hit.id.equals(id)) {
                addResult[0] = false;
                log.error("已存在ID为 -> {} 的文档，插入失败！", id);
            }
        });
        return addResult[0];
    }

    /**
     * 添加一条文档,id自定义
     * @param client 连接
     * @param indexName 索引名称
     * @param id 自定义的索引id
     * @param obj 文档对象
     * @return 是否成功插入
     */
    public static Boolean addOneDocument(JestClient client, String indexName, String id, Object obj) {
        if (!haveMappings(client, indexName)) {
            log.error("请先设置mappings,直接添加文档可能导致自动映射的不是您想要的类型!");
            return false;
        };
        try {
            DocumentResult result = client.execute(getBuilder(indexName, id, obj));
            if (!result.isSucceeded()) {
                log.error(result.getErrorMessage());
            }
            return result.isSucceeded();
        } catch (Exception e) {
            log.error("addOneDocument Exception : ", e);
        }
        return false;
    }

    /**
     * 添加一条文档,id由ES随机生成
     * @param client 连接
     * @param indexName 索引名称
     * @param obj 文档对象
     * @return 添加是否成功
     */
    public static Boolean addOneDocument(JestClient client, String indexName, Object obj) {
        if (!haveMappings(client, indexName)) {
            log.error("请先设置mappings,直接添加文档可能导致自动映射的不是您想要的类型!");
            return false;
        };
        try {
            DocumentResult result = client.execute(getBuilder(indexName, obj));
            if (!result.isSucceeded()) {
                log.error(result.getErrorMessage());
            }
            return result.isSucceeded();
        } catch (Exception e) {
            log.error("addOneDocument Exception : ", e);
        }
        return false;
    }

    /**
     * 批量插入文档,id由ES随机给出
     * 建议不这么做，而是指定id进行批量操作，ES会进行批量操作中的每个操作，遇到失败的操作，会给出错误信息，如果没有指定ID，可能导致无法精准定位
     * @param client 连接
     * @param indexName 索引名称
     * @param objs 文档对象
     * @return 批量操作是否成功，有一条操作错误即为false
     */
    public static Boolean bulkAddDocuments(JestClient client, String indexName, List<?> objs) {
        if (CollectionUtils.isEmpty(objs)) {
            log.error("bulkAddDocuments Exception : {}", objs);
            return false;
        }
        try {
            Bulk.Builder builder = new Bulk.Builder();
            for (Object obj : objs) {
                builder.addAction(getBuilder(indexName, obj));
            }
            return executeResult(client, builder.build());
        } catch (Exception e) {
            log.error("bulkAddDocuments Exception : ", e);
        }
        return false;
    }

    /**
     * 批量插入,文档的id是自定义的
     * @param client 连接
     * @param indexName 索引名称
     * @param map 文档信息，map中的key是文档id,value是文档对象
     * @return 批量操作是否成功，有一条操作错误即为false
     */
    public static Boolean bulkAddDocuments(JestClient client, String indexName, Map<String, ?> map) {
        if (MapUtils.isEmpty(map)) {
            log.error("bulkAddDocuments Exception : {}", map);
            return false;
        }
        try {
            Bulk.Builder builder = new Bulk.Builder();
            for (Map.Entry<String, ?> entry : map.entrySet()){
                builder.addAction(getBuilder(indexName, entry.getKey(), entry.getValue()));
            }
            return executeResult(client, builder.build());
        } catch (Exception e) {
            log.error("bulkAddDocuments Exception : ", e);
        }
        return false;
    }

    /**
     * 批量插入,文档的id是自定义,并进行检查，不插入id相同的文档
     * @param client 连接
     * @param indexName 索引名称
     * @param map 文档信息，map中的key是文档id,value是文档对象
     * @return 批量操作是否成功，有一条操作错误即为false
     */
    public static Boolean bulkAddDocumentsWithCheck(JestClient client, String indexName, Map<String, ?> map) {
        if (MapUtils.isEmpty(map)) {
            log.error("bulkAddDocuments Exception : {}", map);
            return false;
        }
        final Boolean[] addResult = {true};
        final Class[] clazz = {null};
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.query(Query.matchAllQuery());
        SearchResult result = DoSearch.getSearchResult(client, indexName, ssb);
        ssb.size(result.getTotal().intValue());
        map.entrySet().forEach(entrySet -> {
            clazz[0] = entrySet.getValue().getClass();
        });
        List<SearchResult.Hit<Object, Void>> result2 = DoSearch.getHits(client, indexName, ssb, clazz[0]);
        result2.forEach(hit -> {
            map.forEach((k, v) -> {
                if (hit.id.equals(k)) {
                    addResult[0] = false;
                    log.error("已存在ID为 -> {} 的文档，插入失败！", hit.id);
                }
            });
        });
        return addResult[0];
    }

    private static Boolean haveMappings(JestClient client, String indexName) {
        Map map = GetIndexMsg.getMappingsMap(client, indexName);
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        Map indexNameMap = (Map) map.get(indexName);
        Map mappingsMap = (Map) indexNameMap.get("mappings");
        if (null == mappingsMap.get(ProjectParams.ES_PROJECT_NAME + ".type")) {
            return false;
        } else {
            return true;
        }
    }

    private static Boolean executeResult(JestClient client, Bulk bulk) {
        try {
            BulkResult result = client.execute(bulk);
            List<BulkResult.BulkResultItem> items = result.getFailedItems();
            if (CollectionUtils.isNotEmpty(items)) {
                items.forEach(item -> log.error("id为 {} 的文档插入失败,原因 {}", item.id, item.errorReason));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("executeResult Exception : ", e);
        }
        return false;
    }

    private static Index getBuilder(String indexName, Object source) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        return new Index.Builder(source)
                .index(indexName)
                .type(ProjectParams.ES_PROJECT_NAME + ".type")
                .refresh(true)
                .build();
    }

    private static Index getBuilder(String indexName, String id, Object source) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        return new Index.Builder(source)
                .index(indexName)
                .type(ProjectParams.ES_PROJECT_NAME + ".type")
                .id(id)
                .refresh(true)
                .build();
    }
}
