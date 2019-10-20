package com.nj.fh.es.settings;

import com.nj.fh.es.analysis.Analysis;

/**
 * 索引的settings设置
 * @author 赵元路 18358572500 2019/9/8 16:32
 * @version 1.0
 */
public class Settings {
	
	/**
	 * 分片数，默认5
	 */
	private Integer number_of_shards = 5;
	
	/**
	 * 副本数，默认1
	 */
	private Integer number_of_replicas = 1;
	
	/**
	 * 分析过程
	 */
	private Analysis analysis;
	
	public Integer getNumber_of_shards() {
		return number_of_shards;
	}
	public void setNumber_of_shards(Integer number_of_shards) {
		this.number_of_shards = number_of_shards;
	}
	public Integer getNumber_of_replicas() {
		return number_of_replicas;
	}
	public void setNumber_of_replicas(Integer number_of_replicas) {
		this.number_of_replicas = number_of_replicas;
	}
	public Analysis getAnalysis() {
		return analysis;
	}
	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}

}
