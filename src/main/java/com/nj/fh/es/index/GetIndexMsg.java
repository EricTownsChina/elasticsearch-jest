package com.nj.fh.es.index;

import com.alibaba.fastjson.JSON;
import com.nj.fh.es.client.Client;
import com.nj.fh.es.common.ProjectParams;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.settings.GetSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 获取索引信息
 * 包括索引的settings设置和mappings设置
 * @author 赵元路 18358572500 2019/9/18 14:27
 * @version 1.0
 */
public class GetIndexMsg {
	
	/**
	 * 私有化构造
	 */
	private GetIndexMsg() {}
	private static final Logger log = LoggerFactory.getLogger(GetIndexMsg.class);

	/**
	 * 获取索引的settings设置(字符串形式)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return settings设置(字符串形式)
	 */
	public static String getSettingsString(JestClient client, String indexName) {
		indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
		try {
			GetSettings.Builder builder = new GetSettings.Builder().addIndex(indexName);
			JestResult result = client.execute(builder.build());
			if (!result.isSucceeded()) {
				log.error(result.getErrorMessage());
				return null;
			}
			return result.getJsonString();
		} catch (Exception e) {
			log.error("getSettingsString Exception : ", e);
		}
		return null;
	}

	/**
	 * 获取索引的settings设置(Map格式)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return 索引settings设置(map形式)
	 */
	public static Map<String, String> getSettingsMap(JestClient client, String indexName) {
		String settingsString = getSettingsString(client, indexName);
		Map map = (Map) JSON.parse(settingsString);
		return map;
	}

	/**
	 * 获取索引的mappings设置(字符串形式)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return 索引mappings设置(字符串形式)
	 */
	public static String getMappingsString(JestClient client, String indexName) {
		indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
		try {
			GetMapping.Builder builder = new GetMapping.Builder().addIndex(indexName);
            JestResult result = client.execute(builder.build());
            if (result == null || !result.isSucceeded()) {
            	log.error(result.getErrorMessage());
            	return null;
            }
			return result.getJsonString();
        } catch (Exception e) {
            log.error("getMappings Exception : ", e);
        }
        return null;
    }

	/**
	 * 获取索引的mappings设置(Map形式)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return 索引mappings设置(map形式)
	 */
	public static Map<String, String> getMappingsMap(JestClient client, String indexName) {
		String mappingsString = getMappingsString(client, indexName);
		Map map = (Map) JSON.parse(mappingsString);
		return map;
	}

}