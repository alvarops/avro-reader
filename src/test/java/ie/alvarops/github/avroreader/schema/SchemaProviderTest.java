package ie.alvarops.github.avroreader.schema;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ie.alvarops.github.avroreader.BaseTest;
import ie.alvarops.github.avroreader.reader.DataFileReaderFactory;

import java.io.IOException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.io.DatumReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SchemaProviderTest extends BaseTest {
  @Mock
  private DataFileReaderFactory dataFileReaderFactoryMock;
  @Mock
  private DataFileReader dataFileReaderMock;
  @Mock
  private SeekableFileInput inputStreamMock;
  private SchemaProvider schemaProvider;

  @Before
  public void initialiseParser() throws IOException {
    when(dataFileReaderFactoryMock.getGenericRecords(any(DatumReader.class))).thenReturn(dataFileReaderMock);
    schemaProvider = new SchemaProvider(dataFileReaderFactoryMock);
  }

  @Test
  public void whenIParseAString_thenIGetTheStringSchema() throws IOException {
    schemaProvider.getSchema();
    verify(dataFileReaderFactoryMock).getGenericRecords(any(DatumReader.class));
    verify(dataFileReaderMock).getSchema();
  }

}
