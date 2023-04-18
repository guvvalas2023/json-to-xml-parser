package com.javatechcloud.jsontoxmlparser.utils;

import com.fasterxml.jackson.databind.JsonNode;


/**
 *
 */
public class JQueryUtils {

    /**
     *
     * @param path
     * @param root
     * @return
     */
    public JsonNode evaluate(String path, JsonNode root){
        return root.at(path);
    }
}
