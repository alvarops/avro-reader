package ie.alvarops.github.avroreader.reader;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvroReader {
  private final Schema schema;
  private final DataFileReaderFactory dataFileReaderFactory;

  @Autowired
  public AvroReader(final Schema schema, final DataFileReaderFactory dataFileReaderFactory) {
    this.schema = schema;
    this.dataFileReaderFactory = dataFileReaderFactory;
  }

  public String toJson() throws IOException {
    final DataFileReader<GenericRecord> dataFileReader = initDataFileReader();
    final StringBuilder stringBuilder = new StringBuilder();
    GenericRecord user = null;
    while (dataFileReader.hasNext()) {
      user = dataFileReader.next(user);
      stringBuilder.append(user.toString()).append('\n');
    }
    return stringBuilder.toString();
  }

  private DataFileReader<GenericRecord> initDataFileReader() throws IOException {
    return dataFileReaderFactory.getGenericRecords(new GenericDatumReader<GenericRecord>(schema));
  }
}
