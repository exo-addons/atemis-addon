eXo Atemis Extension
===============
Basic Integration between eXo Platform and Atemis Solution

Configuration
---------------

* Copy and rename this file /exo-atemis-portlet/src/main/webapp/WEB-INF/portlet-ini.xml into portlet.xml.
* Edit the file and setup the following information : 
	* atemis.host : Atemis endpoint (ex: http://atemiscloud.com/)
	* atemis.page.holidayRequest : Module holiday Request History Id

(More modules to come in the following version)

Build
---------------

Simply build it with :

	mvn clean install


Deploy to eXo
---------------

In the build folder exo-atemis-pkg/target/atemis-addon/ copy :

	* /lib/atemis-addon-config-4.0.x.jar (activation jar)

in your exo-tomcat/lib/ folder

	* atemis-addon.war
	* atemis-porlet.war

in your exo-tomcat/webapps/ folder

Finally, start your tomcat.
