MySQLDeploy
===========

Running SQLs on multiple MySQL servers using a config file.


Config file description
========================
Description: This document instruct how MySqlDeploy run. 
First let me show you the dom structure: 
-1- means contain only one element. 
-*- means 	contain any number element 
|-1-&lt;servers&gt; 
| |-*-&lt;server id="" type=""&gt; 
	|    |-1-&lt;ip&gt; 
	|    |-1-&lt;port&gt; 
	|    |-1-&lt;user&gt; 
	|  	 |-1-&lt;password&gt;
	|-1-&lt;tasks&gt; 
	     |-*-&lt;task id="" target-server="" target-database=""&gt; 
	     |-*-&lt;sql&gt; 
	         |-1-&lt;do&gt; 
	         |-1-&lt;undo&gt; 
	         
There are two sections in this file: &lt;servers&gt; and &lt;tasks&gt;.

&lt;servers&gt; has any number of children &lt;server&gt; which describe the connection info 
of a server. 

&lt;server&gt; has two attribute: id and type. 
	* Id is the identifier of this database. Id is required and should be unique 
	within &lt;servers&gt;. 
	* Type tells the type of sql server:"mysql","sqlserver"	etc. Since this 
	project only support mysql for now, type is optional and default to "mysql". 

&lt;server&gt; has following children &lt;ip&gt;, &lt;port&gt;, &lt;user&gt; and &lt;password&gt;.

&lt;tasks&gt; has any number of children &lt;task&gt; which describe how SQLs should be 
executed. 

&lt;task&gt; has three attribute id, target-server and target-database. They are all
required. 
  * Id is the identifier of	this task and should be unique within &lt;tasks&gt;. 
  * Target-server	is the list of on which servers this task should be run. 
  The list of databases	use comma as separator.
  * Target-database is on which databases will this task run.

&lt;task&gt; has any number of children &lt;sql&gt;. All the &lt;sql&gt; under this task will 
be executed on the target-database. 

&lt;sql&gt; has two children &lt;do&gt; and &lt;undo&gt;. 

The sql in &lt;do&gt; and &lt;undo&gt; are opposite operations. For e.g. &lt;do&gt; is creating 
a table, then &lt;undo&gt; in the same &lt;sql&gt; is dropping this table.