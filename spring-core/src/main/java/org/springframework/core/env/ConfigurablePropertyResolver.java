package org.springframework.core.env;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public interface ConfigurablePropertyResolver extends PropertyResolver {

    ConfigurablePropertyResolver getConversionService();

    void setPlaceholderPrefix(String placeholderPrefix);

    void setPlaceholderSuffix(String placeholderSuffix);

    void setValueSeparator(String valueSeparator);

    void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders);

    void setRequiredProperties(String... requiredProperties);

    void validateRequiredProperties() throws MissingRequiredPropertiesException;
}
