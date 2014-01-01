package com.lzybj.test;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo3 {
	public static void main(String[] args) throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/soaprj/services");
		WebTarget resource = target.path("myservice");
		WebTarget findbycnameService = resource.path("findbycname/l");
		Invocation.Builder builder = findbycnameService.request(MediaType.APPLICATION_XML);
		Response response = builder.get();
		String xmlStr = response.readEntity(String.class);
		System.out.println(xmlStr);
		//�����ַ�����ʽ��xml
		SAXReader sax = new SAXReader();
		//���Document����
		Document doc = sax.read(new ByteArrayInputStream(xmlStr.getBytes()));
		//��ø��ڵ�Ԫ��
		Element root = doc.getRootElement();
		//���customersԪ�ؼ���
		List<Element> customersList = root.elements("customers");
		//�������ϻ��ĳһ��Ԫ��
		for (Element customers : customersList) {
			System.out.println(customers.elementText("cid"));
			System.out.println(customers.elementText("cname"));
			System.out.println(customers.elementText("cpwd"));
		}
	}
}
