package pAssign07;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * File: PAssign07.java Class: CSCI 1302
 * 
 * @author Jocelyn Varghese 
 * Created on: Oct 31, 2022 
 * Last Modified: Nov 21, 2022
 * Description: JavaFX ATM simulator
 * 
 *         GitHub hello-world repository:
 *         https://github.com/JocelynGV/hello-world
 */

public class PAssign07v2 extends Application {
	// Make keypad pane and pane for pin
	KeyPadPaneATM atm = new KeyPadPaneATM();
	VBox vBoxPanePin = new VBox(5);
	
	// Create gridpane for home screen
	GridPane gridPaneHome = new GridPane();
	
	// Create vbox pane for withdrawal screen
	VBox vBoxPaneTransfer = new VBox(10);
	
	// Create the mother pane
	BorderPane borderPane = new BorderPane();

	// Textfield to enter pin num
	TextField tfPin = new TextField();

	// User-entered pin
	StringBuilder pin = new StringBuilder();

	// Pin that is displayed in textfield
	StringBuilder pinDisplay = new StringBuilder();

	// Error message for incorrect pin
	Label lblIncorrectPin = new Label("Invalid Pin. Please try again.");
	
	// Label for transfer
	Label lblTransfer = new Label();

	// Text field for withdrawal
	TextField tfTransfer = new TextField();

	// User-entered withdrawal amount
	StringBuilder transferAmount = new StringBuilder();
	
	// Withdrawal amount double
	double transferDouble;
	
	// Create label message for withdrawal
	Label lblMessage = new Label();

	// User's current balance
	double currentBalance = 3.16;

	// User-entered amount for transfer
	StringBuilder moneyTransfer = new StringBuilder();

	public void start(Stage primaryStage) {
		// Make UI changes to atm pane
		atm.setPadding(new Insets(10, 10, 10, 10));
		atm.setHgap(5);
		atm.setVgap(5);
		atm.setAlignment(Pos.CENTER);
		atm.setStyle("-fx-background-color: gray;");

		// Set bank name image
		File image = new File("src/pAssign07/image/VargheseBank2.PNG");
		ImageView namePic = new ImageView(image.toURI().toString());
		namePic.setFitHeight(45);
		namePic.setFitWidth(340);

		// Add image to a pane
		HBox imagePane = new HBox();
		imagePane.getChildren().add(namePic);
		imagePane.setAlignment(Pos.CENTER);
		imagePane.setStyle("-fx-background-color: linear-gradient(red, yellow);");

		// Make label and edit text field for pin pane
		Label lblPin = new Label("Enter your PIN number: ");
		lblPin.setPadding(new Insets(5, 5, 5, 5));
		tfPin.setEditable(false);
		tfPin.setMaxWidth(175);
		tfPin.setAlignment(Pos.BOTTOM_RIGHT);

		// Add nodes to vbox pane for pin screem
		vBoxPanePin.getChildren().setAll(lblPin, tfPin);
		vBoxPanePin.setPadding(new Insets(15, 15, 15, 15));
		vBoxPanePin.setAlignment(Pos.TOP_CENTER);
		vBoxPanePin.setStyle("-fx-border-color: black; -fx-border-width: 3");

		// Make buttons for home screen
		Label welcome = new Label("Welcome, You!");
		Button btWithdraw = new Button("Make Withdrawal");
		Button btBalance = new Button("Check Balance");
		Button btDeposit = new Button("Make Deposit");
		Button btReturn = new Button("Go Back");

		ArrayList<Button> homeButtons = new ArrayList<>(Arrays.asList(btWithdraw, btBalance, btDeposit, btReturn));

		for (int i = 0; i < homeButtons.size(); i++) {
			homeButtons.get(i).setMinHeight(30);
			;
			homeButtons.get(i).setMinWidth(130);
			homeButtons.get(i).setStyle("-fx-border-color: black; -fx-background-color: lightblue");
		}

		// Make grid pane for home screen and put nodes in it
		gridPaneHome.setPadding(new Insets(15, 15, 15, 15));
		gridPaneHome.setVgap(15);
		gridPaneHome.setHgap(15);
		gridPaneHome.setAlignment(Pos.CENTER);
		gridPaneHome.setStyle("-fx-border-color: black; -fx-border-width: 3");
		gridPaneHome.add(welcome, 0, 0);
		gridPaneHome.add(homeButtons.get(0), 0, 1);
		gridPaneHome.add(homeButtons.get(1), 1, 1);
		gridPaneHome.add(homeButtons.get(2), 0, 2);
		gridPaneHome.add(homeButtons.get(3), 1, 2);

		// Make label and rectangle for stack pane for the balance
		Label lblBalance = new Label(String.format("Current Balance: \t$%,.2f", currentBalance));
		Rectangle rect = new Rectangle(250, 100);
		rect.setFill(Color.LIGHTBLUE);
		rect.setStroke(Color.BLACK);
		rect.setArcHeight(10);
		rect.setArcWidth(10);

		// Create a stack pane for balance screen
		StackPane stackPaneBalance = new StackPane();
		Font font2 = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 17);
		lblBalance.setFont(font2);
		stackPaneBalance.getChildren().addAll(rect, lblBalance);

		// Put stack pane and a button in a HBox pane
		VBox vBoxPaneBalance = new VBox(10);
		Button btReturnBalance = new Button("Go Back");
		btReturnBalance.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
		vBoxPaneBalance.getChildren().addAll(stackPaneBalance, btReturnBalance);
		vBoxPaneBalance.setPadding(new Insets(15, 15, 15, 15));
		vBoxPaneBalance.setAlignment(Pos.CENTER);
		vBoxPaneBalance.setStyle("-fx-border-color: black; -fx-border-width: 3");

		// Make vbox pane for withdraw screen
		tfTransfer.setEditable(false);
		tfTransfer.setMaxWidth(175);
		tfTransfer.setAlignment(Pos.BOTTOM_RIGHT);
		Button btReturnTransfer = new Button("Go Back");
		btReturnTransfer.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
		vBoxPaneTransfer.getChildren().addAll(lblTransfer, tfTransfer, btReturnTransfer);
		vBoxPaneTransfer.setPadding(new Insets(15, 15, 15, 15));
		vBoxPaneTransfer.setAlignment(Pos.CENTER);
		vBoxPaneTransfer.setStyle("-fx-border-color: black; -fx-border-width: 3");

		// Create the pane of all panes
		borderPane.setBottom(atm);
		borderPane.setCenter(vBoxPanePin);
		borderPane.setTop(imagePane);

		Scene scene = new Scene(borderPane, 550, 450);
		primaryStage.setTitle("ATM");
		primaryStage.setScene(scene);
		primaryStage.show();

		
		// Set keypad actions for grid pane pin
		if (borderPane.getCenter().equals(vBoxPanePin)) {
			registerEventHandlersPin();
			System.out.println("pin");
		}

		/*
		if (borderPane.getCenter().equals(vBoxPaneWithdraw)) {
			registerEventHandlersWithdraw();
			System.out.println("withdraw");
		} */

		// Process withdrawal
		btWithdraw.setOnAction(e -> {
			borderPane.setCenter(vBoxPaneTransfer);
			registerEventHandlersWithdraw();
			
			// Reset screen
			if (vBoxPaneTransfer.getChildren().contains(lblMessage)) {
				vBoxPaneTransfer.getChildren().remove(lblMessage);
			}
			
		}); 
		
		// Process deposit
		btDeposit.setOnAction(e -> {
			borderPane.setCenter(vBoxPaneTransfer);
			registerEventHandlersDeposit();
			
			// Reset screen
			if (vBoxPaneTransfer.getChildren().contains(lblMessage)) {
				vBoxPaneTransfer.getChildren().remove(lblMessage);
			}
			
		}); 

		// Process go back button on withdraw screen
		btReturnTransfer.setOnAction(e -> {
			borderPane.setCenter(gridPaneHome);
			registerEventHandlersPin();
		});

		// Process check balance
		btBalance.setOnAction(e -> {
			borderPane.setCenter(vBoxPaneBalance);
			lblBalance.setText(String.format("Current Balance: \t$%,.2f", currentBalance));
		});

		// Process go back button
		btReturn.setOnAction(e -> borderPane.setCenter(vBoxPanePin));

		// Process go back button on balance screen
		btReturnBalance.setOnAction(e -> borderPane.setCenter(gridPaneHome));
	}

	// Method to handle keypad action events
	protected void registerEventHandlersPin() {
		// Process the numbers for keypad events
		for (int i = 0; i < atm.listButtons.size(); i++) {
			atm.listButtons.get(i).setOnAction(e -> {
				// Remove error message if it exists
				if (vBoxPanePin.getChildren().get(0).equals(lblIncorrectPin)) {
					vBoxPanePin.getChildren().remove(0);
				}

				// Clear Pin display if pin is deleted
				if (pin.length() == 0) {
					pinDisplay.delete(0, pinDisplay.length());
				}

				// Add user number when button is clicked
				pin.append(((Button) e.getSource()).getText());

				// Censor user's pin
				for (int j = 0; j < pin.length() - 1; j++) {
					pinDisplay.setCharAt(j, '*');
				}

				pinDisplay.append(pin.charAt(pin.length() - 1));

				tfPin.setText(pinDisplay.toString());
				System.out.println(pin.toString());
			});
		}

		// Process clear button
		atm.btnClear.setOnAction(e -> {
			// Clear any numbers
			pin.delete(0, pin.length());
			moneyTransfer.delete(0, moneyTransfer.length());

			tfPin.setText("");
		});

		// Process delete button
		atm.btnDelete.setOnAction(e -> deletePin());
		
		// Process enter button
		atm.btnEnter.setOnAction(e -> {
			// Check if pin is valid or not
			if (checkPin()) {
				// PIN: 14789
				borderPane.setCenter(gridPaneHome);
				pin.delete(0, pin.length());
				tfPin.setText("");

			} else {
				lblIncorrectPin.setStyle("-fx-text-fill: darkred; -fx-background-color: palevioletred");
				vBoxPanePin.getChildren().add(0, lblIncorrectPin);
				tfPin.setText("");

				pin.delete(0, pin.length());
			}
		});
	}

	// Method to handle common functionality between transfers (withdrawal and deposit)
	protected void registerEventHandlersTransfer() {
		// Display blank transfer amount
		tfTransfer.setText(String.format("$%,.2f", transferDouble));

		for (int i = 0; i < atm.listButtons.size(); i++) {
			atm.listButtons.get(i).setOnAction(e -> {
				// Remove message if it exists
				if (vBoxPaneTransfer.getChildren().contains(lblMessage)) {
					vBoxPaneTransfer.getChildren().remove(lblMessage);
				}
				// Add user number when button is clicked
				transferAmount.append(((Button) e.getSource()).getText());

				// Display amount in dollars
				for (int j = 0; j < transferAmount.length(); j++) {
					transferDouble = (Double.parseDouble(transferAmount.toString())) / 100;
					tfTransfer.setText(String.format("$%,.2f", transferDouble));
				}
			});

			// Process clear button
			atm.btnClear.setOnAction(e -> {
				// Clear any numbers
				clearTransferAmount();
			});

			// Process delete button for withdrawal
			atm.btnDelete.setOnAction(e -> {
				// Change withdrawal amount in the string builder, set the new double, and display the new amount
				transferAmount.deleteCharAt(transferAmount.length() - 1);
				transferDouble = (Double.parseDouble(transferAmount.toString())) / 100;
				tfTransfer.setText(String.format("$%,.2f", transferDouble));
			});
		}
	}

	// Method to handle keypad events for withdrawal
	protected void registerEventHandlersWithdraw() {
		registerEventHandlersTransfer();
		
		// Set transfer label 
		lblTransfer.setText("Enter the amount you would like to withdraw");

		// Process enter button for withdrawal
		atm.btnEnter.setOnAction(e -> {
			if (transferDouble <= currentBalance) {
				// Update balance
				currentBalance = currentBalance - transferDouble;

				// Set text and style for the message
				lblMessage.setText("Withdrawal complete");
				lblMessage.setStyle("-fx-text-fill: black; -fx-background-color: lightgreen");

				// Add message to pane
				vBoxPaneTransfer.getChildren().add(2, lblMessage);

				// Reset the withdrawal string builder and the display text field
				clearTransferAmount();
			} else {
				// Set text and style for the error message
				lblMessage.setText("Insufficient Balance");
				lblMessage.setStyle("-fx-text-fill: darkred; -fx-background-color: palevioletred");

				// Add message to pane
				vBoxPaneTransfer.getChildren().add(2, lblMessage);

				// Reset the withdrawal string builder, double, and the display text field
				clearTransferAmount();
			}
		});
	}
	
	// Method to handle keypad events for deposit
	protected void registerEventHandlersDeposit() {
		registerEventHandlersTransfer();
		
		// Set transfer label 
		lblTransfer.setText("Enter the amount you would like to deposit");
		
		// Process enter button deposit 
		atm.btnEnter.setOnAction(e -> {
			// Update current balance
			currentBalance += transferDouble;
			
			// Set text and style for the message
			lblMessage.setText("Deposit complete");
			lblMessage.setStyle("-fx-text-fill: black; -fx-background-color: lightgreen");

			// Add message to pane
			vBoxPaneTransfer.getChildren().add(2, lblMessage);

			// Reset the withdrawal string builder and the display text field
			clearTransferAmount();
		});
		
	}

	protected void clearTransferAmount() {
		// Clear string builder
		transferAmount.delete(0, transferAmount.length());
		
		// Clear double
		transferDouble = 0;
		
		// Clear text field
		tfTransfer.setText(String.format("$%,.2f", transferDouble));
	}

	// Method to delete a single number from pin
	protected void deletePin() {
		// Remove last number from user pin
		pin.deleteCharAt(pin.length() - 1);

		// Clear pin display
		pinDisplay.delete(0, pinDisplay.length());

		// Display censored part of pin
		if (pin.length() > 0) {
			for (int i = 0; i < pin.length() - 1; i++) {
				pinDisplay.append("*");
			}

			pinDisplay.append(pin.charAt(pin.length() - 1));
			tfPin.setText(pinDisplay.toString());

		} else if (pin.length() == 0) {
			tfPin.setText("");
		}

		tfPin.setText(pinDisplay.toString());
	}

	// Method to check pin's validity
	protected boolean checkPin() {
		int myPin = 11111;

		if (Double.parseDouble(pin.toString()) == myPin) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
