package ie.alvarops.github.avroreader.reader;

import java.io.IOException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataFileReaderFactory {

  private AvroFileReader schemaFileReader;

  @Autowired
  public DataFileReaderFactory(final AvroFileReader schemaFileReader) {
    this.schemaFileReader = schemaFileReader;
  }

  public DataFileReader<GenericRecord> getGenericRecords(final DatumReader<GenericRecord> datumReader) throws IOException {
    return new DataFileReader<>(schemaFileReader.getSeekableFileInput(), datumReader);
  }
}
