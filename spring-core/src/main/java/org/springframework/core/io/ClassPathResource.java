package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jianglei
 * @since 2018/4/20
 */
// TODO: 2018/4/20 没完玩
public class ClassPathResource extends AbstractFileResolvingResource{

    private final String path;

    private ClassLoader classLoader;

    private Class<?> clazz;


    public ClassPathResource(String path, Class<?> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
