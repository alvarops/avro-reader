package ie.alvarops.github.avroreader.schema;

import ie.alvarops.github.avroreader.reader.AvroFileReader;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SchemaProvider {
  private final AvroFileReader schemaFileReader;

  @Autowired
  public SchemaProvider(final AvroFileReader avroFileReader) {
    this.schemaFileReader = avroFileReader;
  }

  @Bean
  Schema getSchema() throws IOException {
    final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
    final DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(schemaFileReader.getSeekableFileInput(), datumReader);
    return dataFileReader.getSchema();
  }
}
