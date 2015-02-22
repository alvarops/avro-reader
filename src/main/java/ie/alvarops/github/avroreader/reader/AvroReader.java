package ie.alvarops.github.avroreader.reader;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvroReader {
  private final Schema schema;
  private final AvroFileReader avroFileReader;

  @Autowired
  public AvroReader(final Schema schema, final AvroFileReader avroFileReader) {
    this.schema = schema;
    this.avroFileReader = avroFileReader;
  }

  public String toJson() throws IOException {
    final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
    final DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(avroFileReader.getSeekableFileInput(), datumReader);
    final StringBuilder stringBuilder = new StringBuilder();
    GenericRecord user = null;
    while (dataFileReader.hasNext()) {
      user = dataFileReader.next(user);
      stringBuilder.append(user.toString()).append('\n');
    }
    return stringBuilder.toString();
  }
}
