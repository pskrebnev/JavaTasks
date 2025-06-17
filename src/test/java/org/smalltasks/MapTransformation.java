package org.smalltasks;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTransformation {

    // Assuming Key is your custom key type
    public static class Key {
        private final String value;

        public Key(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Key key = (Key) obj;
            return value.equals(key.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "Key{" + value + '}';
        }
    }

    /**
     * Transform a Map<String, Integer> into Map<Map<Key, Integer>, Map<Key, String>>
     * using Stream API
     * @param originalMap The original map to transform
     * @return The transformed complex map structure
     */
    public static Map<Map<Key, Integer>, Map<Key, String>> transformMap(Map<String, Integer> originalMap) {
        AtomicInteger counter = new AtomicInteger(0);

        // Create a stream of entries with index
        Stream<AbstractMap.SimpleEntry<Integer, Map.Entry<String, Integer>>> indexedEntries =
                originalMap.entrySet().stream()
                        .map(entry -> new AbstractMap.SimpleEntry<>(counter.getAndIncrement(), entry));

        // Create the key map (storing original values)
        Map<Key, Integer> keyMap = indexedEntries
                .collect(Collectors.toMap(
                        entry -> new Key("value_" + entry.getKey()),
                        entry -> entry.getValue().getValue()
                ));

        // Reset counter and create a new stream
        counter.set(0);

        // Create the value map (storing original keys)
        Map<Key, String> valueMap = originalMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> new Key("key_" + counter.getAndIncrement()),
                        Map.Entry::getKey
                ));

        // Create result map with a single entry
        return Stream.of(new AbstractMap.SimpleEntry<>(keyMap, valueMap))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * Extract the original Map<String, Integer> from the transformed map
     * using Stream API
     * @param transformedMap The transformed map
     * @return The original map structure
     */
    public static Map<String, Integer> extractOriginalMap(Map<Map<Key, Integer>, Map<Key, String>> transformedMap) {
        // Get the only entry in the outer map
        Map.Entry<Map<Key, Integer>, Map<Key, String>> outerEntry =
                transformedMap.entrySet().iterator().next();

        Map<Key, Integer> keyMap = outerEntry.getKey();
        Map<Key, String> valueMap = outerEntry.getValue();

        // Number of entries should match
        int size = valueMap.size();

        // Use a stream to create the original map
        return Stream.iterate(0, i -> i < size, i -> i + 1)
                .collect(Collectors.toMap(
                        i -> valueMap.get(new Key("key_" + i)),     // Original key
                        i -> keyMap.get(new Key("value_" + i))      // Original value
                ));
    }

    // Main method to demonstrate the transformation
    public static void main(String[] args) {
        // Create original map
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);
        originalMap.put("three", 3);

        System.out.println("Original Map: " + originalMap);

        // Transform the map
        Map<Map<Key, Integer>, Map<Key, String>> transformedMap = transformMap(originalMap);
        System.out.println("Transformed Map: " + transformedMap);

        // Extract back the original map
        Map<String, Integer> extractedMap = extractOriginalMap(transformedMap);
        System.out.println("Extracted Map: " + extractedMap);

        // Verify they are equal
        System.out.println("Maps are equal: " + originalMap.equals(extractedMap));
    }
}
