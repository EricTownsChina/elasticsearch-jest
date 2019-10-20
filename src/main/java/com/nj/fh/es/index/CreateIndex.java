package com.nj.fh.es.index;

import com.alibaba.fastjson.JSON;
import com.nj.fh.es.common.ProjectParams;
import com.nj.fh.es.mappings.CreateMappings;
import com.nj.fh.es.mappings.MappingsField;
import com.nj.fh.es.settings.Settings;
import com.nj.fh.es.settings.SettingsTemplate;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.mapping.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 创建索引类，不开放批量创建索引方法，支持手动创建一个索引
 * @author 赵元路 18358572500 2019/9/8 15:37
 * @version 1.0
 */
public final class CreateIndex {

	/**
	 * 私有化构造
	 */
	private CreateIndex() {
	}
	private static final Logger log = LoggerFactory.getLogger(CreateIndex.class);


	/**
	 * 创建一个索引,使用默认的settings设置和自定义的mappings设置
	 * @param client 连接
	 * @param indexName 自定义索引名称
	 * @param mappingsFields 自定义的mappings字段List
	 * @return 创建索引是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName, List<MappingsField> mappingsFields) {
		Settings defaultSettings = SettingsTemplate.getDefaultSettings();
		return createOneIndex(client, indexName, defaultSettings, mappingsFields);
	}

	/**
	 * 创建一个索引,使用默认的settings设置
	 * 没有mappings设置，需要通过UpdateIndex中的putMappings方法设置mappings
	 * @param client 连接
	 * @param indexName 索引名称
	 * @return 创建索引是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName) {
		Settings defaultSettings = SettingsTemplate.getDefaultSettings();
		return createOneIndex(client, indexName, defaultSettings);
	}

	/**
	 * 创建一个索引,使用自定义settings设置
	 * 没有mappings设置，需要通过UpdateIndex中的putMappings方法设置mappings
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settings 自定义的settings设置
	 * @return 创建索引是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName, Settings settings) {
		indexName = ProjectParams.ES_PROJECT_NAME + "." + indexName;
		JestResult createResult;
		String settingsString = JSON.toJSONString(settings);
		try {
			createResult = client.execute(
					new io.searchbox.indices.CreateIndex
					.Builder(indexName)
					.settings(settingsString)
					.build());
			if (!createResult.isSucceeded()) {
				log.error(createResult.getErrorMessage());
				return false;
			}
			return createResult.isSucceeded();
		} catch (Exception e) {
			log.error("CreateOneIndex Exception : ", e);
		}
        return false;
	}

	/**
	 * 创建一个索引,使用自定义的settings设置和自定义的mappings设置
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settings 自定义的settings设置
	 * @param mappingsFields mappings设置的字段集合
	 * @return 创建索引是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName, Settings settings, List<MappingsField> mappingsFields) {
		try {
			Boolean createOneIndex = createOneIndex(client, indexName, settings);
			if (!createOneIndex) {
				return false;
			}
			String jsonMappings = CreateMappings.getMappingsJsonString(mappingsFields);
			PutMapping.Builder builder = new PutMapping.Builder(ProjectParams.ES_PROJECT_NAME + "." + indexName, ProjectParams.ES_PROJECT_NAME + ".type", jsonMappings);
			JestResult result = client.execute(builder.build());
			if (!result.isSucceeded()) {
				DeleteIndex.deleteByName(client, indexName);
				log.error(result.getErrorMessage());
			}
			return result.isSucceeded();
		} catch (Exception e) {
			log.error("CreateOneIndex Exception : ", e);
		}
		return false;
	}

	/**
	 * 创建一个索引,使用自定义的settings设置(字符串)和自定义的mappings设置(字符串)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settingsString settings字符串
	 * @return 操作是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName, String settingsString) {
		try {
			JestResult result = client.execute(
					new io.searchbox.indices.CreateIndex
							.Builder(ProjectParams.ES_PROJECT_NAME + "." + indexName)
							.settings(settingsString)
							.build());
			if (!result.isSucceeded()) {
				log.error(result.getErrorMessage());
			}
			return result.isSucceeded();
		} catch (IOException e) {
			log.error("CreateOneIndex Exception : ", e);
		}
		return false;
	}

	/**
	 * 创建一个索引,使用自定义的settings设置(字符串)和自定义的mappings设置(字符串)
	 * @param client 连接
	 * @param indexName 索引名称
	 * @param settingsString settings字符串
	 * @param mappingsString mappings字符串
	 * @return 操作是否成功
	 */
	public static Boolean createOneIndex(JestClient client, String indexName, String settingsString, String mappingsString) {
		if (!createOneIndex(client, indexName, settingsString)) {
			return false;
		}
		try {
			PutMapping.Builder builder = new PutMapping.Builder(ProjectParams.ES_PROJECT_NAME + "." + indexName,
					ProjectParams.ES_PROJECT_NAME + ".type",
					JSON.parse(mappingsString));
			JestResult mappingsResult = client.execute(builder.build());
			if (!mappingsResult.isSucceeded()) {
				DeleteIndex.deleteByName(client, indexName);
				log.error(mappingsResult.getErrorMessage());
				return false;
			}
			return true;
		} catch (IOException e) {
			log.error("CreateOneIndex Exception : ", e);
		}
		return false;
	}
}
