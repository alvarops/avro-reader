package ie.alvarops.github.avroreader.reader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import ie.alvarops.github.avroreader.BaseTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AvroReaderTest extends BaseTest {
  @Mock
  private AvroFileReader avroFileReaderMock;
  private DataFileReaderFactory dataFileReaderFactory;
  private AvroReader avroReader;

  @Before
  public void initialiseAvroReader() throws IOException {
    when(avroFileReaderMock.getSeekableFileInput()).thenReturn(generateAvroData());
    dataFileReaderFactory = new DataFileReaderFactory(avroFileReaderMock);
    this.avroReader = new AvroReader(SCHEMA, dataFileReaderFactory);
  }

  @Test
  public void whenIReadAnAvroFile_thenIGetTheJsonString() throws IOException {
    String json = avroReader.toJson();
    assertThat(json,
        is(equalTo("{\"name\": \"Alyssa\", \"favorite_number\": 256, \"favorite_color\": null}\n{\"name\": \"Ben\", \"favorite_number\": 7, \"favorite_color\": \"red\"}\n")));
  }

  private SeekableByteArrayInput generateAvroData() throws IOException {
    final GenericRecord user1 = createUser("Alyssa", 256, null);
    final GenericRecord user2 = createUser("Ben", 7, "red");
    return createTestFile(user1, user2);
  }

  private GenericRecord createUser(final String name, final int favoriteNumber, final String colour) {
    GenericRecord user = new GenericData.Record(SCHEMA);
    user.put("name", name);
    user.put("favorite_number", favoriteNumber);
    user.put("favorite_color", colour);
    return user;
  }

  private SeekableByteArrayInput createTestFile(final GenericRecord... users) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(SCHEMA);
    DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
    dataFileWriter.create(SCHEMA, outputStream);
    for (final GenericRecord user : users) {
      dataFileWriter.append(user);
    }
    dataFileWriter.close();
    return new SeekableByteArrayInput(outputStream.toByteArray());
  }
}
