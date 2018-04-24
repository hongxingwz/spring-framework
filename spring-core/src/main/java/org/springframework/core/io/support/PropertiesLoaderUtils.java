package org.springframework.core.io.support;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public abstract class PropertiesLoaderUtils {



    public static Properties loadProperties(Resource resource) throws IOException {
        Properties props = new Properties();

        return props;
    }
}
