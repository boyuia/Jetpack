package com.example.jetpack;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an element with a name and a set of properties.
 */
public class Element {
    private String name;
    private Map<String, Object> properties;

    /**
     * Constructor to initialize the element with a name.
     *
     * @param name the name of the element
     */
    public Element(String name) {
        this.name = name;
        this.properties = new HashMap<>();
    }

    /**
     * Adds a property to the element.
     *
     * @param name  the name of the property
     * @param value the value of the property
     */
    public void addProperty(String name, Object value) {
        properties.put(name, value);
    }

    /**
     * Gets the name of the element.
     *
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the properties of the element.
     *
     * @return a map of property names to values
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
