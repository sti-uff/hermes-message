--here goes the sql for database migration
-- check http://flywaydb.org/ for instructions

create domain str varchar(256);
create table sendtask (
    sendto str not null,
    replyto str,
    subject str,
    content str,
    createdat timestamp,
    status str,
    id int not null primary key
);
