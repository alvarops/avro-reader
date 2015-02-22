package ie.alvarops.github.avroreader;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ie.alvarops.github.avroreader.schema.SchemaProvider;

import org.junit.Before;
import org.mockito.Mock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AvroReaderMainTest extends BaseTest {
  @Mock
  private AnnotationConfigApplicationContext applicationContextMock;
  @Mock
  private SchemaProvider schemaProviderMock;
  private AvroReaderMain avroReaderMain;

  @Before
  public void setup() {
    when(applicationContextMock.getBean(eq(SchemaProvider.class))).thenReturn(schemaProviderMock);
    avroReaderMain = new AvroReaderMain(applicationContextMock);
  }

}
