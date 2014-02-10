if gae plugin is not installed then you can download using: mvn gae:unpack

Run on command line using `mvn gae:run`.

Debug in eclipse: gae:debug

Create an Eclipse project by `mvn eclipse:eclipse` and use [Google Plugin for Eclipse](http://code.google.com/eclipse/) to run it.

Deploy to App Engine by `mvn gae:deploy`. You might need to do `mvn gae:unpack` before the first time you run that.


See Database:
http://localhost:8080/_ah/admin/datastore?kind=Greeting

http://gae-spring-jdo.appspot.com/
