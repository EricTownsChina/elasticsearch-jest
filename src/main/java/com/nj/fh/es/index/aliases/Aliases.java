package com.nj.fh.es.index.aliases;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.aliases.AddAliasMapping;
import io.searchbox.indices.aliases.ModifyAliases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 别名操作类,别名操作可在es-head插件中快速操作,jar包中只提供添加操作
 * @author 赵元路 18358572500 2019/9/19 14:41
 * @version 1.0
 */
public final class Aliases {

    /**
     * 私有化构造
     */
    private Aliases() {}
    private static Logger log = LoggerFactory.getLogger(Aliases.class);

    /**
     * 添加别名,索引和别名是多对多的关系,不同的索引可以有相同的别名,不同的别名可以指向相同的索引
     * 不开放索引集合的指定,请手动添加单一别名至一个索引
     * @param client 连接
     * @param indexName 索引名称
     * @param alias 别名
     * @return 添加别名是否成功
     */
    public static Boolean addAlias(JestClient client, String indexName, String alias) {
        List<String> indexNames = new ArrayList<>();
        indexNames.add(indexName);
        return addAlias(client, indexNames, alias);
    }

    /**
     * 为多个索引添加同一别名
     * @param client 连接
     * @param indexNames 索引名称集合
     * @param alias 别名
     * @return 添加别名是否成功
     */
    private static Boolean addAlias(JestClient client, List<String> indexNames, String alias) {
        try {
            AddAliasMapping build = new AddAliasMapping.Builder(indexNames, alias).build();
            JestResult jestResult = client.execute(new ModifyAliases.Builder(build).build());
            if (!jestResult.isSucceeded()) {
                log.error(jestResult.getErrorMessage());
            }
            return jestResult.isSucceeded();
        } catch (Exception e) {
            log.error("addAlias Exception : ", e);
        }
        return false;
    }
}
