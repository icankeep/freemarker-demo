package com.passer.demo.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author passer
 * @time 2022/5/21 15:24
 */
public class FreeMarkerDemo {

    private static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    }


    public static Template getTemplateFromResource(String filename) throws IOException {
        return cfg.getTemplate(filename);
    }

    public static Template getTemplateFromString(String content) throws IOException {
        return new Template("strTpl", "您好${name}，晚上好！您目前余额：${money?string(\"#.##\")}元，积分：${point}", cfg);
    }

    public static void main(String[] args) {
        File file = new File("/Users/passer/dev/code/gs-caching/freemarker-demo/target/classes/templates");
        System.out.println(file.exists());;

        try {
            final String s = FreeMarkerDemo.class.getResource("/").getPath() + "/templates";
            System.out.println(s);
            cfg.setDirectoryForTemplateLoading(new File(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Map<String, String> map = new HashMap<>();
            map.put("sss_tt", "张三");
            map.put("tt_sss", "hsllsd");
            map.put("tt", "sssssss");
            Template template = getTemplateFromResource("test.ftl");
            StringWriter result = new StringWriter();
            template.process(map, result);
            System.out.println(result.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
