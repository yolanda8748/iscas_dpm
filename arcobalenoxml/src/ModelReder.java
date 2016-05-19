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
    
    
    public static ArrayList block_List = new ArrayList();//UI中的xml读出来的blocklist
    public static String p_name="";
    
 public void uiRead() throws Exception{
	
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("ui_forsample.xml"));
        
        Element root = document.getRootElement();//project节点
        p_name=root.attributeValue("name"); //project的名字
        Iterator it = root.elementIterator(); 
       
        
        for(;it.hasNext();)
        {
        	 Element b_element = (Element) it.next(); //block节点
             String b_f_name=b_element.attributeValue("type");//第一层block名字
             block_List.add(b_f_name);//往block_List中放入第一层block
             String b_f_id="";//第一层blockID
             it= b_element.elementIterator();//for第一层节点
        	if (b_f_name.equals("controls_repeat_for"))
        	{
        		it= b_element.elementIterator();//for第一层节点
        		Element value_element = (Element) it.next(); //for value节点
        		it= value_element.elementIterator();//for第三层节点
                Element shadow_element = (Element) it.next(); //for shadow节点
                it = shadow_element.elementIterator();//for第四层节点
                Element abr_element = (Element) it.next(); //for field节点
           	 	String f_abr=abr_element.getText();//for_block的参数值
           	 	block_List.add(f_abr);//for后面的参数
           	 	Element for_statement=b_element.element("statement");
           	 	block_List.add("statement");//放入statement
           	 	it = for_statement.elementIterator();//for statement 第二层节点
           	 	for(;it.hasNext();) //statement中的block
           	 	{
           	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
           	 		b_f_name=next_element.attributeValue("type");//第一个block名字
           	 		block_List.add(b_f_name);//statement中的第一个block
           	 		it=next_element.elementIterator();//下移一行
           	 		if(it.hasNext())//取next
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//下移一行
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("statement");//放入statement
           	 	Element for_next=b_element.element("next");
           	 	it = for_next.elementIterator();//for组件的next
           	 	for(;it.hasNext();) //next中的block
        	 	{
        	 		Element next_element=(Element) it.next(); //获得next中的第一个block
        	 		b_f_name=next_element.attributeValue("type");//第一个block名字
        	 		block_List.add(b_f_name);//next中的第一个block
        	 		it=next_element.elementIterator();//下移一行
        	 		if(it.hasNext())//取next
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//下移一行
        	 		}
        	 		
        	 	}           	 	
           	         	 	
           	 }else if(b_f_name.equals("controls_and"))
           	 {
           		it= b_element.elementIterator();//and第一层节点 
           		Element and_statement=b_element.element("statement");
           	 	block_List.add("and_statement");//放入statement
           	 	it = and_statement.elementIterator();//for statement 第二层节点
           	 	for(;it.hasNext();) //statement中的block
           	 	{
           	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
           	 		b_f_name=next_element.attributeValue("type");//第一个block名字
           	 		block_List.add(b_f_name);//statement中的第一个block
           	 		it=next_element.elementIterator();//下移一行
           	 		if(it.hasNext())//取next
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//下移一行
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("and_statement");//放入statement
           	 	Element and_next=b_element.element("next");
           	 	it = and_next.elementIterator();//and组件的next
           	 	for(;it.hasNext();) //next中的block
        	 	{
        	 		Element next_element=(Element) it.next(); //获得next中的第一个block
        	 		b_f_name=next_element.attributeValue("type");//第一个block名字
        	 		block_List.add(b_f_name);//next中的第一个block
        	 		it=next_element.elementIterator();//下移一行
        	 		if(it.hasNext())//取next
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//下移一行
        	 		}
        	 		
        	 	}  
           		
           	 }else if(b_f_name.equals("controls_or"))
           	 {
           		it= b_element.elementIterator();//or第一层节点 
           		Element or_statement=b_element.element("statement");
           	 	block_List.add("or_statement");//放入statement
           	 	it = or_statement.elementIterator();//or statement 第二层节点
           	 	for(;it.hasNext();) //statement中的block
           	 	{
           	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
           	 		b_f_name=next_element.attributeValue("type");//第一个block名字
           	 		block_List.add(b_f_name);//statement中的第一个block
           	 		it=next_element.elementIterator();//下移一行
           	 		if(it.hasNext())//取next
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//下移一行
           	 		}
           	 		
           	 	}           	 	
           	 	block_List.add("or_statement");//放入statement
           	 	Element or_next=b_element.element("next");
           	 	it = or_next.elementIterator();//or组件的next
           	 	for(;it.hasNext();) //next中的block
        	 	{
        	 		Element next_element=(Element) it.next(); //获得next中的第一个block
        	 		b_f_name=next_element.attributeValue("type");//第一个block名字
        	 		block_List.add(b_f_name);//next中的第一个block
        	 		it=next_element.elementIterator();//下移一行
        	 		if(it.hasNext())//取next
        	 		{
        	 			next_element=(Element) it.next();
        	 			it=next_element.elementIterator();//下移一行
        	 		}
        	 		
        	 	}  
           		
           	 }else if(b_f_name.equals("controls_repeat_while"))
           	 {
            	it= b_element.elementIterator();//while第一层节点 
            	for(Iterator iner=b_element.elementIterator("statement");iner.hasNext();)//选择是condition还是body
            	{
            		Element w_element=(Element)iner.next();
                	String sta_name=w_element.attributeValue("name");
            		//判断读到的statement的类型
                	if(sta_name.equals("condition"))
                	{
                		block_List.add("con_statement");//放入statement
                		it = w_element.elementIterator();//while中条件 statement 第二层节点
                		//条件框中只能放and或者or
                       	Element w_con_b=(Element) it.next();//while中的条件节点
                       	b_f_name=w_con_b.attributeValue("type");//取出while的condition中的逻辑组件类型
                       	block_List.add(b_f_name);//放入逻辑组件类型
                       	if(b_f_name.equals("controls_and"))//判断条件逻辑是and还是or
                       	{
                       		it= w_con_b.elementIterator();//or第一层节点 
                       		Element and_statement=w_con_b.element("statement");
                       	 	block_List.add("and_statement");//放入statement
                       	 	it = and_statement.elementIterator();//or statement 第二层节点
                       	 	for(;it.hasNext();) //statement中的block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                       	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                       	 		block_List.add(b_f_name);//statement中的第一个block
                       	 		it=next_element.elementIterator();//下移一行
                       	 		if(it.hasNext())//取next
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//下移一行
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("and_statement");//放入statement
                       	}else if(b_f_name.equals("controls_or"))
                       	{
                       		it= w_con_b.elementIterator();//or第一层节点 
                       		Element or_statement=w_con_b.element("statement");
                       	 	block_List.add("or_statement");//放入statement
                       	 	it = or_statement.elementIterator();//or statement 第二层节点
                       	 	for(;it.hasNext();) //statement中的block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                       	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                       	 		block_List.add(b_f_name);//statement中的第一个block
                       	 		it=next_element.elementIterator();//下移一行
                       	 		if(it.hasNext())//取next
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//下移一行
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("or_statement");//放入statement
                       	}
                       	block_List.add("con_statement");//放入statement
                	}else if(sta_name.equals("body"))//body中只能放普通block
                	{
                		block_List.add("body_statement");//放入statement
                		it= w_element.elementIterator();//while第一层节点 
                		for(;it.hasNext();) //statement中的block
                   	 	{
                   	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                   	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                   	 		block_List.add(b_f_name);//statement中的第一个block
                   	 		it=next_element.elementIterator();//下移一行
                   	 		if(it.hasNext())//取next
                   	 		{
                   	 			next_element=(Element) it.next();
                   	 			it=next_element.elementIterator();//下移一行
                   	 		}
                   	 		
                   	 	}    
                		block_List.add("body_statement");//放入statement
                	}
            	}
            	Element while_next=b_element.element("next");
           	 	it = while_next.elementIterator();//while组件的next
               	for(;it.hasNext();) //whilenext节点中的block
           	 	{
           	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
           	 		b_f_name=next_element.attributeValue("type");//第一个block名字
           	 		block_List.add(b_f_name);//statement中的第一个block
           	 		it=next_element.elementIterator();//下移一行
           	 		if(it.hasNext())//取next
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//下移一行
           	 		}
           	 		
           	 	}           	 	
           	 }else if(b_f_name.equals("controls_repeat_until"))
           	 {
            	it= b_element.elementIterator();//until第一层节点 
            	for(Iterator iner=b_element.elementIterator("statement");iner.hasNext();)//选择是condition还是body
            	{
            		Element w_element=(Element)iner.next();
                	String sta_name=w_element.attributeValue("name");
            		//判断读到的statement的类型
                	if(sta_name.equals("condition"))
                	{
                		block_List.add("con_statement");//放入statement
                		it = w_element.elementIterator();//while中条件 statement 第二层节点
                		//条件框中只能放and或者or
                       	Element w_con_b=(Element) it.next();//while中的条件节点
                       	b_f_name=w_con_b.attributeValue("type");//取出while的condition中的逻辑组件类型
                       	block_List.add(b_f_name);//放入逻辑组件类型
                       	if(b_f_name.equals("controls_and"))//判断条件逻辑是and还是or
                       	{
                       		it= w_con_b.elementIterator();//or第一层节点 
                       		Element and_statement=w_con_b.element("statement");
                       	 	block_List.add("and_statement");//放入statement
                       	 	it = and_statement.elementIterator();//or statement 第二层节点
                       	 	for(;it.hasNext();) //statement中的block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                       	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                       	 		block_List.add(b_f_name);//statement中的第一个block
                       	 		it=next_element.elementIterator();//下移一行
                       	 		if(it.hasNext())//取next
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//下移一行
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("and_statement");//放入statement
                       	}else if(b_f_name.equals("controls_or"))
                       	{
                       		it= w_con_b.elementIterator();//or第一层节点 
                       		Element or_statement=w_con_b.element("statement");
                       	 	block_List.add("or_statement");//放入statement
                       	 	it = or_statement.elementIterator();//or statement 第二层节点
                       	 	for(;it.hasNext();) //statement中的block
                       	 	{
                       	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                       	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                       	 		block_List.add(b_f_name);//statement中的第一个block
                       	 		it=next_element.elementIterator();//下移一行
                       	 		if(it.hasNext())//取next
                       	 		{
                       	 			next_element=(Element) it.next();
                       	 			it=next_element.elementIterator();//下移一行
                       	 		}
                       	 		
                       	 	}           	 	
                       	 	block_List.add("or_statement");//放入statement
                       	}
                       	block_List.add("con_statement");//放入statement
                	}else if(sta_name.equals("body"))//body中只能放普通block
                	{
                		block_List.add("body_statement");//放入statement
                		it= w_element.elementIterator();//while第一层节点 
                		for(;it.hasNext();) //statement中的block
                   	 	{
                   	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
                   	 		b_f_name=next_element.attributeValue("type");//第一个block名字
                   	 		block_List.add(b_f_name);//statement中的第一个block
                   	 		it=next_element.elementIterator();//下移一行
                   	 		if(it.hasNext())//取next
                   	 		{
                   	 			next_element=(Element) it.next();
                   	 			it=next_element.elementIterator();//下移一行
                   	 		}
                   	 		
                   	 	}    
                		block_List.add("body_statement");//放入statement
                	}
            	}
            	Element while_next=b_element.element("next");
           	 	it = while_next.elementIterator();//until组件的next
               	for(;it.hasNext();) //untilnext节点中的block
           	 	{
           	 		Element next_element=(Element) it.next(); //获得statement中的第一个block
           	 		b_f_name=next_element.attributeValue("type");//第一个block名字
           	 		block_List.add(b_f_name);//statement中的第一个block
           	 		it=next_element.elementIterator();//下移一行
           	 		if(it.hasNext())//取next
           	 		{
           	 			next_element=(Element) it.next();
           	 			it=next_element.elementIterator();//下移一行
           	 		}
           	 		
           	 	}           	 	
           	 }if (b_f_name.equals("text_print_delay"))
         	{
         		it= b_element.elementIterator();//delay第一层节点
         		Element value_element = (Element) it.next(); //delay value节点
         		it= value_element.elementIterator();//delay第三层节点
                 Element shadow_element = (Element) it.next(); //delay shadow节点
                 it = shadow_element.elementIterator();//delay第四层节点
                 Element num_element = (Element) it.next(); //delay field节点
            	 	String d_num=num_element.getText();//delay_block的参数值
            	 	block_List.add(d_num);//delay后面的参数            	 	
            	 	Element delay_next=b_element.element("next");
            	 	it = delay_next.elementIterator();//delay组件的next
            	 	for(;it.hasNext();) //next中的block
         	 	{
         	 		Element next_element=(Element) it.next(); //获得next中的第一个block
         	 		b_f_name=next_element.attributeValue("type");//第一个block名字
         	 		block_List.add(b_f_name);//next中的第一个block
         	 		it=next_element.elementIterator();//下移一行
         	 		if(it.hasNext())//取next
         	 		{
         	 			next_element=(Element) it.next();
         	 			it=next_element.elementIterator();//下移一行
         	 		}
         	 		
         	 	}           	 	
            	         	 	
             }if (b_f_name.equals("text_print_timeout"))
         	{
         		it= b_element.elementIterator();//timeout第一层节点
         		Element value_element = (Element) it.next(); //timeoutvalue节点
         		it= value_element.elementIterator();//timeout第三层节点
                Element shadow_element = (Element) it.next(); //timeout shadow节点
                it = shadow_element.elementIterator();//timeout第四层节点
                Element num_element = (Element) it.next(); //timeout field节点
            	String to_abr=num_element.getText();//for_block的参数值
            	block_List.add(to_abr);//timeout后面的参数
            	Element to_statement=b_element.element("statement");
            	block_List.add("statement");//放入statement
            	it = to_statement.elementIterator();//timeout_statement 第二层节点
            	for(;it.hasNext();) //statement中的block
            	{
            	 	Element next_element=(Element) it.next(); //获得statement中的第一个block
            	 	b_f_name=next_element.attributeValue("type");//第一个block名字
            	 	block_List.add(b_f_name);//statement中的第一个block
            	 	it=next_element.elementIterator();//下移一行
            	 	if(it.hasNext())//取next
            	 	{
            	 		next_element=(Element) it.next();
            	 		it=next_element.elementIterator();//下移一行
            	 	}
            	 		
            	 }           	 	
            	 block_List.add("statement");//放入statement
            	 Element to_next=b_element.element("next");
            	 it = to_next.elementIterator();//timeout组件的next
            	 for(;it.hasNext();) //next中的block
         	 	{
         	 		Element next_element=(Element) it.next(); //获得next中的第一个block
         	 		b_f_name=next_element.attributeValue("type");//第一个block名字
         	 		block_List.add(b_f_name);//next中的第一个block
         	 		it=next_element.elementIterator();//下移一行
         	 		if(it.hasNext())//取next
         	 		{
         	 			next_element=(Element) it.next();
         	 			it=next_element.elementIterator();//下移一行
         	 		}
         	 		
         	 	}           	 	
            	         	 	
            }
        	else
        	{
        		
        		if(it.hasNext())
        		{
        		
            		Element next_element=(Element) it.next(); //获得next
            		it= next_element.elementIterator();//block第一层节点
            		
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
     
 	 ArrayList List1 = new ArrayList();//xml中的blocklist
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
    		 i=i+1;//指针下移到statement
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
    		 i=i+1;//指针下移到statement
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
    		 i=i+1;//指针下移到statement
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
    		 i=i+1;//指针下移到statement
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
    		 
    	 }else if(List1.get(i).equals("controls_repeat_while"))//判断是否为while组件
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 Element condition_elements=next_block.addElement("elements").addAttribute("s","condition");//condition的节点
    		 Element body_elements=next_block.addElement("elements").addAttribute("s","body");//body的节点
    		 i=i+2;//指针下移到condition节点下面的第一个block
    		 if(List1.get(i).equals("controls_and"))
			 {
    			 Element w_condition_elements=condition_elements.addElement("block").addAttribute("s",(String)List1.get(i));
    			 Element w_condition_next_elements=w_condition_elements.addElement("elements").addAttribute("s","statement");
    			 int end_and=List1.size();
    			 i=i+2;//and_statement中间夹得第一个block
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
    			 i=i+2;//or_statement中间夹得第一个block
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
     		 i++;//读到con_statement
     		 i=i+2;//读到body_statement
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
			
    	 }else if(List1.get(i).equals("controls_repeat_until"))//判断是否为until组件
    	 {
    		 next_block = list.addElement("block").addAttribute("s", (String)List1.get(i));
    		 Element body_elements=next_block.addElement("elements").addAttribute("s","body");//body的节点
    		 Element condition_elements=next_block.addElement("elements").addAttribute("s","condition");//condition的节点
    		 i=i+2;//指针下移到body节点下面的第一个block
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
    			 i=i+2;//and_statement中间夹得第一个block
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
    			 i=i+2;//or_statement中间夹得第一个block
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

    //xml读取代码部分
    public static ArrayList operation_List = new ArrayList();
    public static ArrayList dofor_List = new ArrayList();
    public static ArrayList operation_value_List = new ArrayList();
    public static String project_name;
    
    public void cdlRead() throws Exception{
        
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("arcosamplexml_and.xml"));
        
        Element root = document.getRootElement();//project节点
        project_name= root.attributeValue("name");  
        List childList = root.elements(); //list节点
        //System.out.println(childList.size()); 
        Element first = root.element("list"); //将list按照element形式存下来
        List blocklist = first.elements(); // block节点
        //System.out.println(blocklist.size()); 
        
               
        
        for (Iterator iter = first.elementIterator(); iter.hasNext();) {
            Element b_element = (Element) iter.next(); //block节点
                      
            String b_name=b_element.attributeValue("s");
 
            
            //判断是否为FOR组件
            if(b_name.equals("controls_repeat_for"))
            {
            	String for_abr="";
            	for (Iterator for_iter = b_element.elementIterator(); for_iter.hasNext();) 
            	{
                    Element for_elements = (Element) for_iter.next(); //elements节点
                    String e_name=for_elements.attributeValue("s"); 
                   // System.out.println(e_name);
                    if (e_name.equals("abr")) //判断for的模块是参数，则将参数的值存下来
                    {
                    	for_abr=for_elements.getText();                    	
                      //  System.out.println(for_abr);
                    }
                    else if (e_name.equals("statement"))  //判断for的模块是循环体
                    {
                    	int num=0;
                    	if (num<1)
                    	{
                        	for (Iterator for_b = for_elements.elementIterator(); for_b.hasNext();)
                        	{
                                Element for_statement = (Element) for_b.next(); //for中的statement节点
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
            	
            	operation_List.add("controls_and");//放入control_and
            	operation_List.add("and_statement");
            	
            	for (Iterator and_iter = b_element.element("elements").elementIterator(); and_iter.hasNext();) //移到elements
            	{
     	
            		Element and_statement = (Element) and_iter.next(); //and中的statement节点
                    String and_b_name=and_statement.attributeValue("s");
                    operation_List.add(and_b_name);
                    operation_value_List.add("");

               	}
            	
            	operation_List.add("and_statement");
            	

            }else if(b_name.equals("controls_or"))
            {
            	operation_List.add("controls_or");//放入control_or
            	operation_List.add("or_statement");
            	
            	for (Iterator and_iter = b_element.element("elements").elementIterator(); and_iter.hasNext();) //移到elements
            	{
            		Element and_statement = (Element) and_iter.next(); //or中的statement节点
                    String and_b_name=and_statement.attributeValue("s");
                    operation_List.add(and_b_name);
                    operation_value_List.add("");
               	}
            	operation_List.add("or_statement");

            }else if(b_name.equals("controls_repeat_while"))
            {
            	operation_List.add("controls_repeat_while");//放入controls_repeat_while
            	            	
            	for (Iterator while_iter = b_element.elementIterator(); while_iter.hasNext();) //移到elements
            	{
            		
            		Element while_statement = (Element) while_iter.next(); //while中的statement节点
                    String while_elements_name=while_statement.attributeValue("s");
                    
            		if(while_elements_name.equals("condition"))//判断是否为condition
            		{
            			operation_List.add("con_statement");//放入con_statement
            			Iterator while_con_iter = while_statement.elementIterator();
            			Element condition_element = (Element) while_con_iter.next(); //while中的condition节点
            			b_name=condition_element.attributeValue("s");
            			operation_List.add(b_name);
            			operation_value_List.add("");
            			if (b_name.equals("controls_and"))
            			{
            				operation_List.add("and_statement");
            				for (Iterator and_iter = condition_element.element("elements").elementIterator(); and_iter.hasNext();) //移到elements
                        	{
            					Element next_element = (Element) and_iter.next(); //while中的condition节点
            					b_name=next_element.attributeValue("s");
            					operation_List.add(b_name);
                    			operation_value_List.add("");
                        	}
            				operation_List.add("and_statement");
            			}else if(b_name.equals("controls_or"))
            			{
            				operation_List.add("or_statement");
            				for (Iterator or_iter = condition_element.element("elements").elementIterator(); or_iter.hasNext();) //移到elements
                        	{
            					Element next_element = (Element) or_iter.next(); //while中的condition节点
            					b_name=next_element.attributeValue("s");
            					operation_List.add(b_name);
                    			operation_value_List.add("");
                        	}
            				operation_List.add("or_statement");
            			}
            			operation_List.add("con_statement");//放入con_statement
            		}else if(while_elements_name.equals("body"))//判断是否为body
            		{
            			operation_List.add("body_statement");//放入body_statement
            			for (Iterator body_iter = while_statement.elementIterator(); body_iter.hasNext();) //移到elements
                    	{
            				Element next_element = (Element) body_iter.next(); //body中的statement节点
                            b_name=next_element.attributeValue("s");
                            operation_List.add(b_name);
                            operation_value_List.add("");
                    	}
            			operation_List.add("body_statement");//放入body_statement
            			
            		}
                }
            	
           }else if(b_name.equals("controls_repeat_until"))
           {
           	operation_List.add("controls_repeat_until");//放入controls_repeat_while
           	            	
           	for (Iterator while_iter = b_element.elementIterator(); while_iter.hasNext();) //移到elements
           	{
           		
           		Element while_statement = (Element) while_iter.next(); //while中的statement节点
                String while_elements_name=while_statement.attributeValue("s");
                   
           		if(while_elements_name.equals("condition"))//判断是否为condition
           		{
           			operation_List.add("con_statement");//放入con_statement
           			Iterator while_con_iter = while_statement.elementIterator();
           			Element condition_element = (Element) while_con_iter.next(); //while中的condition节点
           			b_name=condition_element.attributeValue("s");
           			operation_List.add(b_name);
           			operation_value_List.add("");
           			if (b_name.equals("controls_and"))
           			{
           				operation_List.add("and_statement");
           				for (Iterator and_iter = condition_element.element("elements").elementIterator(); and_iter.hasNext();) //移到elements
                       	{
           					Element next_element = (Element) and_iter.next(); //while中的condition节点
           					b_name=next_element.attributeValue("s");
           					operation_List.add(b_name);
                   			operation_value_List.add("");
                       	}
           				operation_List.add("and_statement");
           			}else if(b_name.equals("controls_or"))
           			{
           				operation_List.add("or_statement");
           				for (Iterator or_iter = condition_element.element("elements").elementIterator(); or_iter.hasNext();) //移到elements
                       	{
           					Element next_element = (Element) or_iter.next(); //while中的condition节点
           					b_name=next_element.attributeValue("s");
           					operation_List.add(b_name);
                   			operation_value_List.add("");
                       	}
           				operation_List.add("or_statement");
           			}
           		
           			operation_List.add("con_statement");//放入con_statement
           		}else if(while_elements_name.equals("body"))//判断是否为body
           		{
           			operation_List.add("body_statement");//放入body_statement
           			for (Iterator body_iter = while_statement.elementIterator(); body_iter.hasNext();) //移到elements
                   	{
           				Element next_element = (Element) body_iter.next(); //body中的statement节点
                           b_name=next_element.attributeValue("s");
                           operation_List.add(b_name);
                           operation_value_List.add("");
                   	}
           			operation_List.add("body_statement");//放入body_statement
           			
           		}
               }
           	
          }else if(b_name.equals("text_print_delay"))
          {
          	String delay_abr="";
          	for (Iterator delay_iter = b_element.elementIterator(); delay_iter.hasNext();) 
          	{
                  Element delay_elements = (Element) delay_iter.next(); //elements节点
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
                  Element to_elements = (Element) to_iter.next(); //elements节点
                  String e_name=to_elements.attributeValue("s"); 
                  if (e_name.equals("abr")) //判断timeout的模块是参数，则将参数的值存下来
                  {
                	  to_abr=to_elements.getText();       
                	  operation_value_List.add(to_abr);
                  }
                  else if (e_name.equals("statement"))  //判断timeout的模块是循环体
                  {
                  	int num=0;
                  	if (num<1)
                  	{
                      	for (Iterator for_b = to_elements.elementIterator(); for_b.hasNext();)
                      	{
                              Element for_statement = (Element) for_b.next(); //for中的statement节点
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


    	ArrayList List1 = new ArrayList();//cdl中component_list的name
    	List1.addAll(operation_List);

    	ArrayList List2 = new ArrayList();//cdl中需要整合的component_list的name

    	ArrayList List3 = new ArrayList(); //cdl中component的类型
    	List3.add("Switch");
    	List3.add("LED_1");   	
    	List3.add("LED_2");
    	List3.add("LED_3");
    	List3.add("HT"); 
    	//List3.add("HT");
    	
    	ArrayList List4 = new ArrayList();//component的参数
    	List4.addAll(operation_value_List);

    	//connectionlist的内容
    	ArrayList List5 = new ArrayList();//cdl中connection_list的name
    	int for_m=0;//for的个数
    	ArrayList for_id_List = new ArrayList();//for特殊组件的ID的list
    	ArrayList for_name_begin_List = new ArrayList();//for特殊组件的内部block开始的的list
    	for_name_begin_List.addAll(dofor_List);  
    	ArrayList for_id_begin_List = new ArrayList();//for特殊组件的内部block开始的id的list
    	ArrayList List6 = new ArrayList();//cdl中connection_list的from
    	ArrayList List7 = new ArrayList();//cdl中connection_list的to
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
				
    	for(int i=0;i<List1.size();i++)//遍历拿到的component列表
    	{

    		if(List1.get(i).equals("controls_and"))
    		{
    			i=i+2;//光标下移到and_statement下面的第一个组件
    			and_begin=i;
    			
    		}else if(List1.get(i).equals("controls_or"))
    		{
    			i=i+2;//光标下移到or_statement下面的第一个组件
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
    			i=i+2;//光标移到条件判断的节点
    			if(List1.get(i).equals("controls_and"))
    			{
    				i=i+2;//光标下移到and_statement下面的第一个组件
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
        			//光标此时在and_statement处
        			while_con_end=i-1;
        			i=i+3;//光标移到body_statement下一个组件
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
    				i=i+2;//光标下移到or_statement下面的第一个组件
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
        			i=i+3;//光标移到body_statement下一个组件
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
    			i=i+2;//光标移到条件判断的节点
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
    				i=i+2;//光标下移到and_statement下面的第一个组件
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
        			//光标此时在and_statement处
        			until_con_end=i-1;
        			i++;
        			
    			}else if(List1.get(i).equals("controls_or"))
    			{
    				i=i+2;//光标下移到or_statement下面的第一个组件
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
    			i=i+2;//光标移到timeout_statement下一位
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
    			i++;//光标移到timeout_statement下一位
    			

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
        	 for (int i=0;i<List2.size();i++)//先写componentlist
             {
             	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
             	Element componenttype = component.addElement("type").addText((String)List3.get(i));
             	Element componentabr = component.addElement("value").addText((String)List4.get(i));
             }
        	 for (int i=0;i<(List2.size()-1);i++)
             {
          	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(i+1));
          	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
          	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//存入一个to

             }
        }
        if(timeout_begin>0)//只有timeout
        {

             //生成rules部分
             Element rule = rulelist.addElement("rule").addAttribute("name", "timeout");
             Element rule_t_from = rule.addElement("from").addAttribute("name", (String)List1.get(timeout_begin));
             Element rule_t_to = rule.addElement("to").addAttribute("name", (String)List1.get(timeout_end));
             for (int i=0;i<List2.size();i++)//遍历到timeout
             {
            	 if(List2.get(i).equals("timeout"))
            	 {
            		 Element rule_t_value = rule.addElement("value");
            		 rule_t_value.addText((String)List4.get(i));
            		 List2.remove(i);
            		 List4.remove(i);
            	 }
             }
             
         	//直接取消timeout这个组件
        	 for (int i=0;i<List2.size();i++)//先写componentlist
             {
             	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
             	Element componenttype = component.addElement("type").addText((String)List3.get(i));
             	Element componentabr = component.addElement("value").addText((String)List4.get(i));
             }
        	 //connection就是按照List2中直接生成
             for(int j=0;j<(List2.size()-1);j++)
             {
          	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
          	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List2.get(j));//存入1个from
          	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List2.get(j+1));//存入一个to
          	   
             }
        }
        
        
        if(while_con_begin>0)//只有while 
        {
        	List2.add("W1");//加入while生成的中间component项
        	List3.add("VDEV");
        	List2.add("W2");
//        	List3.add("VDEV");
//        	List4.add("");
//        	List4.add("");
        	
            for (int i=0;i<List2.size();i++)//先写componentlist
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
            //生成在while之前的组件之间的con
            int con_num=0;
           for(int j=0;j<(while_con_begin-5);j++)
           {
        	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
        	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(j));//存入1个from
        	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(j+1));//存入一个to
        	   con_num++;
           }

           if(List1.get(while_con_begin-1).equals("and_statement"))//如果条件中是and，则生成一个con
           {
        	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
        	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin-5));//存入1个from
        	   for(int i=while_con_begin;i<=while_con_end;i++)
        	   {
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
        	   }
        	   Element connectionto = connection.addElement("to").addAttribute("name", "W1");//存入一个to
        	   con_num++;
           }else if(List1.get(while_con_begin-1).equals("or_statement"))//如果条件中是and，则生成n个con
           {
        	   for(int i=0;i<=(while_con_end-while_con_begin);i++)
        	   {
        		   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        		   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin-5));//存入1个from        		   
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin+i));//存入1个from
        		   Element connectionto = connection.addElement("to").addAttribute("name", "W1");//存入一个to
        		   con_num++;
        	   }
        	
           }
           //W1下面生成两个connection
       
           Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
           con_num++;
           Element connectionfrom = connection.addElement("from").addAttribute("name", "W1");//存入1个from，从W1到body内的第一个
           Element connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_begin));//存入一个to
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
           connectionfrom = connection.addElement("from").addAttribute("name", "W1");//存入1个from，从W1到body外的
           connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(while_body_end+2));//存入一个to
           con_num++;
           for(int i=0;i<(while_body_end-while_body_begin);i++)//对于body当中的block，生成相应中间的connection
           {
        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        	   con_num++;
        	   connectionfrom = connection.addElement("from").addAttribute("name",(String)List1.get(while_body_begin+i));//存入1个from，从W1到body外的
               connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(while_body_begin+i+1));//存入一个to
           }
           //生成W2处关键的connection，需要连接condition中的block
           
           
           if(List1.get(while_con_begin-1).equals("and_statement"))//如果条件中是and，则生成一个con
           {
        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
        	   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_body_end));//存入1个from
        	   for(int i=while_con_begin;i<=while_con_end;i++)
        	   {
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
        	   }
        	   connectionto = connection.addElement("to").addAttribute("name", "W2");//存入一个to
        	   con_num++;
           }else if(List1.get(while_con_begin-1).equals("or_statement"))//如果条件中是and，则生成n个con
           {
        	   for(int i=0;i<=(while_con_end-while_con_begin);i++)
        	   {
        		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_body_end));//存入1个from        		   
        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(while_con_begin+i));//存入1个from
        		   connectionto = connection.addElement("to").addAttribute("name", "W2");//存入一个to
        		   con_num++;
        	   }
        	
           }
           
           //W2结束后，要生成2个connection
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1)); 
           con_num++;
           connectionfrom = connection.addElement("from").addAttribute("name", "W2");//存入1个from
		   connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_begin));//存入一个to
           connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1)); 
           connectionfrom = connection.addElement("from").addAttribute("name", "W2");//存入1个from
		   connectionto = connection.addElement("to").addAttribute("name",(String)List1.get(while_body_end+2));//存入一个to
           //while后面的block之间的connection
		   con_num++;
		   int post_num=List1.size()-(while_body_end+3);
		   if (post_num>0)
		   {
	      		for(int i=(while_body_end+3);i<List1.size();i++)
	      		{
	     		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	     		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from        		   
	     		   connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//存入一个to
	     		   con_num++;
	      		}
		   }
        }
        
        if(until_con_begin>0)//只有until 
        {
        	List2.add("UN");//加入while生成的中间component项
        	List3.add("VDEV");
        	List4.add("");
            for (int i=0;i<List2.size();i++)//先写componentlist
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
            //生成在until之前的组件之间的con
            int con_num=0;
            for(int j=0;j<(until_body_begin-3);j++)
            {
         	   Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
         	   Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(j));//存入1个from
         	   Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(j+1));//存入一个to
         	   con_num++;
            }
            //until之前的block和body第一个的con
            Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
            Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_begin-3));//存入1个from
      	   	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin));//存入一个to
      	   	con_num++;
            //body之间的block间的connection
	      	for(int i=0;i<(until_body_end-until_body_begin);i++)//对于body当中的block，生成相应中间的connection
	        {
	      		connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	      		con_num++;
	       	   	connectionfrom = connection.addElement("from").addAttribute("name",(String)List1.get(until_body_begin+i));//存入1个from，从W1到body外的
	            connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin+i+1));//存入一个to
	         }
	      	//根据condition中的type，生成相应个数的connection
	      	 if(List1.get(until_con_begin-1).equals("and_statement"))//如果条件中是and，则生成一个con
	           {
	        	   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));        	   
	        	   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_end));//存入1个from
	        	   for(int i=until_con_begin;i<=until_con_end;i++)
	        	   {
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
	        	   }
	        	   connectionto = connection.addElement("to").addAttribute("name", "UN");//存入一个to
	        	   con_num++;
	           }else if(List1.get(until_con_begin-1).equals("or_statement"))//如果条件中是and，则生成n个con
	           {
	        	   for(int i=0;i<=(until_con_end-until_con_begin);i++)
	        	   {
	        		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_body_end));//存入1个from        		   
	        		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(until_con_begin+i));//存入1个from
	        		   connectionto = connection.addElement("to").addAttribute("name", "UN");//存入一个to
	        		   con_num++;
	        	   }
	        	
	           }
	      	 //UN下面生成2个connection
	      	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
	      	connectionfrom = connection.addElement("from").addAttribute("name", "UN");//存入1个from 
	      	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_con_end+3));//存入一个to
	      	con_num++;
	      	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+1));
	      	connectionfrom = connection.addElement("from").addAttribute("name", "UN");//存入1个from 
	      	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(until_body_begin));//存入一个to
	      	//until后面的block之间的connection
	      	con_num++;
	      	int post_num=List1.size()-(until_con_end+4);
	      	if (post_num>0)
	      	{
	      		for(int i=(until_con_end+3);i<List1.size();i++)
	      		{
	     		   connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(con_num+i+1)); 
	     		   connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from        		   
	     		   connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//存入一个to
	     		   con_num++;
	      		}
	      	}
        }
    	
    	if (and_begin>0)//存在and，and前面不能有组件
    	{
    		int j=1;
    		Element connection = connectionlist.addElement("connection").addAttribute("name", "con_1");
    		for (int i=and_begin;i<=and_end;i++)
            {
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入多个from
            }
    		Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(and_end+2));//存入一个to

            for (int i=and_end+2;i<(List1.size()-1);i++)
            {
            	
            	connection = connectionlist.addElement("connection").addAttribute("name", "con_"+(j+1));
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
            	connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//存入一个to
            	j++;
            }
            for (int i=0;i<List2.size();i++)
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
    		
    	}
    	
    	if (or_begin>0)//存在or，or前面不能有组件
    	{
    		int j=1;//connection个数
    		
    		for (int i=or_begin;i<=or_end;i++)
            {
    			Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+j);
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
            	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(or_end+2));//存入一个to
            	j++;
            }
    		

            for (int i=or_end+2;i<(List1.size()-1);i++)
            {
            	
            	Element connection = connectionlist.addElement("connection").addAttribute("name", "con_"+j);
            	Element connectionfrom = connection.addElement("from").addAttribute("name", (String)List1.get(i));//存入1个from
            	Element connectionto = connection.addElement("to").addAttribute("name", (String)List1.get(i+1));//存入一个to
            	j++;
            }
            for (int i=0;i<List2.size();i++)
            {
            	Element component = componentlist.addElement("component").addAttribute("name",(String)List2.get(i)) ;
            	Element componenttype = component.addElement("type").addText((String)List3.get(i));
            	Element componentabr = component.addElement("value").addText((String)List4.get(i));
            }
    		
    	}
    	
        //计算dofor的个数
        for (int i=0;i<List1.size();i++)
        {
        	if (List1.get(i).equals("doFOR_temp"))
        	{
        		for_m++;
        	}
        }
    
    	//添加dofor个数的相应的connection
        if(for_m>0)//如果存在for
        {
            for (int i=0;i<(List1.size()-1);i++)//正常有比component个数少一个的connection
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
