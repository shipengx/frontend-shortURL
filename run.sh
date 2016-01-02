#!/bin/bash
#

rm -rf $TOMCAT_HOME/libexec/webapps/shortenURL.war
rm -rf $TOMCAT_HOME/libexec/webapps/shortenURL


mvn clean package
cp ./target/shortenURL.war  $TOMCAT_HOME/libexec/webapps

catalina run


