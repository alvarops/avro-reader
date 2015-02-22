package ie.alvarops.github.avroreader.schema;

import static org.mockito.Mockito.when;

import ie.alvarops.github.avroreader.BaseTest;
import ie.alvarops.github.avroreader.reader.AvroFileReader;

import java.io.IOException;
import org.apache.avro.file.SeekableFileInput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SchemaProviderTest extends BaseTest {

  @Mock
  private AvroFileReader schemaFileReaderMock;
  @Mock
  private SeekableFileInput inputStreamMock;
  private SchemaProvider schemaProvider;

  @Before
  public void initialiseParser() throws IOException {
    when(schemaFileReaderMock.getSeekableFileInput()).thenReturn(inputStreamMock);

    schemaProvider = new SchemaProvider(schemaFileReaderMock);
  }

  @Test
  public void whenIParseAString_thenIGetTheStringSchema() throws IOException {
//    final Schema schema = schemaProvider.getSchema();
//    assertThat(schema, is(equalTo(SCHEMA)));
  }

}
