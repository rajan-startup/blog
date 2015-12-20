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

[0] run : mvn eclipse:eclipse -> to fetch datanucleus plugin for eclipse
[1] check jdk = 1.7
[2] properties : Google > ORM > set all classes and packages as an entity
[3] mvn clean
[4] Build project 
[5] mvn install
[6] mvn gae:debug

make sure to run env.bat fro java7
C:\XXXXX\maven.repo\XXXXX\gae\com\google\appengine\appengine-java-sdk\1.8.9\appengine-java-sdk-1.8.9\bin\appcfg.cmd --enable_jar_splitting update target\study2tech-1-SNAPSHOT\


(1)set PATH=C:\Java\java7\jdk1.7.0_60\bin;%PATH%
(2)mvn install -settings C:\rd\prjcts\gae\settings.xml
(3)mvn gae:debug -settings C:\rd\prjcts\gae\settings.xml
(4)C:\rd\prjcts\gae\appengine-java-sdk-1.8.9\bin\appcfg.cmd --enable_jar_splitting update target\study2tech-1-SNAPSHOT\

If project already has Target folder then just make changes into Target/files only and run step: 4


Kill port:
1. netstat -a -o -n > log.txt
2. search for 8080 PID
3. taskkill /F /PID [PID]

(*) If you have stable project with target folder created then:
1. just add content-files under target directory
2. run step (4) deploy it to remote server
3. Pre-req:
a. setup java7
b. setup gae engine 

