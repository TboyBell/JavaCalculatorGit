import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Backend implements ActionListener {
	JFrame frame;
	JPanel panel;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[17];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton;
	JButton perButton, sqrButton, negButton, powButton, hisButton;
	boolean isRunning;
	DocumentLogger logfilHistory = new DocumentLogger();

	Font myFont = new Font("Arial Black", Font.PLAIN, 25);

	double num1 = 0, num2 = 0, result = 0, powCount = 0;
	char operator;

	Backend() {

		frame = new JFrame("Calculator");
		frame.setSize(400, 600); // Adjusted width and height to fit all buttons
		frame.setLayout(null);
		isRunning = true;

		// Special Close function for history function.
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeApplication();
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			if (!isRunning) {
				logfilHistory.deleteTracebackFile();
			}
		}));

		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50); // Adjusted width to match frame
		textfield.setFont(myFont);
		textfield.setEditable(false);

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		perButton = new JButton("%");
		sqrButton = new JButton("√");
		negButton = new JButton("±");
		powButton = new JButton("^2");
		delButton = new JButton("D");
		clrButton = new JButton("C");
		hisButton = new JButton("<#");
		

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = perButton;
		functionButtons[9] = sqrButton;
		functionButtons[10] = negButton;
		functionButtons[11] = powButton;
		functionButtons[12] = hisButton;

		for (int i = 0; i < 13; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}

		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 400); // Panel adjusted for correct layout size
		panel.setLayout(new GridLayout(6, 4, 10, 10)); // 6 rows and 4 columns layout

		// Adding buttons to panel in the order from the provided calculator image
		panel.add(clrButton);
		panel.add(delButton);
		panel.add(perButton);
		panel.add(divButton);

		panel.add(hisButton); // Empty space placeholder
		panel.add(powButton);
		panel.add(sqrButton);
		panel.add(mulButton);

		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(subButton);

		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(addButton);

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(equButton);

		panel.add(negButton);
		panel.add(numberButtons[0]);
		panel.add(decButton);
		panel.add(new JButton("")); // Empty space placeholder for symmetry

		frame.add(panel);
		frame.add(textfield);
		frame.setVisible(true);
	}

	

	public void closeApplication() {
		System.out.println("Closing application. Cleaning up resources...");
		isRunning = false; 
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			for (int i = 0; i < 10; i++) {
				if (e.getSource() == numberButtons[i]) {
					textfield.setText(textfield.getText().concat(String.valueOf(i)));
				}
			}

			if (e.getSource() == decButton) {
				textfield.setText(textfield.getText().concat("."));
			}
			if (e.getSource() == addButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '+';
				textfield.setText("");
			}
			if (e.getSource() == subButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '-';
				textfield.setText("");
			}

			if (e.getSource() == mulButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '*';
				textfield.setText("");
			}

			if (e.getSource() == divButton) {
				num1 = Double.parseDouble(textfield.getText());
				operator = '/';
				textfield.setText("");
			}


			// Special Characters Operators
			
			if (e.getSource() == powButton) {
				if (powCount < 2) { 
					num1 = Double.parseDouble(textfield.getText());
					System.out.println(num1);
					operator = '^'; 
					num2 += 2; 
					textfield.setText(textfield.getText().concat("^(2)"));
				} else {
					num2 += 2;
					operator = '^';
					textfield.setText(textfield.getText().concat("^(2)"));
				}
			}

			if (e.getSource() == perButton) {
				double basevalue = Double.parseDouble(textfield.getText());
				double percentage = basevalue / 100;
				textfield.setText(String.valueOf(percentage));
			}

			if (e.getSource() == sqrButton) {
				double basevalue = Double.parseDouble(textfield.getText());
				double sqrtValue = 0;
				if (basevalue >= 0) {
					sqrtValue = Math.sqrt(basevalue);
					textfield.setText(String.valueOf(sqrtValue));
				}

				String temp = "√" + basevalue;
				logfilHistory.logTraceback(temp, sqrtValue);
			}

			if (e.getSource() == negButton) {
				double basevalue = Double.parseDouble(textfield.getText());
				textfield.setText(String.valueOf(-basevalue));
			}

			if (e.getSource() == equButton) {
				num2 = Double.parseDouble(textfield.getText());

				switch (operator) {
					case '+':
						result = num1 + num2;
						logfilHistory.logTraceback(num1, num2, operator, result);
						break;
					case '-':
						result = num1 - num2;
						logfilHistory.logTraceback(num1, num2, operator, result);
						break;
					case '*':
						result = num1 * num2;
						logfilHistory.logTraceback(num1, num2, operator, result);
						break;
					case '/':
						result = num1 / num2;
						logfilHistory.logTraceback(num1, num2, operator, result);
						break;
					case '^':
						String operation = textfield.getText();
						result = Math.pow(num1, num2);
						System.out.println(result);
						logfilHistory.logTraceback(operation, result);
						break;
				}
					
					textfield.setText(String.valueOf(result));
					num1 = result;
			}


			if (e.getSource() == clrButton) {
				textfield.setText("");
			}

			if (e.getSource() == delButton) {
				String string = textfield.getText();
				textfield.setText("");
				for (int i = 0; i < string.length() - 1; i++) {
					textfield.setText(textfield.getText() + string.charAt(i));
				}
			}

		} catch (ArithmeticException i) {
			JOptionPane.showMessageDialog(null, "Arithmetic error: " + i.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException i) {
			JOptionPane.showMessageDialog(null, "Invalid number format: " + i.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception i) {
			JOptionPane.showMessageDialog(null, "An unexpected error has occurred: " + i.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}



}