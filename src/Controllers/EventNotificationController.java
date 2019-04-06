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


import Models.User;
import Views.Notification;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventNotificationController {

    private Notification noti;
    private Text message;
    private User user;
    private int remindMins = 10; //Number of minutes to remind before event (Default: 10 mins)

    public EventNotificationController() { ; }

    public void showNoti() throws InterruptedException {
        //Declare formatter to convert date and time into correct format
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Create timeline
        final Timeline checkCycle = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //Update user event list
                                user = new User();
                                user.getEventsFromFile();

                                //Variables to store local date and timee
                                LocalDate dateNow;
                                dateNow = LocalDate.now();
                                LocalTime now;
                                now = LocalTime.now();

                                //Go through event list and check for notification pop up
                                for (int n = 0; n < user.getEventList().size(); n++){
                                    //Check notification time specified from user to pop up notification
                                    if (user.getEventList().get(n).getNotiType().equals("Minute(s)")) remindMins = Integer.parseInt(user.getEventList().get(n).getNotiTime());
                                    else if (user.getEventList().get(n).getNotiType().equals("Hour(s)")) remindMins = Integer.parseInt(user.getEventList().get(n).getNotiTime())*60;

                                    //Number of days to remind before event
                                    int remindDays = remindMins / (60*24);

                                    // Notification popup for start day of the event
                                    if (LocalDate.parse(user.getEventList().get(n).getStartDate(), formatDate).minusDays(remindDays).toString().equals(dateNow.toString())) {
                                        if (LocalTime.parse(user.getEventList().get(n).getStartTime(), formatTime).minusMinutes(remindMins).toString().equals(now.format(formatTime))) {
                                            message = new Text("Title: " + user.getEventList().get(n).getTitle() +
                                                    "\nDescription: " + user.getEventList().get(n).getDescription() +
                                                    "\nWhen: " + user.getEventList().get(n).getStartDate() + " " + user.getEventList().get(n).getStartTime() + " to " + user.getEventList().get(n).getEndDate() + " "+ user.getEventList().get(n).getEndTime() +
                                                    "\nWhere: " + user.getEventList().get(n).getLocation() +
                                                    "\nGuest: " + user.getEventList().get(n).getGuestList());
                                            noti = new Notification(new Text("Event Notification"), "Event", message);
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
                new KeyFrame(Duration.seconds(60)) //Check every minute
        );

        checkCycle.setCycleCount(Animation.INDEFINITE);
        checkCycle.play();
    }




}
