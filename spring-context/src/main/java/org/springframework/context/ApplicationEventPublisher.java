package org.springframework.context;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

    void publishEvent(Object event);
}
