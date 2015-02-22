package ie.alvarops.github.avroreader;

import ie.alvarops.github.avroreader.reader.AvroReader;

import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class AvroReaderMain {
  private final AnnotationConfigApplicationContext applicationContext;
  private final AvroReader avroFileReader;

  public AvroReaderMain(final AnnotationConfigApplicationContext applicationContext, String... args) {
    final PropertySource propertySource = new SimpleCommandLinePropertySource(args);
    this.applicationContext = applicationContext;
    this.applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
    this.applicationContext.scan("ie.alvarops.github.avroreader");
    this.applicationContext.refresh();
    this.avroFileReader = this.applicationContext.getBean(AvroReader.class);
  }

  public static void main(String[] args) {
    new AvroReaderMain(new AnnotationConfigApplicationContext(), args).start();
  }

  private void start() {
    try {
      System.out.println(this.avroFileReader.toJson());
    } catch (IOException e) {
      System.out.println("Usage:java AvroReaderMain --avro.path=myavrofile.avro");
    }
  }
}
