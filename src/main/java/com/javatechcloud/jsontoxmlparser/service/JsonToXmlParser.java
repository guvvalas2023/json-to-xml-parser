package com.javatechcloud.jsontoxmlparser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechcloud.jsontoxmlparser.utils.AppConstants;
import com.javatechcloud.jsontoxmlparser.utils.JQueryUtils;
import com.javatechcloud.jsontoxmlparser.utils.MvelUtils;
import com.javatechcloud.jsontoxmlparser.utils.XPathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
@Service
public class JsonToXmlParser {

    private final ObjectMapper mapper = new ObjectMapper();
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


    /**
     *
     * @param json
     * @param xmlTemplate
     * @param mvelScript
     * @param <T1>
     * @param <T2>
     * @return
     * @throws Exception
     */
    public<T1,T2> T2 transform(String json,String xmlTemplate,String mvelScript) throws Exception {

        //convert json string to JsonNode
        var jsonNode = mapper.readTree(json);

        //Convert Xml String to Xml Document
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document targetDoc = builder.parse(new InputSource(new StringReader(xmlTemplate)));

        //Create the MVel script variable with reference
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put(AppConstants.SOURCE_ENTITY, jsonNode);
        vars.put(AppConstants.TARGET_ENTITY, targetDoc);
        vars.put(AppConstants.JPATH, new JQueryUtils());
        vars.put(AppConstants.XPATH, new XPathUtils());

        //execute the MVel scripts
        var val = MvelUtils.evaluate(mvelScript, vars);

        return (T2) XPathUtils.docAsString(targetDoc);
    }
}
