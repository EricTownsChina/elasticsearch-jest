package com.nj.fh.es.index;

import com.nj.fh.es.client.Client;
import com.nj.fh.es.common.ProjectParams;
import com.nj.fh.es.search.query.Query;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 删除索引
 * 只支持单条删除，不支持批量删除
 * @author 赵元路 18358572500 2019/9/8 16:09
 * @version 1.0
 */
public class DeleteIndex {

	/**
	 * 私有化构造
	 */
	private DeleteIndex() {}
	private static final Logger log = LoggerFactory.getLogger(DeleteIndex.class);

	/**
	 * 根据索引名称删除索引
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return 删除是否成功
	 */
	public static Boolean deleteByName(JestClient client, String indexName) {
		indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
		try {
	        JestResult jestResult = client.execute(new io.searchbox.indices.DeleteIndex.Builder(indexName).build());
	        if (!jestResult.isSucceeded()) {
	        	log.error(jestResult.getErrorMessage());
	        }
	        return jestResult.isSucceeded();
	    } catch (IOException e) {
	        log.error("DeleteIndex Exception : ", e);
	    }
		return false;
	}
}