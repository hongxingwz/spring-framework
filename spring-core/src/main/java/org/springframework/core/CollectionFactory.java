package org.springframework.core;

import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * 意识到Java5，Java6和Spring集合类型的集合工厂。
 *
 * <p>主要用于框架内部使用
 * @author jianglei
 * @since 2018/4/9
 */
public abstract class CollectionFactory {

    private static final Set<Class<?>> approximableCollectionTypes = new HashSet<>();

    private static final Set<Class<?>> approximableMapTypes = new HashSet<>();

    static {
        // Standard collection interfaces
        approximableCollectionTypes.add(Collection.class);
        approximableCollectionTypes.add(List.class);
        approximableCollectionTypes.add(Set.class);
        approximableCollectionTypes.add(SortedSet.class);
        approximableCollectionTypes.add(NavigableSet.class);
        approximableMapTypes.add(Map.class);
        approximableMapTypes.add(SortedMap.class);
        approximableMapTypes.add(NavigableMap.class);

        // Common concrete collection classes
        approximableCollectionTypes.add(ArrayList.class);
        approximableCollectionTypes.add(LinkedList.class);
        approximableCollectionTypes.add(HashSet.class);
        approximableCollectionTypes.add(LinkedHashSet.class);
        approximableCollectionTypes.add(TreeSet.class);
        approximableCollectionTypes.add(EnumSet.class);
        approximableMapTypes.add(HashMap.class);
        approximableMapTypes.add(LinkedHashMap.class);
        approximableMapTypes.add(TreeMap.class);
        approximableMapTypes.add(EnumMap.class);
    }

    /**
     * 确定给定的集合类型是否是<em>approximable</em>类型，
     * i.e. 也就是{@link #createApproximateCollection}可以创造的类型
     * @param collectionType 要检测的集合类型
     * @return {@code true}如果是<em>approximable</em>
     */
    public static boolean isApproximableCollectionType(Class<?> collectionType) {
        return (collectionType != null && approximableCollectionTypes.contains(collectionType));
    }

    public static <E> Collection<E> createApproximateCollection(Object collection, int capacity) {
        if (collection instanceof LinkedList) {
            return new LinkedList<E>();
        } else if (collection instanceof List) {
            return new ArrayList<>(capacity);
        } else if (collection instanceof EnumSet) {
            Collection<E> enumSet = (Collection<E>)EnumSet.copyOf((EnumSet) collection);
            enumSet.clear();
            return enumSet;
        } else if (collection instanceof SortedSet) {
            return new TreeSet<E>(((SortedSet<E>) collection).comparator());
        } else {
            return new LinkedHashSet<E>(capacity);
        }
    }

    @SuppressWarnings({"unchecked", "cast"})
    public static <E> Collection<E> createCollection(Class<?> collectionType, Class<?> elementType, int capacity) {
        Assert.notNull(collectionType, "Collection type must not be null");
        if (collectionType.isInterface()) {
            if (Set.class == collectionType || Collection.class == collectionType) {
                return new LinkedHashSet<E>(capacity);
            } else if (List.class == collectionType) {
                return new ArrayList<E>(capacity);
            } else if (SortedSet.class == collectionType || NavigableSet.class == collectionType) {
                return new TreeSet<E>();
            } else {
                throw new IllegalArgumentException("Unsupported Collection interface: " + collectionType.getName());
            }
        } else if (EnumSet.class == collectionType) {
            Assert.notNull(elementType, "Cannot create EnumSet for unknown element type");
            return (Collection<E>) EnumSet.noneOf(asEnumType(elementType));
        } else {
            if (!Collection.class.isAssignableFrom(collectionType)) {
                throw new IllegalArgumentException("Unsupported Collection type: " + collectionType.getName());
            }
            try {
                return (Collection<E>) collectionType.newInstance();
            } catch (Throwable ex) {
                throw new IllegalArgumentException("Count not instantiate Collection type: " + collectionType.getName(), ex);
            }
        }
    }

    public static boolean isApproximableMapType(Class<?> mapType) {
        return (mapType != null && approximableMapTypes.contains(mapType));
    }

    public static <K, V> Map<K, V> createApproximateMap(Object map, int capacity) {
        if (map instanceof EnumMap) {
            EnumMap enumMap = new EnumMap((EnumMap) map);
            enumMap.clear();
            return enumMap;
        } else if (map instanceof SortedMap) {
            return new TreeMap<K, V>(((SortedMap<K, V>) map).comparator());
        } else {
            return new LinkedHashMap<K, V>(capacity);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> createMap(Class<?> mapType, Class<?> keyType, int capacity) {
        Assert.notNull(mapType, "Map type must not be null");
        if (mapType.isInterface()) {
            if (Map.class == mapType) {
                return new LinkedHashMap<K, V>(capacity);
            } else if (SortedMap.class == mapType || NavigableMap.class == mapType) {
                return new TreeMap<K, V>();
            } else if(MultiValueMap.class == mapType){
                return new LinkedMultiValueMap();
            } else {
                throw new IllegalArgumentException("Unsupported Map interface: " + mapType.getName());
            }
        } else if (EnumMap.class == mapType) {
            Assert.notNull(keyType, "Cannot create EnumMap for unknown key type");
            return new EnumMap(asEnumType(keyType));
        } else {
            if (!Map.class.isAssignableFrom(mapType)) {
                throw new IllegalArgumentException("Unsupported Map types: " + mapType.getName());
            }

            try {
                return (Map<K, V>) mapType.newInstance();
            } catch (Throwable ex) {
                throw new IllegalArgumentException("Could not instantiate Map type: " + mapType.getName(), ex);
            }
        }
    }

    @SuppressWarnings("serial")
    public static Properties createStringAdaptingProperties() {
        return new Properties() {
            @Override
            public String getProperty(String key) {
                Object value = get(key);
                return (value != null ? value.toString() : null);
            }
        };
    }

    @SuppressWarnings("rawtypes")
    private static Class<? extends Enum> asEnumType(Class<?> enumType) {
        Assert.notNull(enumType, "Enum type must not be null");
        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new IllegalArgumentException("Supplied type is not an enum: " + enumType.getName());
        }

        return enumType.asSubclass(Enum.class);
    }
}
