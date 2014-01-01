package com.lzybj.factory;

import com.lzybj.dao.MyDao;
import com.lzybj.dao.imp.MyDaoImp;
import com.lzybj.domain.Users;

public class MyFactory {
	public Users getUsers(){
		System.out.println("ͨ���������ö���ʵ��!");
		return Users.getInstance();
	}
	
	public MyDao getMyDao(){
		return MyDaoImp.getInstance();
	}
	
}
