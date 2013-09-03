Hermes-message
===============

[![Build Status](https://travis-ci.org/sti-uff/hermes-message.png?branch=master)](https://travis-ci.org/sti-uff/hermes-message)

Hermes is an app to send mail and sms messages. It is used and developed by STI-UFF, but anyone that wants can use it.

__API description:__ https://github.com/sti-uff/hermes-message/wiki/API

Why another email API?
---
__A little bit of motivation (for us):__ Today in STI-UFF we have more than 10 projects that send email, through our internal mail server. 
However, we're studying to change to AWS someday or other mail service. Thus, not to change all applications every time, Hermes is our API interface to send mail.
It's another level of abstraction.

__general motivation__
* Its a interface API to send mail, so if you want to change from one mail service to other just change Hermes config and all your apps will follow.
* It desacouples the mail service implementation from your apps, providing another level of abstraction.
* It enables you to send mass mail without the anoyance of group or recepient limits, as it abstracts this.
* Also, with it you can know how much emails each application sent, etc, etc, etc.

