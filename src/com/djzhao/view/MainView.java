package com.djzhao.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.djzhao.dao.QueryDao;

/**
 * 主视图。
 * 
 * @author djzhao
 * @time 2017年2月22日 上午10:28:19
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 6032566382189566281L;
	private JPanel contentPane;
	private JTextField uri;
	private JTextField user;
	private JTextField password;
	private JComboBox<String> database;
	private JTextArea result;
	private JTextArea sql;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("SQLTools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("\u6570\u636E\u5E93\u5730\u5740\uFF1A");
		panel.add(label);
		
		uri = new JTextField();
		uri.setText("jdbc:oracle:thin:@172.21.3.131:1521:baan");
		panel.add(uri);
		uri.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		panel.add(label_2);
		
		user = new JTextField();
		user.setText("proshow");
		user.setColumns(10);
		panel.add(user);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\uFF1A");
		panel.add(label_3);
		
		password = new JTextField();
		password.setText("abc123");
		password.setColumns(10);
		panel.add(password);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label_4 = new JLabel("\u6570\u636E\u5E93\uFF1A");
		label_4.setEnabled(false);
		panel_1.add(label_4);
		
		database = new JComboBox<String>();
		database.setEnabled(false);
		database.setModel(new DefaultComboBoxModel<String>(new String[] {"Oracle", "MySQL"}));
		panel_1.add(database);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					QueryDao qd = new QueryDao();
					String resultStr = qd.Query(
							uri.getText().toString().trim(), 
							user.getText().toString().trim(), 
							password.getText().toString().trim(), 
							sql.getText().toString().trim()
							);
					result.setText(resultStr);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		sql = new JTextArea();
		sql.setWrapStyleWord(true);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		String formatToday = sdf.format(today);
		String formatTomorrow = sdf.format(tomorrow);
		sql.setText("select (case when GW='DOP' then (case when CrtAcc in ('SCDOP1','SCDOP2','SCDOP3') then 'D15' else 'D10'  end)  else GW end)  as \u5C97\u4F4D,'" 
		+ formatToday + "' as \u65E5\u671F,round(sum(BQUA),0) as \u4EA7\u91CF ,sum(case when Crtdate between to_date('"
		+ formatToday +" 07:15:00','yyyy-mm-dd hh24:mi:ss') and to_date('" 
		+ formatToday + " 15:14:59','yyyy-mm-dd hh24:mi:ss') then BQUA else 0 end) as \u65E9\u73ED ,sum(case when Crtdate between to_date('"
		+ formatToday + " 15:15:00','yyyy-mm-dd hh24:mi:ss') and to_date('" 
		+ formatToday + " 23:14:59','yyyy-mm-dd hh24:mi:ss') then BQUA else 0 end) as \u4E2D\u73ED ,sum(case when Crtdate between to_date('"
		+ formatToday + " 23:15:00','yyyy-mm-dd hh24:mi:ss') and to_date('"
		+ formatTomorrow + " 07:14:59','yyyy-mm-dd hh24:mi:ss') then BQUA else 0 end) as \u665A\u73ED  from baandb.ttiwcn108220 e,baandb.ttiwcn109220 f where e.BOXID=f.BOXID and ( f.DES1='\u5C01\u7BB1') and f.CrtDate between to_date('" 
		+ formatToday + " 07:15:00','yyyy-mm-dd hh24:mi:ss') and to_date('"
		+ formatTomorrow + " 07:15:00','yyyy-mm-dd hh24:mi:ss') group by (case when GW='DOP' then (case when CrtAcc in ('SCDOP1','SCDOP2','SCDOP3') then 'D15' else 'D10'  end)  else GW end)");
		sql.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(sql);
		panel_3.add(scrollPane);
		
		result = new JTextArea();
		result.setEditable(false);
		result.setWrapStyleWord(true);
		result.setLineWrap(true);
		
		JScrollPane scrollPane_1 = new JScrollPane(result);
		panel_3.add(scrollPane_1);
		
		JLabel lblSql = new JLabel("SQL For Query");
		panel_2.add(lblSql, BorderLayout.NORTH);
	}

}
