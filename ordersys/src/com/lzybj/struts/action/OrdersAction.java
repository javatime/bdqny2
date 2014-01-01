package com.lzybj.struts.action;

import org.hibernate.criterion.Order;

import com.lzybj.hibernate.domain.Customers;
import com.lzybj.hibernate.domain.Orders;
import com.lzybj.service.CustomersService;
import com.lzybj.service.OrdersService;
import com.opensymphony.xwork2.ActionContext;

public class OrdersAction {
	private String customerid = "";
	private String delivername = "";
	private String deliveraddr = "";
	
	private CustomersService custservice = null;
	private OrdersService orderservice = null;
	
	
	
	
	
	public OrdersService getOrderservice() {
		return orderservice;
	}
	public void setOrderservice(OrdersService orderservice) {
		this.orderservice = orderservice;
	}
	public CustomersService getCustservice() {
		return custservice;
	}
	public void setCustservice(CustomersService custservice) {
		this.custservice = custservice;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getDelivername() {
		return delivername;
	}
	public void setDelivername(String delivername) {
		this.delivername = delivername;
	}
	public String getDeliveraddr() {
		return deliveraddr;
	}
	public void setDeliveraddr(String deliveraddr) {
		this.deliveraddr = deliveraddr;
	}
	
	public String createneworder(){
		//���ݿͻ�ID��ÿͻ�����
		Customers cust = custservice.findByCustid(customerid);
		//���ɶ�������
		Orders myorder = new Orders(cust, delivername, deliveraddr,"�´���");
		//����ʱ�������󱣴浽SESSION��
		ActionContext.getContext().getSession().put("temporder",myorder);
		return "succ";
	}
	
	public String ordersubmit(){
		Orders myorder = (Orders)ActionContext.getContext().getSession().get("temporder");
		orderservice.addOrder(myorder);
		ActionContext.getContext().getSession().remove("temporder");
		return "succ";
	}
	
}
