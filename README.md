Hermes-message [![Build Status](https://travis-ci.org/sti-uff/hermes-message.png?branch=master)](https://travis-ci.org/sti-uff/hermes-message)
===============


Hermes is an app to send mail and sms messages. It is used and developed by STI-UFF, but anyone that wants can use it.

__API description:__ https://github.com/sti-uff/hermes-message/wiki/API

Why another email API?
---
__A little bit of motivation (for us):__ Today we have more than 10 projects that send email, through our internal mail server. 
However, we're studying to change to AWS someday or other mail service. Thus, not to change all applications every time, Hermes is our API interface to send mail.
It's another level of abstraction.

__General motivation__
* Its a interface API to send mail, so if you want to change from one mail service to other just change Hermes config and all your apps will follow.
* It desacouples the mail service implementation from your apps, providing another level of abstraction.
* It enables you to send mass mail without the anoyance of group or recepient limits, as it abstracts this.
* Also, with it you can know how much emails each application sent, etc, etc, etc.

How it works?
---

Basically it receives API or web (in future versions) requests to send emails and latter sends them as configured to do. 
The API documentation is on the [wiki](https://github.com/sti-uff/hermes-message/wiki/API).

How to use it?
---

Replace the $TOMCAT_HOME with your tomcat 6 folder when needed.

1. Clone the source
<pre> git clone https://github.com/sti-uff/hermes-message.git </pre>
2. Fill the file [config.properties](https://github.com/sti-uff/hermes-message/blob/master/src/main/resources/config.properties) (in src/main/resources) with your configuration
<pre> vim hermes-message/src/main/resources/config.properties </pre>
3. Use Maven to build the war
<pre> mvn clean war -DskipTests=true </pre>
4. Deploy it under a Tomcat 6 (or Jetty 7) 
<pre> cp hermes-message/target/hermes.war $TOMCAT_HOME/webapps/ </pre>
5. Run Tomcat
<pre> ./ $TOMCAT_HOME/bin/startup.sh </pre>

