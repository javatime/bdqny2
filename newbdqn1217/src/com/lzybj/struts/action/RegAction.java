/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.lzybj.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ActionSupport;

import com.lzybj.service.CustomersService;

/** 
 * MyEclipse Struts
 * Creation date: 12-17-2013
 * 
 * XDoclet definition:
 * @struts.action path="/reg" name="regform" scope="request"
 * @struts.action-forward name="succ" path="/cx.jsp" redirect="true"
 */
public class RegAction extends ActionSupport {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm regform = (DynaActionForm) form;// TODO Auto-generated method stub
		String cname = regform.getString("cname");
		String cpwd = regform.getString("cpwd");
		WebApplicationContext wac = this.getWebApplicationContext();
		CustomersService cs = (CustomersService)wac.getBean("myservice");
		cs.addCustomer(cname, cpwd);
		return mapping.findForward("succ");
	}
}