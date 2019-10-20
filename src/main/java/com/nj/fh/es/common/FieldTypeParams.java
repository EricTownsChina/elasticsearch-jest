package com.nj.fh.es.common;

/**
 * 字段类型常量
 * 该类中的常量可以用在自定义mappings设置时
 * @author 赵元路 18358572500 2019/9/9 19:48
 * @version 1.0
 */
public final class FieldTypeParams {

    /**
     * 私有化构造
     */
    private FieldTypeParams() {
    }

    /**
     * 常量字段 当一个字段需要用于全文搜索(会被分词), 比如产品名称、产品描述信息, 就应该使用text类型.
     */
    public static final String TEXT = "text";

    /**
     * 常量字段 keyword的内容不会被分词
     */
    public static final String KEYWORD = "keyword";

    /**
     * 常量字段 有符号的8位整数, 范围: [-128 ~ 127]
     */
    public static final String BYTE = "byte";

    /**
     * 常量字段 有符号的16位整数, 范围: [-32768 ~ 32767]
     */
    public static final String SHORT = "short";

    /**
     * 常量字段 有符号的32位整数, 范围: [$-2^{31}$ ~ $2^{31}$-1]
     */
    public static final String INTEGER = "integer";

    /**
     * 常量字段 有符号的32位整数, 范围: [$-2^{63}$ ~ $2^{63}$-1]
     */
    public static final String LONG = "long";

    /**
     * 常量字段 32位单精度浮点数
     */
    public static final String FLOAT = "float";

    /**
     * 常量字段 64位双精度浮点数
     */
    public static final String DOUBLE = "double";

    /**
     * 常量字段 JSON没有日期数据类型, 所以在ES中, 日期可以是:
     * 1.包含格式化日期的字符串, "2018-10-01", 或"2018/10/01 12:10:30".
     * 2.代表时间毫秒数的长整型数字.
     * 3.代表时间秒数的整数.
     *
     * 例子:
     * "date": {
     *    "type": "date",  // 可以接受如下类型的格式
     *    "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
     * }
     */
    public static final String DATE = "date";

    /**
     * 常量字段 可以接受表示真、假的字符串或数字:
     * 真值: true, "true", "on", "yes", "1"...
     * 假值: false, "false", "off", "no", "0", ""(空字符串), 0.0, 0
     */
    public static final String BOOLEAN = "boolean";

    /**
     * 常量字段 二进制类型是Base64编码字符串的二进制值, 不以默认的方式存储, 且不能被搜索.
     * Base64编码的二进制值不能嵌入换行符\n
     */
    public static final String BINARY = "binary";

    /**
     * 常量字段 IP类型的字段用于存储IPv4或IPv6的地址, 本质上是一个长整型字段.
     */
    public static final String IP = "ip";

    public static final String INTEGER_RANGE = "integer_range";
    public static final String LONG_RANGE = "long_range";
    public static final String FLOAT_RANGE = "float_range";
    public static final String DOUBLE_RANGE = "double_range";
    public static final String DATE_RANGE = "date_range";
    public static final String IP_RANGE = "ip_range";
}
