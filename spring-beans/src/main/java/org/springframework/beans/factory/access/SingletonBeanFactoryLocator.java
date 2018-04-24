package org.springframework.beans.factory.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public class SingletonBeanFactoryLocator implements BeanFactoryLocator {

    private static final String DEFAULT_RESOURCE_LOCATION = "classpath*:beanRefFactory.xml";

    protected static final Log logger = LogFactory.getLog(SingletonBeanFactoryLocator.class);

    private static final Map<String, BeanFactoryLocator> instances = new HashMap<>();

    private final Map<String, BeanFactoryGroup> bfgInstancesByKey = new HashMap<>();

    private final Map<BeanFactory, BeanFactoryGroup> bfgInstancesByObj = new HashMap<>();

    private final String resourceLocation;

    protected SingletonBeanFactoryLocator(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    @Override
    public BeanFactoryReference useBeanFactory(String factoryKey) throws BeansException {
        synchronized (this.bfgInstancesByKey) {
            BeanFactoryGroup bfg = this.bfgInstancesByKey.get(this.resourceLocation);
            if (bfg != null) {
                bfg.refCount++;
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Factory group with resource name [" + this.resourceLocation +
                        "] requested. Creating new instance.");


                }
            }
        }
    }

    private static class BeanFactoryGroup {

        private BeanFactory definition;

        private int refCount = 0;
    }

    private class CountingBeanFactoryReference implements BeanFactoryReference {

        private BeanFactory beanFactory;

        private BeanFactory groupContextRef;

        public CountingBeanFactoryReference(BeanFactory beanFactory, BeanFactory groupContextRef) {
            this.beanFactory = beanFactory;
            this.groupContextRef = groupContextRef;
        }

        @Override
        public BeanFactory getFactory() {
            return this.beanFactory;
        }

        @Override
        public void release() {

        }
    }



}
