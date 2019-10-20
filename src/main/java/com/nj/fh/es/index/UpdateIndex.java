package com.nj.fh.es.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nj.fh.es.common.ProjectParams;
import com.nj.fh.es.mappings.CreateMappings;
import com.nj.fh.es.mappings.MappingsField;
import com.nj.fh.es.settings.Settings;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CloseIndex;
import io.searchbox.indices.OpenIndex;
import io.searchbox.indices.mapping.PutMapping;
import io.searchbox.indices.settings.GetSettings;
import io.searchbox.indices.settings.UpdateSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 更新索引
 * 更新settings，settings的更新只能在索引创建之时，mappings设置确定之前
 * 添加mappings，mappings的添加只能在索引settings设置确定之后，索引尚未有mappings之时
 * mappings设置不能更新，请谨慎设置。如需修改，请删除索引重新创建
 * @author 赵元路 18358572500 2019/9/8 16:10
 * @version 1.0
 */
public class UpdateIndex {
	
	/**
	 * 私有化构造
	 */
	private UpdateIndex() {}
	private static final Logger log = LoggerFactory.getLogger(UpdateIndex.class);

	/**
	 * 修改settings设置,如果已有mappings设置,则不能修改
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settingsString 新的settings设置(JSON字符串形式)
	 * @return 更新settings设置是否成功
	 */
	public static Boolean updateIndexSettings(JestClient client, String indexName, String settingsString) {
		Settings settings = (Settings) JSONObject.parse(settingsString);
		return updateIndexSettings(client, indexName, settings);
	}

	/**
	 * 修改settings设置,如果已有mappings设置,则不能修改
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settings 新的settings设置(Settings形式)
	 * @return 更新settings设置是否成功
	 */
	public static Boolean updateIndexSettings(JestClient client, String indexName, Settings settings) {
		JestResult getResult = null;
		Integer old = null;
		String relativeIndexName = indexName;
		indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
		try {
			getResult = client.execute(new GetSettings.Builder().addIndex(indexName).build());
			if (!getResult.isSucceeded()) {
				log.error(getResult.getErrorMessage());
				return false;
			}
			String resultString = getResult.getJsonString();
			String tempString = resultString.substring(resultString.indexOf("number_of_shards"), resultString.indexOf("provided_name"));
			String[] split = tempString.split("\"");
			old = Integer.parseInt(split[2]);
		} catch (Exception e1) {
			log.error("updateIndexSettings Exception : ", e1);
			return false;
		}

		/**
		 * 分片数不同则需要删除并新建索引,相同则不用删除
		 */
		if (old.equals(settings.getNumber_of_shards())) {
			settings.setNumber_of_shards(null);
			try {
	    		String source = JSON.toJSONString(settings);
				client.execute(new CloseIndex.Builder(indexName).build());
				JestResult jestResult = client.execute(new UpdateSettings.Builder(source).addIndex(indexName).build());
				if(!jestResult.isSucceeded()) {
					log.error(jestResult.getErrorMessage());
				}
				client.execute(new OpenIndex.Builder(indexName).build());
				return jestResult.isSucceeded();
			} catch (Exception e) {
				log.error("updateIndexSettings Exception : ", e);
			}
		} else {
			try {
				String haveMappings = GetIndexMsg.getMappingsString(client, relativeIndexName);
				Map indexMap = (Map) JSONObject.parse(haveMappings);
				Map mappingMap = (Map) indexMap.get(indexName);
				Object mapping = mappingMap.get("mapping");
				if (mapping != null) {
					log.error("已经存在mappings,不能更新settings设置！");
					return false;
				}
				DeleteIndex.deleteByName(client, relativeIndexName);
				return CreateIndex.createOneIndex(client, relativeIndexName, settings);
			} catch (Exception e) {
				log.error("updateIndexSettings Exception : " + e);
			}
		}
		return false;
	}

	/**
	 * 添加mappings设置
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param mappingsFields mappings设置的字段集合
	 * @return 添加mappings设置是否成功
	 */
	public static Boolean putIndexMapping(JestClient client, String indexName, List<MappingsField> mappingsFields) {
		try {
			indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
	    	String mappingsJsonString = CreateMappings.getMappingsJsonString(mappingsFields);
	        PutMapping.Builder builder = new PutMapping.Builder(indexName, ProjectParams.ES_PROJECT_NAME + ".type", mappingsJsonString);
	        JestResult jestResult = client.execute(builder.build());
	        if (!jestResult.isSucceeded()) {
	            log.error(jestResult.getErrorMessage());
	        }
	        return jestResult.isSucceeded();
	    } catch (Exception e) {
	        log.error("putIndexMapping Exception : ", e);
	    }
	    return false;
	}

	/**
	 * 添加mappings设置
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param mappingsString mappings字符串
	 * @return 添加mappings设置是否成功
	 */
	public static Boolean putIndexMapping(JestClient client, String indexName, String mappingsString) {
		try {
			indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
			PutMapping.Builder builder = new PutMapping.Builder(indexName,
					ProjectParams.ES_PROJECT_NAME + ".type", JSON.parse(mappingsString));
			JestResult jestResult = client.execute(builder.build());
			if (!jestResult.isSucceeded()) {
				log.error(jestResult.getErrorMessage());
			}
			return jestResult.isSucceeded();
		} catch (Exception e) {
			log.error("putIndexMapping Exception : ", e);
		}
		return false;
	}
}