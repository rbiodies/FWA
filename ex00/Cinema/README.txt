# Instructions on how to deploy and use your application.

# You need to create a database. I used SQLite. MAKE SURE THE PATH TO THE DATABASE db.url=/Users/${USER}/your_path/FWA/test.db IN THE src/main/webapp/WEB-INF/application.properties FILE IS CORRECT! Run schema.sql and data.sql.

# You need to run ./README.txt from the Cinema folder.

# ./README.txt

# If you get the error zsh: permission denied: ./README.txt

# chmod 755 README.txt

# Apache Tomcat is one of the most popular web servers in the Java community. It ships as a servlet container capable of serving Web Archives with the WAR extension.

curl https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.tar.gz | tar -xz

# WAR – Short for Web Archive. It's the extension of a file that packages a web application directory hierarchy in ZIP format. Java web applications are usually packaged as WAR files for deployment. These files can be created on the command line or with an IDE. Create a WAR archive using maven package command.

mvn clean package

# Such archive shall be deployed in Tomcat.

cp target/Cinema-1.0-SNAPSHOT.war apache-tomcat-9.0.71/webapps/

# We can start the Tomcat server by simply running the startup script

# Before starting the server, make sure that the server is turned off to avoid errors

apache-tomcat-9.0.71/bin/startup.sh

# After deploying the WAR file, Tomcat unpacks it and stores all the project files from the webapps directory in a new directory named after the project.

# Wait for the server to start

sleep 0.5

# Web application be accessed by the URL

open http://localhost:8080/Cinema-1.0-SNAPSHOT

# Run all commands at once

# curl https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.tar.gz | tar -xz && mvn clean package && cp target/Cinema-1.0-SNAPSHOT.war apache-tomcat-9.0.71/webapps/ && apache-tomcat-9.0.71/bin/startup.sh && sleep 0.5 && open http://localhost:8080/Cinema-1.0-SNAPSHOT

# We can shut down the Tomcat server by simply running the shutdown script

# apache-tomcat-9.0.71/bin/shutdown.sh

# Remove a WAR archive using maven package command.

# mvn clean

# Remove an Apache Tomcat.

# rm -rf apache-tomcat-9.0.71

# Run all commands at once

# apache-tomcat-9.0.71/bin/shutdown.sh && mvn clean && rm -rf apache-tomcat-9.0.71
