package com.nj.fh.es.analysis.filter;

/**
 * 拼音过滤器,默认的拼音过滤器是全拼形式的
 * 刘德华 ⇒ liu de hua
 * @author 赵元路 18358572500 2019/9/26 9:34
 * @version 1.0
 */
public class PinyinFilter {

    /**
     * 过滤器类型,pinyin
     */
    private String type = "pinyin";

    /**
     * 转为小写, 默认true
     */
    private Boolean lowercase = true;

    /**
     * 保留原始数据,默认false
     */
    private Boolean keep_origin = false;

    /**
     * 保留首字母,默认false
     */
    private Boolean keep_first_letter = false;

    /**
     * 保留分开的首字母,默认false
     */
    private Boolean keep_separate_first_letter = false;

    /**
     * 限制首字母长度,默认16
     * 刘德华真帅 ⇒ ldhzs
     */
    private Integer limit_first_letter_length = 16;

    /**
     * 保留全拼,默认true
     */
    private Boolean keep_full_pinyin = true;

    /**
     * 保留非中文部分,默认true
     */
    private Boolean keep_none_chinese_together = true;

    /**
     * 非中文部分分词,默认true,需要先开启keep_none_chinese_together = true
     */
    private Boolean none_chinese_pinyin_tokenize = true;

    /**
     * 去除重复词语,默认true
     */
    private Boolean remove_duplicated_term = true;

    public Boolean getLowercase() {
        return lowercase;
    }

    public String getType() {
        return type;
    }

    public void setLowercase(Boolean lowercase) {
        this.lowercase = lowercase;
    }

    public Boolean getKeep_origin() {
        return keep_origin;
    }

    public void setKeep_origin(Boolean keep_origin) {
        this.keep_origin = keep_origin;
    }

    public Boolean getKeep_first_letter() {
        return keep_first_letter;
    }

    public void setKeep_first_letter(Boolean keep_first_letter) {
        this.keep_first_letter = keep_first_letter;
    }

    public Boolean getKeep_separate_first_letter() {
        return keep_separate_first_letter;
    }

    public void setKeep_separate_first_letter(Boolean keep_separate_first_letter) {
        this.keep_separate_first_letter = keep_separate_first_letter;
    }

    public Integer getLimit_first_letter_length() {
        return limit_first_letter_length;
    }

    public void setLimit_first_letter_length(Integer limit_first_letter_length) {
        this.limit_first_letter_length = limit_first_letter_length;
    }

    public Boolean getKeep_full_pinyin() {
        return keep_full_pinyin;
    }

    public void setKeep_full_pinyin(Boolean keep_full_pinyin) {
        this.keep_full_pinyin = keep_full_pinyin;
    }

    public Boolean getKeep_none_chinese_together() {
        return keep_none_chinese_together;
    }

    public void setKeep_none_chinese_together(Boolean keep_none_chinese_together) {
        this.keep_none_chinese_together = keep_none_chinese_together;
    }

    public Boolean getNone_chinese_pinyin_tokenize() {
        return none_chinese_pinyin_tokenize;
    }

    public void setNone_chinese_pinyin_tokenize(Boolean none_chinese_pinyin_tokenize) {
        this.none_chinese_pinyin_tokenize = none_chinese_pinyin_tokenize;
    }

    public Boolean getRemove_duplicated_term() {
        return remove_duplicated_term;
    }

    public void setRemove_duplicated_term(Boolean remove_duplicated_term) {
        this.remove_duplicated_term = remove_duplicated_term;
    }
}
