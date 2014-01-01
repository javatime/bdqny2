package com.lzybj.service;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzybj.hibernate.domain.BizAccount;
import com.lzybj.hibernate.domain.BizCheckResult;
import com.lzybj.hibernate.domain.BizClaimVoucher;
import com.lzybj.hibernate.domain.SysEmployee;

public class BizClaimVoucherService extends HibernateDaoSupport{
	private SysEmployeeService empservice = null;
	
	
	public SysEmployeeService getEmpservice() {
		return empservice;
	}

	public void setEmpservice(SysEmployeeService empservice) {
		this.empservice = empservice;
	}

	public boolean addBXD(BizClaimVoucher bxd){
		this.getHibernateTemplate().save(bxd);
		return true;
	}
	
	public BizClaimVoucher findByBxdId(String bxdid){
		BizClaimVoucher bxd = this.getHibernateTemplate().get(BizClaimVoucher.class,new Integer(bxdid));
		return bxd;
	}
	
	public boolean spbxdFormPM(String bxdid,SysEmployee se,String result,String comm){
		//��ñ���������
		BizClaimVoucher bxd = this.findByBxdId(bxdid);
		//��ñ��������
		double bxdaccount = bxd.getTotalAcount();
		//�жϱ������������
		String sheet_type = "";
		if(bxdaccount <= 5000){
			sheet_type = "���ž���������͵���";
		}else{
			sheet_type = "�ܾ���������͵���";
		}
		//�����������(���磺���ž�����ˣ��ܾ�����ˣ��������)
		String vType = "���ž������";
		//�ж���˽��
		if(result.equals("���ͨ��")){
			//�жϽ��
			if(bxdaccount <= 5000){//״̬������������һ�������ˣ�����
				bxd.setStatus("������");
				SysEmployee nextDealSe = empservice.findByPostionName("CW");
				bxd.setSysEmployeeByNextDealSn(nextDealSe);
			}else{//״̬�����ύ����һ�������ˣ��ܾ���
				bxd.setStatus("���ύ");
				SysEmployee nextDealSe = empservice.findByPostionName("ZJL");
				bxd.setSysEmployeeByNextDealSn(nextDealSe);
			}
		}else{
			if(result.equals("��˾ܾ�")){
				bxd.setStatus("�ܾ�");
				bxd.setSysEmployeeByNextDealSn(null);
			}else{//���
				bxd.setStatus("���");
				bxd.setSysEmployeeByNextDealSn(bxd.getSysEmployeeByCreateSn());
			}
		}
		//������˽������
		BizCheckResult res = new BizCheckResult(se, bxd, sheet_type, vType, result, comm);
		//������ض���
		this.getHibernateTemplate().update(bxd);
		this.getHibernateTemplate().save(res);
		return true;
	}
	
	public boolean spbxdFromZJL(String bxdid,SysEmployee se,String result,String comm){
		//��ñ���������
		BizClaimVoucher bxd = this.findByBxdId(bxdid);
	    String sheet_type = "�ܾ���������͵���";
		//�����������(���磺���ž�����ˣ��ܾ�����ˣ��������)
		String vType = "�ܾ������";
		//�ж���˽��
		if(result.equals("���ͨ��")){
			//״̬������������һ�������ˣ�����
				bxd.setStatus("������");
				SysEmployee nextDealSe = empservice.findByPostionName("CW");
				bxd.setSysEmployeeByNextDealSn(nextDealSe);
		}else{
			if(result.equals("��˾ܾ�")){
				bxd.setStatus("�ܾ�");
				bxd.setSysEmployeeByNextDealSn(null);
			}else{//���
				bxd.setStatus("���");
				bxd.setSysEmployeeByNextDealSn(bxd.getSysEmployeeByCreateSn());
			}
		}
		//������˽������
		BizCheckResult res = new BizCheckResult(se, bxd, sheet_type, vType, result, comm);
		//������ض���
		this.getHibernateTemplate().update(bxd);
		this.getHibernateTemplate().save(res);
		return true;
	}
	
	public boolean spbxdFromCW(String bxdid,SysEmployee se,String result,String comm){
		//��ñ���������
		BizClaimVoucher bxd = this.findByBxdId(bxdid);
		//��ñ��������
		double bxdaccount = bxd.getTotalAcount();
		//�жϱ������������
		String sheet_type = "";
		if(bxdaccount <= 5000){
			sheet_type = "���ž���������͵���";
		}else{
			sheet_type = "�ܾ���������͵���";
		}
		//�����������(���磺���ž�����ˣ��ܾ�����ˣ��������)
		String vType = "�������";
		//�ж���˽��
		if(result.equals("���ͨ��")){
			//״̬:�����һ�������ˣ�null
				bxd.setStatus("����");
				bxd.setSysEmployeeByNextDealSn(null);
		}else{
			if(result.equals("��˾ܾ�")){
				bxd.setStatus("�ܾ�");
				bxd.setSysEmployeeByNextDealSn(null);
			}else{//���
				bxd.setStatus("���");
				bxd.setSysEmployeeByNextDealSn(bxd.getSysEmployeeByCreateSn());
			}
		}
		//������˽������
		BizCheckResult res = new BizCheckResult(se, bxd, sheet_type, vType, result, comm);
		//���ɲ��񵥾ݶ���
		BizAccount account = new BizAccount(se, bxd, "�ֽ�",bxd.getTotalAcount().intValue());
		//������ض���
		this.getHibernateTemplate().update(bxd);
		this.getHibernateTemplate().save(res);
		this.getHibernateTemplate().save(account);
		return true;		
	}
	
}
