package com.minlia.rocket.i18n.system;
//~--- non-JDK imports --------------------------------------------------------

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

//~--- JDK imports ------------------------------------------------------------

/**
 * The <code>MergedMessageSource</code> class implements the merged message source that provides
 * support for merging multiple message property files with the .properties suffix.
 *
 * @author Marcus Portmann
 */
public class MergedMessageSource
  extends ReloadableResourceBundleMessageSource
{
  private static final String PROPERTIES_SUFFIX = ".properties";
  private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

  @Override
  protected PropertiesHolder refreshProperties(String filename, PropertiesHolder propertiesHolder)
  {
    if (filename.startsWith(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX))
    {
      return refreshClassPathProperties(filename, propertiesHolder);
    }
    else
    {
      return super.refreshProperties(filename, propertiesHolder);
    }
  }

  private PropertiesHolder refreshClassPathProperties(String filename,
      PropertiesHolder propertiesHolder)
  {
    Properties properties = new Properties();
    long lastModified = -1;
    try
    {
      Resource[] resources = resolver.getResources(filename + PROPERTIES_SUFFIX);
      for (Resource resource : resources)
      {
        String resourcePath = resource.getURI().toString().replace(PROPERTIES_SUFFIX, "");
        PropertiesHolder resourcePropertiesHolder = super.refreshProperties(resourcePath,
            propertiesHolder);

        Properties resourceProperties = resourcePropertiesHolder.getProperties();

        if (resourceProperties != null)
        {
          properties.putAll(resourceProperties);
        }

        if (lastModified < resource.lastModified())
        {
          lastModified = resource.lastModified();
        }
      }
    }
    catch (IOException ignored) {}

    return new PropertiesHolder(properties, lastModified);
  }
}
