package com.nj.fh.es.settings;

import com.alibaba.fastjson.JSON;
import com.nj.fh.es.analysis.Analysis;
import com.nj.fh.es.analysis.analyzer.CustomAnalyzer;
import com.nj.fh.es.analysis.charFilter.StconvertFilter;
import com.nj.fh.es.analysis.filter.PinyinFilter;
import com.nj.fh.es.analysis.filter.StopFilter;
import com.nj.fh.es.common.AnalyzerTypeParams;
import com.nj.fh.es.common.CharFilterTypeParams;
import com.nj.fh.es.common.FilterTypeParams;
import com.nj.fh.es.common.TokenizerTypeParams;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Settings模板,可以根据
 * @author 赵元路 18358572500 2019/9/26 10:06
 * @version 1.0
 */
public class SettingsTemplate {

    /**
     * 私有化构造
     */
    private SettingsTemplate() {}

    /**
     * 获取默认的settings,Settings形式
     * @return
     */
    public static Settings getDefaultSettings() {
        Settings settings = new Settings();
        settings.setAnalysis(getDefaultAnalysis());
        return settings;
    }

    /**
     * 获取默认settings设置的JSON字符串形式
     * @return
     */
    public static String getDefaultSettingsJson() {
        Settings defaultSettings = getDefaultSettings();
        return JSON.toJSONString(defaultSettings);
    }

    private static Analysis getDefaultAnalysis() {
        Map<String, Object> filter = new HashMap<>();
        Map<String, Object> char_filter = new HashMap<>();
        Map<String, Object> analyzer = new HashMap<>();

        getDefaultFilter(filter);
        getDefaultCharFilter(char_filter);
        getDefaultAnalyzer(analyzer);

        Analysis analysis = new Analysis();
        analysis.setFilter(filter);
        analysis.setChar_filter(char_filter);
        analysis.setAnalyzer(analyzer);

        return analysis;
    }

    private static void getDefaultAnalyzer(Map<String, Object> analyzer) {
        //ik_max_word + 繁简转换
        CustomAnalyzer ik_max_word = new CustomAnalyzer();
        ik_max_word.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word.setTokenizer(AnalyzerTypeParams.IK_MAX_WORD);

        //ik_smart + 繁简转换
        CustomAnalyzer ik_smart = new CustomAnalyzer();
        ik_smart.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart.setTokenizer(TokenizerTypeParams.IK_SMART);

        //ik_max_word + 中文停用词过滤 + 繁简转换
        CustomAnalyzer ik_max_word_stop = new CustomAnalyzer();
        ik_max_word_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word_stop.setFilter(Collections.singletonList(FilterTypeParams.CHINESE_STOP));
        ik_max_word_stop.setTokenizer(TokenizerTypeParams.IK_MAX_WORD);

        //ik_smart + 中文停用词 + 繁简转换
        CustomAnalyzer ik_smart_stop = new CustomAnalyzer();
        ik_smart_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart_stop.setFilter(Collections.singletonList(FilterTypeParams.CHINESE_STOP));
        ik_smart_stop.setTokenizer(TokenizerTypeParams.IK_SMART);

        //ik_max_word + 拼音全拼 + 繁简转换
        CustomAnalyzer ik_max_word_pinyin_full = new CustomAnalyzer();
        ik_max_word_pinyin_full.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word_pinyin_full.setFilter(Collections.singletonList(FilterTypeParams.PINYIN_FULL));
        ik_max_word_pinyin_full.setTokenizer(TokenizerTypeParams.IK_MAX_WORD);

        //ik_max_word + 拼音简拼 + 繁简转换
        CustomAnalyzer ik_max_word_pinyin_simple = new CustomAnalyzer();
        ik_max_word_pinyin_simple.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word_pinyin_simple.setFilter(Collections.singletonList(FilterTypeParams.PINYIN_SIMPLE));
        ik_max_word_pinyin_simple.setTokenizer(TokenizerTypeParams.IK_MAX_WORD);

        //ik_smart + 拼音全拼 + 繁简转换
        CustomAnalyzer ik_smart_pinyin_full = new CustomAnalyzer();
        ik_smart_pinyin_full.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart_pinyin_full.setFilter(Collections.singletonList(FilterTypeParams.PINYIN_FULL));
        ik_smart_pinyin_full.setTokenizer(TokenizerTypeParams.IK_SMART);

        //ik_smart + 拼音简拼 + 繁简转换
        CustomAnalyzer ik_smart_pinyin_simple = new CustomAnalyzer();
        ik_smart_pinyin_simple.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart_pinyin_simple.setFilter(Collections.singletonList(FilterTypeParams.PINYIN_SIMPLE));
        ik_smart_pinyin_simple.setTokenizer(TokenizerTypeParams.IK_SMART);

        //ik_max_word + 拼音全拼 + 中文停用词过滤 + 繁简转换
        CustomAnalyzer ik_max_word_pinyin_full_stop = new CustomAnalyzer();
        ik_max_word_pinyin_full_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word_pinyin_full_stop.setFilter(Arrays.asList(FilterTypeParams.PINYIN_FULL, FilterTypeParams.CHINESE_STOP));
        ik_max_word_pinyin_full_stop.setTokenizer(TokenizerTypeParams.IK_MAX_WORD);

        //ik_max_word + 拼音简拼 + 中文停用词过滤 + 繁简转换
        CustomAnalyzer ik_max_word_pinyin_simple_stop = new CustomAnalyzer();
        ik_max_word_pinyin_simple_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_max_word_pinyin_simple_stop.setFilter(Arrays.asList(FilterTypeParams.PINYIN_SIMPLE, FilterTypeParams.CHINESE_STOP));
        ik_max_word_pinyin_simple_stop.setTokenizer(TokenizerTypeParams.IK_MAX_WORD);

        //ik_smart + 拼音全拼 + 中文停用词过滤 + 繁简转换
        CustomAnalyzer ik_smart_pinyin_full_stop = new CustomAnalyzer();
        ik_smart_pinyin_full_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart_pinyin_full_stop.setFilter(Arrays.asList(FilterTypeParams.PINYIN_FULL, FilterTypeParams.CHINESE_STOP));
        ik_smart_pinyin_full_stop.setTokenizer(TokenizerTypeParams.IK_SMART);

        //ik_smart + 拼音简拼 + 中文停用词过滤 + 繁简转换
        CustomAnalyzer ik_smart_pinyin_simple_stop = new CustomAnalyzer();
        ik_smart_pinyin_simple_stop.setChar_filter(Collections.singletonList(CharFilterTypeParams.TSCONVERT));
        ik_smart_pinyin_simple_stop.setFilter(Arrays.asList(FilterTypeParams.PINYIN_SIMPLE, FilterTypeParams.CHINESE_STOP));
        ik_smart_pinyin_simple_stop.setTokenizer(TokenizerTypeParams.IK_SMART);

        /**
         * 放入analyzer
         */
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD, ik_max_word);
        analyzer.put(AnalyzerTypeParams.IK_SMART, ik_smart);
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD_STOP, ik_max_word_stop);
        analyzer.put(AnalyzerTypeParams.IK_SMART_STOP, ik_smart_stop);
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_FULL, ik_max_word_pinyin_full);
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_SIMPLE, ik_max_word_pinyin_simple);
        analyzer.put(AnalyzerTypeParams.IK_SMART_PINYIN_FULL, ik_smart_pinyin_full);
        analyzer.put(AnalyzerTypeParams.IK_SMART_PINYIN_SIMPLE, ik_smart_pinyin_simple);
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_FULL_STOP, ik_max_word_pinyin_full_stop);
        analyzer.put(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_SIMPLE_STOP, ik_max_word_pinyin_simple_stop);
        analyzer.put(AnalyzerTypeParams.IK_SMART_PINYIN_FULL_STOP, ik_smart_pinyin_full_stop);
        analyzer.put(AnalyzerTypeParams.IK_SMART_PINYIN_SIMPLE_STOP, ik_smart_pinyin_simple_stop);
    }

    private static void getDefaultCharFilter(Map<String, Object> char_filter) {
        char_filter.put(CharFilterTypeParams.TSCONVERT, new StconvertFilter());
    }

    private static void getDefaultFilter(Map<String, Object> filter) {
        PinyinFilter pinyin = new PinyinFilter();
        pinyin.setKeep_full_pinyin(false);
        pinyin.setKeep_first_letter(true);
        //中文停用词过滤器
        filter.put(FilterTypeParams.CHINESE_STOP, new StopFilter());
        //拼音全拼过滤器
        filter.put(FilterTypeParams.PINYIN_FULL, new PinyinFilter());
        //拼音简拼过滤器
        filter.put(FilterTypeParams.PINYIN_SIMPLE, pinyin);
    }
}
