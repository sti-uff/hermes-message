--here goes the sql for database migration
-- check http://flywaydb.org/ for instructions


CREATE TABLE sendtask (
    id integer AUTO_INCREMENT,
    sendto CHAR(255),
    replyto CHAR(255),
    subject CHAR(255),
    content CHAR(255),
    createdat TIMESTAMP,
    status CHAR(255),
    PRIMARY KEY (id)
);
