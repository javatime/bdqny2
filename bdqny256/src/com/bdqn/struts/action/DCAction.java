package com.bdqn.struts.action;

import com.bdqn.hibernate.domain.Diaocha;
import com.bdqn.service.DiaoChaService;

public class DCAction {
	private String[] ah = null;
	private String uname = "";
	public String[] getAh() {
		return ah;
	}
	public void setAh(String[] ah) {
		this.ah = ah;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String adddc(){
		/**
		 * 1.����ҵ���߼�
		 * 2.��ý��
		 * 3.�����Ҫ����������(request,session)
		 */
		DiaoChaService dcs = new DiaoChaService();
		Diaocha dc = null;
		/**
		 * �����������Ϊ�ַ���
		 */
		String ahlist = "";
		for (int i = 0; i < ah.length; i++) {
			if(ahlist.equals("")){
				ahlist = ah[i];
			}else{
				ahlist = ah[i] + "," + ahlist;
			}
		}
		if(uname.equals("")){
			dc = new Diaocha(ahlist);
		}else{
			dc = new Diaocha(uname, ahlist);
		}
		boolean isSucc = dcs.addResult(dc);
		return "succ";
	}
	
}
