package com.passer.demo.freemarker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author passer
 * @time 2022/5/21 17:44
 */
public class TemplateHelperTest {

    @Test
    public void testPopulateTemplateFile() {
        final Map<String, String> map = new HashMap<String, String>() {{
            this.put("a1", "hello");
            this.put("a2", "passer");
            this.put("a3", "test");
        }};
        final String result = TemplateHelper.populateTemplateFile("test.ftl", map);
        Assertions.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testPopulateTemplateString() {
        final Map<String, String> map = new HashMap<String, String>() {{
            this.put("a1", "passer");
            this.put("a2", "hong");
            this.put("a3", "ming");
        }};
        final String result = TemplateHelper.populateTemplateString("test", "I'm ${a1}, you are ${a2}, but he is ${a3}", map);
        Assertions.assertNotNull(result);
        System.out.println(result);
    }
}
