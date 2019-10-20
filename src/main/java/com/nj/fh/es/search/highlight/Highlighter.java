package com.nj.fh.es.search.highlight;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * @author 赵元路 18358572500 2019/9/19 16:04
 * @version 1.0
 */
public class Highlighter {

    private Highlighter() {}

    /**
     * 字段名数组
     */
    private static String[] fields;

    /**
     * 创建高亮生成器
     * @param pre 高亮部分的前缀html
     * @param post 高亮部分的后缀html
     * @param size 高亮字段的长度
     * @param fieldNames 可变数组 字段名称
     * @return 高亮生成器,可作为SearchSourceBuilder对象highlighter()方法的参数
     */
    public static HighlightBuilder createHighlighter(String pre, String post, Integer size, String... fieldNames) {
        fields = fieldNames;
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder
                .preTags(pre)
                .postTags(post)
                .fragmentSize(size);
        for (String s : fields) {
            highlightBuilder.field(s);
        }
        return highlightBuilder;
    }
}
