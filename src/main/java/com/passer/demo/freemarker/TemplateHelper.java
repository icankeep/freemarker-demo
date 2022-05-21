package com.passer.demo.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author passer
 * @time 2022/5/21 16:56
 */
public class TemplateHelper {

    private static final String TEMPLATE_DIR = "templates";

    private static final AtomicBoolean init = new AtomicBoolean(false);

    private static Configuration conf;

    private static synchronized void init() throws IOException {
        if (!init.get()) {
            conf = new Configuration(Configuration.VERSION_2_3_22);

            final String classpath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResource("")).getPath();
            final String templatePath = Paths.get(classpath, TEMPLATE_DIR).toString();
            conf.setDirectoryForTemplateLoading(new File(templatePath));
            conf.setDefaultEncoding(StandardCharsets.UTF_8.toString());
            conf.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            init.set(true);
        }
    }

    public static String populateTemplateFile(String templateFilename, Object obj) {
        try {
            if (!init.get()) {
                init();
            }
            final Template template = conf.getTemplate(templateFilename);
            final StringWriter sw = new StringWriter();
            template.process(obj, sw);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String populateTemplateString(String name, String templateStr, Object obj) {
        try {
            if (!init.get()) {
                init();
            }
            final Template template = new Template(name, templateStr, conf);
            final StringWriter sw = new StringWriter();
            template.process(obj, sw);

            final Template template2 = new Template(name, templateStr, conf);
            System.out.println(template.equals(template2));
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
