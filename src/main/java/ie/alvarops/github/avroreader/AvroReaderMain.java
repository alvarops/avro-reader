package ie.alvarops.github.avroreader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AvroReaderMain {
  private final AnnotationConfigApplicationContext applicationContext;

  public AvroReaderMain(final AnnotationConfigApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
    this.applicationContext.scan("ie.alvarops.github.avroreader");
    this.applicationContext.refresh();
  }
}
