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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.security.PrivateKey;

public class Notification {
    private Button close;
    private VBox view;
    public Notification(Text title, String type, Text message){
        //Create title
        title.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,50));
        title.setFill(Color.WHITE);

        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(10,10,10,10));
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);

        HBox contentBox = new HBox();
        contentBox.setStyle("-fx-border-color: black;"+"-fx-background-color: #cccccc");

        contentBox.setPadding(new Insets(10,10,10,10));

        if (type.matches("Reminder")) {
            titleBox.setStyle("-fx-background-color: RED;"+"-fx-border-color: black;");
            contentBox.setAlignment(Pos.TOP_CENTER);
            contentBox.setPrefSize(300,50);
        } else {
            titleBox.setStyle("-fx-background-color: GREEN;"+"-fx-border-color: black;");
            contentBox.setAlignment(Pos.TOP_LEFT);
            contentBox.setPrefSize(300,200);
        }

        message.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));

        contentBox.getChildren().add(message);



        //Create close button
        close = new Button("Close");
        view = new VBox();
        view.setAlignment(Pos.CENTER);
        view.getChildren().addAll(titleBox,contentBox,close);
    }


    public VBox getView(){ return view;}
    public Button getClose(){ return close;}
}
