<h2>Running on your own computer</h2>

<ul>
  <li>Clone this repository</li>
  <li>Install Glassfish Server 4.1.1</li>
  <li>Configure JMS Connection Factory on Glassfish (keep reading)</li>
  <li>Configure JMS Queues on Glassfish (keep reading)</li>
  <li>Deploy MDBProject01 (Publish) on Glassfish</li>
  <li>Run MDBProjct01Client locally</li>
</ul>

<h2> Creating Connection Factory and Queues </h2>
<p> From windows CMD run the following commands using Glasfish command line tool (asadmin). Note: You'll have to configure Glassfish home on Windows path to get access to asadmin command line tool. It's usually in C:\Program Files\glassfish-4.1.1\bin. </p>
<pre class="command-line">
<span class="command">asadmin create-jms-resource --restype javax.jms.ConnectionFactory --description "connection factory for echo" --property ClientId=echoMDB jms/echoMDB</span>
<span class="command">asadmin create-jms-resource --restype javax.jms.Queue --property Name=Queue01 Queue01</span>
<span class="command">asadmin create-jms-resource --restype javax.jms.Queue --property Name=Queue02 Queue02</span>
</pre>
