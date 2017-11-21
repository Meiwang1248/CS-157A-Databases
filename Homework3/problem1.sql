/* Question 1 */

#a
CREATE TABLE Movies ( 
title   CHAR(100), 
year   INT, 
length  INT, 
genre  CHAR(10), 
studioName CHAR(30), 
producerC# INT REFERENCES MovieExec(cert#)
PRIMARY KEY (title, year) 
);

#b
CREATE TABLE Movies ( 
title   CHAR(100), 
year   INT, 
length  INT, 
genre  CHAR(10), 
studioName CHAR(30), 
producerC# INT REFERENCES MovieExec(cert#)
ON DELETE SET NULL 
ON UPDATE SET NULL, 
PRIMARY KEY (title, year) 
);

#c
CREATE TABLE Movies ( 
title   CHAR(100), 
year   INT, 
length  INT, 
genre  CHAR(10), 
studioName CHAR(30), 
producerC# INT REFERENCES MovieExec(cert#)
ON DELETE CASCADE 
ON UPDATE CASCADE, 
PRIMARY KEY (title, year)
PRIMARY KEY (title, year) 
);

#d
CREATE TABLE StarsIn  
movieTitle CHAR(100)   REFERENCES  Movie(title), 
movieYear INT, 
starName CHAR(30), 
PRIMARY KEY (movieTItle, movieYear, starName) 
);