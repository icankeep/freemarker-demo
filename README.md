## 渲染模板文件
```java
final Map<String, String> map = new HashMap<String, String>() {{
    this.put("a1", "hello");
    this.put("a2", "passer");
    this.put("a3", "test");
}};
final String result = TemplateHelper.populateTemplateFile("test.ftl", map);
System.out.println(result);
```

## 渲染模板字符串
```java
final Map<String, String> map = new HashMap<String, String>() {{
    this.put("a1", "passer");
    this.put("a2", "hong");
    this.put("a3", "ming");
}};
final String result = TemplateHelper.populateTemplateString("test", "I'm ${a1}, you are ${a2}, but he is ${a3}", map);
System.out.println(result);
```