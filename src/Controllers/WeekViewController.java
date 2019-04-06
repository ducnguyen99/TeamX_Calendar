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

import Models.*;
import Views.Week;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeekViewController {
    private VBox view;
    private Week week;
    private LocalDateTime currentTime;
    private EventViewController eventViewController = new EventViewController();
    private ReminderViewController reminderViewController = new ReminderViewController();
    private EventDetailsController eventDetailsController = new EventDetailsController();
    private ReminderDetailsController reminderDetailsController = new ReminderDetailsController();

    public WeekViewController(LocalDateTime thisTime){
        currentTime = thisTime;
        week = new Week();

        //Next button function
        week.getNavigateBar().getNext().setOnAction(e->{
            currentTime = currentTime.plusWeeks(1);
            createDayLabels(currentTime);
            updateActivityDateTime(currentTime);
        });

        //Previous button function
        week.getNavigateBar().getPrev().setOnAction(e->{
            currentTime = currentTime.minusWeeks(1);
            createDayLabels(currentTime);
            updateActivityDateTime(currentTime);
        });

        //Today button function
        week.getNavigateBar().getToday().setOnAction(e->{
            currentTime = LocalDateTime.now();
            createDayLabels(currentTime);
            updateActivityDateTime(currentTime);
        });

        //Create Event button function
        week.getCreateEvent().setOnAction(e -> {
            eventViewController.getPopup().show();
            eventViewController.clearEventInfo();
        });

        //Save button inside "Create Event" Window function
        eventViewController.getEventWindow().getSave().setOnAction(e->{
            User user = new User();
            user.getEventsFromFile();

            //Check whether thee input event valid
            if(eventViewController.isInputValueValid()){
                //Find and delete if the event already exists in the user event array list, then add the new (same) event to the array list
                if(user.findEvent(eventViewController.getInputEvent()) <= user.getEventList().size()-1){
                    Event event = new Event();
                    event.setTitle(eventDetailsController.getEventDetails().getInfo().get(0).getText());
                    event.setStartDate(eventDetailsController.getEventDetails().getInfo().get(1).getText());
                    event.setEndDate(eventDetailsController.getEventDetails().getInfo().get(2).getText());
                    event.setStartTime(eventDetailsController.getEventDetails().getInfo().get(3).getText());
                    event.setEndTime(eventDetailsController.getEventDetails().getInfo().get(4).getText());
                    event.setLocation(eventDetailsController.getEventDetails().getInfo().get(5).getText());
                    event.setDescription(eventDetailsController.getEventDetails().getInfo().get(6).getText());
                    event.setOwner(eventDetailsController.getEventDetails().getInfo().get(7).getText());
                    event.setGuestList(eventDetailsController.getEventDetails().getInfo().get(8).getText());
                    event.setNotiTime(eventDetailsController.getEventDetails().getInfo().get(9).getText());
                    event.setNotiType(eventDetailsController.getEventDetails().getInfo().get(10).getText());
                    user.getEventList().remove(user.findEvent(event));
                    user.getEventList().add(eventViewController.getInputEvent());
                    eventViewController.sendEmail();
                }
                else{
                    //If the event has not existed yet, add it into the array list
                    user.getEventList().add(eventViewController.getInputEvent());
                    eventViewController.sendEmail();
                }
                //Write all user containing event to database
                user.writeEventsToFile();

                eventViewController.clearEventInfo();
                eventViewController.getEventWindow().getError().setVisible(false);
                eventViewController.getPopup().close();
                eventDetailsController.getPopup().close();
            }
            else {
                //Clear all typed information and alert the error message
                eventViewController.clearEventInfo();
                eventViewController.getEventWindow().getError().setVisible(true);
            }
            //Update the new calendar view
            createDayLabels(LocalDateTime.now());
            updateActivityDateTime(LocalDateTime.now());
        });

        //Close button inside "Create Event" Window function
        eventViewController.getEventWindow().getClose().setOnAction(e->{
            eventViewController.clearEventInfo();
            eventViewController.getEventWindow().getError().setVisible(false);
            eventViewController.getPopup().close();
        });

        //"Create reminder" button function
        week.getCreateReminder().setOnAction(e -> {
            reminderViewController.getPopup().show();
            reminderViewController.clearReminderInfo();
        });

        //"Save" button inside "Create Reminder" Window function
        reminderViewController.getReminderWindow().getSave().setOnAction(e->{
            User user = new User();
            user.getRemindersFromFile();
            if(reminderViewController.isInputValueValid()){
                Reminder reminder = reminderViewController.getInputReminder();
                //Find and delete if the reminder already exists in the user event array list, then add the new (same) reminder to the array list
                if(user.findReminder(reminder) <= user.getRemindList().size()-1){
                    Reminder reminder1 = new Reminder();
                    reminder1.setTitle(reminderDetailsController.getReminderDetails().getInfo().get(0).getText());
                    reminder1.setDate(reminderDetailsController.getReminderDetails().getInfo().get(1).getText());
                    reminder1.setTime(reminderDetailsController.getReminderDetails().getInfo().get(2).getText());
                    reminder1.setRepeatType(reminderDetailsController.getReminderDetails().getInfo().get(3).getText());
                    user.getRemindList().remove(user.findReminder(reminder1));
                    user.getRemindList().add(reminder);
                }
                else {
                    //If the reminder has not existed yet, add it into the array list
                    user.getRemindList().add(reminder);
                }
                user.writeRemindersToFile();
                reminderViewController.clearReminderInfo();
                reminderViewController.getReminderWindow().getError().setVisible(false);
                reminderViewController.getPopup().close();
            }
            else{
                //Clear all typed information and alert the error message
                reminderViewController.clearReminderInfo();
                reminderViewController.getReminderWindow().getError().setVisible(true);
            }
            createDayLabels(LocalDateTime.now());
            updateActivityDateTime(LocalDateTime.now());
            reminderDetailsController.getPopup().close();
        });

        //"Close" button inside "Create Reminder" Window function
        reminderViewController.getReminderWindow().getClose().setOnAction(e->{
            reminderViewController.clearReminderInfo();
            reminderViewController.getReminderWindow().getError().setVisible(false);
            reminderViewController.getPopup().close();
        });

        //"Ok" button inside "Event Details" Window function
        eventDetailsController.getEventDetails().getOk().setOnAction(e->{
            eventDetailsController.getPopup().close();
        });

        //"Delete" button inside "Event Details" Window function
        eventDetailsController.getEventDetails().getDelete().setOnAction(e->{
            User user = new User();
            user.getEventsFromFile();
            Event event = new Event();
            event.setTitle(eventDetailsController.getEventDetails().getInfo().get(0).getText());
            event.setStartDate(eventDetailsController.getEventDetails().getInfo().get(1).getText());
            event.setEndDate(eventDetailsController.getEventDetails().getInfo().get(2).getText());
            event.setStartTime(eventDetailsController.getEventDetails().getInfo().get(3).getText());
            event.setEndTime(eventDetailsController.getEventDetails().getInfo().get(4).getText());
            event.setLocation(eventDetailsController.getEventDetails().getInfo().get(5).getText());
            event.setDescription(eventDetailsController.getEventDetails().getInfo().get(6).getText());
            event.setOwner(eventDetailsController.getEventDetails().getInfo().get(7).getText());
            event.setGuestList(eventDetailsController.getEventDetails().getInfo().get(8).getText());
            event.setNotiTime(eventDetailsController.getEventDetails().getInfo().get(9).getText());
            event.setNotiType(eventDetailsController.getEventDetails().getInfo().get(10).getText());
            user.getEventList().remove(user.findEvent(event));
            user.writeEventsToFile();
            eventDetailsController.getPopup().close();
            createDayLabels(LocalDateTime.now());
            updateActivityDateTime(LocalDateTime.now());
        });

        //"Update" button inside "Event details" Window function
        eventDetailsController.getEventDetails().getUpdate().setOnAction(e->{
            eventViewController.getPopup().show();
        });

        //"Ok" button inside "Reminder details" Window function
        reminderDetailsController.getReminderDetails().getOk().setOnAction(e->{
            reminderDetailsController.getPopup().close();
        });

        //"Update" button inside "Reminder details" Window function
        reminderDetailsController.getReminderDetails().getUpdate().setOnAction(e->{
            reminderViewController.getPopup().show();
        });

        //"Delete" button inside "Reminder details" Window function
        reminderDetailsController.getReminderDetails().getDelete().setOnAction(e->{
            User user = new User();
            user.getRemindersFromFile();
            Reminder reminder = new Reminder();
            reminder.setTitle(reminderDetailsController.getReminderDetails().getInfo().get(0).getText());
            reminder.setDate(reminderDetailsController.getReminderDetails().getInfo().get(1).getText());
            reminder.setTime(reminderDetailsController.getReminderDetails().getInfo().get(2).getText());
            reminder.setRepeatType(reminderDetailsController.getReminderDetails().getInfo().get(3).getText());
            user.getRemindList().remove(user.findReminder(reminder));
            user.writeRemindersToFile();
            reminderDetailsController.getPopup().close();
            createDayLabels(LocalDateTime.now());
            updateActivityDateTime(LocalDateTime.now());
        });

        createDayLabels(currentTime);
        updateActivityDateTime(currentTime);
        view = week.getView();
    }

    public void createDayLabels(LocalDateTime thisTime) {
        LocalDateTime dayOfMonday = thisTime;
        while (!dayOfMonday.getDayOfWeek().toString().equals("MONDAY") ) {
            dayOfMonday = dayOfMonday.minusDays(1);
        }

        LocalDateTime checkingDay = dayOfMonday;
        for (VboxNode vboxNode : week.getWeekCalendar().getDayOfCurrentWeek()){

            if (vboxNode.getChildren().size() != 0) {
                vboxNode.getChildren().clear();
            }

            Text date = new Text(Integer.toString(checkingDay.getDayOfMonth()));

            vboxNode.getChildren().add(date);
            vboxNode.setDate(checkingDay.toLocalDate());

            checkingDay = checkingDay.plusDays(1);
        }

        LocalDateTime dayOfSunday = checkingDay.minusDays(1);

        if (dayOfMonday.getYear() == dayOfSunday.getYear()) {
            if (dayOfMonday.getMonthValue() == dayOfSunday.getMonthValue()) {
                week.getNavigateBar().getCalendarTitle().setText(dayOfMonday.getMonth().toString() + " " + String.valueOf(thisTime.getYear()));
            } else {
                week.getNavigateBar().getCalendarTitle().setText(dayOfMonday.getMonth().toString() + "-" + dayOfSunday.getMonth().toString() + " " + String.valueOf(dayOfMonday.getYear()));
            }
        } else {
            week.getNavigateBar().getCalendarTitle().setText(dayOfMonday.getMonth().toString() + " " + String.valueOf(dayOfMonday.getYear()) + "-" + dayOfSunday.getMonth().toString() + " " + String.valueOf(dayOfSunday.getYear()));
        }
    }

    public void updateActivityDateTime(LocalDateTime thisTime){
        User user = new User();
        user.getRemindersFromFile();
        user.getEventsFromFile();

        //Updated (declare vars to display)
        LocalDate eventStartDate, eventEndDate;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (PaneNode activity: week.getWeekCalendar().getShortActivities()){
            if (activity.getChildren().size() != 0) activity.getChildren().clear();
        }


        LocalDateTime timezone = thisTime;
        while (!timezone.getDayOfWeek().toString().equals("MONDAY") ) {
            timezone = timezone.minusDays(1);
        }

        for(PaneNode activity : week.getWeekCalendar().getShortActivities()){
            activity.setDate(timezone.toLocalDate());
            if(activity.getDate().compareTo(LocalDate.now()) == 0){
                activity.setStyle("-fx-background-color: rgb(74,144,226,0.2);");
            }
            else activity.setStyle("-fx-background-color: transparent;");
            timezone = timezone.plusDays(1);
        }


        //Length of height per minute in calendar
        float unit = (24*200)/1440.0F;
        //Sort reminder event
        user.sortReminder();

        for (int n = 0; n < user.getRemindList().size(); n++){
            eventStartDate = LocalDate.parse(user.getRemindList().get(n).getDate(), format);
            String title = user.getRemindList().get(n).getTitle();
            String time = user.getRemindList().get(n).getTime();
            String rep = user.getRemindList().get(n).getRepeatType();

            for (PaneNode activity: week.getWeekCalendar().getShortActivities()){
                //Display reminder on week calendar based on its start date
                if (eventStartDate.equals(activity.getDate())){
                    Rectangle rect = new Rectangle(50,50, new Color(1,0,0,1));
                    rect.setX(1 + 50 * (activity.getChildren().size()/2));
                    rect.setY(unit * convertTime(user.getRemindList().get(n).getTime()));
                    rect.setArcWidth(15);
                    rect.setArcHeight(15);
                    rect.setOnMouseClicked(e -> {
                        reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                        reminderDetailsController.getPopup().show();
                        reminderViewController.clearReminderInfo();
                        reminderViewController.getReminderWindow().getTitle().setText(title);
                        reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                        reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                        reminderViewController.getReminderWindow().getTime().setText(time);
                        reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                    });

                    Text txt = new Text(user.getRemindList().get(n).getTitle());
                    txt.setFill(Color.WHITE);
                    txt.setWrappingWidth(50);
                    txt.setX(5 + 50 * (activity.getChildren().size()/2));
                    txt.setY(unit * convertTime(user.getRemindList().get(n).getTime()) + 20);
                    txt.setOnMouseClicked(e -> {
                        reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                        reminderDetailsController.getPopup().show();
                        reminderViewController.clearReminderInfo();
                        reminderViewController.getReminderWindow().getTitle().setText(title);
                        reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                        reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                        reminderViewController.getReminderWindow().getTime().setText(time);
                        reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                    });

                    activity.getChildren().addAll(rect,txt);
                }

                //Display daily reminders
                if (user.getRemindList().get(n).getRepeatType().compareTo("Daily") == 0) {
                    if (activity.getDate().compareTo(eventStartDate) > 0) {
                        Rectangle rect = new Rectangle(50,50, Color.RED);
                        rect.setX(1 + 50 * (activity.getChildren().size()/2));
                        rect.setY(unit * convertTime(user.getRemindList().get(n).getTime()));
                        rect.setArcWidth(15);
                        rect.setArcHeight(15);
                        rect.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        Text txt = new Text(user.getRemindList().get(n).getTitle());
                        txt.setFill(Color.WHITE);
                        txt.setWrappingWidth(50);
                        txt.setX(5 + 50 * (activity.getChildren().size()/2));
                        txt.setY(unit * convertTime(user.getRemindList().get(n).getTime()) + 20);
                        rect.setArcWidth(15);
                        rect.setArcHeight(15);
                        txt.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        activity.getChildren().addAll(rect,txt);
                    }
                    //Display weekly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Weekly") == 0){
                    if ((activity.getDate().compareTo(eventStartDate) > 0) && (activity.getDate().getDayOfWeek().compareTo(eventStartDate.getDayOfWeek()) == 0)){
                        Rectangle rect = new Rectangle(50,50, new Color(1,0,0,0.99));
                        rect.setX(1 + 50 * (activity.getChildren().size()/2));
                        rect.setY(unit * convertTime(user.getRemindList().get(n).getTime()));
                        rect.setArcWidth(15);
                        rect.setArcHeight(15);
                        rect.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        Text txt = new Text(user.getRemindList().get(n).getTitle());
                        txt.setFill(Color.WHITE);
                        txt.setWrappingWidth(50);
                        txt.setX(5 + 50 * (activity.getChildren().size()/2));
                        txt.setY(unit * convertTime(user.getRemindList().get(n).getTime()) + 20);
                        txt.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        activity.getChildren().addAll(rect,txt);

                    }
                    //Display monthly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Monthly") == 0){
                    if ((activity.getDate().compareTo(eventStartDate) > 0) && (activity.getDate().getDayOfMonth() == eventStartDate.getDayOfMonth())){
                        Rectangle rect = new Rectangle(50,50, new Color(1,0,0,0.98));
                        rect.setX(1 + 50 * (activity.getChildren().size()/2));
                        rect.setY(unit * convertTime(user.getRemindList().get(n).getTime()));
                        rect.setArcWidth(15);
                        rect.setArcHeight(15);
                        rect.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        Text txt = new Text(user.getRemindList().get(n).getTitle());
                        txt.setFill(Color.WHITE);
                        txt.setWrappingWidth(50);
                        txt.setX(5 + 50 * (activity.getChildren().size()/2));
                        txt.setY(unit * convertTime(user.getRemindList().get(n).getTime()) + 20);
                        txt.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        activity.getChildren().addAll(rect,txt);
                    }
                    //Display yearly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Yearly") == 0){
                    if ((activity.getDate().compareTo(eventStartDate) > 0) && (activity.getDate().getDayOfYear() == eventStartDate.getDayOfYear())){
                        Rectangle rect = new Rectangle(50,50, new Color(1,0,0,0.97));
                        rect.setX(1 + 50 * (activity.getChildren().size()/2));
                        rect.setY(unit * convertTime(user.getRemindList().get(n).getTime()));
                        rect.setArcWidth(15);
                        rect.setArcHeight(15);
                        rect.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        Text txt = new Text(user.getRemindList().get(n).getTitle());
                        txt.setFill(Color.WHITE);
                        txt.setWrappingWidth(50);
                        txt.setX(5 + 50 * (activity.getChildren().size()/2));
                        txt.setY(unit * convertTime(user.getRemindList().get(n).getTime()) + 20);
                        txt.setOnMouseClicked(e -> {
                            reminderDetailsController.assignInformation(title,activity.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(activity.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(activity.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        activity.getChildren().addAll(rect,txt);
                    }
                }
            }
        }


        //Sort user event
        user.sortEvent();

        for (int n = 0; n < user.getEventList().size(); n++) {
            //Store event fields
            eventStartDate = LocalDate.parse(user.getEventList().get(n).getStartDate(),format);
            eventEndDate = LocalDate.parse(user.getEventList().get(n).getEndDate(), format);
            String title = user.getEventList().get(n).getTitle();
            String stD = user.getEventList().get(n).getStartDate();
            String eD = user.getEventList().get(n).getEndDate();
            String stT = user.getEventList().get(n).getStartTime();
            String eT = user.getEventList().get(n).getEndTime();
            String loc = user.getEventList().get(n).getLocation();
            String des = user.getEventList().get(n).getDescription();
            String own = user.getEventList().get(n).getOwner();
            String guest = user.getEventList().get(n).getGuestList();
            String notiTime = user.getEventList().get(n).getNotiTime();
            String notiType = user.getEventList().get(n).getNotiType();

            if (eventStartDate.compareTo(eventEndDate) == 0) {
                for (PaneNode activity: week.getWeekCalendar().getShortActivities()) {
                    //Display event based on its start date
                    if (eventStartDate.equals(activity.getDate())) {
                        float length = convertTime(user.getEventList().get(n).getEndTime()) - convertTime(user.getEventList().get(n).getStartTime());
                        Rectangle rect = new Rectangle(50, unit * length, Color.GREEN); //To display event based on its DURATION (height = unit * duration)
                        rect.setX(1 + 50 * (activity.getChildren().size()/2));
                        rect.setY(unit * convertTime(user.getEventList().get(n).getStartTime()));
                        rect.setArcHeight(15);
                        rect.setArcWidth(15);

                        rect.setOnMouseClicked(e->{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            eventDetailsController.assignInformation(title,stD,eD,stT,eT,loc,des,own,guest,notiTime,notiType);
                            eventDetailsController.getPopup().show();
                            eventViewController.clearEventInfo();
                            eventViewController.getEventWindow().getInfo().get(0).setText(title);
                            eventViewController.getEventWindow().getInfoDate().get(0).setValue(LocalDate.parse(stD,formatter));
                            eventViewController.getEventWindow().getInfoDate().get(1).setValue(LocalDate.parse(eD,formatter));
                            eventViewController.getEventWindow().getInfoDate().get(0).getEditor().setText(stD);
                            eventViewController.getEventWindow().getInfoDate().get(1).getEditor().setText(eD);
                            eventViewController.getEventWindow().getInfo().get(1).setText(stT);
                            eventViewController.getEventWindow().getInfo().get(2).setText(eT);
                            eventViewController.getEventWindow().getInfo().get(3).setText(loc);
                            eventViewController.getEventWindow().getInfo().get(4).setText(own);
                            eventViewController.getEventWindow().getInfo().get(5).setText(des);
                            eventViewController.getEventWindow().getInfo().get(6).setText(guest);
                            eventViewController.getEventWindow().getInfo().get(7).setText(notiTime);
                            eventViewController.getEventWindow().getNotiType().setValue(notiType);
                        });

                        Text txt = new Text(user.getEventList().get(n).getTitle());
                        txt.setFill(Color.WHITE);
                        txt.setWrappingWidth(50);
                        txt.setX(5 + 50 * (activity.getChildren().size()/2));
                        txt.setY(unit * convertTime(user.getEventList().get(n).getStartTime()) + 20);
                        txt.setOnMouseClicked(e->{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            eventDetailsController.assignInformation(title,stD,eD,stT,eT,loc,des,own,guest,notiTime,notiType);
                            eventDetailsController.getPopup().show();
                            eventViewController.clearEventInfo();
                            eventViewController.getEventWindow().getInfo().get(0).setText(title);
                            eventViewController.getEventWindow().getInfoDate().get(0).setValue(LocalDate.parse(stD,formatter));
                            eventViewController.getEventWindow().getInfoDate().get(1).setValue(LocalDate.parse(eD,formatter));
                            eventViewController.getEventWindow().getInfoDate().get(0).getEditor().setText(stD);
                            eventViewController.getEventWindow().getInfoDate().get(1).getEditor().setText(eD);
                            eventViewController.getEventWindow().getInfo().get(1).setText(stT);
                            eventViewController.getEventWindow().getInfo().get(2).setText(eT);
                            eventViewController.getEventWindow().getInfo().get(3).setText(loc);
                            eventViewController.getEventWindow().getInfo().get(4).setText(own);
                            eventViewController.getEventWindow().getInfo().get(5).setText(des);
                            eventViewController.getEventWindow().getInfo().get(6).setText(guest);
                            eventViewController.getEventWindow().getInfo().get(7).setText(notiTime);
                            eventViewController.getEventWindow().getNotiType().setValue(notiType);
                        });

                        activity.getChildren().addAll(rect, txt);
                    }
                }
            } else {
                //To check whether an event occurs in current week
                boolean inWeekCheck = false;

                for (VboxNode vbox: week.getWeekCalendar().getDayOfCurrentWeek()){
                    if ((vbox.getDate().compareTo(eventStartDate) >= 0) && (vbox.getDate().compareTo(eventEndDate) <= 0)){
                        inWeekCheck = true;
                        if (vbox.getDate().equals(eventStartDate) || vbox.getDate().getDayOfWeek().toString().equals("MONDAY")){
                            Rectangle rect = new Rectangle (150 ,19, Color.GREEN);
                            rect.setX(0);
                            rect.setY(0);

                            Label lbl = new Label(user.getEventList().get(n).getTitle());
                            lbl.setTextFill(Color.WHITE);
                            lbl.setAlignment(Pos.CENTER_LEFT);

                            StackPane pane = new StackPane();
                            pane.setPrefHeight(20);
                            pane.getChildren().addAll(rect,lbl);
                            pane.setOnMouseClicked(e->{
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                eventDetailsController.assignInformation(title,stD,eD,stT,eT,loc,des,own,guest,notiTime,notiType);
                                eventDetailsController.getPopup().show();
                                eventViewController.clearEventInfo();
                                eventViewController.getEventWindow().getInfo().get(0).setText(title);
                                eventViewController.getEventWindow().getInfoDate().get(0).setValue(LocalDate.parse(stD,formatter));
                                eventViewController.getEventWindow().getInfoDate().get(1).setValue(LocalDate.parse(eD,formatter));
                                eventViewController.getEventWindow().getInfoDate().get(0).getEditor().setText(stD);
                                eventViewController.getEventWindow().getInfoDate().get(1).getEditor().setText(eD);
                                eventViewController.getEventWindow().getInfo().get(1).setText(stT);
                                eventViewController.getEventWindow().getInfo().get(2).setText(eT);
                                eventViewController.getEventWindow().getInfo().get(3).setText(loc);
                                eventViewController.getEventWindow().getInfo().get(4).setText(own);
                                eventViewController.getEventWindow().getInfo().get(5).setText(des);
                                eventViewController.getEventWindow().getInfo().get(6).setText(guest);
                                eventViewController.getEventWindow().getInfo().get(7).setText(notiTime);
                                eventViewController.getEventWindow().getNotiType().setValue(notiType);
                            });

                            vbox.setSpacing(1);
                            vbox.getChildren().addAll(pane);
                        } else { //if event last more than 1 week --> display event on top

                            Rectangle rect = new Rectangle (150 ,19, Color.GREEN);
                            rect.setX(0);
                            rect.setY(1);
                            rect.setOnMouseClicked(e->{
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                eventDetailsController.assignInformation(title,stD,eD,stT,eT,loc,des,own,guest,notiTime,notiType);
                                eventDetailsController.getPopup().show();
                                eventViewController.clearEventInfo();
                                eventViewController.getEventWindow().getInfo().get(0).setText(title);
                                eventViewController.getEventWindow().getInfoDate().get(0).setValue(LocalDate.parse(stD,formatter));
                                eventViewController.getEventWindow().getInfoDate().get(1).setValue(LocalDate.parse(eD,formatter));
                                eventViewController.getEventWindow().getInfoDate().get(0).getEditor().setText(stD);
                                eventViewController.getEventWindow().getInfoDate().get(1).getEditor().setText(eD);
                                eventViewController.getEventWindow().getInfo().get(1).setText(stT);
                                eventViewController.getEventWindow().getInfo().get(2).setText(eT);
                                eventViewController.getEventWindow().getInfo().get(3).setText(loc);
                                eventViewController.getEventWindow().getInfo().get(4).setText(own);
                                eventViewController.getEventWindow().getInfo().get(5).setText(des);
                                eventViewController.getEventWindow().getInfo().get(6).setText(guest);
                                eventViewController.getEventWindow().getInfo().get(7).setText(notiTime);
                                eventViewController.getEventWindow().getNotiType().setValue(notiType);
                            });

                            StackPane pane = new StackPane();
                            pane.setPrefHeight(20);
                            pane.getChildren().addAll(rect);

                            vbox.setSpacing(1);
                            vbox.getChildren().add(pane);
                        }
                    }
                }

                //If check is true --> add WHITE region to the rest of days in the week outside of event
                if (inWeekCheck) {
                    for (VboxNode vbox : week.getWeekCalendar().getDayOfCurrentWeek()) {
                        if (vbox.getDate().compareTo(eventStartDate) < 0 || vbox.getDate().compareTo(eventEndDate) > 0) {
                            Rectangle rect = new Rectangle(150, 19, Color.valueOf("F4F4F4"));
                            rect.setX(0);
                            rect.setY(0);

                            StackPane pane = new StackPane();
                            pane.setPrefHeight(20);
                            pane.getChildren().addAll(rect);

                            vbox.setSpacing(1);
                            vbox.getChildren().add(rect);
                        }
                    }
                }

            }
        }
    }

    //Func to convert time in string in form HH:mm to total minutes
    static public int convertTime(String time){
        int hour = 0, minute = 0;
        int timeValue;
        int len = time.length();
        for (int i = 0; i < len; i++){
            if (time.charAt(i) == ':'){
                hour = Integer.parseInt(time.substring(0,i));
                minute = Integer.parseInt(time.substring(i+1, len));
                break;
            }
        }
        timeValue = (hour * 60 + minute);
        return timeValue;
    }

    public VBox getView() {
        return view;
    }

    public Week getWeek() {
        return week;
    }
}
