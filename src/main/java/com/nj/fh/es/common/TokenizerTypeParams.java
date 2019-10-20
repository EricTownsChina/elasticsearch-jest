package com.nj.fh.es.common;

/**
 * 分词参数,包含ik_max_word和ik_smart
 * es内置了一些分析器analyzer,里面包含一些分词方式
 * 可以使用内置analyzer,请直接到AnalyzerTypeParams中使用
 * @author 赵元路 18358572500 2019/9/26 11:02
 * @version 1.0
 */
public class TokenizerTypeParams {

    /**
     * 私有化构造
     */
    private TokenizerTypeParams() {}

    /**
     * 常量字段 IK中文分词器的ik_smart分词方式
     * ik_smart分词方式将text内容分成尽量长的词语
     * 例子:
     * {
     * 	"analyzer":"ik_smart",
     * 	"text":"中华人民共和国"
     * }
     * 结果为
     * {
     *     "tokens": [
     *         {
     *             "token": "中华人民共和国",
     *             "start_offset": 0,
     *             "end_offset": 7,
     *             "type": "CN_WORD",
     *             "position": 0
     *         }
     *     ]
     * }
     */
    public static final String IK_SMART = "ik_smart";

    /**
     * 常量字段 IK中文分词器的ik_max_word分词方式
     * ik_max_word分词方式将text文本分成尽量短的中文词语
     * 例子:
     * [中华人民共和国]
     * ⇒
     * {
     *     "tokens": [
     *         {
     *             "token": "中华人民共和国",
     *             "start_offset": 0,
     *             "end_offset": 7,
     *             "type": "CN_WORD",
     *             "position": 0
     *         },
     *         {
     *             "token": "中华人民",
     *             "start_offset": 0,
     *             "end_offset": 4,
     *             "type": "CN_WORD",
     *             "position": 1
     *         },
     *         {
     *             "token": "中华",
     *             "start_offset": 0,
     *             "end_offset": 2,
     *             "type": "CN_WORD",
     *             "position": 2
     *         },
     *         {
     *             "token": "华人",
     *             "start_offset": 1,
     *             "end_offset": 3,
     *             "type": "CN_WORD",
     *             "position": 3
     *         },
     *         {
     *             "token": "人民共和国",
     *             "start_offset": 2,
     *             "end_offset": 7,
     *             "type": "CN_WORD",
     *             "position": 4
     *         },
     *         {
     *             "token": "人民",
     *             "start_offset": 2,
     *             "end_offset": 4,
     *             "type": "CN_WORD",
     *             "position": 5
     *         },
     *         {
     *             "token": "共和国",
     *             "start_offset": 4,
     *             "end_offset": 7,
     *             "type": "CN_WORD",
     *             "position": 6
     *         },
     *         {
     *             "token": "共和",
     *             "start_offset": 4,
     *             "end_offset": 6,
     *             "type": "CN_WORD",
     *             "position": 7
     *         },
     *         {
     *             "token": "国",
     *             "start_offset": 6,
     *             "end_offset": 7,
     *             "type": "CN_CHAR",
     *             "position": 8
     *         }
     *     ]
     * }
     */
    public static final String IK_MAX_WORD = "ik_max_word";

}
