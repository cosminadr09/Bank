import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class gui extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldcnp;
	public static Bank bank= new Bank();
	private JButton btnShowClients;
	//public static List<CPerson> clients=new ArrayList<CPerson>();
	private JTable clientsTable = new JTable();
	private JScrollPane scrollPaneClients;
	private JScrollPane scrollPaneAccounts;

	private DefaultTableModel modelClienti;
	private DefaultTableModel modelConturi;

	
	///////////////////////////PANELS//////////////////////////
	private JPanel clientsPanel = new JPanel();
	private JPanel accountsPanel = new JPanel();
	///////////////////////////headtables/////////////////////
	private String[] clientsHead = {"nume", "CNP"};
	private String[] accountsHead = {"IBAN", "holder", "sold", "type"};
	private JTextField textFieldIban;
	private JTextField textFieldSuma;
	private JTextField textFieldDobanda;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					this.dateIntrare();
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void dateIntrare() {
				// TODO Auto-generated method stub
				Bank b = new Bank();
				//clients =b.getClients();
				Person p1 = new Person("Daniela", 4512345678L);
				Person p2 = new Person("Oana", 2324535642L);
				Person p3 = new Person("Cosmina", 9142764958L);
				b.addPerson(p1);
				b.addPerson(p2);
				b.addPerson(p3);
				b.saveData();
				bank = b.loadData();
				bank.listPersons();
			}
		});
	}
	public JTable createClientsTable (){
		Object[][] list = new Object[bank.getHashmap().size()][2];
		Object[] headers = new Object[clientsHead.length];
		int j=0;
		for(String s: clientsHead){
			headers[j]=s;
			j++;
		}
		modelClienti = new DefaultTableModel(list, headers);
		JTable table = new JTable(modelClienti);
		Set<Entry<Person, HashSet<Account>>> set = bank.getHashmap().entrySet();
		 Iterator<Entry<Person, HashSet<Account>>> i = set.iterator();
		 while (i.hasNext()) {
		 Map.Entry<Person, HashSet<Account>> entry = (Map.Entry<Person,
		 HashSet<Account>>) i.next();
		 Person p = entry.getKey();

		 modelClienti.addRow(new Object[] {p.getName(), p.getCnp()});
		 }
		 return table;
	}
	public JTable createAccountsTable (HashSet<Account> accounts){
		
		Object[][] list = new Object[bank.getHashmap().size()][4];
		Object[] headers = new Object[accountsHead.length];
		int j=0;
		for(String s: accountsHead){
			headers[j]=s;
			j++;
		}
		modelConturi = new DefaultTableModel(list, headers);
		JTable table = new JTable(modelConturi);
		for(Account a:accounts){
			modelConturi.addRow(new Object[]{a.getIban(), a.getHolder().getName(), a.getCurrentSum(), a.getType()});
		}

		 return table;
	}
	
	/**
	 * Create the frame.
	 */
	public gui() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contentPane.add(clientsPanel);
		clientsPanel.setBounds(10,10,700,265);
		clientsPanel.setVisible(true);
		clientsPanel.setLayout(null);
		
		contentPane.add(accountsPanel);
		accountsPanel.setBounds(10, 288, 700, 320);
		accountsPanel.setVisible(true);
		accountsPanel.setLayout(null);
		
		JLabel lblPersonName = new JLabel("Person name:");
		lblPersonName.setBounds(12, 13, 80, 16);
		//contentPane.add(lblPersonName);
		clientsPanel.add(lblPersonName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(104, 10, 116, 22);
		//contentPane.add(textFieldName);
		clientsPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblPersonCnp = new JLabel("Person CNP:");
		lblPersonCnp.setBounds(12, 42, 80, 16);
		//contentPane.add(lblPersonCnp);
		clientsPanel.add(lblPersonCnp);
		
		textFieldcnp = new JTextField();
		textFieldcnp.setBounds(104, 45, 116, 22);
		//contentPane.add(textFieldcnp);
		clientsPanel.add(textFieldcnp);
		textFieldcnp.setColumns(10);
		
		
		
		btnShowClients = new JButton("Show clients:");
		btnShowClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				scrollPaneClients = new JScrollPane(createClientsTable());
				scrollPaneClients.setBounds(250,10,250,200);
				scrollPaneClients.setVisible(true);
				clientsPanel.add(scrollPaneClients, BorderLayout.EAST);
				//System.out.println(model.getValueAt(3, 0).toString());
			
	
			}
		});
		btnShowClients.setBounds(12, 92, 116, 25);
		//contentPane.add(btnShowClients);
		clientsPanel.add(btnShowClients);
		
		JButton btnAddClients = new JButton("Add clients");
		btnAddClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TryBank b = new TryBank();
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person p = new Person(strName, cnp);
				bank.addPerson(p);
				bank.saveData();
				bank = bank.loadData();
			}
		});
		btnAddClients.setBounds(12, 130, 116, 25);
		clientsPanel.add(btnAddClients);
		
//		JButton btnEditClient = new JButton("Edit client");
//		btnEditClient.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//JOptionPane.showMessageDialog(clientsPanel, "Type person's CNP and press button!");
////				clientsTable.setAutoCreateRowSorter(true);
////			
////				JTableHeader header = clientsTable.getTableHeader(); 
////				header.addMouseListener(new MouseAdapter(clientsTable));
//				List<String> numdata = new ArrayList<String>();
//		        for (int count = 0; count < model.getRowCount(); count++){
//		              numdata.add(model.getValueAt(count, 0).toString());
//		              System.out.println(numdata.get(count));
//		        }
				//System.out.println(model.getValueAt(3, 0).toString());
//			}
//		});
//		btnEditClient.setBounds(12, 177, 97, 25);
//		clientsPanel.add(btnEditClient);
		
		JButton btnDeleteClient = new JButton("Delete client");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(clientsPanel, "Type person's CNP and press button!");
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person p = new Person(strName, cnp);
				if(bank.isClient(p)){
					bank.removePerson(p);
				}
				else{
					JOptionPane.showMessageDialog(clientsPanel, "The person is not a client!");
				}
				bank.saveData();
				bank = bank.loadData();
			}
		});
		btnDeleteClient.setBounds(12, 168, 116, 25);
		clientsPanel.add(btnDeleteClient);
		
		JButton btnShowAccounts = new JButton("Show accounts");
		btnShowAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashSet<Account> accounts = new HashSet<Account>();
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person holder = new Person(strName, cnp);
				accounts = bank.getHashmap().get(holder);
				
				scrollPaneAccounts = new JScrollPane(createAccountsTable(accounts));
				scrollPaneAccounts.setBounds(250,230,500,200);
				scrollPaneAccounts.setVisible(true);
				contentPane.add(scrollPaneAccounts, BorderLayout.EAST);
				
				
			}
		});
		//btnShowAccounts.setBounds(10, 288, 141, 25);
		btnShowAccounts.setBounds(10, 10, 166, 25);

		//contentPane.add(btnShowAccounts);
		accountsPanel.add(btnShowAccounts);
		
		JButton btnAddSpendingAccount = new JButton("Add spending account");
		btnAddSpendingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person p = new Person(strName, cnp);
				if(bank.isClient(p)){
					SpendingAccount spend = new SpendingAccount(p, "spending");//type==spending
					bank.addAccount(p, spend);
					bank.listAccounts(p);
					bank.saveData();
					bank.loadData();
				}
			}
		});
		//btnAddSpendingAccount.setBounds(30, 330, 166, 25);
		btnAddSpendingAccount.setBounds(10, 44, 166, 25);

		//contentPane.add(btnAddSpendingAccount);
		accountsPanel.add(btnAddSpendingAccount);
		
		JButton btnAddSavingAccount = new JButton("Add saving account");
		btnAddSavingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person p = new Person(strName, cnp);
				if(bank.isClient(p)){
					SpendingAccount save = new SpendingAccount(p, "saving");//type==spending
					bank.addAccount(p, save);
					bank.listAccounts(p);
					bank.saveData();
					bank.loadData();
					
				}
			}
		});
		//btnAddSavingAccount.setBounds(20, 368, 151, 25);
		btnAddSavingAccount.setBounds(10, 82, 166, 25);

		//contentPane.add(btnAddSavingAccount);
		accountsPanel.add(btnAddSavingAccount);
		
		JLabel lblIban = new JLabel("IBAN:");
		lblIban.setBounds(10, 160, 56, 16);
		accountsPanel.add(lblIban);
		
		textFieldIban = new JTextField();
		textFieldIban.setBounds(51, 157, 116, 22);
		accountsPanel.add(textFieldIban);
		textFieldIban.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person holder = new Person(strName, cnp);
				
				HashSet<Account> accounts = new HashSet<Account>();
				accounts = bank.getHashmap().get(holder);
				
				String strIban = textFieldIban.getText();
				long iban = Long.parseLong(strIban);
				
				String strSuma = textFieldSuma.getText();
				double suma = Double.parseDouble(strSuma);
				
				for(Object a : accounts){
					if(a.getClass() == SavingsAccount.class){
						if(((SavingsAccount) a).getIban() == iban){
							((Account)a).addObserver(holder);
							bank.depositMoney((Account)a, suma);
							bank.saveData();
							bank.loadData();
							
						}
					}
					if(a.getClass() == SpendingAccount.class){
						if(((SpendingAccount) a).getIban() == iban) {
							((Account)a).addObserver(holder);
							bank.depositMoney((Account)a, suma);
							bank.saveData();
							bank.loadData();
						}
					}
				}
			}
		});
		btnDeposit.setBounds(10, 244, 97, 25);
		accountsPanel.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person holder = new Person(strName, cnp);
				
				HashSet<Account> accounts = new HashSet<Account>();
				accounts = bank.getHashmap().get(holder);
				
				String strIban = textFieldIban.getText();
				long iban = Long.parseLong(strIban);
				
				String strSuma = textFieldSuma.getText();
				double suma = Double.parseDouble(strSuma);
				
				for(Object a : accounts){
					if(a.getClass() == SavingsAccount.class){
						if(((SavingsAccount) a).getIban() == iban){
							((Account)a).addObserver(holder);
							bank.withdrawMoney((Account)a, suma);
							bank.saveData();
							bank.loadData();
							
						}
					}
					if(a.getClass() == SpendingAccount.class){
						if(((SpendingAccount) a).getIban() == iban) {
							((Account)a).addObserver(holder);
							bank.withdrawMoney((Account)a, suma);
							bank.saveData();
							bank.loadData();
						}
					}
				}
			
			}
		});
		btnWithdraw.setBounds(10, 282, 97, 25);
		accountsPanel.add(btnWithdraw);
		
		JButton btnDeleteAccount = new JButton("Delete account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strIban = textFieldIban.getText();
				long iban = Long.parseLong(strIban);
				
				String strName = textFieldName.getText();
				String strCnp = textFieldcnp.getText();
				long cnp = Long.parseLong(strCnp);
				Person holder = new Person(strName, cnp);
				bank.removeAccount(holder, iban);
				bank.saveData();
				bank.loadData();
				
			}
		});
		btnDeleteAccount.setBounds(10, 120, 166, 25);
		accountsPanel.add(btnDeleteAccount);
		
		JLabel lblSuma = new JLabel("Suma:");
		lblSuma.setBounds(10, 189, 56, 16);
		accountsPanel.add(lblSuma);
		
		textFieldSuma = new JTextField();
		textFieldSuma.setBounds(51, 186, 116, 22);
		accountsPanel.add(textFieldSuma);
		textFieldSuma.setColumns(10);
		

		
		
	
	}
}
