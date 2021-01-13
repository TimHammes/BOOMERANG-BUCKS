# BOOMERANG-BUCKS
 This application is a payment reimbursement system built for the web using Java and Servlet technology.
 It has two types of users(Finance Manager, Employees).
 
## Technologies
 
* Java version 1.8
* Maven version 1.8
* Jackson core version 2.11.3
* JUnit version 4.13
* Log4j version 1.2.17
* PostgreSQL version 4.2.18
* Apache Tomcat version 9.0.41

## Features

* Employees can create tickets (reimbursement requests) 
* Employees can view the full history of their submitted tickets
* Finance Managers can view all tickets for all employees
* Finance Managers can filter request by their current status (Pending, Approved, or Denied)
* Finance Managers can Approve or Deny reimbursement requests created by employees

To-do list:
* Add logged in users name and title to view

## Getting Started 
* Command Line - git clone git@github.com:TimHammes/BOOMERANG-BUCKS.git
* Open Eclipse IDE and select Import from File menu to add project to workspace
* Type Maven into search bar and select Existing Maven project 
* Browse to Project Directory and click Finish
* [Download Tomcat 9 Here](https://tomcat.apache.org/tomcat-9.0-doc/setup.html) to local directory
* Add Tomcat to Eclipse workspace: Window--preferences--Server--Runtime Environment--Add--Apache--Tomcat version 9
* Set port: In package explorer find Tomcat server inside Server folder and open Server.xml to change port(if needed)
* Add Tomcat to project build path: Right click project and select properties--targeted runtimes--Apache Tomcat
* Open Server view: Window--Other--Server
* Add project to Tomcat: Right click Tomcat in server view and select 'Add and Remove'. Select project and Add
* Start up Tomcat Server: Right click Tomcat in server view and select Start
* Launch project: Right click project in package explorer and select Run As--Run on Server

## Usage
![Ticket View](../../img/CreateTicketView.jpg?raw=true "Ticket View")

## License
This project uses the following license: [GNU General Public License](LICENSE)