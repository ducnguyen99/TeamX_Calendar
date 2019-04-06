/*

RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2018C
Assessment: Assignment 3 - Class Diagram
Authors: Nguyen Huu Ngoc Duc, Duong Minh Nhat, Nguyen Minh Tri
ID: S3680598, S3715125, S3726096
Created date:  17/01/2019
Acknowledgement:
Java Naming Convention, Java 8 API  (https://docs.oracle.com/javase/8/docs/api/)
https://gist.github.com/jewelsea/2658491?fbclid=IwAR3Vkg6UMcBJ0DjuDnw56wcAcej2A7G7jmWOqX7tNWG0MK39J-QLNUeJ-QU
https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/?fbclid=IwAR14MgPJu91xJh61Lkru6g6OA-kPTzvPQi3mxu1GPr5ojXuzUQ57Cns7kXY
https://gist.github.com/jewelsea/2658491?fbclid=IwAR1kF3zNiIJjIbgg0MLx4J0RtLIcOO9hpucSGIE3IBWMsMQQdJOo3n9afYg
https://www.tutorialspoint.com/java/java_date_time.htm?fbclid=IwAR0eSC7M2i79JGrFDHmG-zQtM8113mIytetaaAdsgqkCJ7gkTiNOEnHZy3w
https://gist.github.com/jewelsea/2658491?fbclid=IwAR1WW_9I8imgCJT28mGUg11UIDcSxVcMzukGZV_rdvnYAM-crwC3nRP2HwI
https://github.com/SirGoose3432/javafx-calendar
https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method?fbclid=IwAR0y14VzptPFpZAn00b18Gwmq3mM09boMMG9vqPv-HusEu6SRnV4HQeWQMQ
https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
https://o7planning.org/en/11085/javafx-datepicker-tutorial
*/

package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SignIn {
    private Button signIn;
    private VBox view;
    private TextField inputNameArea;
    private PasswordField inputPasswordArea;
    private Text error;

    public SignIn(){
        //Create title bar
        Text title = new Text("TeamX Calendar");
        title.setFont(Font.font("Times New Roman",FontWeight.EXTRA_BOLD,50));
        title.setFill(Color.WHITE);
        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(10,10,10,10));
        titleBox.setStyle("-fx-background-color: #004987;"+"-fx-border-color: black;");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title);

        //Create userBox
        VBox userBox = new VBox();
        userBox.setStyle("-fx-border-color: black;"+"-fx-background-color: #cccccc");
        userBox.setAlignment(Pos.CENTER);
        userBox.setPadding(new Insets(10,10,10,10));
        userBox.setPrefSize(300,200);

        //Create input name area
        HBox inputName = new HBox();
        inputName.setAlignment(Pos.CENTER);
        Text name = new Text("User Name: ");
        name.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        inputNameArea = new TextField();
        inputNameArea.setPrefColumnCount(20);
        inputNameArea.setPromptText("Type in your email...");
        inputNameArea.setFocusTraversable(false);
        inputName.getChildren().addAll(name,inputNameArea);

        //Create input password area
        HBox inputPassword = new HBox();
        inputPassword.setAlignment(Pos.CENTER);
        inputPassword.setSpacing(18);
        Text password = new Text("Password: ");
        password.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        inputPasswordArea = new PasswordField();
        inputPasswordArea.setPrefColumnCount(20);
        inputPasswordArea.setPromptText("Type in your password...");
        inputPasswordArea.setFocusTraversable(false);
        inputPassword.getChildren().addAll(password,inputPasswordArea);

        //Create error message area
        error = new Text("Invalid user name or password!");
        error.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        error.setFill(Color.RED);
        error.setVisible(false);

        userBox.getChildren().addAll(inputName,inputPassword,error);

        //Create sign in button
        VBox signInBox = new VBox();
        signInBox.setAlignment(Pos.CENTER);
        signInBox.setPrefHeight(50);
        signIn = new Button("SIGN IN");
        signInBox.getChildren().addAll(signIn);

        view = new VBox();
        view.setAlignment(Pos.CENTER);
        view.getChildren().addAll(titleBox,userBox,signInBox);
    }
    public VBox getView(){ return view;}
    public Button getSignIn(){ return signIn;}

    public PasswordField getInputPasswordArea() {
        return inputPasswordArea;
    }

    public TextField getInputNameArea() {
        return inputNameArea;
    }

    public Text getError() { return error; }
}
