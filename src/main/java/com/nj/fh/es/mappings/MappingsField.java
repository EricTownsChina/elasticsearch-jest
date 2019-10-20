package com.nj.fh.es.mappings;

import java.util.ArrayList;
import java.util.List;

/**
 * mappings设置的字段
 * 字段中的fieldName、fieldType、index、boost是字段公共属性
 * 字段中的analyzer、index_analyzer、search_analyzer是字符串类型字段的属性
 * 字段中的format是日期类型字段的属性
 * 错误的类型属性选择将导致mappings设置失败
 * @author 赵元路 18358572500 2019/9/18 14:55
 * @version 1.0
 */
public class MappingsField {

	/********************************************* 公共属性 *********************************************/

	/**
	 * 字段名称
	 */
	private String fieldName;
	
	/**
	 * 字段类型(类型值请参考FieldTypeParams)
	 */
	private String fieldType;

	/**
	 * 该字段是否被编入索引以供搜索
	 * 值为true表示该字段将被编入索引以供搜索
	 * 值为false表示将无法搜索该字段
	 */
	private Boolean index;

	/**
	 * 表示该字段在文档中的重要性,默认值为1,boost越高,字段中值的重要性也越高,建议不要超过10
	 */
	private Integer boost;

	/***************************************** 字符串(text)属性 *****************************************/

	/**
	 * 字段分析器
	 * 只设置analyzer的话,建立索引和查询是时使用相同的分析器
	 * 如果你想要在查询时使用不同的分析器,可以设置search_analyzer
	 */
	private String analyzer;

	/**
	 * 建立索引时该字段所用的分析器
	 */
	private String index_analyzer;

	/**
	 * 查询该字段时所用的分析器
	 */
	private String search_analyzer;

	/******************************************** 日期类型属性 *******************************************/

	/**
	 * 日期转换格式
	 */
	private List<String> format;

	public MappingsField() {}

	public MappingsField(String fieldName, String fieldType) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}

	public String getFieldName() { return fieldName; }

	public void setFieldName(String fieldName) { this.fieldName = fieldName; }

	public String getFieldType() { return fieldType; }

	public void setFieldType(String fieldType) { this.fieldType = fieldType; }

	public List<String> getFormat() { return format; }

	public void setFormat(List<String> format) { this.format = format; }

	public void setFormat(String format) {
		List<String> formats = new ArrayList<>();
		formats.add(format);
		this.format = formats;
	}

	public Boolean getIndex() {
		return index;
	}

	public void setIndex(Boolean index) {
		this.index = index;
	}

	public Integer getBoost() {
		return boost;
	}

	public void setBoost(Integer boost) {
		this.boost = boost;
	}

	public String getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(String analyzer) {
		this.analyzer = analyzer;
	}

	public String getIndex_analyzer() {
		return index_analyzer;
	}

	public void setIndex_analyzer(String index_analyzer) {
		this.index_analyzer = index_analyzer;
	}

	public String getSearch_analyzer() {
		return search_analyzer;
	}

	public void setSearch_analyzer(String search_analyzer) {
		this.search_analyzer = search_analyzer;
	}
}
