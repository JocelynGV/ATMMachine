package pAssign07;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class KeyPadPaneATM extends KeyPadPane {
	protected Button btnDelete = new Button("Delete");
	protected Button btnClear = new Button("Clear");
	protected Button btnEnter = new Button("Enter");
	protected Button btnBlank3 = new Button("");
	
	protected ArrayList<Button> ATMButtons = new ArrayList<>(Arrays.asList(btnDelete, btnClear, btnEnter, btnBlank3));
	
	public KeyPadPaneATM() {
		// Add unique buttons and change size
		for (int i = 0; i < ATMButtons.size(); i++) {
			super.add(ATMButtons.get(i), 3, i);
			ATMButtons.get(i).setMinHeight(40);
			ATMButtons.get(i).setMinWidth(60);
			ATMButtons.get(i).setAlignment(Pos.TOP_CENTER);
//			ATMButtons.get(i).setStyle("-fx-background-color: silver;");
			ATMButtons.get(i).setStyle("-fx-border-color: black;");
		}
		
		// Change button colors 
		ATMButtons.get(0).setTextFill(Color.RED);
		ATMButtons.get(1).setTextFill(Color.GOLDENROD);
		ATMButtons.get(2).setTextFill(Color.GREEN);
		
		// Make other buttons bigger
		for (int i = 0; i < listButtons.size(); i++) {
			listButtons.get(i).setMinHeight(40);
			listButtons.get(i).setMinWidth(40);
			listButtons.get(i).setStyle("-fx-border-color: black;");

		}
		
		
//		super.add(btnBlank3, REMAINING, REMAINING);
		
		/*
		btnBlank1.setStyle("-fx-background-color: gainsboro;");
		btnBlank2.setStyle("-fx-background-color: gainsboro;");
		btnBlank3.setStyle("-fx-background-color: gainsboro;");  */

	}
	
	
	/*
	protected StringBuilder registerEventHandlersString() {
		StringBuilder pin = new StringBuilder();
		String pinString = "";
		
		for (int i = 0; i < listButtons.size(); i++) {
			listButtons.get(i).setOnAction(e -> {
				
				pin.append(((Button)e.getSource()).getText());
//				System.out.println(pin.toString());
				
			});
			
			pinString = pin.toString();
		}
		System.out.println(pin);
		return pin;

	} */
	
	
}