package ie.alvarops.github.avroreader.schema;

import ie.alvarops.github.avroreader.reader.DataFileReaderFactory;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SchemaProvider {
  private final DataFileReaderFactory dataFileReaderFactory;

  @Autowired
  public SchemaProvider(final DataFileReaderFactory dataFileReaderFactory) {
    this.dataFileReaderFactory = dataFileReaderFactory;
  }

  @Bean
  Schema getSchema() throws IOException {
    final DataFileReader<GenericRecord> dataFileReader = dataFileReaderFactory.getGenericRecords(new GenericDatumReader<GenericRecord>());
    return dataFileReader.getSchema();
  }

}
