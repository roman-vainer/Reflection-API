package org.example.jsonmapper;


import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class JsonConverterTest {

  @Test
  @SneakyThrows
  void checkCorrectCreatedJson() {
    Object object = prepare();
    String expectedJson = """
        {
        \t"name": "TestUser",
        \t"age": 33,
        \t"address": {
        \t\t"street": "TestStreet",
        \t\t"number": 11
        \t}
        }""";
    JsonConverter converter = new JsonConverter();
    assertEquals(expectedJson, converter.convertObjectToJason(object));
  }

  private Object prepare() {
    record Address(String street, int number) {

    }
    ;
    record User(String name, int age, Address address) {

    }
    ;
    Address address = new Address("TestStreet", 11);
    return new User("TestUser", 33, address);
  }
}