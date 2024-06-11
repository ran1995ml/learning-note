# Jackson

## 常用注解

### @JsonProperty

将对象的字段和 `JSON` 数据中的属性进行映射，在序列化和反序列化中发挥作用。

### @JsonSerialize

自定义对象序列化为 `JSON` 时的行为，需要继承 `JsonSerializer`。

```java
public class EventMapSerializer extends JsonSerializer<EventMap>
{
  @Override
  public void serialize(EventMap map, JsonGenerator gen, SerializerProvider serializers) throws IOException
  {
    //开始写对象，生成{
    gen.writeStartObject();
    //生成对象的字段
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      gen.writeObjectField(entry.getKey(), entry.getValue());
    }
    //结束写对象，生成}
    gen.writeEndObject();
  }
}
```

