package com.lzybj.struts.action;

import java.util.Set;

import com.lzybj.hibernate.domain.BizClaimVoucher;
import com.lzybj.hibernate.domain.BizClaimVoucherDetail;
import com.lzybj.hibernate.domain.SysDictionary;
import com.lzybj.hibernate.domain.SysEmployee;
import com.lzybj.service.BizClaimVoucherService;
import com.lzybj.service.SysDictionaryService;
import com.opensymphony.xwork2.ActionContext;

public class BizClaimVoucherAction {
	private String event = "";
	private String itemid = "";
	private String jiner = "";
	private String desc = "";
	private String submit = "";
	private String bxdid = "";
	
	private BizClaimVoucher bxd = null;
	
	
	private SysDictionaryService itemservice = null;
	private BizClaimVoucherService bxdservice = null;
	
	

	public String getBxdid() {
		return bxdid;
	}

	public void setBxdid(String bxdid) {
		this.bxdid = bxdid;
	}

	public BizClaimVoucher getBxd() {
		return bxd;
	}

	public void setBxd(BizClaimVoucher bxd) {
		this.bxd = bxd;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public BizClaimVoucherService getBxdservice() {
		return bxdservice;
	}

	public void setBxdservice(BizClaimVoucherService bxdservice) {
		this.bxdservice = bxdservice;
	}

	public SysDictionaryService getItemservice() {
		return itemservice;
	}

	public void setItemservice(SysDictionaryService itemservice) {
		this.itemservice = itemservice;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getJiner() {
		return jiner;
	}

	public void setJiner(String jiner) {
		this.jiner = jiner;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public String createbxd(){
		SysEmployee se = (SysEmployee)ActionContext.getContext().getSession().get("se");
		BizClaimVoucher bxd = new BizClaimVoucher();
		//����Ա������
		bxd.setSysEmployeeByCreateSn(se);
		//��ǰ������һ��ִ��Ա������
		SysEmployee nextDeal = se.getSysDepartment().getSysEmployee();
		bxd.setSysEmployeeByNextDealSn(nextDeal);
		//����
		bxd.setEnent(event);
		//״̬
		bxd.setStatus("�´���");
		//����ʱ����������session
		ActionContext.getContext().getSession().put("bxdtemp",bxd);
		return "succ";
	}
	
	public String createbxddetail(){
		//��sesssion��ȡ������������
		BizClaimVoucher bxd = (BizClaimVoucher)ActionContext.getContext().getSession().get("bxdtemp");
		//�����Ŀ����
		SysDictionary item = itemservice.findByItemid(itemid);
		//������������ϸ����
		BizClaimVoucherDetail bxd_detail = new BizClaimVoucherDetail(item, bxd,new Double(jiner), desc);
		//����������ϸ������ӵ��������еı�������ϸ������
		bxd.getBizClaimVoucherDetails().add(bxd_detail);
		//�ٴν��������������session
		ActionContext.getContext().getSession().put("bxdtemp",bxd);
		return "succ";
	}
	
	public String ylviewbxd(){
		BizClaimVoucher bxd = (BizClaimVoucher)ActionContext.getContext().getSession().get("bxdtemp");
		double sum = 0;
		Set<BizClaimVoucherDetail> details = bxd.getBizClaimVoucherDetails();
		for (BizClaimVoucherDetail bizClaimVoucherDetail : details) {
			sum = sum + bizClaimVoucherDetail.getAccount().doubleValue();
		}
		bxd.setTotalAcount(sum);
		ActionContext.getContext().getSession().put("bxdtemp",bxd);
		return "succ";
	}
	
	public String submitbxd(){
		BizClaimVoucher bxd = (BizClaimVoucher)ActionContext.getContext().getSession().get("bxdtemp");
		if(!submit.equals("�´���")){
			bxd.setStatus("�����");
		}
		bxdservice.addBXD(bxd);
		ActionContext.getContext().getSession().remove("bxdtemp");
		return "succ";
	}
	
	public String viewspbxddetail(){
		this.setBxd(bxdservice.findByBxdId(bxdid));
		return "succ";
	}
	
}
