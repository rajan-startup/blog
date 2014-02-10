if gae plugin is not installed then you can download using: mvn gae:unpack

Run on command line using `mvn gae:run`.

Debug in eclipse: gae:debug

Create an Eclipse project by `mvn eclipse:eclipse` and use [Google Plugin for Eclipse](http://code.google.com/eclipse/) to run it.

Deploy to App Engine by `mvn gae:deploy`. You might need to do `mvn gae:unpack` before the first time you run that.


See Database:
http://localhost:8080/_ah/admin/datastore?kind=Greeting

http://gae-spring-jdo.appspot.com/


FAQ:
Q1: If you get ERROR: org.datanucleus.store.exceptions.NoTableManagedException: Persistent class
Ans: Datanucleus is not getting enhannced due to two reason: solutions:
1. Check Google app engine path: project > build path > google app engine > sdk > 1.8.9
2.
a. maven > disable nature -> cmd: mvn eclipse:eclipse -> convert maven project 
b. Google App Engine > ORM > give exact path of entity class and class package -> mvn clean install -> mvn gae:debug