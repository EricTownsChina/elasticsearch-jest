package com.nj.fh.es.analysis;

import java.util.Map;

/**
 * 分析过程，您可以创建自己的Analysis来创建自定义的settings设置
 * @author 赵元路 18358572500 2019/9/26 10:28
 * @version 1.0
 */
public class Analysis {

    /**
     * 字符过滤器信息
     */
    private Map<String, Object> char_filter;

    /**
     * 分词器设置
     */
    private Map<String, Object> tokenizer;

    /**
     * 字符过滤器信息
     */
    private Map<String, Object> filter;

    /**
     * 分析器信息
     */
    private Map<String, Object> analyzer;

    public Map<String, Object> getChar_filter() {
        return char_filter;
    }

    public void setChar_filter(Map<String, Object> char_filter) {
        this.char_filter = char_filter;
    }

    public Map<String, Object> getTokenizer() {
        return tokenizer;
    }

    public void setTokenizer(Map<String, Object> tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public Map<String, Object> getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Map<String, Object> analyzer) {
        this.analyzer = analyzer;
    }
}
