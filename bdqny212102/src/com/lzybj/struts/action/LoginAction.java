package com.lzybj.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lzybj.hibernate.domain.Customers;
import com.lzybj.service.CustomersService;
import com.lzybj.struts.form.LoginActionForm;

public class LoginAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//1.���actionform
		LoginActionForm loginform = (LoginActionForm)form;
		//2.����ҵ���߼�
		CustomersService cs = new CustomersService();
		Customers c = cs.isLogin(loginform.getUname(),loginform.getUpwd());
		//3.�ж��Ƿ�ɹ�,�������߼���ַ
		String logicAddr = "";
		if(c != null){
			logicAddr = "succ";
			HttpSession session = request.getSession();
			session.setAttribute("c",c);
		}else{
			logicAddr = "fail";
		}
		return mapping.findForward(logicAddr);
	}

}
