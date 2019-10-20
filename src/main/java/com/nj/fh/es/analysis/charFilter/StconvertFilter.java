package com.nj.fh.es.analysis.charFilter;

/**
 * 繁简转换过滤器,默认t2s,繁体转简体
 * @author 赵元路 18358572500 2019/9/26 9:56
 * @version 1.0
 */
public class StconvertFilter {

    /**
     * 过滤器类型,stconvert
     */
    private String type = "stconvert";

    /**
     * 转换类型,繁体转简体
     */
    private String convert_type = "t2s";

    public String getType() {
        return type;
    }

    public String getConvert_type() {
        return convert_type;
    }

    public void setConvert_type(String convert_type) {
        this.convert_type = convert_type;
    }
}
