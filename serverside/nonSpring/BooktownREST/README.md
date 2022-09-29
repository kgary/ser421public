# Booktown REST example

SER422 updated May 2021

## Description

This is a basic REST API example using the Jersey framework and optionally Jackson for custom output

## Getting Started

### Dependencies

* This version has been tested on Tomcat 9 and Tomcat 10 (in the tomcat10 directory) on JDK11 and up
* The tomcat 9 version uses the Jersey 2.2.7 libraries, tomcat 10 3.0
* Tomcat 8 and JDK8 should work but have not been tested. Tomcat 7 probably works as well.

### Installing

Simply unzip to get the source tree. The tomcat10 has a complete replica intended for tomcat10, with the only changes being the use of jakarta servlet libraries and upgrades to the jersey libraries to use 3.0 (The tomcat 9 version uses Jersey 2.2.7)

### Executing program

* Initialize a mysql or postgres database using the respective SQL file in the home directory
* By default the directory is btown_rest but this is easily renamed. rdbm.properties controls database access and queries used.
* booktown.properties allows you to configure and switch between an in-memory store (SimpleBooktownServiceImpl) and a RDBM (RDBMBooktownService Impl) database one.
* rdbm.properties is only read if an RDBM is used.
* The build script will by defauly try to create HTML API documentation using apidocjs. There are sometimes issues with doing this on Windows, though we think we have them resolved. If you get error yo can still run apidocjs directly on the command-line, see their documentation.
* Note that by default if you use a browser to execute GET on resources (authors), you will get a 500 error because the default accept header s not application/json, text/json, or */*. Use Postman, ARC, or a similar client instead, or modify the Accepts request header using your browser dev tools.

## Help

## Authors

Contributors names and contact info

* Kevin Gary, Course Coordinator SER422
* Updates by Abhishek Deore, May 2021 (thank you!)

## Version History

* 0.2 (May 2021)
    * Support for JDK 11 and 15 tested
    * Support for Tomcat 9 and 10 added using Jersey 2.2.7 and 3.0 respectively
* 0.1 (January 2018)
    * Initial Release ported from DAO example
