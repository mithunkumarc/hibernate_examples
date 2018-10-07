/**
 * 
 */
/**
 * @author mithun
 *
 */
module CRUDhibernate {
	requires org.hibernate.orm.core;
	requires java.xml.bind;
	requires mysql.connector.java;
	requires java.sql;
	requires javax.persistence;
	opens com.example.hibernate;
}