package ie.alvarops.github.avroreader.reader;

import ie.alvarops.github.avroreader.BaseTest;

import org.junit.Before;
import org.junit.Test;

public class AvroReaderTest extends BaseTest {
  private AvroReader avroReader;

  @Before
  public void initialiseAvroReader() {
    this.avroReader = new AvroReader();
  }

  @Test
  public void whenIReadAnAvroFile_thenIGetTheJsonString() {

  }
}
