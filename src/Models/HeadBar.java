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
package Models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//A bar to show the name of the calendar "TeamX calendar" and "Sign out" button
public class HeadBar {
    private AnchorPane headBar;
    private HBox signOutBox;
    public HeadBar(){
        //Create and initialize the head bar
        headBar = new AnchorPane();
        headBar.setPrefHeight(50);
        headBar.setStyle("-fx-background-color: #004987;"+"-fx-border-color: black;");

        //Create and arrange TeamX Calendar
        Text title = new Text("TeamX Calendar");
        title.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,50));
        title.setFill(Color.WHITE);
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.getChildren().addAll(title);

        //Create and arrange "Sign out" area
        signOutBox = new HBox();
        signOutBox.setPadding(new Insets(10,5,5,5));
        signOutBox.setStyle("-fx-background-color: #004987;");
        ImageView userLogo = new ImageView("Models/images/userIcon.png");
        userLogo.setPreserveRatio(true);
        userLogo.setFitHeight(50);
        Text signOut = new Text("Sign Out");
        signOut.setFont(Font.font("Times New Roman",FontWeight.EXTRA_BOLD,25));
        signOut.setFill(Color.WHITE);
        signOutBox.setSpacing(10);
        signOutBox.setAlignment(Pos.CENTER);
        signOutBox.getChildren().addAll(userLogo,signOut);

        AnchorPane.setLeftAnchor(titleBox,10.0);
        AnchorPane.setBottomAnchor(titleBox,10.0);
        AnchorPane.setRightAnchor(signOutBox,10.0);
        AnchorPane.setBottomAnchor(signOutBox,5.0);
        headBar.getChildren().addAll(titleBox,signOutBox);
    }

    public HBox getSignOutBox() {
        return signOutBox;
    }

    public AnchorPane getHeadBar() {
        return headBar;
    }
}
