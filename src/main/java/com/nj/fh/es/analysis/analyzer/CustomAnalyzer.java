package com.nj.fh.es.analysis.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵元路 18358572500 2019/9/26 10:45
 * @version 1.0
 */
public class CustomAnalyzer {

    /**
     * 分析器类型,custom用户自定义
     */
    private String type = "custom";

    /**
     * 字符过滤器,可以有多个,List形式
     */
    private List<String> char_filter = new ArrayList<>();

    /**
     * 过滤器,可以有多个,List形式
     */
    private List<String> filter = new ArrayList<>();

    /**
     * 分词器,只能有一个
     */
    private String tokenizer;

    public String getType() {
        return type;
    }

    public List<String> getChar_filter() {
        return char_filter;
    }

    public void setChar_filter(List<String> char_filter) {
        this.char_filter = char_filter;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public String getTokenizer() {
        return tokenizer;
    }

    public void setTokenizer(String tokenizer) {
        this.tokenizer = tokenizer;
    }
}
