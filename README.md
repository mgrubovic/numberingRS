# numberingRS
This application uses Java and JSP for application logic.

JavaScript is for checking input values.

JAXB for reading and writing XML

The first choice is to retrieve the data from RATEL site dedicated to assigned telephone ranges (blocks):
http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv

If an attempt to retrieve data from site fails, application will try second choice and take data from file in /data folder

Source for retrieving data, URL address and file name are configurable in web.xml file

Summary page is made using Hibernate and HSQLDB

Tested on Apache Tomcat/8.0.36
using browsers:
Firefox Quantum 58.0.2 (64-bit)
Google Chrome Version 63.0.3239.132 (Official Build) (64-bit)
Microsoft Edge 41.16299.248.0
