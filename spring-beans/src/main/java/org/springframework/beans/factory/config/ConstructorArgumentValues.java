package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public class ConstructorArgumentValues {

    private final Map<Integer, ValueHolder> indexedArgumentValues = new LinkedHashMap<>(0);

    private final List<ValueHolder> genericArgumentValues = new LinkedList<>();

    public ConstructorArgumentValues() {
    }

    public ConstructorArgumentValues(ConstructorArgumentValues original) {

    }

    public void addArgumentValues(ConstructorArgumentValues other) {
        if (other != null) {
            for (Map.Entry<Integer,ValueHolder> entry : other.indexedArgumentValues.entrySet()) {


            }
            for (ValueHolder valueHolder : other.genericArgumentValues) {

            }
        }
    }



    public static class ValueHolder implements BeanMetadataElement {

        private Object value;

        private String type;

        private String name;

        private Object Source;

        private boolean converted = false;

        private Object convertedValue;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }
    }
}

