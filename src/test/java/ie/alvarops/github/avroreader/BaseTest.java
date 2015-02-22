package ie.alvarops.github.avroreader;

import static org.mockito.MockitoAnnotations.initMocks;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.junit.Before;

public class BaseTest {
  protected static final Schema SCHEMA = generateSchema();
  protected static final String SCHEMA_STRING = "{\"namespace\": \"example.avro\",\n"
      + " \"type\": \"record\",\n"
      + " \"name\": \"User\",\n"
      + " \"fields\": [\n"
      + "     {\"name\": \"name\", \"type\": \"string\"},\n"
      + "     {\"name\": \"favorite_number\",  \"type\": [\"int\", \"null\"]},\n"
      + "     {\"name\": \"favorite_color\", \"type\": [\"string\", \"null\"]}\n"
      + " ]\n"
      + "}";

  @Before
  public void initialise() {
    initMocks(this);
  }

  private static Schema generateSchema() {
    return new Parser().parse(SCHEMA_STRING);
  }
}
