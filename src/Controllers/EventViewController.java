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
import Models.User;
import Views.EventWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Control "Create event" window
public class EventViewController {
    public EventWindow eventWindow;
    private Stage popup;
    private Scene eventScene;
    private Event event = new Event();

    public EventViewController() {
        eventWindow = new EventWindow();
        popup = new Stage();
        eventScene = new Scene(eventWindow.getView());
        popup.setScene(eventScene);

        User user = new User();
        user.getEventsFromFile();
    }

    //get input values
    public Event getInputEvent() {
        event.setTitle(eventWindow.getTitle().getText());
        event.setStartDate(eventWindow.getStartDate().getEditor().getText());
        event.setEndDate(eventWindow.getEndDate().getEditor().getText());
        event.setStartTime(eventWindow.getStartTime().getText());
        event.setEndTime(eventWindow.getEndTime().getText());
        event.setLocation(eventWindow.getLocation().getText());
        event.setDescription(eventWindow.getDescription().getText());
        event.setOwner(eventWindow.getOwner().getText());
        event.setGuestList(eventWindow.getGuestList().getText());
        event.setNotiTime(eventWindow.getNotiTime().getText());
        event.setNotiType(eventWindow.getNotiType().getValue());
        return event;
    }

    // check valid input
    public Boolean isInputValueValid() {
        if(checkTimeFormat(eventWindow.getStartTime().getText()) && (checkTimeFormat(eventWindow.getEndTime().getText()))
                && !(eventWindow.getStartDate().getEditor().getText().matches("")) && !(eventWindow.getEndDate().getEditor().getText().matches(""))
                && isNotificationValid(eventWindow.getNotiTime().getText())) {
            if (eventWindow.getEndDate().getValue().equals(eventWindow.getStartDate().getValue())) {
                if(checkValidTime(eventWindow.getStartTime().getText(), eventWindow.getEndTime().getText())  ) return true;
                else return false;
            } else {
                if (eventWindow.getEndDate().getValue().isAfter(eventWindow.getStartDate().getValue())) return true;
                else return false;
            }
        }
        else return false;
    }

    // clear Text on event window
    public void clearEventInfo() {
        eventWindow.getTitle().clear();
        eventWindow.getStartDate().getEditor().clear();
        eventWindow.getEndDate().getEditor().clear();
        eventWindow.getStartTime().clear();
        eventWindow.getEndTime().clear();
        eventWindow.getLocation().clear();
        eventWindow.getDescription().clear();
        eventWindow.getOwner().clear();
        eventWindow.getGuestList().clear();
        eventWindow.getNotiTime().clear();
        eventWindow.getNotiType().setValue("Minute(s)");
    }

    // send email to user (source code online)
    public void sendEmail() {
        if(!(event.getGuestList().matches(""))){
            final String username = "teamxdemo@gmail.com";
            final String password = "calendar123";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress());
                // slipt and send email to each guest
                String[] guestEmails = new String[]{};
                guestEmails = event.getGuestList().split(" ");
                message.setSubject("Invitation: " + event.getTitle());
                message.setText("Title: " + event.getTitle() +
                        "\nDescription: " + event.getDescription() +
                        "\nWhen: " + event.getStartDate() + " " + event.getStartTime() + " - " + event.getEndDate() + " " + event.getEndTime() +
                        "\nWhere: " + event.getLocation() +
                        "\nGuest: " + event.getGuestList());
                for (String guestEmail : guestEmails) {
                    int i = 1;
                    if (isValidEmailAddress(guestEmail)) {
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(guestEmail));
                        Transport.send(message);
                    } else System.out.println("Can not send email to guest" );
                }

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // check valid email address
    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    //check notification
    public boolean isNotificationValid(String noti){
        if (noti.matches("")) {
            return false;
        } else {
            for(int i = 0; i < noti.length(); i++){
                if(noti.charAt(i) < '0' || noti.charAt(i) > '9') return false;
            }
        }
        return true;
    }

    // check time format (HH:mm)
    public boolean checkTimeFormat(String time) {
        boolean result = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date testTime = new Date();
        try {
            testTime = dateFormat.parse(time);
        } catch (ParseException e) {
            result = false;
        }

        if (dateFormat.format(testTime).equals(time)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    // valid start time and end time
    private boolean checkValidTime(String fromTime, String toTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date testStartTime;
        Date testEndTime;
        try {
            testStartTime = dateFormat.parse(fromTime);
            testEndTime = dateFormat.parse(toTime);

        } catch (ParseException e) {
            return false;
        }

        return testEndTime.after(testStartTime);
    }

    public EventWindow getEventWindow() {
        return eventWindow;
    }

    public Stage getPopup() {
        return popup;
    }
}

