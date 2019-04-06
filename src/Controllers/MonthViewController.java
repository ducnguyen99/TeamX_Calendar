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
import Models.VboxNode;
import Views.Month;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class MonthViewController {
    private VBox view;
    private YearMonth currentYearMonth;
    private Month month = new Month();
    private EventViewController eventViewController = new EventViewController();;
    private ReminderViewController reminderViewController = new ReminderViewController();
    private EventDetailsController eventDetailsController = new EventDetailsController();
    private ReminderDetailsController reminderDetailsController = new ReminderDetailsController();

    public MonthViewController(YearMonth yearMonth){
        currentYearMonth = yearMonth;

        //"Previous" button function
        month.getNavigateBar().getPrev().setOnAction(e->{
            currentYearMonth = currentYearMonth.minusMonths(1);
            populateCalendar(currentYearMonth);
        });

        //"Next" button function
        month.getNavigateBar().getNext().setOnAction(e->{
            currentYearMonth = currentYearMonth.plusMonths(1);
            populateCalendar(currentYearMonth);
        });

        //"Today" button function
        month.getNavigateBar().getToday().setOnAction(e->{
            currentYearMonth = YearMonth.now();
            populateCalendar(currentYearMonth);
        });

        //"Create Event" button function
        month.getCreateEvent().setOnAction(e -> {
            eventViewController.getPopup().show();
            eventViewController.clearEventInfo();
        });

        //"Save" button inside "Create Event" window function
        eventViewController.getEventWindow().getSave().setOnAction(e->{
            User user = new User();
            user.getEventsFromFile();
            if(eventViewController.isInputValueValid()){
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
                    user.getEventList().add(eventViewController.getInputEvent());
                    eventViewController.sendEmail();
                }
                user.writeEventsToFile();
                eventViewController.clearEventInfo();
                eventViewController.getEventWindow().getError().setVisible(false);
                eventViewController.getPopup().close();
                eventDetailsController.getPopup().close();
            }
            else{
                eventViewController.clearEventInfo();
                eventViewController.getEventWindow().getError().setVisible(true);
            }
            populateCalendar(YearMonth.now());
        });

        //"Close" button inside "Create Event" window function
        eventViewController.getEventWindow().getClose().setOnAction(e->{
            eventViewController.clearEventInfo();
            System.out.println(eventViewController.getEventWindow().getTitle().getText());
            eventViewController.getEventWindow().getError().setVisible(false);
            eventViewController.getPopup().close();
        });

        month.getCreateReminder().setOnAction(e -> {
            reminderViewController.getPopup().show();
            reminderViewController.clearReminderInfo();
        });

        //"Save" button inside "Create Reminder" window function
        reminderViewController.getReminderWindow().getSave().setOnAction(e->{
            User user = new User();
            user.getRemindersFromFile();
            if(reminderViewController.isInputValueValid()){
                Reminder reminder = reminderViewController.getInputReminder();
                if(user.findReminder(reminder) <= user.getRemindList().size()-1){
                    Reminder reminder1 = new Reminder();
                    reminder1.setTitle(reminderDetailsController.getReminderDetails().getInfo().get(0).getText());
                    reminder1.setDate(reminderDetailsController.getReminderDetails().getInfo().get(1).getText());
                    reminder1.setTime(reminderDetailsController.getReminderDetails().getInfo().get(2).getText());
                    reminder1.setRepeatType(reminderDetailsController.getReminderDetails().getInfo().get(3).getText());
                    user.getRemindList().remove(user.findReminder(reminder1));
                    user.getRemindList().add(reminder);
                }
                else{
                    user.getRemindList().add(reminder);
                }
                user.writeRemindersToFile();
                reminderViewController.getReminderWindow().getError().setVisible(false);
                reminderViewController.clearReminderInfo();
                reminderViewController.getPopup().close();
                reminderDetailsController.getPopup().close();
            }
            else{
                reminderViewController.clearReminderInfo();
                reminderViewController.getReminderWindow().getError().setVisible(true);
            }
            populateCalendar(YearMonth.now());
        });

        //"Close" button inside "Create Event" window function
        reminderViewController.getReminderWindow().getClose().setOnAction(e->{
            reminderViewController.clearReminderInfo();
            reminderViewController.getReminderWindow().getError().setVisible(false);
            reminderViewController.getPopup().close();
        });

        //"Ok" button inside "Event Details" window function
        eventDetailsController.getEventDetails().getOk().setOnAction(e->{
            eventDetailsController.getPopup().close();
        });

        //"Delete" button inside "Event Details" window function
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
            event.setGuestList(eventDetailsController.getEventDetails().getInfo().get(7).getText());
            user.getEventList().remove(user.findEvent(event));
            user.writeEventsToFile();
            eventDetailsController.getPopup().close();
            populateCalendar(YearMonth.now());
        });

        //"Update" button inside "Event Details" window function
//        eventDetailsController.getEventDetails().getUpdate().setOnAction(e->{
//            eventViewController.getPopup().show();
//        });

        //"Ok" button inside "Reminder Details" window function
        reminderDetailsController.getReminderDetails().getOk().setOnAction(e->{
            reminderDetailsController.getPopup().close();
        });

        //"Update" button inside "Reminder Details" window function
        reminderDetailsController.getReminderDetails().getUpdate().setOnAction(e->{
            reminderViewController.getPopup().show();
        });

        //"Delete" button inside "Reminder Details" window function
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
            populateCalendar(YearMonth.now());
        });

        view = month.getView();
        populateCalendar(currentYearMonth);
    }

    public void populateCalendar(YearMonth yearMonth) {
        User user = new User();
        user.getRemindersFromFile();
        user.getEventsFromFile();
        //Parsing date to appear on calendar (updated)
        LocalDate eventDate;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (VboxNode vBox : month.getMonthCalendar().getAllCalendarDays()) {
            if (vBox.getChildren().size() != 0) {
                    vBox.getChildren().clear();
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            if(calendarDate.compareTo(LocalDate.now()) == 0) {
                txt.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,15));
                vBox.setStyle("-fx-background-color: rgb(74,144,226,0.2);");
            }
            else vBox.setStyle("-fx-background-color: transparent;");
            vBox.getChildren().add(txt);
            vBox.setDate(calendarDate);
            calendarDate = calendarDate.plusDays(1);
        }

        //Display reminders in month calendar

        //Sort reminders based on its start time
        user.sortReminder();

        for (int n = 0; n < user.getRemindList().size(); n++) {
            //Get date of the reminder
            eventDate = LocalDate.parse(user.getRemindList().get(n).getDate(),format);

            String title = user.getRemindList().get(n).getTitle();
            String time = user.getRemindList().get(n).getTime();
            String rep = user.getRemindList().get(n).getRepeatType();

            for (VboxNode vBox : month.getMonthCalendar().getAllCalendarDays()) {
                //Display reminder on its start date
                if (eventDate.equals(vBox.getDate())) {
                    Label lbl = new Label(user.getRemindList().get(n).getTime() + "\t\t" + user.getRemindList().get(n).getTitle());
                    lbl.setStyle("-fx-background-color: rgb(222,58,62);");
                    lbl.setTextFill(Color.WHITE);
                    lbl.setAlignment(Pos.CENTER_LEFT);
                    lbl.setMaxWidth(190);
                    String date =  user.getRemindList().get(n).getDate();
                    lbl.setOnMouseClicked(e->{
                        reminderDetailsController.assignInformation(title,vBox.getDate().format(format),time,rep);
                        reminderDetailsController.getPopup().show();
                        reminderViewController.clearReminderInfo();
                        reminderViewController.getReminderWindow().getTitle().setText(title);
                        reminderViewController.getReminderWindow().getDate().setValue(vBox.getDate());
                        reminderViewController.getReminderWindow().getTime().setText(time);
                        reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                    });

                    vBox.setSpacing(2);
                    vBox.getChildren().add(lbl);
                }

                //Display reminders for daily reminders
                if (user.getRemindList().get(n).getRepeatType().compareTo("Daily") == 0) {
                    if (vBox.getDate().compareTo(eventDate) > 0) {
                        Label lbl = new Label(user.getRemindList().get(n).getTime() + "\t\t" + user.getRemindList().get(n).getTitle());
                        lbl.setStyle("-fx-background-color: rgb(222,58,61);");
                        lbl.setTextFill(Color.WHITE);
                        lbl.setAlignment(Pos.CENTER_LEFT);
                        lbl.setMaxWidth(190);
                        lbl.setOnMouseClicked(e->{
                            reminderDetailsController.assignInformation(title,vBox.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(vBox.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(vBox.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        vBox.setSpacing(2);
                        vBox.getChildren().add(lbl);
                    }
                    //Display reminders for weekly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Weekly") == 0){
                    if ((vBox.getDate().compareTo(eventDate) > 0) && (vBox.getDate().getDayOfWeek().compareTo(eventDate.getDayOfWeek()) == 0)){
                        Label lbl = new Label(user.getRemindList().get(n).getTime() + "\t\t" + user.getRemindList().get(n).getTitle());
                        lbl.setStyle("-fx-background-color: rgb(222,57,61);");
                        lbl.setTextFill(Color.WHITE);
                        lbl.setAlignment(Pos.CENTER_LEFT);
                        lbl.setMaxWidth(190);
                        String date =  user.getRemindList().get(n).getDate();
                        lbl.setOnMouseClicked(e->{
                            reminderDetailsController.assignInformation(title,vBox.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(vBox.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(vBox.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        vBox.setSpacing(2);
                        vBox.getChildren().add(lbl);
                    }
                    //Display reminders for monthly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Monthly") == 0){
                    if ((vBox.getDate().compareTo(eventDate) > 0) && (vBox.getDate().getDayOfMonth() == eventDate.getDayOfMonth())){
                        Label lbl = new Label(user.getRemindList().get(n).getTime() + "\t\t" + user.getRemindList().get(n).getTitle());
                        lbl.setStyle("-fx-background-color: rgb(221,57,61);");
                        lbl.setTextFill(Color.WHITE);
                        lbl.setAlignment(Pos.CENTER_LEFT);
                        lbl.setMaxWidth(190);
                        String date =  user.getRemindList().get(n).getDate();
                        lbl.setOnMouseClicked(e->{
                            reminderDetailsController.assignInformation(title,vBox.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(vBox.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(vBox.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        vBox.setSpacing(2);
                        vBox.getChildren().add(lbl);
                    }
                    //Display reminders for yearly reminders
                } else if (user.getRemindList().get(n).getRepeatType().compareTo("Yearly") == 0){
                    if ((vBox.getDate().compareTo(eventDate) > 0) && (vBox.getDate().getDayOfYear() == eventDate.getDayOfYear())){
                        Label lbl = new Label(user.getRemindList().get(n).getTime() + "\t\t" + user.getRemindList().get(n).getTitle());
                        lbl.setStyle("-fx-background-color: rgb(220,57,61);");
                        lbl.setTextFill(Color.WHITE);
                        lbl.setAlignment(Pos.CENTER_LEFT);
                        lbl.setMaxWidth(190);
                        String date =  user.getRemindList().get(n).getDate();
                        lbl.setOnMouseClicked(e->{
                            reminderDetailsController.assignInformation(title,vBox.getDate().format(format),time,rep);
                            reminderDetailsController.getPopup().show();
                            reminderViewController.clearReminderInfo();
                            reminderViewController.getReminderWindow().getTitle().setText(title);
                            reminderViewController.getReminderWindow().getDate().setValue(vBox.getDate());
                            reminderViewController.getReminderWindow().getDate().getEditor().setText(vBox.getDate().format(format));
                            reminderViewController.getReminderWindow().getTime().setText(time);
                            reminderViewController.getReminderWindow().getRepeatType().setValue(rep);
                        });

                        vBox.setSpacing(2);
                        vBox.getChildren().add(lbl);
                    }
                }
            }
        }

        //Display events
        //Sort user event
        user.sortEvent();

        for (int n = 0; n < user.getEventList().size(); n++) {
            //Store event start date
            eventDate = LocalDate.parse(user.getEventList().get(n).getStartDate(),format);

            //Store fields of the event
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

            for (VboxNode vBox : month.getMonthCalendar().getAllCalendarDays()) {
                //Display event date based on start date
                if (eventDate.equals(vBox.getDate())) {
                    Label lbl = new Label(user.getEventList().get(n).getStartTime() + "\t\t" + user.getEventList().get(n).getTitle());
                    lbl.setStyle("-fx-background-color: rgb(20,87,15);");
                    lbl.setTextFill(Color.WHITE);
                    lbl.setAlignment(Pos.CENTER_LEFT);
                    lbl.setMaxWidth(190);



                    lbl.setOnMouseClicked(e->{
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
                    vBox.setSpacing(2);
                    vBox.getChildren().add(lbl);
                }
            }
        }
        // Change the title of the calendar
        month.getNavigateBar().getCalendarTitle().setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    public Month getMonth() {
        return month;
    }

    public VBox getView() {
        return view;
    }
}

