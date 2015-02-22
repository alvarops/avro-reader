package ie.alvarops.github.avroreader.reader;

import java.io.File;
import java.io.IOException;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.file.SeekableInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AvroFileReader {
  private final String filePath;

  @Autowired
  public AvroFileReader(final Environment environment) {
    this.filePath = environment.getProperty("avro.path");
  }

  public SeekableInput getSeekableFileInput() throws IOException {
    return new SeekableFileInput(new File(filePath));
  }
}
