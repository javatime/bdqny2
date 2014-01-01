package com.lzybj.struts.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import com.lzybj.struts.domain.Company;
import com.lzybj.struts.domain.Employee;
import com.lzybj.struts.domain.Group;

public class Demo {

	/**
	 * @param args
	 * @throws OgnlException 
	 */
	public static void main(String[] args) throws OgnlException {
		// TODO Auto-generated method stub
		Company comp = new Company("10001","��������");
		Group group = new Group("jn10001","ѧ����",comp);
		Employee emp = new Employee("jnxs001","����",group);
		Employee emp2 = new Employee("jnxs002","��֣",group);
		
		Map<String,Employee> context = new HashMap<String, Employee>();
		context.put("emp1",emp);
		context.put("emp2",emp2);
		List<Employee> data = new ArrayList<Employee>();
		data.add(emp);
		data.add(emp2);
		//javaд��
		//System.out.println(emp.getGroup().getComp().getCompname());
		//ognl
		//ui/vi #emp.group.comp.compname
		String value = (String)Ognl.getValue("group.comp.compname",emp);
		System.out.println(value);
		String value2 = (String)Ognl.getValue("ename",emp);
		System.out.println(value2);
		System.out.println(Ognl.getValue("comp.compname",group));
		String value3 = (String)Ognl.getValue("#emp1.ename + ',' + #emp2.ename",context,new Object());
		System.out.println(value3);
		//emp.setEname("����");
		Ognl.setValue("setEname",emp,"����");
		System.out.println(Ognl.getValue("getEname()",emp));
		//����
		System.out.println(Ognl.getValue("size",data));
		System.out.println(Ognl.getValue("get(1).ename",data));
	}

}
