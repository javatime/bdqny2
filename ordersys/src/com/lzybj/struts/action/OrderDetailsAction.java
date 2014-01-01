package com.lzybj.struts.action;

import java.math.BigDecimal;

import com.lzybj.hibernate.domain.Orderdetails;
import com.lzybj.hibernate.domain.Orders;
import com.lzybj.hibernate.domain.Products;
import com.lzybj.service.ProductsService;
import com.opensymphony.xwork2.ActionContext;

public class OrderDetailsAction {
	private String prodid = "";
	private String prodcount = "";
	
	private ProductsService ps = null;
	
	
	
	public ProductsService getPs() {
		return ps;
	}
	public void setPs(ProductsService ps) {
		this.ps = ps;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdcount() {
		return prodcount;
	}
	public void setProdcount(String prodcount) {
		this.prodcount = prodcount;
	}
	
	public String createorderdetails(){
		//���ݲ�ƷID��ò�Ʒ����
		Products p = ps.findByProdid(prodid);
		//��SESSION�л�ö�������
		Orders myorder = (Orders)ActionContext.getContext().getSession().get("temporder");
		//����������ϸ����
		Orderdetails od = new Orderdetails(myorder,p,new BigDecimal(prodcount));
		//��������ϸ������ӵ���������
		myorder.getOrderdetailses().add(od);
		//���������󱣴浽SESSION��
		ActionContext.getContext().getSession().put("temporder",myorder);
		return "succ";
	}
	
}
