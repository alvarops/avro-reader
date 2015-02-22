package ie.alvarops.github.avroreader;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ie.alvarops.github.avroreader.schema.SchemaProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

public class AvroReaderMainTest extends BaseTest {
  @Mock
  private AnnotationConfigApplicationContext applicationContextMock;
  @Mock
  private ConfigurableEnvironment configurableEnvironmentMock;
  @Mock
  private MutablePropertySources propertySourcesMock;
  @Mock
  private SchemaProvider schemaProviderMock;
  private AvroReaderMain avroReaderMain;

  @Before
  public void setup() {
    when(applicationContextMock.getBean(eq(SchemaProvider.class))).thenReturn(schemaProviderMock);
    when(applicationContextMock.getEnvironment()).thenReturn(configurableEnvironmentMock);
    when(configurableEnvironmentMock.getPropertySources()).thenReturn(propertySourcesMock);
    avroReaderMain = new AvroReaderMain(applicationContextMock, "--avro.path=sample.avro");
  }

  @Test
  public void whenIStart_thenTheAppSetsTheEnvironmentProperly() {
    verify(propertySourcesMock).addFirst(any(PropertySource.class));
    verify(applicationContextMock).scan(eq("ie.alvarops.github.avroreader"));
    verify(applicationContextMock).refresh();
  }
}
