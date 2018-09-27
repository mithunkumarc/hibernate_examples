include below modules to run hibernate on java10


module <your-module-name> {
	requires hibernate.core;
	requires hibernate.jpa;
	requires mysql.connector.java;
	requires java.sql;
	requires java.xml.bind;
}
