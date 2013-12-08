MySQLDeploy
===========

Running SQLs on multiple MySQL servers using a config file.


Config file description
========================
Description: This document instruct how MySqlDeploy run. 
First let me show you the dom structure: 
-1- means contain only one element. 
-*- means 	contain any number element 
|-1-<servers> 
| |-*-<server id="" type=""> 
	|    |-1-<ip> 
	|    |-1-<port> 
	|    |-1-<user> 
	|  	 |-1-<password>
	|-1-<tasks> 
	     |-*-<task id="" target-server="" target-database=""> 
	     |-*-<sql> 
	         |-1-<do> 
	         |-1-<undo> 
	         
There are two sections in this file: <servers> and <tasks>.

<servers> has any number of children <server> which describe the connection info 
of a server. 

<server> has two attribute: id and type. 
	* Id is the identifier of this database. Id is required and should be unique 
	within <servers>. 
	* Type tells the type of sql server:"mysql","sqlserver"	etc. Since this 
	project only support mysql for now, type is optional and default to "mysql". 

<server> has following children <ip>, <port>, <user> and <password>.

<tasks> has any number of children <task> which describe how SQLs should be 
executed. 

<task> has three attribute id, target-server and target-database. They are all
required. 
  * Id is the identifier of	this task and should be unique within <tasks>. 
  * Target-server	is the list of on which servers this task should be run. 
  The list of databases	use comma as separator.
  * Target-database is on which databases will this task run.

<task> has any number of children <sql>. All the <sql> under this task will 
be executed on the target-database. 

<sql> has two children <do> and <undo>. 

The sql in <do> and <undo> are opposite operations. For e.g. <do> is creating 
a table, then <undo> in the same <sql> is dropping this table.