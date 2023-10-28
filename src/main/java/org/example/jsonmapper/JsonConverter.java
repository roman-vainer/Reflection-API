package org.example.jsonmapper;

import lombok.SneakyThrows;

public class JsonConverter {

  private final StringBuilder builder = new StringBuilder();
  private int tabs;

  @SneakyThrows
  public String convertObjectToJason(Object obj) {
    tabs++;
    builder.append("{\n");
    var fields = obj.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      var field = fields[i];
      field.setAccessible(true);
      var name = field.getName();
      var value = field.get(obj);
      builder
          .append("\t".repeat(tabs))
          .append("\"")
          .append(name)
          .append("\"")
          .append(": ");
      if (value instanceof Number) {
        builder.append(value);
      } else if (value instanceof String) {
        builder.append("\"").append(value).append("\"");
      } else {
        convertObjectToJason(value);
      }
      if (i < fields.length - 1) {
        builder.append(",");
      }
      builder.append("\n");
    }
    builder.append("\t".repeat(--tabs)).append("}");
    return builder.toString();
  }
}
