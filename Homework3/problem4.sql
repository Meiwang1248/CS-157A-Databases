/* PROBLEM 4*/

/* a */ 
CREATE VIEW RichExec AS 
    SELECT * 
    FROM MovieExec
    WHERE netWorth >= 10000000;

/* b */
CREATE VIEW StudioPress AS  
    SELECT m.name, m.address, s.PresC#
    FROM MovieExec m , Studio s
    Where m.cert# = s.presC#;

/* c */ 
CREATE VIEW ExecutiveStar AS 
    SELECT star.name, star.address, star.gender, star.birthdate, exec.cert#, exec.netWorth
    FROM MovieStar star, MovieExec exec
    WHERE star.name = exec.name AND star.address = exec.address


    


