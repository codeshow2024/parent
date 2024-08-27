package top.codeshow.common.util;

import java.util.*;

public class MyCollUtils {
    public static <K, V> List<V> getByList(List<K> keys, Map<K, V> map) {
        return map.entrySet().stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .toList();
    }

    /**
     * 集合是否为空
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <T> List<T> toList(Set<T> set) {
        return new ArrayList<>(set);
    }
}
