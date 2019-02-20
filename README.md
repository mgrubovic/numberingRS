# numberingRS

FUNCTIONALITY

The project uses data from the RATEL Database of assigned telephone numbering resource. The Database is the official database of geographic telephone numbers and ranges(blocks)in Serbia.

On this site you can:

    Find out does the (geographic) subscriber number is assigned and if it is, who is assignee (operator);
    Check out availability of geographic numbering range(block);
    Upload a file, and then compare the uploaded file against the official database;
    Take a look at the distribution of phone numbers in relation to the area code or in relation to the operator.

TECHNOLOGY

This web application is build on the MVC pattern. Application logic is written in Java where initialization is done by servlet listener and servlets act also sa controllers, view is in JSP, data are stored in ArrayLists and in database.
Summary page, which gives the view on number distribution, is made using Hibernate and HSQLDB

JavaScript is used for checking input values and to make group request for number checking.
JQuery is used to adjust table view by area code, by start of range or by telco operator.
JAXB is used for reading and writing XML.

CONFIGURATION

The first choice is to retrieve the data from RATEL Database of numbering resource use
Table data are taken from web address: http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv

If an attempt to retrieve data from site fails, application will try the second choice and take data from file in /data folder

Source for retrieving data, URL address and file name are configurable in web.xml file

 

Tested on Apache Tomcat/8.0.36
using browsers:
Firefox Quantum 58.0.2 (64-bit)
Google Chrome Version 63.0.3239.132 (Official Build) (64-bit)
Microsoft Edge 41.16299.248.0
