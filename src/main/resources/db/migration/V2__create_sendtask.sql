--here goes the sql for database migration
-- check http://flywaydb.org/ for instructions

create sequence sendtask_seq START WITH 1;

create table sendtask(
    sendto str,
    replyto str,
    subject str,
    content str,
    createdat timestamp,
    status str,
    id int primary key
);
