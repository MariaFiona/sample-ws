package com.pkmn.resource.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ExpandedProperties {

    // allowable expansion properties. Keep in alphabetical order
    public enum ValidExpansions {
        defaultforme,
        formes;
    }

    /**
     * Bridge for getExpandedProperties(List properties)
     * 
     * @return ExpandedProperties object containing the specified single expansion
     */
    public static ExpandedProperties getExpandedProperties(String expand) {
        if (StringUtils.isEmpty(expand)) {
            return ExpandedProperties.none();
        }
        List<String> expandList = Arrays.asList(StringUtils.split(expand, ","));
        return getExpandedProperties(expandList);
    }

    public static ExpandedProperties getExpandedProperties(List<String> properties) {
        List<ValidExpansions> expansions = new ArrayList<ValidExpansions>();
        if (properties != null) {
            for (String property : properties) {
                try {
                    ValidExpansions expansion = ValidExpansions.valueOf(property.toLowerCase());
                    expansions.add(expansion);
                } catch (IllegalArgumentException e) {
                    // ignore it
                }
            }
        }
        return new ExpandedProperties(expansions);
    }

    public static ExpandedProperties none() {
        return new ExpandedProperties(new ArrayList<ValidExpansions>());
    }

    List<ValidExpansions> expanded;

    public ExpandedProperties(List<ValidExpansions> expanded) {
        this.expanded = expanded;

    }

    public boolean isExpanded(ValidExpansions value) {
        return expanded.contains(value);
    }
}
