import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Backend implements ActionListener{

	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[8];
	JButton addbutton, subbutton, mulbutton, divbutton;
	JButton decbutton, equbutton, delbutton, clrbutton;
	JPanel panel;
	
	Font myfont = new Font("Arial Black", Font.ITALIC, 25);
	
	double num1 = 0, num2 =0, result=0;
	char operator;
	
	

	Backend() {

	
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitapplication();
			}
		} );
		frame.setSize(420,550);
		frame.setLayout(null);
		
		
		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50);
		textfield.setFont(myfont);
		textfield.setEditable(false);
		
		
		addbutton = new JButton("+");
		subbutton = new JButton("-");
		mulbutton = new JButton("*");
		divbutton = new JButton("/");
		decbutton = new JButton(".");
		equbutton = new JButton("=");
		delbutton = new JButton("Delete");
		clrbutton = new JButton("Clear");
		
		functionButtons[0] = addbutton;
		functionButtons[1] = subbutton;
		functionButtons[2] = mulbutton;
		functionButtons[3] = divbutton;
		functionButtons[4] = decbutton;
		functionButtons[5] = equbutton;
		functionButtons[6] = delbutton;
		functionButtons[7] = clrbutton;
		
		
		
		for(int i = 0; i < 8; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myfont);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myfont);
			numberButtons[i].setFocusable(false);
		}
		
		
		
		delbutton.setBounds(50, 430, 145, 50);
		clrbutton.setBounds(205, 430, 145, 50);
		
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,4,10,10));

		
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addbutton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subbutton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulbutton);
		panel.add(decbutton);
		panel.add(numberButtons[0]);
		panel.add(equbutton);
		panel.add(divbutton);
		
		
		frame.add(panel);
		frame.add(delbutton);
		frame.add(clrbutton);
		frame.add(textfield);
		frame.setVisible(true);
		
		
	}
	
	private void exitapplication() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for(int i=0;i<10;i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		
		
		if(e.getSource() == decbutton) {
			textfield.setText(textfield.getText().concat("."));
		}
		if(e.getSource() == addbutton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		if (e.getSource() == subbutton) {
	        num1 = Double.parseDouble(textfield.getText());
	        operator = '-';
	        textfield.setText("");
	    }

	    if (e.getSource() == mulbutton) {
	        num1 = Double.parseDouble(textfield.getText());
	        operator = '*';
	        textfield.setText("");
	    }

	    if (e.getSource() == divbutton) {
	        num1 = Double.parseDouble(textfield.getText());
	        operator = '/';
	        textfield.setText("");
	    }

	    if(e.getSource() == equbutton) {
	    	num2=Double.parseDouble(textfield.getText());
	    	
	    	  switch (operator) {
	            case '+':
	                result = num1 + num2;
	                break;
	            case '-':
	                result = num1 - num2;
	                break;
	            case '*':
	                result = num1 * num2;
	                break;
	            case '/':
	                result = num1 / num2;
	                break;
	        }
	        textfield.setText(String.valueOf(result));
	        num1 = result;
	    }
	    	
	    if (e.getSource() == clrbutton) {
	    	textfield.setText("");
	    }	
	    if (e.getSource() == delbutton) {
	        String string = textfield.getText();
	        textfield.setText("");
	        for (int i = 0; i < string.length() - 1; i++) {
	            textfield.setText(textfield.getText() + string.charAt(i));
	        }
	    
	    }
	    
	    
	    
		
	}
	
}

