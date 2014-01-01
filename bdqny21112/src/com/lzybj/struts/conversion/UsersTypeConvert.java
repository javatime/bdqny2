package com.lzybj.struts.conversion;

import java.util.Map;

import com.lzybj.domain.Users;

import ognl.DefaultTypeConverter;

public class UsersTypeConvert extends DefaultTypeConverter{
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		//valueΪת��ǰ��ֵ��һ��ΪString[]����,toType:Ŀ��ת������
		
		if(toType == Users.class){
			String[] myvalue = (String[])value;
			Users myUser = new Users(myvalue[0],myvalue[1],myvalue[2]);
			return myUser;
		}
		return value;
	}
}
