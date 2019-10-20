package com.nj.fh.es.mappings;

import com.nj.fh.es.common.ProjectParams;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建Mappings设置,生成字符串形式的mappings设置
 * @author 赵元路 18358572500 2019/9/8 16:21
 * @version 1.0
 */
public class CreateMappings {
	
	/**
	 * 私有化构造
	 */
	private CreateMappings() {}

	/**
	 * 获取mappings设置的json格式字符串
	 * @param mappingsFields mappings设置的字段(List形式,字段较多的时候)
	 * @return mappings设置的json格式字符串
	 */
	public static String getMappingsJsonString(List<MappingsField> mappingsFields) {
		StringBuilder sb = new StringBuilder("{\"properties\":{");
		for (MappingsField field : mappingsFields) {
			getOneMappingFieldJsonString(sb, field);
		}
		return sb.deleteCharAt(sb.length() - 1).append("}}").toString();
	}

	/**
	 * 获取mappings设置的json格式字符串
	 * @param mappingsFields mappings设置的字段(可变数组形式,字段较少的时候)
	 * @return mappings设置的json格式字符串
	 */
	public static String getMappingsJsonString(MappingsField... mappingsFields) {
		List<MappingsField> mappingsFieldList = new ArrayList<>(Arrays.asList(mappingsFields));
		return getMappingsJsonString(mappingsFieldList);
	}

	/**
	 * 私有方法
	 * @param sb StringBuilder
	 * @param field 字段
	 * @return StringBuilder
	 */
	private static StringBuilder getOneMappingFieldJsonString(StringBuilder sb, MappingsField field) {
		String analyzer = field.getAnalyzer();
		Boolean index = field.getIndex();
		Integer boost = field.getBoost();
		String index_analyzer = field.getIndex_analyzer();
		String search_analyzer = field.getSearch_analyzer();
		List<String> formats = field.getFormat();
		sb.append("\"").append(field.getFieldName()).append("\":{").append("\"type\":\"").append(field.getFieldType()).append("\"},");
		if (analyzer != null && analyzer.length() != 0) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"analyzer\":\"").append(analyzer).append("\"},");
		}
		if (CollectionUtils.isNotEmpty(formats)) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"format\":\"");
			for (String format : formats) {
				sb.append(format).append("||");
			}
			sb.deleteCharAt(sb.length() - 2).deleteCharAt(sb.length() - 1);
			sb.append( "\"},");
		}
		if (index != null) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"index\":\"").append(index).append("\"},");
		}
		if (null != boost) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"index\":").append(boost).append("\"},");
		}
		if (index_analyzer != null && index_analyzer.length() != 0) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"index_analyzer\":\"").append(index_analyzer).append("\"},");
		}
		if (search_analyzer != null && search_analyzer.length() != 0) {
			sb.deleteCharAt(sb.length() - 2);
			sb.append("\"search_analyzer\":\"").append(search_analyzer).append("\"},");
		}
		return sb;
	}

}
