package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import com.compoment.addfunction.android.DateSelect;
import com.compoment.jsonToJava.creater.RequestRespond;
import com.compoment.jsonToJava.creater.RequestRespondParamBean;
import com.compoment.jsonToJava.creater.WordtableToJavaObject;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.InterfaceBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class ProjectDocPanel {
	ProjectFrame projectFrame;

	ArrayList listDate = new ArrayList();
	/** 中文 */
	JTextField cnNameValueEditText;
	/** enName */
	JTextField enNameValueEditText;
	/** type */
	JTextField typeValueEditText;

	/** remarks */
	JTextField remarksValueEditText;
	JList listListView;
	JTextField pathValueEditText;
	
	JTextField requestJsonValueEditText;
	JTextField respondJsonValueEditText;
	JTextField respondJsonDetailValueEditText;
	
	public ProjectDocPanel(ProjectFrame projectFrame) {
		this.projectFrame = projectFrame;
	}

	public JPanel create() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/**
		 * bg1421553901724LinearLayout bg1421553933783LinearLayout
		 * bg1421554005542LinearLayout bg1421554061119LinearLayout
		 * bg1421554117584LinearLayout bg1421554170543LinearLayout
		 * bg1421554220555LinearLayout bg1421554259446LinearLayout
		 */
		GroupLayout.SequentialGroup bg1421553890229LinearLayout = layout
				.createSequentialGroup();
		/** titleTextView */
		GroupLayout.ParallelGroup bg1421553901724LinearLayout = layout
				.createParallelGroup();
		/** 接口文档 */
		JLabel titleTextView = new JLabel("接口文档");
		bg1421553901724LinearLayout.addComponent(titleTextView);

		bg1421553890229LinearLayout.addGroup(bg1421553901724LinearLayout);
		/** pathTitleTextView pathValueEditText */
		GroupLayout.ParallelGroup bg1421553933783LinearLayout = layout
				.createParallelGroup();
		/** 接口文档路径 */
		JLabel pathTitleTextView = new JLabel("接口文档路径");
		bg1421553933783LinearLayout.addComponent(pathTitleTextView);

		/** path */
		pathValueEditText = new JTextField("path");
		bg1421553933783LinearLayout.addComponent(pathValueEditText);

		Document doc = pathValueEditText.getDocument();

		// 添加DocumentListener监听器
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		bg1421553890229LinearLayout.addGroup(bg1421553933783LinearLayout);
		/** cnNameTitleTextView cnNameValueEditText */
		GroupLayout.ParallelGroup bg1421554005542LinearLayout = layout
				.createParallelGroup();
		/** 中文名字 */
		JLabel cnNameTitleTextView = new JLabel("中文名Index");
		bg1421554005542LinearLayout.addComponent(cnNameTitleTextView);

		/** 中文 */
		cnNameValueEditText = new JTextField("0");
		bg1421554005542LinearLayout.addComponent(cnNameValueEditText);

		bg1421553890229LinearLayout.addGroup(bg1421554005542LinearLayout);
		/** enNameTitleTextView enNameValueEditText */
		GroupLayout.ParallelGroup bg1421554061119LinearLayout = layout
				.createParallelGroup();
		/** 变量 */
		JLabel enNameTitleTextView = new JLabel("变量Index");
		bg1421554061119LinearLayout.addComponent(enNameTitleTextView);

		/** enName */
		enNameValueEditText = new JTextField("1");
		bg1421554061119LinearLayout.addComponent(enNameValueEditText);

		bg1421553890229LinearLayout.addGroup(bg1421554061119LinearLayout);
		
		
		
		
		/** typeTitleTextView typeValueEditText */
		GroupLayout.ParallelGroup bg1421554117584LinearLayout = layout
				.createParallelGroup();
		/** 变量类型 */
		JLabel typeTitleTextView = new JLabel("变量类型Index");
		bg1421554117584LinearLayout.addComponent(typeTitleTextView);

		/** type */
		typeValueEditText = new JTextField("2");
		bg1421554117584LinearLayout.addComponent(typeValueEditText);

		bg1421553890229LinearLayout.addGroup(bg1421554117584LinearLayout);
		
		
		
		
		/** remarksTitleTextView remarksValueEditText */
		GroupLayout.ParallelGroup bg1421554170543LinearLayout = layout
				.createParallelGroup();
		/** 备注 */
		JLabel remarksTitleTextView = new JLabel("备注Index");
		bg1421554170543LinearLayout.addComponent(remarksTitleTextView);

		/** remarks */
		remarksValueEditText = new JTextField("4");
		bg1421554170543LinearLayout.addComponent(remarksValueEditText);

		bg1421553890229LinearLayout.addGroup(bg1421554170543LinearLayout);
		
		
		
		/**请求格式 request json   */
		GroupLayout.ParallelGroup requestJsonLinearLayout = layout
				.createParallelGroup();
		/** request */
		JLabel requestJsonTitleTextView = new JLabel("RequestJson");
		requestJsonLinearLayout.addComponent(requestJsonTitleTextView);

		/** request */
		requestJsonValueEditText = new JTextField("");
		requestJsonLinearLayout.addComponent(requestJsonValueEditText);

		bg1421553890229LinearLayout.addGroup(requestJsonLinearLayout);
		
		
		
		/**响应 格式respond json  简化 */
		GroupLayout.ParallelGroup respondJsonLinearLayout = layout
				.createParallelGroup();
		/** respond*/
		JLabel respondJsonTitleTextView = new JLabel("RespondJson简化");
		respondJsonLinearLayout.addComponent(respondJsonTitleTextView);

		/** respond */
		respondJsonValueEditText = new JTextField("{\"returnCode\": \"0\",\"returnData\": {\"HEAD\": { \"RET_MSG\": \"成功\", \"RET_ERR\": \"000000\"}, \"RECORDNUM\": \"3\" ,\"ASS_NO\": [\"\", \"\", \"\" ],\"MAXRECORD\": \"6\"}}");
		respondJsonLinearLayout.addComponent(respondJsonValueEditText);

		bg1421553890229LinearLayout.addGroup(respondJsonLinearLayout);
		
		
		
		/**响应 格式respond json  详细 */
		GroupLayout.ParallelGroup respondJsonDetailLinearLayout = layout
				.createParallelGroup();
		/** respond*/
		JLabel respondJsonDetailTitleTextView = new JLabel("RespondJson详细");
		respondJsonDetailLinearLayout.addComponent(respondJsonDetailTitleTextView);

		/** respond */
		respondJsonDetailValueEditText = new JTextField("{\"returnCode\": \"0\",\"returnMsg\": \"\",\"returnDate\": {\"HEAD\": {\"RET_MSG\": \"成功\",\"RET_ERR\": \"000000\"},\"product\": [{\"code\": \"13\",\"totalPrice\": 6},{\"code\": \"3\",\"totalPrice\": 3.22}]}}");
		respondJsonDetailLinearLayout.addComponent(respondJsonDetailValueEditText);

		bg1421553890229LinearLayout.addGroup(respondJsonDetailLinearLayout);
	
		
		/** tojavaButton */
		GroupLayout.ParallelGroup bg1421554220555LinearLayout = layout
				.createParallelGroup();
		/** toJava */
		JButton tojavaButton = new JButton("toJava");
		bg1421554220555LinearLayout.addComponent(tojavaButton);
		tojavaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				write();
				String s = pathValueEditText.getText();

				searchDoc(s);
			}
		});

		bg1421553890229LinearLayout.addGroup(bg1421554220555LinearLayout);
		
		
		
		
		
		/** listListView */
		GroupLayout.ParallelGroup bg1421554259446LinearLayout = layout
				.createParallelGroup();
		/** list */
		listListView = new JList();
		JScrollPane listScrollPane = new JScrollPane(listListView);
		ArrayList listDate = new ArrayList();

		listListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listListView.setListData(listDate.toArray());
		listListView.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent even) {
				String value = listListView.getSelectedValue().toString();
			}
		});
		bg1421554259446LinearLayout.addComponent(listScrollPane);

		bg1421553890229LinearLayout.addGroup(bg1421554259446LinearLayout);
		layout.setVerticalGroup(bg1421553890229LinearLayout);

		// 水平
		/** listListView bg1421554294450 bg1421554314417 bg1421554323910 */
		GroupLayout.ParallelGroup bg1421554327272 = layout
				.createParallelGroup();
		/** list */
		bg1421554327272.addComponent(listScrollPane);

		/** titleTextView */
		GroupLayout.SequentialGroup bg1421554294450 = layout
				.createSequentialGroup();
		/** 接口文档 */
		bg1421554294450.addComponent(titleTextView);

		bg1421554327272.addGroup(bg1421554294450);

		/** bg1421554304832 bg1421554308560 */
		GroupLayout.SequentialGroup bg1421554314417 = layout
				.createSequentialGroup();
		/**
		 * pathTitleTextView cnNameTitleTextView enNameTitleTextView
		 * typeTitleTextView remarksTitleTextView
		 */
		GroupLayout.ParallelGroup bg1421554304832 = layout
				.createParallelGroup();
		/** 接口文档路径 */
		bg1421554304832.addComponent(pathTitleTextView);

		/** 中文名字 */
		bg1421554304832.addComponent(cnNameTitleTextView);

		/** 变量 */
		bg1421554304832.addComponent(enNameTitleTextView);

		/** 变量类型 */
		bg1421554304832.addComponent(typeTitleTextView);

		/** 备注 */
		bg1421554304832.addComponent(remarksTitleTextView);
		
		/** 请求格式 */
		bg1421554304832.addComponent(requestJsonTitleTextView);
		
		/** 响应格式  简版*/
		bg1421554304832.addComponent(respondJsonTitleTextView);

		/** 响应格式  详细 */
		bg1421554304832.addComponent(respondJsonDetailTitleTextView);
		
		bg1421554314417.addGroup(bg1421554304832);

		/**
		 * pathValueEditText cnNameValueEditText enNameValueEditText
		 * typeValueEditText remarksValueEditText
		 */
		GroupLayout.ParallelGroup bg1421554308560 = layout
				.createParallelGroup();
		/** path */
		bg1421554308560.addComponent(pathValueEditText);

		/** 中文 */
		bg1421554308560.addComponent(cnNameValueEditText);

		/** enName */
		bg1421554308560.addComponent(enNameValueEditText);

		/** type */
		bg1421554308560.addComponent(typeValueEditText);

		/** remarks */
		bg1421554308560.addComponent(remarksValueEditText);
		
		/** 请求格式 */
		bg1421554308560.addComponent(requestJsonValueEditText);
		
		/** 响应格式  简版 */
		bg1421554308560.addComponent(respondJsonValueEditText);

		
		/** 响应格式 详细 */
		bg1421554308560.addComponent(respondJsonDetailValueEditText);
		
		bg1421554314417.addGroup(bg1421554308560);

		bg1421554327272.addGroup(bg1421554314417);

		/** tojavaButton */
		GroupLayout.SequentialGroup bg1421554323910 = layout
				.createSequentialGroup();
		/** toJava */
		bg1421554323910.addComponent(tojavaButton);

		bg1421554327272.addGroup(bg1421554323910);

		layout.setHorizontalGroup(bg1421554327272);

		read();
		return panel;
	}

	public void read() {
		pathValueEditText.setText(KeyValue.readCache("docPath"));
		cnNameValueEditText.setText(KeyValue.readCache("中文名"));

		enNameValueEditText.setText(KeyValue.readCache("变量名"));

		typeValueEditText.setText(KeyValue.readCache("类型"));

		remarksValueEditText.setText(KeyValue.readCache("备注"));
	}

	public void write() {
		KeyValue.writeCache("docPath", pathValueEditText.getText().toString());
		KeyValue.writeCache("中文名", cnNameValueEditText.getText().toString());// 中文注解在表格的哪一列
		KeyValue.writeCache("变量名", enNameValueEditText.getText().toString());
		KeyValue.writeCache("类型", typeValueEditText.getText().toString());// 类型在表格的哪一列
		KeyValue.writeCache("备注", remarksValueEditText.getText().toString());
	}

	public void searchDoc(String filePath) {

		Map point = new HashMap();
		point.put("中文名",
				Integer.valueOf(cnNameValueEditText.getText().toString()));// 中文注解在表格的哪一列
		point.put("变量名",
				Integer.valueOf(enNameValueEditText.getText().toString()));
		point.put("类型", Integer.valueOf(typeValueEditText.getText().toString()));// 类型在表格的哪一列
		point.put("备注",
				Integer.valueOf(remarksValueEditText.getText().toString()));

		WordtableToJavaObject wordtable = new WordtableToJavaObject();
		List<InterfaceBean> interfaceBeans = wordtable.wordAnalyse(filePath,
				point);

		listDate.clear();
		listListView.setListData(listDate.toArray());

		if (interfaceBeans == null) {

			return;
		}

		
		
		// 接口数据异常处理
		for (InterfaceBean interfaceBean : interfaceBeans) {
			if (interfaceBean != null) {
				if (interfaceBean.requestGroups != null
						&& interfaceBean.requestGroups.size() < 1) {// 请检查word中参数表是否有标题"传入参数"

					String err = "请检查word中接口 " + interfaceBean.id
							+ " 是否有标题\"传入参数\"";

					Object[] options = {"检查","不检查"};
					int response=JOptionPane.showOptionDialog(projectFrame, err, "温馨提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
						return;
					}
					else if(response==1)
					{ 
						
					}
				}

				else if (interfaceBean.respondGroups != null
						&& interfaceBean.respondGroups.size() < 1) {// 请检查word中参数表是否有标题"传出参数"

					String err = "请检查word中接口 " + interfaceBean.id
							+ " 是否有标题\"传出参数\"";
					
					
					Object[] options = {"检查","不检查"};
					int response=JOptionPane.showOptionDialog(projectFrame, err, "温馨提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
						return;
					}
					else if(response==1)
					{ 
						
					}
				}
			}

		}

		
		
		for (InterfaceBean f : interfaceBeans) {
			listDate.add(f.id + "" + f.title);

		}
		listListView.setListData(listDate.toArray());
		RequestRespondParamBean requestRespond = new RequestRespondParamBean();
		requestRespond.requestRespondParamBean(interfaceBeans);

		RequestRespond rr = new RequestRespond();
		rr.requestRespond(interfaceBeans);
		
		
		JOptionPane.showMessageDialog(projectFrame, "代码生成,请刷新doc目录", "",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public static void main(String[] args) {

	}
}
