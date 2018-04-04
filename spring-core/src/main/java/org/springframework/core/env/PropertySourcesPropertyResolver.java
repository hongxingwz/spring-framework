package org.springframework.core.env;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public class PropertySourcesPropertyResolver implements ConfigurablePropertyResolver{

    private final PropertySources propertySources;

    public PropertySourcesPropertyResolver(PropertySources propertySources) {
        this.propertySources = propertySources;
    }

    @Override
    public ConfigurablePropertyResolver getConversionService() {
        return null;
    }

    @Override
    public void setPlaceholderPrefix(String placeholderPrefix) {

    }

    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {

    }

    @Override
    public void setValueSeparator(String valueSeparator) {

    }

    @Override
    public void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders) {

    }

    @Override
    public void setRequiredProperties(String... requiredProperties) {

    }

    @Override
    public void validateRequiredProperties() throws MissingRequiredPropertiesException {

    }

    @Override
    public boolean containsProperty(String key) {
        return false;
    }

    @Override
    public String getProperty(String key) {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return null;
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return null;
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return null;
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return null;
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return null;
    }

    @Override
    public String resolvePlaceholders(String text) {
        return null;
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return null;
    }
}
