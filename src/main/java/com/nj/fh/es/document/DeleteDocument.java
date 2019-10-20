package com.nj.fh.es.document;

import com.nj.fh.es.client.Client;
import com.nj.fh.es.common.ProjectParams;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 删除文档
 * @author 赵元路 18358572500 2019/9/9 17:24
 * @version 1.0
 */
public class DeleteDocument {

    /**
     * 私有化构造
     */
    private DeleteDocument() {}
    private static final Logger log = LoggerFactory.getLogger(DeleteDocument.class);

    /**
     * 根据文档id精准删除
     * @param client 连接
     * @param indexName 索引名称
     * @param id 索引id
     * @return 是否删除成功
     */
    public static Boolean deleteOneDocument(JestClient client, String indexName, String id) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        DocumentResult result = null;
        try {
            Delete build = new Delete.Builder(id)
                    .index(indexName)
                    .type(ProjectParams.ES_PROJECT_NAME + ".type")
                    .build();
            result = client.execute(build);
        } catch (Exception e) {
            log.error("deleteOneDocument Exception : ", e);
        }
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        return result.isSucceeded();
    }

    /**
     * 根据文档id批量删除
     * @param client 连接
     * @param indexName 索引名称
     * @param ids 索引id集合
     * @return 批量操作是否成功，有一条操作错误即为false
     */
    public static Boolean bulkDeleteDocument(JestClient client, String indexName, List<String> ids) {
        Bulk.Builder builder = new Bulk.Builder();
        ids.forEach(id -> builder.addAction(
            new Delete.Builder(id)
                .index(ProjectParams.ES_PROJECT_NAME + "." + indexName)
                .type(ProjectParams.ES_PROJECT_NAME + ".type")
                .build())
        );
        try {
            BulkResult result = client.execute(builder.build());
            List<BulkResult.BulkResultItem> items = result.getFailedItems();
            if (CollectionUtils.isNotEmpty(items)) {
                items.forEach(item -> log.error("id为 {} 的文档删除失败,原因 {}", item.id, item.errorReason));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("bulkDeleteDocument Exception : ", e);
        }
        return false;
    }

    /**
     * 删除查询结果文档
     * @param client 连接
     * @param ssb 查询创建对象
     * @return 删除操作是否成功
     */
    public static Boolean deleteByQuery(JestClient client, String indexName, SearchSourceBuilder ssb) {
        try {
            indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
            DeleteByQuery builder = new DeleteByQuery.Builder(ssb.toString()).addIndex(indexName).build();
            JestResult result = client.execute(builder);
            if (!result.isSucceeded()) {
                log.error(result.getErrorMessage());
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("deleteByQuery Exception : ", e);
        }
        return false;
    }
}
