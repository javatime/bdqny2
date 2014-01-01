package com.lzybj.test;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lzybj.domain.Customers;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/soaprj/services");
		WebTarget resource = target.path("myservice");
		WebTarget findByAllService = resource.path("findbyall");
		Invocation.Builder build = findByAllService.request(MediaType.APPLICATION_JSON);
		Response response = build.get();
		String jsonStr = response.readEntity(String.class);
		System.out.println(jsonStr);
		//��Json����ΪJSON����
		JSONObject json = JSONObject.fromObject(jsonStr);
		//��JSON���е�JSON���鴮ȡ��
		JSONArray jsonArr = json.getJSONArray("customers");
		//��JSON��������Ϊ���϶���
		Collection<Customers> data = jsonArr.toCollection(jsonArr,Customers.class);
		for (Customers customers : data) {
			System.out.println(customers.getCname());
		}
	}

}
