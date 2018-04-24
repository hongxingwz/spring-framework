package org.springframework.context;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ApplicationContextInitializer<C extends ConfigurableApplicationContext> {

    void initialize(C applicationContext);
}
