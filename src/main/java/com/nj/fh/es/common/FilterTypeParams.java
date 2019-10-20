package com.nj.fh.es.common;

/**
 * 过滤器类型常量
 * 该类中的常量可以用在自定义settings设置时
 * 如需自定义，可在ES权威指南查找并自行设置
 * @author 赵元路 18358572500 2019/9/10 17:09
 * @version 1.0
 */
public final class FilterTypeParams {

    /**
     * 私有化构造
     */
    private FilterTypeParams() {}

    /**
     * 常量字段 拼音过滤器(自定义)
     */
    public static final String PINYIN = "pinyin";

    /**
     * 常量字段 拼音全拼过滤器(自定义)
     */
    public static final String PINYIN_FULL = "pinyin_full";

    /**
     * 常量字段 拼音简拼过滤器(自定义)
     */
    public static final String PINYIN_SIMPLE = "pinyin_simple";

    /**
     * 常量字段 中文停用词过滤器(自定义的名字)
     */
    public static final String CHINESE_STOP = "chinese_stop";

    /**
     * 常量字段 标准token过滤器
     */
    public static final String STANDARD = "standard";

    /**
     * 常量字段 去掉变音符号，并把Unicode字符转化为ASCII来表示:
     * ß ⇒ ss
     * æ ⇒ ae
     * ł ⇒ l
     * ɰ ⇒ m
     * ⁇ ⇒ ??
     * ❷ ⇒ 2
     * ⁶ ⇒ 6
     */
    public static final String ASCIIFOLDING = "asciifolding";

    /**
     * 常量字段 去掉太长或者太短的
     */
    public static final String LENGTH = "length";

    /**
     * 常量字段 转成小写
     */
    public static final String LOWCASE = "lowercase";

    /**
     * 常量字段 n-gram处理
     */
    public static final String NGRAM = "nGram";

    /**
     * 常量字段 波特词干算法
     */
    public static final String PORTERSTEM = "porterStem";

    /**
     * 常量字段 移除停用词
     */
    public static final String STOP = "stop";

    /**
     * 常量字段 将一个单词再拆成子分词
     */
    public static final String WORD_DELIMITER = "word_delimiter";

    /**
     * 常量字段 词干提取过滤
     */
    public static final String STEMMER = "stemmer";

    /**
     * 常量字段 雪球过滤器
     */
    public static final String SNOWBALL = "snowball";

    /**
     * 常量字段 使用同义词
     */
    public static final String SYNONYMS = "synonyms";

    /**
     * 常量字段 去掉两边空格
     */
    public static final String TRIM = "trim";

    /**
     * 常量字段 拼写检查
     */
    public static final String HUNSPELL = "hunspell";

    /**
     * 常量字段 反转字符串
     */
    public static final String REVERSE = "reverse";

}
