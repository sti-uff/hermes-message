--here goes the sql for database migration
-- check http://flywaydb.org/ for instructions

insert into sendtask(sendto, replyto, subject, content, createdat, status, id)
values ('mail@send.to', 'mail@reply.to', 'test subject', 'test mail', 
        CURRENT_TIMESTAMP, 'TODO', next value for sendtask_seq);

insert into sendtask(sendto, replyto, subject, content, createdat, status, id)
values ('another.mail@send.to', 'another.mail@reply.to', 'another test subject', 
        'another test mail', CURRENT_TIMESTAMP, 'TODO', next value for sendtask_seq);
