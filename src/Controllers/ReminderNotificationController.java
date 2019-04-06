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
package Controllers;

import Models.Event;
import Models.Reminder;
import Models.User;
import Views.Notification;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderNotificationController {
    private Notification noti;

    private Text message;
    private User user;
    private final int remindMins = 10;

    public ReminderNotificationController() {
    }

    public void showNoti() throws InterruptedException {

        //Declare formatter to convert date and time into correct format
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        final Timeline checkCycle = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //Update user's remind list
                                user = new User();
                                user.getRemindersFromFile();

                                //Variables to store current date and timee
                                LocalDate dateNow;
                                dateNow = LocalDate.now();
                                LocalTime now;
                                now = LocalTime.now();

                                for (int n = 0; n < user.getRemindList().size(); n++){
                                    //Notification popup reminders
                                    if (user.getRemindList().get(n).getRepeatType().equals("No Repeat")) {
                                        if (LocalTime.parse(user.getRemindList().get(n).getTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Today you have " + user.getRemindList().get(n).getTitle() + " at " + user.getRemindList().get(n).getTime());
                                            noti = new Notification(new Text("Reminder Notification"), "Reminder", message);
                                            Stage popup = new Stage();
                                            Scene notiScene = new Scene(noti.getView());
                                            popup.setScene(notiScene);
                                            popup.show();
                                            noti.getClose().setOnAction(e -> popup.close());
                                        }
                                    }

                                    //Notification popup daily reminders
                                    if (user.getRemindList().get(n).getRepeatType().equals("Daily")){
                                        if (LocalTime.parse(user.getRemindList().get(n).getTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Today you have " + user.getRemindList().get(n).getTitle() + " at " + user.getRemindList().get(n).getTime() + "\nYou will be reminded everyday.");
                                            noti = new Notification(new Text("Reminder Notification"), "Reminder", message);
                                            Stage popup = new Stage();
                                            Scene notiScene = new Scene(noti.getView());
                                            popup.setScene(notiScene);
                                            popup.show();
                                            noti.getClose().setOnAction(e -> popup.close());
                                        }
                                    }

                                    //Notification popup for monthly reminders
                                    if (user.getRemindList().get(n).getRepeatType().equals("Monthly")){
                                        if (LocalDate.parse(user.getRemindList().get(n).getDate(), formatDate).getDayOfMonth() == dateNow.getDayOfMonth() && LocalTime.parse(user.getRemindList().get(n).getTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Today you have " + user.getRemindList().get(n).getTitle() + " at " + user.getRemindList().get(n).getTime() + "\nYou will be reminded monthly.");
                                            noti = new Notification(new Text("Reminder Notification"), "Reminder", message);
                                            Stage popup = new Stage();
                                            Scene notiScene = new Scene(noti.getView());
                                            popup.setScene(notiScene);
                                            popup.show();
                                            noti.getClose().setOnAction(e -> popup.close());
                                        }
                                    }

                                    //Notification popup for weekly reminders
                                    if (user.getRemindList().get(n).getRepeatType().equals("Weekly")){
                                        if (LocalDate.parse(user.getRemindList().get(n).getDate(), formatDate).getDayOfWeek() == dateNow.getDayOfWeek() && LocalTime.parse(user.getRemindList().get(n).getTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Today you have " + user.getRemindList().get(n).getTitle() + " at " + user.getRemindList().get(n).getTime() + "\nYou will be reminded weekly.");
                                            noti = new Notification(new Text("Reminder Notification"), "Reminder", message);
                                            Stage popup = new Stage();
                                            Scene notiScene = new Scene(noti.getView());
                                            popup.setScene(notiScene);
                                            popup.show();
                                            noti.getClose().setOnAction(e -> popup.close());
                                        }
                                    }

                                    //Notification popup for yearly reminders
                                    if (user.getRemindList().get(n).getRepeatType().equals("Yearly")){
                                        if (LocalDate.parse(user.getRemindList().get(n).getDate(), formatDate).getDayOfYear() == dateNow.getDayOfYear() && LocalTime.parse(user.getRemindList().get(n).getTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Today you have " + user.getRemindList().get(n).getTitle() + " at " + user.getRemindList().get(n).getTime() + "\nYou will be reminded yearly.");
                                            noti = new Notification(new Text("Reminder Notification"), "Reminder", message);
                                            Stage popup = new Stage();
                                            Scene notiScene = new Scene(noti.getView());
                                            popup.setScene(notiScene);
                                            popup.show();
                                            noti.getClose().setOnAction(e -> popup.close());
                                        }
                                    }
                                }
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(60))
        );

        checkCycle.setCycleCount(Animation.INDEFINITE);
        checkCycle.play();
    }


}
