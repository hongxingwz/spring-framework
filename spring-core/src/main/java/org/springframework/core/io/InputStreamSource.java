package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;
}
