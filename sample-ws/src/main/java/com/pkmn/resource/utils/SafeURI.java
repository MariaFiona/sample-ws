package com.pkmn.resource.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Object representing a fully validated URI
 */
public class SafeURI {

    public static final String PATH_SEPARATOR = "/";

    // we use java.net.URL internally, to inherit some basic URL validation
    private URL basePath;
    private URL uri;

    /**
     * Shortcut method for concatenating strings into a valid URI. Validates the path separator (slash) in between path elements.
     * 
     * @param basePath
     *            the base path including the protocol, domain & context path
     * @param subPaths
     *            additional sub-paths to concatenate to the URI
     * @see {@link #SafeURI(String, String...)}
     * @return a string URI with valid path separator in between the sub-paths
     */
    public static String buildURI(String basePath, String... subPaths) {
        return new SafeURI(basePath, subPaths).getUriString();
    }

    /**
     * Create a SafeURI object from a path string
     * 
     * @param basePath
     *            the path string
     */
    public SafeURI(String basePath) {
        setBasePathString(basePath);
        setUriString(basePath);
    }

    /**
     * Create a SafeURI object from a string array of path parts
     * 
     * @param basePath
     *            the base path including the protocol, domain & context path
     * @param subPaths
     *            additional sub-paths to concatenate to the URI
     */
    public SafeURI(String basePath, String... subPaths) {
        this(basePath);
        addSubsequentPaths(subPaths);
    }

    /**
     * Concatenate a subpath to the base URI. Used to build a SafeURI object incrementally.
     * 
     * @param subPath
     *            additional sub-path to concatenate to the URI
     * @return a SafeURI with valid path separator in between its sub-paths
     */
    public SafeURI addSubPath(String subPath) {
        if (subPath != null) {
            String separator = PATH_SEPARATOR;
            String uriStr = uri.toString();
            String trimmedSubPath = subPath;

            // first trim any trailing or leading '/' to avoid double slashes
            if (uriStr.endsWith(PATH_SEPARATOR)) {
                uriStr = uriStr.substring(0, uriStr.length() - 1);
            }
            if (subPath.startsWith(PATH_SEPARATOR)) {
                trimmedSubPath = subPath.substring(1, subPath.length());
            }

            setUriString(uriStr + separator + trimmedSubPath);
        }
        return this;
    }

    public String getUriString() {
        return uri.toString();
    }

    public String getBasePathString() {
        return basePath.toString();
    }

    public URL getUri() {
        return uri;
    }

    public URL getBasePath() {
        return basePath;
    }

    /**
     * @return the string representation of the URI
     */
    @Override
    public String toString() {
        return getUriString();
    }

    private void setUriString(String uriString) {
        try {
            this.uri = new URL(uriString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void setBasePathString(String basePath) {
        try {
            this.basePath = new URL(basePath);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // vararg processing
    private void addSubsequentPaths(String... subPaths) {
        if (subPaths != null && subPaths.length > 0) {
            for (int i = 0; i < subPaths.length; i++) {
                addSubPath(subPaths[i]);
            }
        }
    }
}
