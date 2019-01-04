# numberingRS
FUNCTIONALITY

This application uses data from the RATEL Database of assigned telephone numbering resource. The Database is the official database of telephone numbers and ranges(blocks)in Serbia.

This site can help you to:

    Find out does the (geographic) subscriber number is assigned and if it is, who is assignee (operator);
    Check out availability of geographic numbering range(block);
    See the distribution of phone numbers in relation to the area code;


TECHNOLOGY

The application logic is written in Java, JSP and Servlet technology, using MVC pattern.

JavaScript is used for checking input values and to make group request for number checking.
Also, JavaScript is used to adjust table view by area code, by start of range or by telco operator.

JAXB is used for reading and writing XML

The first choice is to retrieve the data from RATEL Database of numbering resource use
Table data are taken from web address: http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv

If an attempt to retrieve data from site fails, application will try the second choice and take data from file in /data folder

Source for retrieving data, URL address and file name are configurable in web.xml file

Summary page is made using the Hibernate and the HSQLDB 

Tested on Apache Tomcat/8.0.36
using browsers:
Firefox Quantum 58.0.2 (64-bit)
Google Chrome Version 63.0.3239.132 (Official Build) (64-bit)
Microsoft Edge 41.16299.248.0
