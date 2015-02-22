package ie.alvarops.github.avroreader.reader;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import ie.alvarops.github.avroreader.BaseTest;

import org.junit.Before;
import org.junit.Test;

public class JsonReaderTest extends BaseTest {
  private JsonReader jsonReader;

  @Before
  public void initialiseAvroReader() {
    this.jsonReader = new JsonReader();
  }

  @Test
  public void whenIReadAJsonString_thenIGetTheAvroStream() {
    byte[] bytes = jsonReader.toAvro("{\"name\": \"Alyssa\", \"favorite_number\": 256, \"favorite_color\": null}");
    assertThat(bytes, is(equalTo("".getBytes())));
  }
}
