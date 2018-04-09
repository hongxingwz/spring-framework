package org.springframework.core.env;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public class MapPropertySource extends EnumerablePropertySource<Map<String, Object>> {
    public MapPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }

    @Override
    public boolean containsProperty(String name) {
        return this.source.containsKey(name);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }


}
