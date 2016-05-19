/*      ModelReder.java
#      
#      Copyright (C) 2016 Jia Liu <liujia@iscas.ac.cn>
#      
#      This program is free software; you can redistribute it and/or modify
#      it under the terms of the GNU General Public License as published by
#      the Free Software Foundation; either version 2 of the License, or
#      (at your option) any later version.
#      
#      This program is distributed in the hope that it will be useful,
#      but WITHOUT ANY WARRANTY; without even the implied warranty of
#      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#      GNU General Public License for more details.
#      
#      You should have received a copy of the GNU General Public License
#      along with this program; if not, write to the Free Software
#      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#      MA 02110-1301, USA.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat; 
import org.dom4j.io.XMLWriter;
import org.dom4j.io.SAXReader;



public class ModelReder {
	
	
	
    public static void main(String[] args) {

    	
        try {
            
            ModelReder modelReder = new ModelReder();
           // modelReder.uiRead();
          //  modelReder.uiWrite();
            modelReder.cdlRead(); 
            modelReder.cdlWrite();    

            
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
    }
    
    
    public static ArrayList block_List = new ArrayList();//UI�е�xml��������blocklist
    public static String p_name="";
    
 public void uiRead() throws Exception{
	
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("ui_forsample.xml"));
        
        Element root = document.getRootElement();//project�ڵ�
        p_name=root.attributeValue("name"); //project������
        Iterator it = root.elementIterator(); 
       
        
        for(;it.hasNext();)
        {
        	 Element b_element = (Element) it.next(); //block�ڵ�
             String b_f_name=b_element.attributeValue("type");//��һ��block����
             block_List.add(b_f_name);//��block_List�з����һ��block
             String b_f_id="";//��һ��blockID
             it= b_element.elementIterator();//for��һ��ڵ�
        	if (b_f_name.equals("controls_repeat_for"))
        	{
        		it= b_element.elementIterator();//for��һ��ڵ�
        		Element value_element = (Element) it.next(); //for value�ڵ�
        		it= value_element.elementIterator();//for������ڵ�
                Element shadow_element = (Element) it.next(); //for shadow�ڵ�
                it = shadow_element.elementIterator();//for���Ĳ�ڵ�
                Element abr_element = (Element) it.next(); //for field�ڵ�
           	 	String f_abr=abr_element.getText();//for_block�Ĳ���ֵ
           	 	block_List.add(f_abr);//for����Ĳ���
           	 	Element for_statement=b_element.element("statement");
           	 	block_List.add("statement");//����statement
           	 	it = for_statement.elementIterator();//for statement �ڶ���ڵ�
           	 	for(;it.hasNext();) //statement�е�block
           	 	{
           	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
           	 		b_f_name=next_element.attributeValue("type");//��һ��block����
           	 		block_List.add(b_f_name);//statement�еĵ�һ��block
           	 		it=next_element.elementIterator();//����һ��
           	 		if(it.hasNext())//ȡnext
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//����һ��
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("statement");//����statement
           	 	Element for_next=b_element.element("next");
           	 	it = for_next.elementIterator();//for�����next
           	 	for(;it.hasNext();) //next�е�block
        	 	{
        	 		Element next_element=(Element) it.next(); //���next�еĵ�һ��block
        	 		b_f_name=next_element.attributeValue("type");//��һ��block����
        	 		block_List.add(b_f_name);//next�еĵ�һ��block
        	 		it=next_element.elementIterator();//����һ��
        	 		if(it.hasNext())//ȡnext
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//����һ��
        	 		}
        	 		
        	 	}           	 	
           	         	 	
           	 }else if(b_f_name.equals("controls_and"))
           	 {
           		it= b_element.elementIterator();//and��һ��ڵ� 
           		Element and_statement=b_element.element("statement");
           	 	block_List.add("and_statement");//����statement
           	 	it = and_statement.elementIterator();//for statement �ڶ���ڵ�
           	 	for(;it.hasNext();) //statement�е�block
           	 	{
           	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
           	 		b_f_name=next_element.attributeValue("type");//��һ��block����
           	 		block_List.add(b_f_name);//statement�еĵ�һ��block
           	 		it=next_element.elementIterator();//����һ��
           	 		if(it.hasNext())//ȡnext
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//����һ��
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("and_statement");//����statement
           	 	Element and_next=b_element.element("next");
           	 	it = and_next.elementIterator();//and�����next
           	 	for(;it.hasNext();) //next�е�block
        	 	{
        	 		Element next_element=(Element) it.next(); //���next�еĵ�һ��block
        	 		b_f_name=next_element.attributeValue("type");//��һ��block����
        	 		block_List.add(b_f_name);//next�еĵ�һ��block
        	 		it=next_element.elementIterator();//����һ��
        	 		if(it.hasNext())//ȡnext
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//����һ��
        	 		}
        	 		
        	 	}  
           		
           	 }else if(b_f_name.equals("controls_or"))
           	 {
           		it= b_element.elementIterator();//or��һ��ڵ� 
           		Element or_statement=b_element.element("statement");
           	 	block_List.add("or_statement");//����statement
           	 	it = or_statement.elementIterator();//or statement �ڶ���ڵ�
           	 	for(;it.hasNext();) //statement�е�block
           	 	{
           	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
           	 		b_f_name=next_element.attributeValue("type");//��һ��block����
           	 		block_List.add(b_f_name);//statement�еĵ�һ��block
           	 		it=next_element.elementIterator();//����һ��
           	 		if(it.hasNext())//ȡnext
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//����һ��
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("or_statement");//����statement
           	 	Element or_next=b_element.element("next");
           	 	it = or_next.elementIterator();//or�����next
           	 	for(;it.hasNext();) //next�е�block
        	 	{
        	 		Element next_element=(Element) it.next(); //���next�еĵ�һ��block
        	 		b_f_name=next_element.attributeValue("type");//��һ��block����
        	 		block_List.add(b_f_name);//next�еĵ�һ��block
        	 		it=next_element.elementIterator();//����һ��
        	 		if(it.hasNext())//ȡnext
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//����һ��
        	 		}
        	 		
        	 	}  
           		
           	 }else if(b_f_name.equals("controls_repeat_while"))
           	 {
            	it= b_element.elementIterator();//while��һ��ڵ� 
            	for(Iterator iner=b_element.elementIterator("statement");iner.hasNext();)//ѡ����condition����body
            	{
            		Element w_element=(Element)iner.next();
                	String sta_name=w_element.attributeValue("name");
            		//�ж϶�����statement������
                	if(sta_name.equals("condition"))
                	{
                		block_List.add("con_statement");//����statement
                		it = w_element.elementIterator();//while������ statement �ڶ���ڵ�
                		//��������ֻ�ܷ�and����or
                       	Element w_con_b=(Element) it.next();//while�е������ڵ�
                       	b_f_name=w_con_b.attributeValue("type");//ȡ��while��condition�е��߼��������
                       	block_List.add(b_f_name);//�����߼��������
                       	if(b_f_name.equals("controls_and"))//�ж������߼���and����or
                       	{
                       		it= w_con_b.elementIterator();//or��һ��ڵ� 
                       		Element and_statement=w_con_b.element("statement");
                       	 	block_List.add("and_statement");//����statement
                       	 	it = and_statement.elementIterator();//or statement �ڶ���ڵ�
                       	 	for(;it.hasNext();) //statement�е�block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                       	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                       	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                       	 		it=next_element.elementIterator();//����һ��
                       	 		if(it.hasNext())//ȡnext
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//����һ��
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("and_statement");//����statement
                       	}else if(b_f_name.equals("controls_or"))
                       	{
                       		it= w_con_b.elementIterator();//or��һ��ڵ� 
                       		Element or_statement=w_con_b.element("statement");
                       	 	block_List.add("or_statement");//����statement
                       	 	it = or_statement.elementIterator();//or statement �ڶ���ڵ�
                       	 	for(;it.hasNext();) //statement�е�block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                       	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                       	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                       	 		it=next_element.elementIterator();//����һ��
                       	 		if(it.hasNext())//ȡnext
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//����һ��
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("or_statement");//����statement
                       	}
                       	block_List.add("con_statement");//����statement
                	}else if(sta_name.equals("body"))//body��ֻ�ܷ���ͨblock
                	{
                		block_List.add("body_statement");//����statement
                		it= w_element.elementIterator();//while��һ��ڵ� 
                		for(;it.hasNext();) //statement�е�block
                   	 	{
                   	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                   	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                   	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                   	 		it=next_element.elementIterator();//����һ��
                   	 		if(it.hasNext())//ȡnext
                   	 		{
                   	 			next_element=(Element) it.next();
                   	 			it=next_element.elementIterator();//����һ��
                   	 		}
                   	 		
                   	 	}    
                		block_List.add("body_statement");//����statement
                	}
            	}
            	Element while_next=b_element.element("next");
           	 	it = while_next.elementIterator();//while�����next
               	for(;it.hasNext();) //whilenext�ڵ��е�block
           	 	{
           	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
           	 		b_f_name=next_element.attributeValue("type");//��һ��block����
           	 		block_List.add(b_f_name);//statement�еĵ�һ��block
           	 		it=next_element.elementIterator();//����һ��
           	 		if(it.hasNext())//ȡnext
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//����һ��
           	 		}
           	 		
           	 	}           	 	
           	 }else if(b_f_name.equals("controls_repeat_until"))
           	 {
            	it= b_element.elementIterator();//until��һ��ڵ� 
            	for(Iterator iner=b_element.elementIterator("statement");iner.hasNext();)//ѡ����condition����body
            	{
            		Element w_element=(Element)iner.next();
                	String sta_name=w_element.attributeValue("name");
            		//�ж϶�����statement������
                	if(sta_name.equals("condition"))
                	{
                		block_List.add("con_statement");//����statement
                		it = w_element.elementIterator();//while������ statement �ڶ���ڵ�
                		//��������ֻ�ܷ�and����or
                       	Element w_con_b=(Element) it.next();//while�е������ڵ�
                       	b_f_name=w_con_b.attributeValue("type");//ȡ��while��condition�е��߼��������
                       	block_List.add(b_f_name);//�����߼��������
                       	if(b_f_name.equals("controls_and"))//�ж������߼���and����or
                       	{
                       		it= w_con_b.elementIterator();//or��һ��ڵ� 
                       		Element and_statement=w_con_b.element("statement");
                       	 	block_List.add("and_statement");//����statement
                       	 	it = and_statement.elementIterator();//or statement �ڶ���ڵ�
                       	 	for(;it.hasNext();) //statement�е�block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                       	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                       	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                       	 		it=next_element.elementIterator();//����һ��
                       	 		if(it.hasNext())//ȡnext
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//����һ��
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("and_statement");//����statement
                       	}else if(b_f_name.equals("controls_or"))
                       	{
                       		it= w_con_b.elementIterator();//or��һ��ڵ� 
                       		Element or_statement=w_con_b.element("statement");
                       	 	block_List.add("or_statement");//����statement
                       	 	it = or_statement.elementIterator();//or statement �ڶ���ڵ�
                       	 	for(;it.hasNext();) //statement�е�block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                       	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                       	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                       	 		it=next_element.elementIterator();//����һ��
                       	 		if(it.hasNext())//ȡnext
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//����һ��
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("or_statement");//����statement
                       	}
                       	block_List.add("con_statement");//����statement
                	}else if(sta_name.equals("body"))//body��ֻ�ܷ���ͨblock
                	{
                		block_List.add("body_statement");//����statement
                		it= w_element.elementIterator();//while��һ��ڵ� 
                		for(;it.hasNext();) //statement�е�block
                   	 	{
                   	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
                   	 		b_f_name=next_element.attributeValue("type");//��һ��block����
                   	 		block_List.add(b_f_name);//statement�еĵ�һ��block
                   	 		it=next_element.elementIterator();//����һ��
                   	 		if(it.hasNext())//ȡnext
                   	 		{
                   	 			next_element=(Element) it.next();
                   	 			it=next_element.elementIterator();//����һ��
                   	 		}
                   	 		
                   	 	}    
                		block_List.add("body_statement");//����statement
                	}
            	}
            	Element while_next=b_element.element("next");
           	 	it = while_next.elementIterator();//until�����next
               	for(;it.hasNext();) //untilnext�ڵ��е�block
           	 	{
           	 		Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
           	 		b_f_name=next_element.attributeValue("type");//��һ��block����
           	 		block_List.add(b_f_name);//statement�еĵ�һ��block
           	 		it=next_element.elementIterator();//����һ��
           	 		if(it.hasNext())//ȡnext
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//����һ��
           	 		}
           	 		
           	 	}           	 	
           	 }if (b_f_name.equals("text_print_delay"))
         	{
         		it= b_element.elementIterator();//delay��һ��ڵ�
         		Element value_element = (Element) it.next(); //delay value�ڵ�
         		it= value_element.elementIterator();//delay������ڵ�
                 Element shadow_element = (Element) it.next(); //delay shadow�ڵ�
                 it = shadow_element.elementIterator();//delay���Ĳ�ڵ�
                 Element num_element = (Element) it.next(); //delay field�ڵ�
            	 	String d_num=num_element.getText();//delay_block�Ĳ���ֵ
            	 	block_List.add(d_num);//delay����Ĳ���            	 	
            	 	Element delay_next=b_element.element("next");
            	 	it = delay_next.elementIterator();//delay�����next
            	 	for(;it.hasNext();) //next�е�block
         	 	{
         	 		Element next_element=(Element) it.next(); //���next�еĵ�һ��block
         	 		b_f_name=next_element.attributeValue("type");//��һ��block����
         	 		block_List.add(b_f_name);//next�еĵ�һ��block
         	 		it=next_element.elementIterator();//����һ��
         	 		if(it.hasNext())//ȡnext
         	 		{
         	 			next_element=(Element) it.next();
         	 			it=next_element.elementIterator();//����һ��
         	 		}
         	 		
         	 	}           	 	
            	         	 	
             }if (b_f_name.equals("text_print_timeout"))
         	{
         		it= b_element.elementIterator();//timeout��һ��ڵ�
         		Element value_element = (Element) it.next(); //timeoutvalue�ڵ�
         		it= value_element.elementIterator();//timeout������ڵ�
                Element shadow_element = (Element) it.next(); //timeout shadow�ڵ�
                it = shadow_element.elementIterator();//timeout���Ĳ�ڵ�
                Element num_element = (Element) it.next(); //timeout field�ڵ�
            	String to_abr=num_element.getText();//for_block�Ĳ���ֵ
            	block_List.add(to_abr);//timeout����Ĳ���
            	Element to_statement=b_element.element("statement");
            	block_List.add("statement");//����statement
            	it = to_statement.elementIterator();//timeout_statement �ڶ���ڵ�
            	for(;it.hasNext();) //statement�е�block
            	{
            	 	Element next_element=(Element) it.next(); //���statement�еĵ�һ��block
            	 	b_f_name=next_element.attributeValue("type");//��һ��block����
            	 	block_List.add(b_f_name);//statement�еĵ�һ��block
            	 	it=next_element.elementIterator();//����һ��
            	 	if(it.hasNext())//ȡnext
            	 	{
            	 		next_element=(Element) it.next();
            	 		it=next_element.elementIterator();//����һ��
            	 	}
            	 		
            	 }           	 	
            	 block_List.add("statement");//����statement
            	 Element to_next=b_element.element("next");
            	 it = to_next.elementIterator();//timeout�����next
            	 for(;it.hasNext();) //next�е�block
         	 	{
         	 		Element next_element=(Element) it.next(); //���next�еĵ�һ��block
         	 		b_f_name=next_element.attributeValue("type");//��һ��block����
         	 		block_List.add(b_f_name);//next�еĵ�һ��block
         	 		it=next_element.elementIterator();//����һ��
         	 		if(it.hasNext())//ȡnext
         	 		{
         	 			next_element=(Element) it.next();
         	 			it=next_element.elementIterator();//����һ��
         	 		}
         	 		
         	 	}           	 	
            	         	 	
            }
        	else
        	{
        		
        		if(it.hasNext())
        		{
        		
            		Element next_element=(Element) it.next(); //���next
            		it= next_element.elementIterator();//block��һ��ڵ�
            		
        		}
        		
        		
        	}
        }
        
       for (int i=0;i<block_List.size();i++)
        {
        	System.out.println(block_List.get(i));
        }    

        
 }
    
 public void uiWrite() throws Exception{

	 Document document = DocumentHelper.createDocument();
     
     Element root = document.addElement("project").addAttribute("name",p_name);

     Element list = root.addElement("list");
     Element next_block;
     
 	 ArrayList List1 = new ArrayList();//xml�е�blocklist
 	 List1.addAll(block_List);

     for (int i=0;i<List1.size();i++)
     {
    	 if (List1.get(i).equals("controls_repeat_for"))
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 i=i+1;
    		 Element abr_elements=next_block.addElement("elements").addAttribute("s","abr");
    		 abr_elements.addText((String)List1.get(i));
    		 Element statement_elements=next_block.addElement("elements").addAttribute("s","statement");
    		 i=i+1;//ָ�����Ƶ�statement
    		 i=i+1;
    		 int end_for=List1.size();
    		 for(int j=i;j<end_for;j++)
    		 {
    			 if(List1.get(i).equals("statement"))
    			 {
    				 j=end_for;
    				 i++;
    			 }
    			 else
    			 {
    				 Element b_statement_elements=statement_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
    				 i++;
    			 }	 
    		 }
    		
    	 }if (List1.get(i).equals("text_print_timeout"))
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 i=i+1;
    		 Element abr_elements=next_block.addElement("elements").addAttribute("s","abr");
    		 abr_elements.addText((String)List1.get(i));
    		 Element statement_elements=next_block.addElement("elements").addAttribute("s","statement");
    		 i=i+1;//ָ�����Ƶ�statement
    		 i=i+1;
    		 int end_for=List1.size();
    		 for(int j=i;j<end_for;j++)
    		 {
    			 if(List1.get(i).equals("statement"))
    			 {
    				 j=end_for;
    				 i++;
    			 }
    			 else
    			 {
    				 Element b_statement_elements=statement_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
    				 i++;
    			 }	 
    		 }
    		
    	 }else if(List1.get(i).equals("controls_and"))
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 i=i+1;
    		 Element statement_elements=next_block.addElement("elements").addAttribute("s","statement");
    		 i=i+1;//ָ�����Ƶ�statement
     		 int end_for=List1.size();
    		 for(int j=i;j<end_for;j++)
    		 {
    			 if(List1.get(i).equals("and_statement"))
    			 {
    				 j=end_for;
    			 }
    			 else
    			 {
    				 Element b_statement_elements=statement_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
    				 i++;
    			 }	 
    		 }
    		 
    	 }else if(List1.get(i).equals("controls_or"))
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 i=i+1;
    		 Element statement_elements=next_block.addElement("elements").addAttribute("s","statement");
    		 i=i+1;//ָ�����Ƶ�statement
     		 int end_for=List1.size();
    		 for(int j=i;j<end_for;j++)
    		 {
    			 if(List1.get(i).equals("or_statement"))
    			 {
    				 j=end_for;
    			 }
    			 else
    			 {
    				 Element b_statement_elements=statement_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
    				 i++;
    			 }	 
    		 }
    		 
    	 }else if(List1.get(i).equals("controls_repeat_while"))//�ж��Ƿ�Ϊwhile���
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 Element condition_elements=next_block.addElement("elements").addAttribute("s","condition");//condition�Ľڵ�
    		 Element body_elements=next_block.addElement("elements").addAttribute("s","body");//body�Ľڵ�
    		 i=i+2;//ָ�����Ƶ�condition�ڵ�����ĵ�һ��block
    		 if(List1.get(i).equals("controls_and"))
			 {
    			 Element w_condition_elements=condition_elements.addElement("block").addAttribute("s",(String)List1.get(i));
    			 Element w_condition_next_elements=w_condition_elements.addElement("elements").addAttribute("s","statement");
    			 int end_and=List1.size();
    			 i=i+2;//and_statement�м�еõ�һ��block
    			 for(int j=i;j<end_and;j++)
        		 {
        			 if(List1.get(i).equals("and_statement"))
        			 {
        				 j=end_and;
        			 }
        			 else
        			 {
        				 Element b_statement_elements=w_condition_next_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
        				 i++;
        			 }	 
        		 }
			 }else if(List1.get(i).equals("controls_or"))
			 {
				 Element w_body_elements=body_elements.addElement("block").addAttribute("s",(String)List1.get(i));
				 Element w_body_next_elements=w_body_elements.addElement("elements").addAttribute("s","statement");
    			 int end_or=List1.size();
    			 i=i+2;//or_statement�м�еõ�һ��block
    			 for(int j=i;j<end_or;j++)
        		 {
        			 if(List1.get(i).equals("or_statement"))
        			 {
        				 j=end_or;
        			 }
        			 else
        			 {
        				 Element b_statement_elements=w_body_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
        				 i++;
        			 }	 
        		 } 
			 }
     		 i++;//����con_statement
     		 i=i+2;//����body_statement
     		 int end_or=List1.size();
			 for(int j=i;j<end_or;j++)
			 {
				 if(List1.get(i).equals("body_statement"))
				 {
					 j=end_or;
				 }
				 else
				 {
	   				 Element b_statement_elements=body_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
	   				 i++;
	   			 }	 
			 } 
			
    	 }else if(List1.get(i).equals("controls_repeat_until"))//�ж��Ƿ�Ϊuntil���
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 Element body_elements=next_block.addElement("elements").addAttribute("s","body");//body�Ľڵ�
    		 Element condition_elements=next_block.addElement("elements").addAttribute("s","condition");//condition�Ľڵ�
    		 i=i+2;//ָ�����Ƶ�body�ڵ�����ĵ�һ��block
    		 for(int j=i;j<List1.size();j++)
			 {
				 if(List1.get(i).equals("body_statement"))
				 {
					 j=List1.size();
				 }
				 else
				 {
	   				 Element b_statement_elements=body_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
	   				 i++;
	   			 }	 
			 } 
    		 i=i+2;
    		 if(List1.get(i).equals("controls_and"))
			 {
    			 Element w_condition_elements=condition_elements.addElement("block").addAttribute("s",(String)List1.get(i));
    			 Element w_condition_next_elements=w_condition_elements.addElement("elements").addAttribute("s","statement");
    			 int end_and=List1.size();
    			 i=i+2;//and_statement�м�еõ�һ��block
    			 for(int j=i;j<end_and;j++)
        		 {
        			 if(List1.get(i).equals("and_statement"))
        			 {
        				 j=end_and;
        			 }
        			 else
        			 {
        				 Element b_statement_elements=w_condition_next_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
        				 i++;
        			 }	 
        		 }
			 }else if(List1.get(i).equals("controls_or"))
			 {
				 Element w_body_elements=body_elements.addElement("block").addAttribute("s",(String)List1.get(i));
				 Element w_body_next_elements=w_body_elements.addElement("elements").addAttribute("s","statement");
    			 int end_or=List1.size();
    			 i=i+2;//or_statement�м�еõ�һ��block
    			 for(int j=i;j<end_or;j++)
        		 {
        			 if(List1.get(i).equals("or_statement"))
        			 {
        				 j=end_or;
        			 }
        			 else
        			 {
        				 Element b_statement_elements=w_body_elements.addElement("block").addAttribute("s",(String)List1.get(i)); 
        				 i++;
        			 }	 
        		 } 
			 }
    		 i++;
    	 }if (List1.get(i).equals("text_print_delay"))
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 i=i+1;
    		 Element num_elements=next_block.addElement("elements").addAttribute("s","abr");
    		 num_elements.addText((String)List1.get(i));
    		 
    	 }
    	 else
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    	 }
     	 
     } 

 	 
 	 XMLWriter writer = new XMLWriter(new FileOutputStream("arcosamplexml_for.xml"),OutputFormat.createPrettyPrint());
     
     writer.write(document);
     writer.close();    
     
 
 	
 }

    //xml��ȡ���벿��
    public static ArrayList operation_List = new ArrayList();
    public static ArrayList dofor_List = new ArrayList();
    public static ArrayList operation_value_List = new ArrayList();
    public static String project_name;
    
    public void cdlRead() throws Exception{
        
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("arcosamplexml_and.xml"));
        
        Element root = document.getRootElement();//project�ڵ�
        project_name= root.attributeValue("name");  
        List childList = root.elements(); //list�ڵ�
        //System.out.println(childList.size()); 
        Element first = root.element("list"); //��list����element��ʽ������
        List blocklist = first.elements(); // block�ڵ�
        //System.out.println(blocklist.size()); 
        
               
        
        for (Iterator iter = first.elementIterator(); iter.hasNext();) {
            Element b_element = (Element) iter.next(); //block�ڵ�
                      
            String b_name=b_element.attributeValue("s");
 
            
            //�ж��Ƿ�ΪFOR���
            if(b_name.equals("controls_repeat_for"))
            {
            	String for_abr="";
            	for (Iterator for_iter = b_element.elementIterator(); for_iter.hasNext();) 
            	{
                    Element for_elements = (Element) for_iter.next(); //elements�ڵ�
                    String e_name=for_elements.attributeValue("s"); 
                   // System.out.println(e_name);
                    if (e_name.equals("abr")) //�ж�for��ģ���ǲ������򽫲�����ֵ������
                    {
                    	for_abr=for_elements.getText();                    	
                      //  System.out.println(for_abr);
                    }
                    else if (e_name.equals("statement"))  //�ж�for��ģ����ѭ����
                    {
                    	int num=0;
                    	if (num<1)
                    	{
                        	for (Iterator for_b = for_elements.elementIterator(); for_b.hasNext();)
                        	{
                                Element for_statement = (Element) for_b.next(); //for�е�statement�ڵ�
                                String for_b_name=for_statement.attributeValue("s");

                                operation_List.add(for_b_name);
                                operation_value_List.add("");
                                dofor_List.add(for_b_name);
                                num++;
                        	}
                    	}

                    }
               	}
            	operation_List.add("doFOR_temp");
            	operation_value_List.add(for_abr);
            }else if(b_name.equals("controls_and"))
            {
            	
            	operation_List.add("controls_and");//����control_and
            	operation_List.add("and_statement");
            	
            	for (Iterator and_iter = b_element.element("elements").elementIterator(); and_iter.hasNext();) //�Ƶ�elements
            	{
     	
            		Element and_statement = (Element) and_iter.next(); //and�е�statement�ڵ�
                    String and_b_name=and_statement.attributeValue("s");
                    operation_List.add(and_b_name);
                    operation_value_List.add("");

               	}
            	
            	operation_List.add("and_statement");
            	

            }else if(b_name.equals("controls_or"))
            {
            	operation_List.add("controls_or");//����control_or
            	operation_List.add("or_statement");
            	
            	for (Iterator and_iter = b_element.element("elements").elementIterator(); and_iter.hasNext();) //�Ƶ�elements
            	{
            		Element and_statement = (Element) and_iter.next(); //or�е�statement�ڵ�
                    String and_b_name=and_statement.attributeValue("s");
                    operation_List.add(and_b_name);
                    operation_value_List.add("");
               	}
            	operation_List.add("or_statement");

            }else if(b_name.equals("controls_repeat_while"))
            {
            	operation_List.add("controls_repeat_while");//����controls_repeat_while
            	            	
            	for (Iterator while_iter = b_element.elementIterator(); while_iter.hasNext();) //�Ƶ�elements
            	{
            		
            		Element while_statement = (Element) while_iter.next(); //while�е�statement�ڵ�
                    String while_elements_name=while_statement.attributeValue("s");
                    
            		if(while_elements_name.equals("condition"))//�ж��Ƿ�Ϊcondition
            		{
            			operation_List.add("con_statement");//����con_statement
            			Iterator while_con_iter = while_statement.elementIterator();
            			Element condition_element = (Element) while_con_iter.next(); //while�е�condition�ڵ�
            			b_name=condition_element.attributeValue("s");
            			operation_List.add(b_name);
            			operation_value_List.add("");
            			if (b_name.equals("controls_and"))
            			{
            				operation_List.add("and_statement");
            				for (Iterator and_iter = condition_element.element("elements").elementIterator(); and_iter.hasNext();) //�Ƶ�elements
                        	{
            					Element next_element = (Element) and_iter.next(); //while�е�condition�ڵ�
            					b_name=next_element.attributeValue("s");
            					operation_List.add(b_name);
                    			operation_value_List.add("");
                        	}
            				operation_List.add("and_statement");
            			}else if(b_name.equals("controls_or"))
            			{
            				operation_List.add("or_statement");
            				for (Iterator or_iter = condition_element.element("elements").elementIterator(); or_iter.hasNext();) //�Ƶ�elements
                        	{
            					Element next_element = (Element) or_iter.next(); //while�е�condition�ڵ�
            					b_name=next_element.attributeValue("s");
            					operation_List.add(b_name);
                    			operation_value_List.add("");
                        	}
            				operation_List.add("or_statement");
            			}
            			operation_List.add("con_statement");//����con_statement
            		}else if(while_elements_name.equals("body"))//�ж��Ƿ�Ϊbody
            		{
            			operation_List.add("body_statement");//����body_statement
            			for (Iterator body_iter = while_statement.elementIterator(); body_iter.hasNext();) //�Ƶ�elements
                    	{
            				Element next_element = (Element) body_iter.next(); //body�е�statement�ڵ�
                            b_name=next_element.attributeValue("s");
                            operation_List.add(b_name);
                            operation_value_List.add("");
                    	}
            			operation_List.add("body_statement");//����body_statement
            			
            		}
                }
            	
           }else if(b_name.equals("controls_repeat_until"))
           {
           	operation_List.add("controls_repeat_until");//����controls_repeat_while
           	            	
           	for (Iterator while_iter = b_element.elementIterator(); while_iter.hasNext();) //�Ƶ�elements
           	{
           		
           		Element while_statement = (Element) while_iter.next(); //while�е�statement�ڵ�
                String while_elements_name=while_statement.attributeValue("s");
                   
           		if(while_elements_name.equals("condition"))//�ж��Ƿ�Ϊcondition
           		{
           			operation_List.add("con_statement");//����con_statement
           			Iterator while_con_iter = while_statement.elementIterator();
           			Element condition_element = (Element) while_con_iter.next(); //while�е�condition�ڵ�
           			b_name=condition_element.attributeValue("s");
           			operation_List.add(b_name);
           			operation_value_List.add("");
           			if (b_name.equals("controls_and"))
           			{
           				operation_List.add("and_statement");
           				for (Iterator and_iter = condition_element.element("elements").elementIterator(); and_iter.hasNext();) //�Ƶ�elements
                       	{
           					Element next_element = (Element) and_iter.next(); //while�е�condition�ڵ�
           					b_name=next_element.attributeValue("s");
           					operation_List.add(b_name);
                   			operation_value_List.add("");
                       	}
           				operation_List.add("and_statement");
           			}else if(b_name.equals("controls_or"))
           			{
           				operation_List.add("or_statement");
           				for (Iterator or_iter = condition_element.element("elements").elementIterator(); or_iter.hasNext();) //�Ƶ�elements
                       	{
           					Element next_element = (Element) or_iter.next(); //while�е�condition�ڵ�
           					b_name=next_element.attributeValue("s");
           					operation_List.add(b_name);
                   			operation_value_List.add("");
                       	}
           				operation_List.add("or_statement");
           			}
           		
           			operation_List.add("con_statement");//����con_statement
           		}else if(while_elements_name.equals("body"))//�ж��Ƿ�Ϊbody
           		{
           			operation_List.add("body_statement");//����body_statement
           			for (Iterator body_iter = while_statement.elementIterator(); body_iter.hasNext();) //�Ƶ�elements
                   	{
           				Element next_element = (Element) body_iter.next(); //body�е�statement�ڵ�
                           b_name=next_element.attributeValue("s");
                           operation_List.add(b_name);
                           operation_value_List.add("");
                   	}
           			operation_List.add("body_statement");//����body_statement
           			
           		}
               }
           	
          }else if(b_name.equals("text_print_delay"))
          {
          	String delay_abr="";
          	for (Iterator delay_iter = b_element.elementIterator(); delay_iter.hasNext();) 
          	{
                  Element delay_elements = (Element) delay_iter.next(); //elements�ڵ�
                  String e_name=delay_elements.attributeValue("s"); 
               	  delay_abr=delay_elements.getText();                    	
              
             }
          	operation_List.add("Delay");
          	operation_value_List.add(delay_abr);
          }else if(b_name.equals("text_print_timeout"))
          {
        	operation_List.add("timeout");
          	String to_abr="";
          	operation_List.add("timeout_statement");
          	for (Iterator to_iter = b_element.elementIterator(); to_iter.hasNext();) 
          	{
                  Element to_elements = (Element) to_iter.next(); //elements�ڵ�
                  String e_name=to_elements.attributeValue("s"); 
                  if (e_name.equals("abr")) //�ж�timeout��ģ���ǲ������򽫲�����ֵ������
                  {
                	  to_abr=to_elements.getText();       
                	  operation_value_List.add(to_abr);
                  }
                  else if (e_name.equals("statement"))  //�ж�timeout��ģ����ѭ����
                  {
                  	int num=0;
                  	if (num<1)
                  	{
                      	for (Iterator for_b = to_elements.elementIterator(); for_b.hasNext();)
                      	{
                              Element for_statement = (Element) for_b.next(); //for�е�statement�ڵ�
                              String for_b_name=for_statement.attributeValue("s");

                              operation_List.add(for_b_name);
                              operation_value_List.add("");
                              num++;
                      	}
                  	}

                  }
             	}
          	operation_List.add("timeout_statement");
          }
            else
            {
            	

                operation_List.add(b_name);
                operation_value_List.add(""); 
            }
        } //end for

/*        System.out.println(operation_value_List.size());
        for (int i=0;i<operation_value_List.size();i++)
        {
        	System.out.println(operation_value_List.get(i));
        }    */   
     
       for (int i=0;i<operation_List.size();i++)
        {
        	System.out.println(operation_List.get(i));
        }    
    }
    

    public void cdlWrite() throws Exception{
    	
        Document document = DocumentHelper.createDocument();
        
        Element root = document.addElement("cdl").addAttribute("name",project_name);

        Element componentlist = root.addElement("list").addAttribute("name", "component");
        Element connectionlist = root.addElement("list").addAttribute("name", "connection");
        Element rulelist = root.addElement("list").addAttribute("name", "rule");;


    	ArrayList List1 = new ArrayList();//cdl��component_list��name
    	List1.addAll(operation_List);

    	ArrayList List2 = new ArrayList();//cdl����Ҫ���ϵ�component_list��name

    	ArrayList List3 = new ArrayList(); //cdl��component������
    	List3.add("Switch");
    	List3.add("LED_1");   	
    	List3.add("LED_2");
    	List3.add("LED_3");
    	List3.add("HT"); 
    	//List3.add("HT");
    	
    	ArrayList List4 = new ArrayList();//component�Ĳ���
    	List4.addAll(operation_value_List);

    	//connectionlist������
    	ArrayList List5 = new ArrayList();//cdl��connection_list��name
    	int for_m=0;//for�ĸ���
    	ArrayList for_id_List = new ArrayList();//for���������ID��list
    	ArrayList for_name_begin_List = new ArrayList();//for����������ڲ�block��ʼ�ĵ�list
    	for_name_begin_List.addAll(dofor_List);  
    	ArrayList for_id_begin_List = new ArrayList();//for����������ڲ�block��ʼ��id��list
    	ArrayList List6 = new ArrayList();//cdl��connection_list��from
    	ArrayList List7 = new ArrayList();//cdl��connection_list��to
		int and_begin=0;
		int and_end=0;
		int or_begin=0;
		int or_end=0;		
		int while_con_begin=0;
		int while_con_end=0;
		int while_body_begin=0;
		int while_body_end=0;
		int until_body_begin=0;
		int until_body_end=0;
		int until_con_begin=0;
		int until_con_end=0;
		int timeout_begin=0;
		int timeout_end=0;
				
    	for(int i=0;i<List1.size();i++)//�����õ���component�б�
    	{

    		if(List1.get(i).equals("controls_and"))
    		{
    			i=i+2;//������Ƶ�and_statement����ĵ�һ�����
    			and_begin=i;
    			
    		}else if(List1.get(i).equals("controls_or"))
    		{
    			i=i+2;//������Ƶ�or_statement����ĵ�һ�����
    			or_begin=i;
    		}
    		else if(List1.get(i).equals("and_statement"))
    		{
    			and_end=i-1;
    		
    			i=i+1;

    		}else if(List1.get(i).equals("or_statement"))
    		{
    		
    			or_end=i-1;
    			i=i+1;

    		}else if(List1.get(i).equals("controls_repeat_while"))
    		{
    			while_con_begin=i+4;
    			i=i+2;//����Ƶ������жϵĽڵ�
    			if(List1.get(i).equals("controls_and"))
    			{
    				i=i+2;//������Ƶ�and_statement����ĵ�һ�����
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("and_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        				
        			}
        			//����ʱ��and_statement��
        			while_con_end=i-1;
        			i=i+3;//����Ƶ�body_statement��һ�����
        			while_body_begin=i;
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("body_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        				
        			}
        			while_body_end=i-1;
        			i++;
        			
    			}else if(List1.get(i).equals("controls_or"))
    			{
    				i=i+2;//������Ƶ�or_statement����ĵ�һ�����
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("or_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        			}
        			while_con_end=i-1;
        			i=i+3;//����Ƶ�body_statement��һ�����
        			while_body_begin=i;
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("body_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        				
        			}
        			while_body_end=i-1;
        			i++;
    			}
    			
    		}else if(List1.get(i).equals("controls_repeat_until"))
    		{
    			until_body_begin=i+2;
    			i=i+2;//����Ƶ������жϵĽڵ�
    			for(int j=i;j<List1.size();j++)
    			{
    				if(List1.get(j).equals("body_statement"))
    				{
    					j=List1.size();
    				}else 
    				{
    					List2.add(List1.get(j));
    					i++;
    				}
    				
    			}
    			until_body_end=i-1;
    			i=i+2;
    					
    			if(List1.get(i).equals("controls_and"))
    			{
    				i=i+2;//������Ƶ�and_statement����ĵ�һ�����
    				until_con_begin=i;
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("and_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        				
        			}
        			//����ʱ��and_statement��
        			until_con_end=i-1;
        			i++;
        			
    			}else if(List1.get(i).equals("controls_or"))
    			{
    				i=i+2;//������Ƶ�or_statement����ĵ�һ�����
    				until_con_begin=i;
        			for(int j=i;j<List1.size();j++)
        			{
        				if(List1.get(j).equals("or_statement"))
        				{
        					j=List1.size();
        				}else 
        				{
        					List2.add(List1.get(j));
        					i++;
        				}
        			}
        			until_con_end=i-1;
        			i=i+1;

    			}
    			i++;
    			
    		}else if(List1.get(i).equals("timeout"))
    		{
    			timeout_begin=i+2;
    			List2.add(List1.get(i));
    			i=i+2;//����Ƶ�timeout_statement��һλ
    			for(int j=i;j<List1.size();j++)
    			{
    				if(List1.get(j).equals("timeout_statement"))
    				{
    					j=List1.size();
    				}else 
    				{
    					List2.add(List1.get(j));
    					i++;
    				}
    			}
    			timeout_end=i-1;
    			i++;//����Ƶ�timeout_statement��һλ
    			

    		}
    		List2.add(List1.get(i));
    	}

        for (int i=0;i<List2.size();i++)
        {
        	System.out.println(List2.get(i));

        }    
        //System.out.println(until_con_end);
        if (while_con_begin==0&&and_begin==0&&or_begin==0&&until_body_begin==0&&for_m==0&&timeout_begin==0)
        {
        	 for (int i=0;i<List2.size();i++)//��дcomponentlist
             {
             	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
             	Element componenttype = component.addElement("type").addText((String)List3.get(i));
             	Element componentabr = component.addElement("value").addText((String)List4.get(i));
             }
        	 for (int i=0;i<(List2.size()-1);i++)
             {
          	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(i+1));
          	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
          	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//����һ��to

             }
        }
        if(timeout_begin>0)//ֻ��timeout
        {

             //����rules����
             Element rule = rulelist.addElement("rule").addAttribute("name", "timeout");
             Element rule_t_from = rule.addElement("from").addAttribute("name", (String)List1.get(timeout_begin));
             Element rule_t_to = rule.addElement("to").addAttribute("name", (String)List1.get(timeout_end));
             for (int i=0;i<List2.size();i++)//������timeout
             {
            	 if(List2.get(i).equals("timeout"))
            	 {
            		 Element rule_t_value = rule.addElement("value");
            		 rule_t_value.addText((String)List4.get(i));
            		 List2.remove(i);
            		 List4.remove(i);
            	 }
             }
             
         	//ֱ��ȡ��timeout������
        	 for (int i=0;i<List2.size();i++)//��дcomponentlist
             {
             	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
             	Element componenttype = component.addElement("type").addText((String)List3.get(i));
             	Element componentabr = component.addElement("value").addText((String)List4.get(i));
             }
        	 //connection���ǰ���List2��ֱ������
             for(int j=0;j<(List2.size()-1);j++)
             {
          	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
          	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List2.get(j));//����1��from
          	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List2.get(j+1));//����һ��to
          	   
             }
        }
        
        
        if(while_con_begin>0)//ֻ��while 
        {
        	List2.add("W1");//����while���ɵ��м�component��
        	List3.add("VDEV");
        	List2.add("W2");
//        	List3.add("VDEV");
//        	List4.add("");
//        	List4.add("");
        	
            for (int i=0;i<List2.size();i++)//��дcomponentlist
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
            //������while֮ǰ�����֮���con
            int con_num=0;
           for(int j=0;j<(while_con_begin-5);j++)
           {
        	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
        	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(j));//����1��from
        	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(j+1));//����һ��to
        	   con_num++;
           }

           if(List1.get(while_con_begin-1).equals("and_statement"))//�����������and��������һ��con
           {
        	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
        	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin-5));//����1��from
        	   for(int i=while_con_begin;i<=while_con_end;i++)
        	   {
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
        	   }
        	   Element connectionto = connection.addElement("to").addAttribute("name", "W1");//����һ��to
        	   con_num++;
           }else if(List1.get(while_con_begin-1).equals("or_statement"))//�����������and��������n��con
           {
        	   for(int i=0;i<=(while_con_end-while_con_begin);i++)
        	   {
        		   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        		   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin-5));//����1��from        		   
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin+i));//����1��from
        		   Element connectionto = connection.addElement("to").addAttribute("name", "W1");//����һ��to
        		   con_num++;
        	   }
        	
           }
           //W1������������connection
       
           Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
           con_num++;
           Element connectionfrom = connection.addElement("from").addAttribute("name", "W1");//����1��from����W1��body�ڵĵ�һ��
           Element connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_begin));//����һ��to
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
           connectionfrom = connection.addElement("from").addAttribute("name", "W1");//����1��from����W1��body���
           connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(while_body_end+2));//����һ��to
           con_num++;
           for(int i=0;i<(while_body_end-while_body_begin);i++)//����body���е�block��������Ӧ�м��connection
           {
        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        	   con_num++;
        	   connectionfrom = connection.addElement("from").addAttribute("name",(String)List1.get(while_body_begin+i));//����1��from����W1��body���
               connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(while_body_begin+i+1));//����һ��to
           }
           //����W2���ؼ���connection����Ҫ����condition�е�block
           
           
           if(List1.get(while_con_begin-1).equals("and_statement"))//�����������and��������һ��con
           {
        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
        	   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_body_end));//����1��from
        	   for(int i=while_con_begin;i<=while_con_end;i++)
        	   {
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
        	   }
        	   connectionto = connection.addElement("to").addAttribute("name", "W2");//����һ��to
        	   con_num++;
           }else if(List1.get(while_con_begin-1).equals("or_statement"))//�����������and��������n��con
           {
        	   for(int i=0;i<=(while_con_end-while_con_begin);i++)
        	   {
        		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_body_end));//����1��from        		   
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin+i));//����1��from
        		   connectionto = connection.addElement("to").addAttribute("name", "W2");//����һ��to
        		   con_num++;
        	   }
        	
           }
           
           //W2������Ҫ����2��connection
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1)); 
           con_num++;
           connectionfrom = connection.addElement("from").addAttribute("name", "W2");//����1��from
		   connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_begin));//����һ��to
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1)); 
           connectionfrom = connection.addElement("from").addAttribute("name", "W2");//����1��from
		   connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_end+2));//����һ��to
           //while�����block֮���connection
		   con_num++;
		   int post_num=List1.size()-(while_body_end+3);
		   if (post_num>0)
		   {
	      		for(int i=(while_body_end+3);i<List1.size();i++)
	      		{
	     		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	     		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from        		   
	     		   connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//����һ��to
	     		   con_num++;
	      		}
		   }
        }
        
        if(until_con_begin>0)//ֻ��until 
        {
        	List2.add("UN");//����while���ɵ��м�component��
        	List3.add("VDEV");
        	List4.add("");
            for (int i=0;i<List2.size();i++)//��дcomponentlist
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
            //������until֮ǰ�����֮���con
            int con_num=0;
            for(int j=0;j<(until_body_begin-3);j++)
            {
         	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
         	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(j));//����1��from
         	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(j+1));//����һ��to
         	   con_num++;
            }
            //until֮ǰ��block��body��һ����con
            Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
            Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_begin-3));//����1��from
      	   	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin));//����һ��to
      	   	con_num++;
            //body֮���block���connection
	      	for(int i=0;i<(until_body_end-until_body_begin);i++)//����body���е�block��������Ӧ�м��connection
	        {
	      		connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	      		con_num++;
	       	   	connectionfrom = connection.addElement("from").addAttribute("name",(String)List1.get(until_body_begin+i));//����1��from����W1��body���
	            connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin+i+1));//����һ��to
	         }
	      	//����condition�е�type��������Ӧ������connection
	      	 if(List1.get(until_con_begin-1).equals("and_statement"))//�����������and��������һ��con
	           {
	        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
	        	   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_end));//����1��from
	        	   for(int i=until_con_begin;i<=until_con_end;i++)
	        	   {
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
	        	   }
	        	   connectionto = connection.addElement("to").addAttribute("name", "UN");//����һ��to
	        	   con_num++;
	           }else if(List1.get(until_con_begin-1).equals("or_statement"))//�����������and��������n��con
	           {
	        	   for(int i=0;i<=(until_con_end-until_con_begin);i++)
	        	   {
	        		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_end));//����1��from        		   
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_con_begin+i));//����1��from
	        		   connectionto = connection.addElement("to").addAttribute("name", "UN");//����һ��to
	        		   con_num++;
	        	   }
	        	
	           }
	      	 //UN��������2��connection
	      	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
	      	connectionfrom = connection.addElement("from").addAttribute("name", "UN");//����1��from 
	      	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_con_end+3));//����һ��to
	      	con_num++;
	      	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
	      	connectionfrom = connection.addElement("from").addAttribute("name", "UN");//����1��from 
	      	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin));//����һ��to
	      	//until�����block֮���connection
	      	con_num++;
	      	int post_num=List1.size()-(until_con_end+4);
	      	if (post_num>0)
	      	{
	      		for(int i=(until_con_end+3);i<List1.size();i++)
	      		{
	     		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	     		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from        		   
	     		   connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//����һ��to
	     		   con_num++;
	      		}
	      	}
        }
    	
    	if (and_begin>0)//����and��andǰ�治�������
    	{
    		int j=1;
    		Element connection = connectionlist.addElement("connection").addAttribute("name", "con_1");
    		for (int i=and_begin;i<=and_end;i++)
            {
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//������from
            }
    		Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(and_end+2));//����һ��to

            for (int i=and_end+2;i<(List1.size()-1);i++)
            {
            	
            	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
            	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//����һ��to
            	j++;
            }
            for (int i=0;i<List2.size();i++)
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
    		
    	}
    	
    	if (or_begin>0)//����or��orǰ�治�������
    	{
    		int j=1;//connection����
    		
    		for (int i=or_begin;i<=or_end;i++)
            {
    			Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+j);
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
            	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(or_end+2));//����һ��to
            	j++;
            }
    		

            for (int i=or_end+2;i<(List1.size()-1);i++)
            {
            	
            	Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+j);
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//����1��from
            	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//����һ��to
            	j++;
            }
            for (int i=0;i<List2.size();i++)
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
    		
    	}
    	
        //����dofor�ĸ���
        for (int i=0;i<List1.size();i++)
        {
        	if (List1.get(i).equals("doFOR_temp"))
        	{
        		for_m++;
        	}
        }
    
    	//���dofor��������Ӧ��connection
        if(for_m>0)//�������for
        {
            for (int i=0;i<(List1.size()-1);i++)//�����б�component������һ����connection
            {
            	List5.add("con_"+(i+1));
            	List6.add(List1.get(i));
            	List7.add(List1.get(i+1));
            }
        	
            for (int i=0;i<for_m;i++)
            {
            	List5.add("con_"+(List5.size()+1));
            }
    	    if (for_m==1)
    	    {
    	        for (int i=0;i<List1.size();i++)
    	         {
    	             if (List1.get(i).equals("doFOR_temp"))
    	             	{
    	            	 	List6.add(List1.get(i));
    	              	}else if (List1.get(i).equals(for_name_begin_List.get(0)))
    	            	{
    	              		List7.add(for_name_begin_List.get(0));
    	            	}
    	         }
    	        
    	    }
            for (int i=0;i<List1.size();i++)
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List1.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
                   
            
            for (int i=0;i<List5.size();i++)
            {
            	Element connection = connectionlist.addElement("connection").addAttribute("name", (String)List5.get(i));
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List6.get(i));
            	Element connectionto = connection.addElement("to").addAttribute("name", (String)List7.get(i));
            	
            }      
        }

        
        XMLWriter writer = new XMLWriter(new FileOutputStream("do_andsample.xml"),OutputFormat.createPrettyPrint());
        
        writer.write(document);
        writer.close();    
        
    }
    
}
