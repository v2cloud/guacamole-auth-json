cd ..
mvn clean
mvn package
rm -f ~/guacamole-client/guacamole_home/extensions/*.jar
cp target/*.jar ~/guacamole-client/guacamole_home/extensions
catalina run
# http://localhost:8080/guacamole-1.3.0/#/
