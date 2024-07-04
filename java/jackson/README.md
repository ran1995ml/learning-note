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

### @JsonTypeInfo

指定 `JSON` 序列化反序列化如何指定类型信息 

```java
//用名称识别类型，类型信息存储在JSON对象的type字段
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
```

### @JsonSubTypes

指定接口的子类型和它们的类型名称

```java
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "loadByPeriod", value = PeriodLoadRule.class),
    @JsonSubTypes.Type(name = "loadByInterval", value = IntervalLoadRule.class),
    @JsonSubTypes.Type(name = PeriodBroadcastDistributionRule.TYPE, value = PeriodBroadcastDistributionRule.class)
})
```

和 `@JsonTypeInfo` 配合使用，`name` 用于 `JSON` 中的 `type` 属性，`value` 对应子类型的类。

### @JsonCreator

指定 `Jackson` 在反序列化时使用的构造方法或工厂方法
