# Documentation

#### Auth Credentials

###### **Admin**

Username: HR0001
Password: sU0fq1!1W_08

###### **Employee**

**Salaried Employee**

Username: jdoe2018

Password: janedoe2311

**Hourly Employee**

Username: norona2024

Password: nateodev213

All users will log in with Spring Boot basic auth form system at the root route “/” or “/login”.
After authenticating, the application will route you to a page based on your permissions.
If a user attempts to visit a page they don’t have access to based on their user role, they’ll be redirected.


##### Employee Users

Employee users will be directed to the /time-entry page. Here, you can view, create, edit, and delete both time entries and PTO requests.

Employee users will also have access to the previous 7 days of payment information, including a full breakdown of taxes and other costs associated with payroll.
The main navigation bar at the top of the page will allow you to visit the time entry page, view the documentation, submit a PTO request, or log out.

**Work Entry & PTO**

Employees will be given a simple form with a date selector and a number input. Make your input selections and hit “Submit” at the bottom of the form to register your new request
You will see a list of previous work entries and PTO requests on the same page, if they are not yet approved by an admin user you may edit or delete them with the two buttons at the bottom of each card.

##### Admin Users

Admin users will instead be directed to the employees screen, where they can view existing employees as well as edit and delete them. Here, you can also create new employees by clicking the button at the top of the page and filling out the form.

**Payroll**
Admin users can access the payroll information of all employees via the /admin/salaries

**Employee Demographics**
Admin users can also access all employee demographics through the /admin/employee-demos route.
Here, you can use the search bar at the top to narrow the employee list you’d like to view.

**Reports**
Admin users can generate and view a fresh report of the current work period with the /admin/payroll route.
This will provide a simple table layout with the current report, as well as a link to the printable version.

**Full documentation and images available available here: https://docs.google.com/document/d/1aOfS3shCiyUjDb36iqE-MPPnOV-ebkrZLlYmslUiYlc/edit?usp=sharing**

