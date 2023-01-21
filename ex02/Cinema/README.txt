Instructions on how to deploy and use your application.

You need to create a database. I used SQLite. Specify db.url=/your_path/FWA/test.db in the src/main/webapp/WEB-INF/application.properties file. Run schema.sql and data.sql.

Apache Tomcat is one of the most popular web servers in the Java community. It ships as a servlet container capable of serving Web Archives with the WAR extension.

curl https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.tar.gz | tar -xz

WAR – Short for Web Archive. It's the extension of a file that packages a web application directory hierarchy in ZIP format. Java web applications are usually packaged as WAR files for deployment. These files can be created on the command line or with an IDE. Create a WAR archive using maven package command.

mvn clean package

Such archive shall be deployed in Tomcat.

cp target/Cinema-1.0-SNAPSHOT.war apache-tomcat-10.1.5/webapps/

We can start the Tomcat server by simply running the startup script

apache-tomcat-9.0.71/bin/startup.sh

After deploying the WAR file, Tomcat unpacks it and stores all the project files from the webapps directory in a new directory named after the project.

Web application be accessed by the URL

open http://localhost:8080/Cinema-1.0-SNAPSHOT

Run all commands at once

curl https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.tar.gz | tar -xz && mvn clean package && cp target/Cinema-1.0-SNAPSHOT.war apache-tomcat-9.0.71/webapps/ && apache-tomcat-9.0.71/bin/startup.sh && open http://localhost:8080/Cinema-1.0-SNAPSHOT

We can shut down the Tomcat server by simply running the shutdown script

apache-tomcat-9.0.71/bin/shutdown.sh

Remove a WAR archive using maven package command.

mvn clean

Remove an Apache Tomcat.

rm -rf apache-tomcat-9.0.71

Run all commands at once

apache-tomcat-9.0.71/bin/shutdown.sh && mvn clean && rm -rf apache-tomcat-9.0.71
