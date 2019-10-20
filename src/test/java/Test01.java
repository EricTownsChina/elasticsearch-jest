import com.nj.fh.es.client.ClientUtils;
import com.nj.fh.es.common.AnalyzerTypeParams;
import com.nj.fh.es.common.FieldTypeParams;
import com.nj.fh.es.index.CreateIndex;
import com.nj.fh.es.index.UpdateIndex;
import com.nj.fh.es.mappings.MappingsField;
import com.nj.fh.es.settings.Settings;
import com.nj.fh.es.settings.SettingsTemplate;
import io.searchbox.client.JestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 赵元路 18358572500 2019/9/24 15:26
 * @version 1.0
 */
public class Test01 {

    public static void main(String[] args) {
//
//        createWithMappings();
//        updateSettings(client);
//        putIndexMappings(client);
        //获得连接
        JestClient client = ClientUtils.getClient();
        //关闭连接
        ClientUtils.closeClient(client);
        System.out.println(Integer.MAX_VALUE);
    }

    private static void createByMappings(JestClient client) {
        MappingsField idField = new MappingsField();
        idField.setFieldName("id");
        idField.setFieldType(FieldTypeParams.INTEGER);
        idField.setIndex(false);

        MappingsField docnoField = new MappingsField();
        docnoField.setFieldName("docno");
        docnoField.setFieldType(FieldTypeParams.KEYWORD);
        docnoField.setIndex(false);

        MappingsField urlField = new MappingsField();
        urlField.setFieldName("url");
        urlField.setFieldType(FieldTypeParams.KEYWORD);
        urlField.setIndex(false);

        MappingsField contentField = new MappingsField();
        contentField.setFieldName("content");
        contentField.setFieldType(FieldTypeParams.TEXT);
        contentField.setAnalyzer(AnalyzerTypeParams.IK_MAX_WORD);

        MappingsField contenttitleField = new MappingsField();
        contenttitleField.setFieldName("contenttitle");
        contenttitleField.setFieldType(FieldTypeParams.TEXT);
        contenttitleField.setAnalyzer(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_FULL_STOP);

        CreateIndex.createOneIndex(
                client,
                "test_create_03",
                Arrays.asList(idField, docnoField, contentField, contenttitleField, urlField));
    }

    private static void putIndexMappings(JestClient client) {
        /**
         * 创建各个字段及设置
         */
        MappingsField idField = new MappingsField();
        idField.setFieldName("id");
        idField.setFieldType(FieldTypeParams.INTEGER);
        idField.setIndex(false);

        MappingsField docnoField = new MappingsField();
        docnoField.setFieldName("docno");
        docnoField.setFieldType(FieldTypeParams.KEYWORD);
        docnoField.setIndex(false);

        MappingsField urlField = new MappingsField();
        urlField.setFieldName("url");
        urlField.setFieldType(FieldTypeParams.KEYWORD);
        urlField.setIndex(false);

        MappingsField contentField = new MappingsField();
        contentField.setFieldName("content");
        contentField.setFieldType(FieldTypeParams.TEXT);
        contentField.setAnalyzer(AnalyzerTypeParams.IK_MAX_WORD);

        MappingsField contenttitleField = new MappingsField();
        contenttitleField.setFieldName("contenttitle");
        contenttitleField.setFieldType(FieldTypeParams.TEXT);
        contenttitleField.setAnalyzer(AnalyzerTypeParams.IK_MAX_WORD_PINYIN_FULL_STOP);

        /**
         * 添加mappings设置
         */
        Boolean putResult = UpdateIndex.putIndexMapping(
                client,
                "test_create_01",
                Arrays.asList(idField, docnoField, urlField, contentField, contenttitleField));
        System.out.println(putResult);
    }

    private static void updateSettings(JestClient client) {
        /**
         * 修改索引的settings设置
         */
        Settings defaultSettings = SettingsTemplate.getDefaultSettings();
        defaultSettings.setNumber_of_shards(3);
        Boolean updateResult = UpdateIndex.updateIndexSettings(client, "test_create_01", defaultSettings);
        System.out.println(updateResult);
    }

    private static void createWithMappings() {
        try {
            //获得连接
            JestClient client = ClientUtils.getClient();

            //字段1
            MappingsField nameField = new MappingsField();
            nameField.setFieldName("name");
            nameField.setFieldType(FieldTypeParams.TEXT);
//            nameField.setAnalyzer(AnalyzerTypeParams.IK_MAX_WORD_CHINESE_STOP);

            //字段2
            MappingsField createField = new MappingsField();
            createField.setFieldName("createTime");
            createField.setFieldType(FieldTypeParams.DATE);
            createField.setFormat(Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy_MM_dd-HH:mm:ss"));

            //放入集合
            List<MappingsField> fields = new ArrayList<>();
            fields.add(nameField);
            fields.add(createField);

            //创建索引
            Boolean result = CreateIndex.createOneIndex(client, "test_create_02", fields);
            System.out.println(result);

            //关闭连接
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createMyOwnIndex() {
        String settings = new String("{\n" +
                "        \"number_of_shards\": \"5\",\n" +
                "        \"number_of_replicas\": \"1\",\n" +
                "        \"analysis\": {\n" +
                "            \"filter\": {\n" +
                "                \"chinese_stop\": {\n" +
                "                    \"type\": \"stop\",\n" +
                "                    \"stopwords_path\": \"stopwords/ik_chinese.txt\"\n" +
                "                },\n" +
                "                \"pinyin_simple\": {\n" +
                "                    \"lowercase\": \"true\",\n" +
                "                    \"keep_origin\": \"false\",\n" +
                "                    \"keep_first_letter\": true,\n" +
                "                    \"keep_separate_first_letter\": false,\n" +
                "                    \"type\": \"pinyin\",\n" +
                "                    \"limit_first_letter_length\": \"32\",\n" +
                "                    \"keep_full_pinyin\": \"false\",\n" +
                "                    \"remove_duplicated_term\" : true\n" +
                "                },\n" +
                "                \"pinyin_full\": {\n" +
                "                    \"lowercase\": \"true\",\n" +
                "                    \"keep_origin\": \"false\",\n" +
                "                    \"keep_first_letter\": false,\n" +
                "                    \"keep_separate_first_letter\": false,\n" +
                "                    \"type\": \"pinyin\",\n" +
                "                    \"limit_first_letter_length\": \"32\",\n" +
                "                    \"keep_full_pinyin\": \"true\",\n" +
                "                    \"keep_none_chinese_together\": true,\n" +
                "                    \"none_chinese_pinyin_tokenize\": true,\n" +
                "                    \"remove_duplicated_term\" : true\n" +
                "                },\n" +
                "                \"edge_ngram\": {\n" +
                "                  \"type\": \"edge_ngram\",\n" +
                "                  \"min_gram\": 1,\n" +
                "                  \"max_gram\": 50\n" +
                "                }\n" +
                "            },\n" +
                "            \"char_filter\" : {\n" +
                "                \"tsconvert\" : {\n" +
                "                    \"type\" : \"stconvert\",\n" +
                "                    \"convert_type\" : \"t2s\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"analyzer\": {\n" +
                "                \"ngram\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"tokenizer\": \"keyword\",\n" +
                "                    \"filter\": [\"edge_ngram\", \"lowercase\"],\n" +
                "                    \"char_filter\": [\"tsconvert\"]\n" +
                "                },\n" +
                "                \"ik_max_word\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_smart\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                },\n" +
                "                \"ik_max_word_stop\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"filter\": [\"chinese_stop\"],\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_smart_stop\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"filter\": [\"chinese_stop\"],\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                },\n" +
                "                \"ik_max_word_pinyin_full\": {\n" +
                "                    \"filter\": [\"pinyin_full\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_max_word_pinyin_simple\": {\n" +
                "                    \"filter\": [\"pinyin_simple\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_smart_pinyin_full\": {\n" +
                "                    \"filter\": [\"pinyin_full\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                },\n" +
                "                \"ik_smart_pinyin_simple\": {\n" +
                "                    \"filter\": [\"pinyin_simple\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                },\n" +
                "                \"ik_max_word_pinyin_full_stop\": {\n" +
                "                    \"filter\": [\"pinyin_full\", \"chinese_stop\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_max_word_pinyin_simple_stop\": {\n" +
                "                    \"filter\": [\"chinese_stop\", \"pinyin_simple\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_max_word\"\n" +
                "                },\n" +
                "                \"ik_smart_pinyin_full_stop\": {\n" +
                "                    \"filter\": [\"pinyin_full\", \"chinese_stop\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                },\n" +
                "                \"ik_smart_pinyin_simple_stop\": {\n" +
                "                    \"filter\": [\"chinese_stop\", \"pinyin_simple\"],\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"char_filter\": [\"tsconvert\"],\n" +
                "                    \"tokenizer\": \"ik_smart\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }");
        String mappings = new String("{\n" +
                "\"properties\": {\n" +
                "    \"url\": {\n" +
                "      \"type\": \"text\",\n" +
                "\t  \"index\": \"false\"\n" +
                "    },\n" +
                "\t\"docno\": {\n" +
                "      \"type\": \"text\",\n" +
                "\t  \"index\": \"false\"\n" +
                "    },\n" +
                "    \"contenttitle\": {\n" +
                "      \"type\": \"text\", \n" +
                "      \"analyzer\": \"ik_max_word\",\n" +
                "      \"fields\": {\n" +
                "        \"N\": {\n" +
                "          \"type\": \"text\", \n" +
                "          \"analyzer\": \"ngram\"\n" +
                "        },\n" +
                "        \"MPFS\": {\n" +
                "          \"type\": \"text\", \n" +
                "          \"analyzer\": \"ik_max_word_pinyin_full_stop\"\n" +
                "        },\n" +
                "        \"MPSS\": {\n" +
                "          \"type\": \"text\", \n" +
                "          \"analyzer\": \"ik_max_word_pinyin_simple_stop\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"content\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"ik_max_word\",\n" +
                "      \"fields\": {\n" +
                "        \"S\": {\n" +
                "          \"type\": \"text\",\n" +
                "          \"analyzer\": \"ik_smart\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");

        JestClient client = ClientUtils.getClient();
        if ( CreateIndex.createOneIndex(client, "test2222222", settings)) {
            System.out.println("success");
        }
        if (CreateIndex.createOneIndex(client, "news-sim_index", settings, mappings)) {
            System.out.println("success");
        }
    }
}
