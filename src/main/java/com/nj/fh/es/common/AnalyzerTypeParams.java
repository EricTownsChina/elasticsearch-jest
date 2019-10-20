package com.nj.fh.es.common;

/**
 * 分析器类型常量
 * 该类中的常量可以用在自定义settings设置或自定义mappings设置时
 * @author 赵元路 18358572500 2019/9/10 14:56
 * @version 1.0
 */
public final class AnalyzerTypeParams {
    /**
     * 私有化构造
     */
    private AnalyzerTypeParams() {}

    /************************************************************************************************/

    /**
     * ik_smart + 繁简转换
     */
    public static final String IK_SMART = "ik_smart";

    /**
     * ik_max_word + 繁简转换
     */
    public static final String IK_MAX_WORD = "ik_max_word";

    /**
     * ik_max_word + 中文停用词过滤 + 繁简转换
     */
    public static final String IK_MAX_WORD_STOP = "ik_max_word_stop";

    /**
     * ik_smart + 中文停用词 + 繁简转换
     */
    public static final String IK_SMART_STOP = "ik_smart_stop";

    /**
     * ik_max_word + 拼音全拼 + 繁简转换
     */
    public static final String IK_MAX_WORD_PINYIN_FULL = "ik_max_word_pinyin_full";

    /**
     * ik_max_word + 拼音简拼 + 繁简转换
     */
    public static final String IK_MAX_WORD_PINYIN_SIMPLE = "ik_max_word_pinyin_simple";

    /**
     * ik_smart + 拼音全拼 + 繁简转换
     */
    public static final String IK_SMART_PINYIN_FULL = "ik_smart_pinyin_full";

    /**
     * ik_smart + 拼音简拼 + 繁简转换
     */
    public static final String IK_SMART_PINYIN_SIMPLE = "ik_smart_pinyin_simple";

    /**
     * ik_max_word + 拼音全拼 + 中文停用词过滤 + 繁简转换
     */
    public static final String IK_MAX_WORD_PINYIN_FULL_STOP = "ik_max_word_pinyin_full_stop";

    /**
     * ik_max_word + 拼音简拼 + 中文停用词过滤 + 繁简转换
     */
    public static final String IK_MAX_WORD_PINYIN_SIMPLE_STOP = "ik_max_word_pinyin_simple_stop";

    /**
     * ik_smart + 拼音全拼 + 中文停用词过滤 + 繁简转换
     */
    public static final String IK_SMART_PINYIN_FULL_STOP = "ik_smart_pinyin_full_stop";

    /**
     * ik_smart + 拼音简拼 + 中文停用词过滤 + 繁简转换
     */
    public static final String IK_SMART_PINYIN_SIMPLE_STOP = "ik_smart_pinyin_simple_stop";

    /************************************************************************************************/

    /**
     * (内置) 标准分析器（standard analyzer）：
     * 是elasticsearch的默认分析器，该分析器综合了大多数欧洲语言来说合理的默认模块，
     * 包括标准分词器、标准分词过滤器、小写转换分词过滤器和停用词分词过滤器。
     */
    public static final String STANDARD = "standard";

    /**
     * (内置) 简单分析器（simple analyzer）：
     * 简单分析器仅使用了小写转换分词，这意味着在非字母处进行分词，并将分词自动转换为小写。
     * 这个分词器对于亚种语言来说效果不佳，因为亚洲语言不是根据空白来分词的，所以一般用于欧洲言中。
     */
    public static final String SIMPLE = "simple";

    /**
     * (内置) 空白（格）分析器（whitespace analyzer）：这玩意儿只是根据空白将文本切分为若干分词，真是有够偷懒！
     */
    public static final String WHITE = "whitespace";

    /**
     * (内置) 停用词分析（stop analyzer）和简单分析器的行为很像，只是在分词流中额外的过滤了停用词。
     * 这里的停用词是ES内置的英文停用词,要使用中文停用词,需要在config文件夹下放入停用词字典,并设置自定义分析器
     */
    public static final String STOP = "stop";

    /**
     * (内置) 关键词分析器（keyword analyzer）将整个字段当做单独的分词，如无必要，我们不在映射中使用关键词分析器。
     */
    public static final String KEYWORD = "keyword";

    /**
     * (内置) 雪球分析器（snowball analyzer）除了使用标准的分词和分词过滤器（和标准分析器一样）也是用了小写分词过滤器和停用词过滤器，
     * 除此之外，它还是用了雪球词干器对文本进行词干提取。
     */
    public static final String SNOWBALL = "snowball";

    /**
     * (内置) 用户自定义分析器
     */
    public static final String CUSTOM = "custom";

    /**
     * (内置) 语言和多语言分析器
     * elasticsearch为很多世界流行语言提供良好的、简单的、开箱即用的语言分析器集合：
     * 阿拉伯语、亚美尼亚语、巴斯克语、巴西语、保加利亚语、加泰罗尼亚语、中文、捷克语、丹麦、荷兰语、英语、芬兰语、法语、
     * 加里西亚语、德语、希腊语、北印度语、匈牙利语、印度尼西亚、爱尔兰语、意大利语、日语、韩国语、库尔德语、挪威语、波斯语、
     * 葡萄牙语、罗马尼亚语、俄语、西班牙语、瑞典语、土耳其语和泰语。
     *
     * 我们可以指定其中之一的语言来指定特定的语言分析器，但必须是小写的名字！如果你要分析的语言不在上述集合中，可能还需要搭配相应的插件支持。
     * 例子:
     *
     * 法语
     * {
     *   "analyzer": "french",
     *   "text":"Je suis ton père"
     * }
     *
     * 德语
     * {
     *   "analyzer": "german",
     *   "text":"Ich bin dein vater"
     * }
     */
}
