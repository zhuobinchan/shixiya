package com.ehighsun.shixiya.util;

/**
 * ����������
 * @author Administrator
 *
 */
public class NavUtil {

	/**
	 * ��ɵ�������
	 * @param subName
	 * @return
	 */
	public static String genNavCode(String subName){
		StringBuffer navCode=new StringBuffer();
		navCode.append("�����ڵ�λ�ã�");
		navCode.append("<a href='index.jsp'>��ҳ</a>&nbsp;");
		navCode.append("&gt; ");
		navCode.append(subName);
		return navCode.toString();
	}
}
