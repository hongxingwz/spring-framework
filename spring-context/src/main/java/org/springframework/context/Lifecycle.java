package org.springframework.context;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface Lifecycle {

    void start();

    void stop();

    boolean isRunning();
}
