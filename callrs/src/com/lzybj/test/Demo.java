package com.lzybj.test;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����Client����
		Client client = ClientBuilder.newClient();
		//Ŀǰ����WebTarget,webservice��ַ
		WebTarget target = client.target("http://localhost:8080/soaprj/services");
		//webservice��
		WebTarget resource = target.path("myservice");
		//webservice��ķ���
//		WebTarget testService = resource.path("test");
		WebTarget testService = resource.path("islogin/lzybj,qaz123");
		//��Ӧ����(MediaType����)
		Invocation.Builder build = testService.request(MediaType.TEXT_HTML_TYPE);
		//��ȡ��Ӧ����
		Response response = build.get();
		//������Ӧ֮��Ӧ״̬��
		System.out.println(response.getStatus());
		//������Ӧ֮����
		String myContent = response.readEntity(String.class);
		System.out.println(myContent);
	}

}
