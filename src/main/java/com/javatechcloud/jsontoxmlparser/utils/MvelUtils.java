package com.javatechcloud.jsontoxmlparser.utils;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MvelUtils {

    public static Object evaluate(String expression,Map<String,Object> params){
        MapVariableResolverFactory vars = new MapVariableResolverFactory(new HashMap(params));
        return  MVEL.eval(expression, vars);
    }
}
