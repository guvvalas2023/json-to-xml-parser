package com.javatechcloud.jsontoxmlparser;

import com.javatechcloud.jsontoxmlparser.service.JsonToXmlParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonToXmlParserApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	JsonToXmlParser jsonToXmlParser;

	//	@Test
	public void jsonToXmlParser() throws Exception {
		var json = "{\"employee\":{\"id\":\"123\",\"name\":\"testName\"}}";

		var template = "<emp><empId></empId><empName></empName></emp>";

		var script = "def jsonToXml(){" +
				"Object jNode= jpath.evaluate('/employee/id',source);" +
				"String idVal=jNode.asText();" +
				"Object node=xpath.evaluateNode('/emp/empId',target);" +
				"node.setTextContent(idVal);" +
				"Object jNameNode= jpath.evaluate('/employee/name',source);" +
				"String nameVal=jNameNode.asText();" +
				"Object empNameNode=xpath.evaluateNode('/emp/empName',target);" +
				"empNameNode.setTextContent(nameVal);" +
				"} " +
				"jsonToXml();";

		var output = jsonToXmlParser.transform(json, template, script);
		System.out.println(output);
	}


}
