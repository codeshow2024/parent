package top.codeshow.common.util;

import java.util.List;
import java.util.Map;

public class MyMapUtils {
    public static <K, V> List<V> getByList(List<K> keys, Map<K, V> map) {
        return map.entrySet().stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .toList();
    }
}
