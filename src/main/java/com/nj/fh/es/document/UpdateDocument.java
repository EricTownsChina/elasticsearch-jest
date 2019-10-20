package com.nj.fh.es.document;

import com.alibaba.fastjson.JSON;
import com.nj.fh.es.client.Client;
import com.nj.fh.es.common.ProjectParams;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 更新文档
 * @author 赵元路 18358572500 2019/9/18 16:18
 * @version 1.0
 */
public class UpdateDocument {

    /**
     * 私有化构造
     */
    private UpdateDocument() {}
    private static final Logger log = LoggerFactory.getLogger(UpdateDocument.class);

    /**
     * 根据文档id修改文档
     * @param client 连接
     * @param indexName 索引名称
     * @param id 索引id
     * @param obj 新的文档对象
     * @return 更新操作是否成功
     */
    public static Boolean updateOneDocument(JestClient client, String indexName, String id, Object obj) {
        indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
        String objString = "{\"doc\":" + JSON.toJSONString(obj) + "}";
        try {
            DocumentResult result = client.execute(
                    new Update.Builder(objString)
                            .index(indexName)
                            .type(ProjectParams.ES_PROJECT_NAME + ".type")
                            .id(id)
                            .build());
            if (!result.isSucceeded()) {
                log.error(result.getErrorMessage());
            }
            return result.isSucceeded();
        } catch (Exception e) {
            log.error("updateOneDocument Exception : ", e);
        }
        return false;
    }

}