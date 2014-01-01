package com.lzybj.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lzybj.hibernate.domain.SysEmployee;
import com.lzybj.service.BizClaimVoucherService;
import com.lzybj.service.SysEmployeeService;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SysEmployeeService ses = (SysEmployeeService)ac.getBean("empservice");
		SysEmployee emp = ses.isLogin("lzybj","qaz123");//�ܾ���
		SysEmployee emp2 = ses.isLogin("hidba","qaz123");//���ž���
		SysEmployee emp3 = ses.isLogin("cnhidba","qaz123");//����
		SysEmployee emp4 = ses.isLogin("ilyj","qaz123");//Ա��		
		
		BizClaimVoucherService bxdservice = (BizClaimVoucherService)ac.getBean("bxdservice");
		
		bxdservice.spbxdFormPM("10",emp2,"���ͨ��","ͬ��");
		bxdservice.spbxdFromZJL("10",emp,"���ͨ��","ͬ��");
		bxdservice.spbxdFromCW("10",emp3,"���ͨ��","ͬ��");
		
		
	}

}
