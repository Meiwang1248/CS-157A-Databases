/* PROBLEM 2*/

#a 
CREATE ASSERTION OnlyMakesPCs CHECK 
( NOT EXISTS         
        (              
            (SELECT maker FROM Product NATURAL JOIN PC)        
         INTERSECT              
            (SELECT maker FROM Product NATURAL JOIN Laptop)         
            )     
); 

#b
CREATE ASSERTION LaptopAtLeastAsFast CHECK 
(NOT EXISTS
    (SELECT *
    FROM PC P, Product Prod1
    WHERE P.model = Prod1.model AND NOT EXISTS
        (SELECT *
        FROM Laptop L, Product Prod2
        WHERE L.model = Prod2.model AND Prod1.maker = Prod2.maker
            AND L.speed >= P.speed))
);

#c
CREATE ASSERTION LaptopMoreMemory CHECK 
(NOT EXISTS
    (SELECT *
    FROM PC P, Laptop L
    WHERE P.price < L.price AND P.ram < L.ram)
);

#d
CREATE ASSERTION ProductRelation CHECK
(
    (SELECT type 
    FROM Product 
    WHERE type = ‘pc’ AND model IN (SELECT model FROM PC))
    OR (type = ‘laptop’ AND model IN (SELECT model FROM Laptop)) 
    OR (type = ‘printer’ AND model IN (SELECT model FROM Printer))
);
