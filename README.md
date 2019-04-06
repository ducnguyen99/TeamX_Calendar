#Introduction

This project is the final assignment of course INTE2512 Object-Oriented Programming, semester 1, RMIT University Vietnam
- Author: Nguyen Huu Ngoc Duc, Duong Minh Nhat, Nguyen Minh Tri
- We are going to optimize the code as there are lot of duplicated codes in this project.
- Please give us credit if you want to use this code in your projects/assignments.
- Feel free to make a pull request

#Requirement

####Problem

- Design and implement a JavaFX calendar app that works similar to but simpler than Google Calendar (Links to an external site.)Links to an external site..

####Features

- The user can create, update and delete events. There are 2 types of events: Event (e.g. meetings, workshops, dinner, etc) and Reminder (birthdays, payment, etc).
- An Event typically consists of a title, start (date & time), end (date & time), location, notification (to allow a notification to pop-up in a specified number of minutes or hours before the event), description, owner, and a guest list (specified by emails). After an Event is created, the guests (if any) in the guest list receive an email with all the information about the Event. If an Event is updated, the guests also receive a new email with the updated information.
- A Reminder only has a title, time (date & time), repeat (no repeat, daily, weekly, monthly, yearly, custom). 
- The user can view all the events by week (default) or by month. Furthermore, the user can also view and navigate to a specific month or day in the calendar conveniently as in Google Calendar.  When the time specified in a Reminder or in the notification of an Event comes, a notification should pop-up to remind the user about the Event/Reminder.
- When the user closes the app and later opens it again, all data previously entered should be loaded and shown properly. To allow testing your app, create a few sample Events and Reminders. Your app should not crash at any time with any user actions on the app.

####Design

- Your JavaFX calendar app must follow the MVC design pattern.
- Your design must be presented in some UML Package Diagrams (Links to an external site.)Links to an external site. and Class Diagrams (Links to an external site.)Links to an external site..
- Packages, classes, properties, methods, relationships, etc. must be clearly identified and described using correct UML notations in the above diagrams. 