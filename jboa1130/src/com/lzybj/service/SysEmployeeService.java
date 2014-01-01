package com.lzybj.service;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzybj.hibernate.domain.BizClaimVoucher;
import com.lzybj.hibernate.domain.SysEmployee;

public class SysEmployeeService extends HibernateDaoSupport{
	public SysEmployee isLogin(String uname,String upwd){
		String hsql = "from SysEmployee se where se.name = ? and se.status <> '��ְ'";
		List<SysEmployee> ses = this.getHibernateTemplate().find(hsql,uname);
		if(ses.size() != 0){
			SysEmployee se = ses.get(0);
			if(se.getPassword().equals(upwd)){
				return se;
			}
		}
		return null;
	}
	
	public List<BizClaimVoucher> findByDshList(SysEmployee se){
		String hsql = "from BizClaimVoucher bxd where bxd.sysEmployeeByNextDealSn.sn = ? ";
		String dNameEn = se.getSysPosition().getNameEn();//ְλ��Ӣ����
		if(dNameEn.equals("BMJL")){
			hsql = hsql + " and bxd.status = '�����'";
		}else{
			if(dNameEn.equals("ZJL")){
				hsql = hsql + " and bxd.status = '���ύ'";				
			}else{
				if(dNameEn.equals("CW")){
					hsql = hsql + " and bxd.status = '������'";					
				}
			}
		}
		List<BizClaimVoucher> bxds = this.getHibernateTemplate().find(hsql,se.getSn());
		return bxds;
	}
	
	public SysEmployee findByPostionName(String pname){
		String hsql = "from SysEmployee se where se.sysPosition.nameEn = ?";
		List<SysEmployee> ses = this.getHibernateTemplate().find(hsql,pname);
		return ses.get(0);
	}
	
}
