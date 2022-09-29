import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BankUI extends JFrame {

	private JPanel contentPane =  (JPanel) getContentPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankUI frame = new BankUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BankUI() {
		setTitle("Customer's View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		
		JLabel lblNewLabel_21 = new JLabel("Enter National ID");
		lblNewLabel_21.setForeground(Color.WHITE);
		lblNewLabel_21.setBounds(179, 45, 100, 20);
		layeredPane.add(lblNewLabel_21);
		
		textField_20 = new JTextField();
		textField_20.setBounds(167, 83, 117, 19);
		layeredPane.add(textField_20);
		textField_20.setColumns(10);
		
		JButton btnNewButton_18 = new JButton("Enter");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							String NID = textField_20.getText();
							BankUI frame = new BankUI(NID);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
		});
		btnNewButton_18.setBounds(167, 131, 117, 21);
		layeredPane.add(btnNewButton_18);
		
		JLabel lblNewLabel_22 = new JLabel("Don't have a profile?");
		lblNewLabel_22.setForeground(Color.WHITE);
		lblNewLabel_22.setBounds(179, 244, 150, 17);
		layeredPane.add(lblNewLabel_22);
		
		JButton btnNewButton_19 = new JButton("Create one!");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							BankUI frame = new BankUI(0);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_19.setBounds(167, 271, 117, 21);
		layeredPane.add(btnNewButton_19);

	}


	public BankUI(String NID) {
		BankAPI bank =  new BankAPI();
		Customer c = bank.selectCustomer(NID);
		if(NID == null || c == null) {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);

			JLabel lblNewLabel = new JLabel("Invalid input!");
			panel.add(lblNewLabel);
		}
		else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 1095, 659);

			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			JButton btnNewButton = new JButton("Show Personal info");
			menuBar.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					JTextArea textArea = new JTextArea();
					contentPane.add(textArea, BorderLayout.CENTER);
					Customer c = bank.selectCustomer(NID);
					textArea.setText(c.toString());
				}
			});

			JButton btnNewButton_1 = new JButton("Update info");
			menuBar.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane = new JLayeredPane();
					contentPane.add(layeredPane, BorderLayout.CENTER);

					JLabel lblNewLabel = new JLabel("New Name");
					lblNewLabel.setBounds(322, 96, 86, 16);
					layeredPane.add(lblNewLabel);

					textField = new JTextField();
					textField.setBounds(446, 93, 96, 19);
					textField.setText(c.Name);
					layeredPane.add(textField);
					textField.setColumns(10);

					JLabel lblNewLabel_1 = new JLabel("new mobile");
					lblNewLabel_1.setBounds(322, 145, 86, 13);
					layeredPane.add(lblNewLabel_1);

					textField_1 = new JTextField();
					textField_1.setBounds(446, 142, 96, 19);
					textField_1.setText(c.Mobile);
					layeredPane.add(textField_1);
					textField_1.setColumns(10);

					JLabel lblNewLabel_2 = new JLabel("new BirthDate");
					lblNewLabel_2.setBounds(322, 202, 103, 13);
					layeredPane.add(lblNewLabel_2);

					textField_2 = new JTextField();
					textField_2.setBounds(446, 199, 96, 19);
					textField_2.setText(c.DOB.toString());
					layeredPane.add(textField_2);
					textField_2.setColumns(10);

					JLabel lblNewLabel_3 = new JLabel("new Email");
					lblNewLabel_3.setBounds(322, 261, 86, 13);
					layeredPane.add(lblNewLabel_3);

					textField_3 = new JTextField();
					textField_3.setBounds(446, 258, 96, 19);
					textField_3.setText(c.Email);
					layeredPane.add(textField_3);
					textField_3.setColumns(10);

					JLabel lblNewLabel_4 = new JLabel("new Address");
					lblNewLabel_4.setBounds(322, 325, 86, 13);
					layeredPane.add(lblNewLabel_4);

					textField_4 = new JTextField();
					textField_4.setBounds(446, 322, 96, 19);
					textField_4.setText(c.Address);
					layeredPane.add(textField_4);
					textField_4.setColumns(10);

					JButton btnNewButton_9 = new JButton("Submit");
					btnNewButton_9.setBounds(809, 510, 85, 21);
					btnNewButton_9.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Customer c = new Customer(NID, textField.getText(), textField_3.getText(), textField_2.getText(), textField_1.getText(), textField_4.getText());
							try {
								bank.updateCustomer(c);
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "profile updated successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					layeredPane.add(btnNewButton_9);
				}
			});

			JButton btnNewButton_2 = new JButton("Add Acccount");
			menuBar.add(btnNewButton_2);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_2 = new JLayeredPane();
					getContentPane().add(layeredPane_2, BorderLayout.CENTER);

					JLabel lblNewLabel_7 = new JLabel("Branch#");
					lblNewLabel_7.setBounds(334, 152, 60, 13);
					layeredPane_2.add(lblNewLabel_7);

					JLabel lblNewLabel_8 = new JLabel("Account Type");
					lblNewLabel_8.setBounds(322, 202, 83, 13);
					layeredPane_2.add(lblNewLabel_8);

					textField_7 = new JTextField();
					textField_7.setBounds(432, 149, 96, 19);
					layeredPane_2.add(textField_7);
					textField_7.setColumns(10);

					textField_8 = new JTextField();
					textField_8.setBounds(432, 199, 96, 19);
					layeredPane_2.add(textField_8);
					textField_8.setColumns(10);

					JButton btnNewButton_13 = new JButton("generate account");
					btnNewButton_13.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Account acc = new Account(NID, textField_7.getText(), textField_8.getText());
							try {
								bank.AddAccount(acc);
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "Account generated successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnNewButton_13.setBounds(354, 260, 144, 21);
					layeredPane_2.add(btnNewButton_13);
				}
			});

			JButton btnNewButton_4 = new JButton("Update Account");
			menuBar.add(btnNewButton_4);
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_3 = new JLayeredPane();
					getContentPane().add(layeredPane_3, BorderLayout.CENTER);


					JRadioButton rdbtnNewRadioButton = new JRadioButton("Set Active?");
					rdbtnNewRadioButton.setSelected(true);
					rdbtnNewRadioButton.setBounds(447, 183, 103, 21);
					layeredPane_3.add(rdbtnNewRadioButton);

					JLabel lblNewLabel_9 = new JLabel("Activate");
					lblNewLabel_9.setBounds(326, 187, 60, 13);
					layeredPane_3.add(lblNewLabel_9);

					JLabel lblNewLabel_10 = new JLabel("IBAN");
					lblNewLabel_10.setBounds(326, 131, 45, 13);
					layeredPane_3.add(lblNewLabel_10);

					textField_9 = new JTextField();
					textField_9.setText("");
					textField_9.setBounds(433, 128, 96, 19);
					layeredPane_3.add(textField_9);
					textField_9.setColumns(10);

					JButton btnNewButton_14 = new JButton("submit");
					btnNewButton_14.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								Account acc = bank.selectCustomerAccount(textField_9.getText());
								if(rdbtnNewRadioButton.getSelectedIcon() != null) {
									acc.Status = "ACTIVE";
								}else {
									acc.Status = "INACTIVE";
								}
								bank.updateAccount(acc);
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "Account updated successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						}
					});
					btnNewButton_14.setBounds(370, 274, 85, 21);
					layeredPane_3.add(btnNewButton_14);

				}
			});

			JButton btnNewButton_3 = new JButton("Delete Account");
			menuBar.add(btnNewButton_3);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_4 = new JLayeredPane();
					getContentPane().add(layeredPane_4, BorderLayout.CENTER);

					textField_10 = new JTextField();
					textField_10.setBounds(455, 179, 96, 19);
					layeredPane_4.add(textField_10);
					textField_10.setColumns(10);

					JLabel lblNewLabel_11 = new JLabel("Enter IBAN");
					lblNewLabel_11.setBounds(316, 182, 77, 13);
					layeredPane_4.add(lblNewLabel_11);

					JButton btnNewButton_15 = new JButton("Delete Account");
					btnNewButton_15.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								bank.deleteAccount(textField_10.getText());
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "Account deleted successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnNewButton_15.setBounds(367, 252, 120, 21);
					layeredPane_4.add(btnNewButton_15);

				}
			});

			JButton btnNewButton_5 = new JButton("Add Card");
			menuBar.add(btnNewButton_5);
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_1 = new JLayeredPane();
					getContentPane().add(layeredPane_1, BorderLayout.CENTER);

					JLabel lblNewLabel_5 = new JLabel("Card Type");
					lblNewLabel_5.setBounds(283, 179, 66, 13);
					layeredPane_1.add(lblNewLabel_5);

					JLabel lblNewLabel_6 = new JLabel("Account IBAN");
					lblNewLabel_6.setBounds(268, 136, 81, 13);
					layeredPane_1.add(lblNewLabel_6);

					textField_5 = new JTextField();
					textField_5.setBounds(398, 133, 96, 19);
					layeredPane_1.add(textField_5);
					textField_5.setColumns(10);

					textField_6 = new JTextField();
					textField_6.setBounds(398, 176, 96, 19);
					layeredPane_1.add(textField_6);
					textField_6.setColumns(10);

					JButton btnNewButton_12 = new JButton("generate Card");
					btnNewButton_12.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Card c = new Card(textField_5.getText(), textField_6.getText());
							try {
								bank.AddCard(c);
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "Card generated successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnNewButton_12.setBounds(321, 252, 124, 21);
					layeredPane_1.add(btnNewButton_12);
				}
			});

			JButton btnNewButton_7 = new JButton("Delete Card");
			menuBar.add(btnNewButton_7);
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_5 = new JLayeredPane();
					getContentPane().add(layeredPane_5, BorderLayout.CENTER);

					JLabel lblNewLabel_12 = new JLabel("Enter Card#");
					lblNewLabel_12.setBounds(284, 188, 87, 13);
					layeredPane_5.add(lblNewLabel_12);

					textField_11 = new JTextField();
					textField_11.setBounds(408, 185, 96, 19);
					layeredPane_5.add(textField_11);
					textField_11.setColumns(10);

					JButton btnNewButton_16 = new JButton("Delete Card");
					btnNewButton_16.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								bank.deleteCard(textField_11.getText());
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "card deleted successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnNewButton_16.setBounds(335, 241, 108, 21);
					layeredPane_5.add(btnNewButton_16);

				}
			});

			JButton btnNewButton_8 = new JButton("Make Transaction");
			menuBar.add(btnNewButton_8);
			btnNewButton_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					JLayeredPane layeredPane_6 = new JLayeredPane();
					getContentPane().add(layeredPane_6, BorderLayout.CENTER);

					JLabel lblNewLabel_13 = new JLabel("IBAN");
					lblNewLabel_13.setBounds(300, 116, 45, 13);
					layeredPane_6.add(lblNewLabel_13);

					JLabel lblNewLabel_14 = new JLabel("Card#");
					lblNewLabel_14.setBounds(300, 160, 45, 13);
					layeredPane_6.add(lblNewLabel_14);

					JLabel lblNewLabel_15 = new JLabel("Receiver IBAN");
					lblNewLabel_15.setBounds(300, 204, 96, 13);
					layeredPane_6.add(lblNewLabel_15);

					JLabel lblNewLabel_16 = new JLabel("Amount");
					lblNewLabel_16.setBounds(300, 296, 45, 13);
					layeredPane_6.add(lblNewLabel_16);

					JLabel lblNewLabel_17 = new JLabel("Currency");
					lblNewLabel_17.setBounds(300, 339, 60, 13);
					layeredPane_6.add(lblNewLabel_17);

					JLabel lblNewLabel_18 = new JLabel("Transaction Type");
					lblNewLabel_18.setBounds(300, 251, 110, 13);
					layeredPane_6.add(lblNewLabel_18);

					JLabel lblNewLabel_19 = new JLabel("Branch#");
					lblNewLabel_19.setBounds(300, 382, 60, 13);
					layeredPane_6.add(lblNewLabel_19);

					JLabel lblNewLabel_20 = new JLabel("Comments");
					lblNewLabel_20.setBounds(300, 431, 61, 13);
					layeredPane_6.add(lblNewLabel_20);

					textField_12 = new JTextField();
					textField_12.setBounds(413, 113, 96, 19);
					layeredPane_6.add(textField_12);
					textField_12.setColumns(10);

					textField_13 = new JTextField();
					textField_13.setBounds(413, 157, 96, 19);
					layeredPane_6.add(textField_13);
					textField_13.setColumns(10);

					textField_14 = new JTextField();
					textField_14.setBounds(413, 201, 96, 19);
					layeredPane_6.add(textField_14);
					textField_14.setColumns(10);

					textField_15 = new JTextField();
					textField_15.setBounds(413, 248, 96, 19);
					layeredPane_6.add(textField_15);
					textField_15.setColumns(10);

					textField_16 = new JTextField();
					textField_16.setBounds(413, 293, 96, 19);
					layeredPane_6.add(textField_16);
					textField_16.setColumns(10);

					textField_17 = new JTextField();
					textField_17.setBounds(413, 336, 96, 19);
					layeredPane_6.add(textField_17);
					textField_17.setColumns(10);

					textField_18 = new JTextField();
					textField_18.setBounds(413, 379, 96, 19);
					layeredPane_6.add(textField_18);
					textField_18.setColumns(10);

					textField_19 = new JTextField();
					textField_19.setBounds(413, 428, 96, 19);
					layeredPane_6.add(textField_19);
					textField_19.setColumns(10);

					JButton btnNewButton_17 = new JButton("Make Transaction");
					btnNewButton_17.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							contentPane.removeAll();
							contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
							contentPane.setLayout(new BorderLayout(0, 0));
							setContentPane(contentPane);
							
							Transaction t = new Transaction(textField_17.getText(), textField_16.getText(), textField_18.getText(), textField_12.getText(), 
									textField_13.getText(), textField_14.getText(), textField_19.getText(), Double.parseDouble(textField_15.getText()));
							try {
								bank.AddTransaction(t);
								JFrame jFrame = new JFrame();
								JOptionPane.showMessageDialog(jFrame, "Transaction made successfully!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnNewButton_17.setBounds(353, 480, 125, 21);
					layeredPane_6.add(btnNewButton_17);

				}
			});


			JButton btnNewButton_6 = new JButton("Show Accounts");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);
					try {
						AccountsTable accounts = new AccountsTable(bank.selectCustomerAccounts(NID));
						if(accounts.getRowCount() != 0) {
							table = new JTable();
							getContentPane().add(table, BorderLayout.CENTER);
							table.setModel(accounts);
							getContentPane().add(new JScrollPane(table));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton_6);

			JButton btnNewButton_10 = new JButton("show Cards");
			btnNewButton_10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);
					try {
						CardsTable cards = new CardsTable(bank.selectCustomerCards(NID));
						if(cards.getRowCount() != 0) {

							table = new JTable();
							getContentPane().add(table, BorderLayout.CENTER);
							table.setModel(cards);
							getContentPane().add(new JScrollPane(table));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton_10);

			JButton btnNewButton_11 = new JButton("show Transactions");
			btnNewButton_11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);
					try {
						TransactionsTable transactions = new TransactionsTable(bank.selectCustomerTransactions(NID));
						if(transactions.getRowCount() != 0) {
							table = new JTable();
							getContentPane().add(table, BorderLayout.CENTER);
							table.setModel(transactions);
							getContentPane().add(new JScrollPane(table));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton_11);	
			
			JButton btnNewButton_18 = new JButton("show loans");
			btnNewButton_18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.removeAll();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);
					LoansTable loans;
					try {
						loans = new LoansTable(bank.selectCustomerLoans(NID));
						if(loans.getRowCount() != 0) {
							table = new JTable();
							getContentPane().add(table, BorderLayout.CENTER);
							table.setModel(loans);
							getContentPane().add(new JScrollPane(table));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			menuBar.add(btnNewButton_18);
		}
	}
	
	public BankUI(int a) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Enter Name");
		lblNewLabel.setBounds(179, 106, 71, 21);
		layeredPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(340, 107, 96, 19);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter National ID");
		lblNewLabel_1.setBounds(179, 162, 100, 13);
		layeredPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(340, 159, 96, 19);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Date of birth");
		lblNewLabel_2.setBounds(179, 220, 120, 13);
		layeredPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(340, 217, 96, 19);
		layeredPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Email");
		lblNewLabel_3.setBounds(179, 278, 82, 13);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Mobile");
		lblNewLabel_4.setBounds(179, 329, 90, 13);
		layeredPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(340, 275, 96, 19);
		layeredPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(340, 326, 96, 19);
		layeredPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Address");
		lblNewLabel_5.setBounds(179, 376, 90, 13);
		layeredPane.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setText("");
		textField_5.setBounds(340, 373, 96, 19);
		layeredPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Create profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankAPI bank = new BankAPI(); 
				//Customer(String nid, String name, String email, String dob, String mobile, String address)
				Customer c = new Customer(textField_1.getText(), textField.getText(), textField_3.getText(), textField_2.getText(), textField_4.getText(), textField_5.getText());
				try {
					bank.AddCustomer(c);
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Profile created successfully!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(248, 448, 120, 21);
		layeredPane.add(btnNewButton);
		
		
	}
}
