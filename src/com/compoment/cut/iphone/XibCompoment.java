package com.compoment.cut.iphone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XibCompoment {
	
public static void main(String[] args) {
	
	XibCompoment xibCompoment=new XibCompoment();
	String n=xibCompoment.average4();
	System.out.println(n);
}	

	/**平均分四分*/
	public static String average4()
	{
		int count=3;
		int rootViewWidth=318;
		int partWidth=rootViewWidth/count;
		int height=100;
		
		Map viewids=new HashMap();
	
		String parentid=id();
		String m="";
		
		m+="   <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+parentid+"\">\n";
		m+="                    <rect key=\"frame\" x=\"0.0\" y=\"0\" width=\""+rootViewWidth+"\" height=\""+height+"\"/>\n";
		m+="                    <subviews>\n";
		
		for(int i=0;i<count;i++)
		{
			String id=id();
			viewids.put(i, id);
		m+="                        <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""+id+"\">\n";
		m+="                            <rect key=\"frame\" x=\""+i*partWidth+"\" y=\"0\" width=\""+partWidth+"\" height=\""+height+"\"/>\n";
		m+="                            <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                        </view>\n";
		}
		
//		m+="                        <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"los-li-UDo\">\n";
//		m+="                            <rect key=\"frame\" x=\"300\" y=\"9\" width=\"150\" height=\"83\"/>\n";
//		m+="                            <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
//		m+="                        </view>\n";
//		m+="                        <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"d9R-vt-gEv\">\n";
//		m+="                            <rect key=\"frame\" x=\"450\" y=\"9\" width=\"150\" height=\"83\"/>\n";
//		m+="                            <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
//		m+="                        </view>\n";
//		m+="                        <view contentMode=\"scaleToFill\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\"Hth-Fn-qeV\">\n";
//		m+="                            <rect key=\"frame\" x=\"0.0\" y=\"9\" width=\"150\" height=\"83\"/>\n";
//		m+="                            <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
//		m+="                        </view>\n";
		m+="                    </subviews>\n";
		m+="                    <color key=\"backgroundColor\" white=\"0.66666666666666663\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                    <constraints>\n";
		
		for(int i=0;i<count;i++)
		{
			
			//top
			m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"top\" secondItem=\""+parentid+"\" secondAttribute=\"top\"  id=\""+id()+"\"/>\n";
			
			//bottom
			m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"bottom\" secondItem=\""+parentid+"\" secondAttribute=\"bottom\"  id=\""+id()+"\"/>\n";
			
			
			//width
			if(i==0)
			{
		//width
		m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"width\" secondItem=\""+parentid+"\" secondAttribute=\"width\" multiplier=\"1/"+count+"\" id=\""+id()+"\"/>\n";                     
		//left
		m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"leading\" secondItem=\""+parentid+"\" secondAttribute=\"leading\" id=\""+id()+"\"/>\n";	
			}
			
			else
			{
		//width
	    m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"width\" secondItem=\""+viewids.get(0)+"\" secondAttribute=\"width\" id=\""+id()+"\"/>\n";
	   //left
	    m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"leading\" secondItem=\""+viewids.get(i-1)+"\" secondAttribute=\"trailing\" id=\""+id()+"\"/>\n";
			
	          if(i==count-1)
				{//right
	        	  m+=" <constraint firstItem=\""+viewids.get(i)+"\" firstAttribute=\"trailing\" secondItem=\""+parentid+"\" secondAttribute=\"trailing\" id=\""+id()+"\"/>\n";
	      			
				}
			}
		}
		
		
//		m+="                        <constraint firstItem=\"d9R-vt-gEv\" firstAttribute=\"width\" secondItem=\"los-li-UDo\" secondAttribute=\"width\" id=\"0RS-lL-IMt\"/>\n";
//		
//		m+="                        <constraint firstItem=\"d9R-vt-gEv\" firstAttribute=\"top\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"top\" constant=\"9\" id=\"0uY-ab-sr7\"/>\n";
//		m+="                        <constraint firstAttribute=\"bottom\" secondItem=\"d9R-vt-gEv\" secondAttribute=\"bottom\" constant=\"8\" id=\"2FW-I5-mXg\"/>\n";
//		m+="                        <constraint firstAttribute=\"height\" constant=\"100\" id=\"5r1-gR-DrP\"/>\n";
//		
//		m+="                        <constraint firstItem=\"Hth-Fn-qeV\" firstAttribute=\"width\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"width\" multiplier=\"1/4\" id=\"7Qy-aH-rp6\"/>\n";
//		m+="                        <constraint firstItem=\"Hth-Fn-qeV\" firstAttribute=\"leading\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"leading\" id=\"SWp-fr-iDb\"/>\n";
//		m+="                        <constraint firstAttribute=\"bottom\" secondItem=\"Hth-Fn-qeV\" secondAttribute=\"bottom\" constant=\"8\" id=\"VVp-Cu-psE\"/>\n";
//		m+="                        <constraint firstAttribute=\"width\" constant=\"1\" id=\"Whv-Lq-d01\"/>\n";
//		
//		m+="                        <constraint firstItem=\"Hth-Fn-qeV\" firstAttribute=\"top\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"top\" constant=\"9\" id=\"Yhk-Jc-kl8\"/>\n";
//		m+="                        <constraint firstItem=\"VTT-z3-e82\" firstAttribute=\"width\" secondItem=\"Hth-Fn-qeV\" secondAttribute=\"width\" id=\"bk4-zn-htW\"/>\n";
//		m+="                        <constraint firstAttribute=\"bottom\" secondItem=\"VTT-z3-e82\" secondAttribute=\"bottom\" constant=\"8\" id=\"m7M-nV-zPu\"/>\n";
//		m+="                        <constraint firstItem=\"los-li-UDo\" firstAttribute=\"leading\" secondItem=\"VTT-z3-e82\" secondAttribute=\"trailing\" id=\"mOq-Sp-skr\"/>\n";
//		m+="                        <constraint firstItem=\"VTT-z3-e82\" firstAttribute=\"width\" secondItem=\"los-li-UDo\" secondAttribute=\"width\" id=\"onb-iS-bOD\"/>\n";
//		m+="                        <constraint firstItem=\"VTT-z3-e82\" firstAttribute=\"leading\" secondItem=\"Hth-Fn-qeV\" secondAttribute=\"trailing\" id=\"sjf-ex-T2H\"/>\n";
//		m+="                        <constraint firstItem=\"los-li-UDo\" firstAttribute=\"top\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"top\" constant=\"9\" id=\"wOD-Lt-Yv6\"/>\n";
//		m+="                        <constraint firstItem=\"d9R-vt-gEv\" firstAttribute=\"leading\" secondItem=\"los-li-UDo\" secondAttribute=\"trailing\" id=\"xxf-xo-v7A\"/>\n";
//		m+="                        <constraint firstAttribute=\"bottom\" secondItem=\"los-li-UDo\" secondAttribute=\"bottom\" constant=\"8\" id=\"y5o-gy-jbI\"/>\n";
//		m+="                        <constraint firstItem=\"VTT-z3-e82\" firstAttribute=\"top\" secondItem=\"jpa-lu-lwk\" secondAttribute=\"top\" constant=\"9\" id=\"zQ8-kX-zIa\"/>\n";
		m+="                    </constraints>\n";
		m+="                    <variation key=\"default\">\n";
		m+="                        <mask key=\"constraints\">\n";
		//m+="                            <exclude reference=\"Whv-Lq-d01\"/>\n";
		m+="                        </mask>\n";
		m+="                    </variation>\n";
		m+="                    <variation key=\"widthClass=compact\" misplaced=\"YES\">\n";
		//m+="                        <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"321\" height=\"137.5\"/>\n";
		m+="                    </variation>\n";
		m+="                </view>\n";

		return m;
	}
	
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String genID(int length) // 参数为返回随机数的长度
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static String id() {

		return genID(3) + "-" + genID(2) + "-" + genID(3);
	}
	
}
