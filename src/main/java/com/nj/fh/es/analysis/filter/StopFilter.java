package com.nj.fh.es.analysis.filter;

/**
 * 停用词过滤器
 * 默认中文停用词过滤(包含少数英文停用词和标点符号)
 * @author 赵元路 18358572500 2019/9/26 9:49
 * @version 1.0
 */
public class StopFilter {

    /**
     * 过滤器类型,stop
     */
    private String type = "stop";

    /**
     * 停用词字典路径
     */
    private String stopwords_path = "stopwords/ik_chinese.txt";

    public String getType() {
        return type;
    }

    public String getStopwords_path() {
        return stopwords_path;
    }

    public void setStopwords_path(String stopwords_path) {
        this.stopwords_path = stopwords_path;
    }
}
