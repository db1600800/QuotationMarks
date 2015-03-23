package com.compoment.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.compoment.cut.CompomentBean;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

//http://www.2cto.com/kf/201208/148029.html
public class SerializeToFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompomentBean myPersons = new CompomentBean(); 
        myPersons = new CompomentBean(); 
        myPersons.x=100;
        myPersons.y=200;
        myPersons.chirlds=new ArrayList();
        
        CompomentBean  myPerson = new CompomentBean(); 
        myPerson.x=300;
        myPerson.y=400;
        myPersons.chirlds.add(myPerson);
        
        
		SerializeToFile serializeToFile=new SerializeToFile();
		serializeToFile.serializeToXml(myPersons);
		serializeToFile.deSerializeFromXml();
		serializeToFile.writeJSON(myPersons);
	}
	
	
	    public void serializeToXml(CompomentBean myPersons){ 
	    	
	        XStream xStream = new XStream(); 
	        xStream.alias("CompomentBean", CompomentBean.class); 
	        try{ 
	            FileOutputStream foStream = new FileOutputStream("E:\\CompomentBean.xml"); 
	            xStream.toXML(myPersons,foStream); 
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	        } 
	    } 
	    public void deSerializeFromXml(){ 
	        XStream xStream = new XStream(); 
	        xStream.alias("CompomentBean", CompomentBean.class); 
	        CompomentBean myPersons = null; 
	        try{ 
	            FileInputStream flStream = new FileInputStream("E:\\CompomentBean.xml"); 
	            myPersons = (CompomentBean)xStream.fromXML(flStream); 
	            if(myPersons!=null){ 
	              
	                    System.out.println(myPersons.x); 
	                    System.out.println(myPersons.y); 
	                
	            } 
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	        } 
	    } 
	    
	    
	    public void writeJSON(CompomentBean myPersons){ 
	        XStream xStream = new XStream(new JettisonMappedXmlDriver()); 
	      
	        try { 
	            FileOutputStream fos = new FileOutputStream("E:\\json.js"); 
	            xStream.setMode(XStream.NO_REFERENCES); 
	            xStream.alias("Person", CompomentBean.class); 
	            xStream.toXML(myPersons, fos); 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	    public void readJSON(){ 
	        XStream xStream = new XStream(new JettisonMappedXmlDriver()); 
	        CompomentBean person = null; 
	        try { 
	            FileInputStream fis = new FileInputStream("E:\\CompomentBean.js"); 
	            xStream.setMode(XStream.NO_REFERENCES); 
	            xStream.alias("Person", CompomentBean.class); 
	            person = (CompomentBean)xStream.fromXML(fis); 
	            System.out.println(person.x); 
	            System.out.println(person.y); 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } 
	    }
	
	

}
