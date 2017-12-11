 /* PROBLEM 3*/

#a 
CREATE TRIGGER LowPricePCTrigger 
AFTER UPDATE OF price ON PC 
REFERENCING   
        OLD ROW   AS OldRow,  
        OLD TABLE AS OldStuff,  
        NEW ROW   AS NewRow,  
        NEW TABLE AS NewStuff 
FOR EACH ROW 
WHEN (NewRow.price < ALL  
        (SELECT PC.price FROM PC   
        WHERE PC.speed = NewRow.speed)) 
BEGIN
        DELETE FROM PC  
        WHERE (model, speed, ram, hd, price) IN NewStuff;  
        INSERT INTO PC   (
            SELECT * FROM OldStuff);  
END;

#b
CREATE TRIGGER modelPresent
AFTER INSERT ON Printer
CHECK (EXISTS
        (Select Product.model
        FROM Product
        WHERE model = Product.model));
END;

#c
CREATE TRIGGER averagePrice
INSTEAD OF UPDATE OF price OF Laptop
INSERT ON Laptop
DELETE ON Laptop
REFERENCING
        OLD_TABLE AS OldStuff
        NEW_TABLE AS NewStuff
WHEN( 1500 <= ALL
        (SELECT AVG(price)
        FROM (Laptop EXCEPT OldStuff) UNION NewStuff, Product
        WHERE Laptop.model = Product.model
        GROUP BY maker ))
        DELETE FROM Laptop
        WHERE (model, speed, ram, hd, screen, price) IN OldStuff
        INSERT INTO Laptop
        (SELECT * FROM NewStuff);
END;

#d
CREATE TRIGGER BF_UPD_PC
     AFTER UPDATE OF ram ON PC
     AFTER UPDATE OF hd ON PC 
     REFERENCING   
        OLD ROW   AS OldRow,  
        OLD TABLE AS OldStuff,  
        NEW ROW   AS NewRow,  
        NEW TABLE AS NewStuff   
     FOR EACH ROW 
         SELECT ram FROM PC   
         WHERE NewRow.hdisk >= 100*(NewRow.ram)
         BEGIN 
                DELETE FROM PC  
                WHERE (model, speed, ram, hd, price) IN NewStuff;  
                INSERT INTO PC   (
                SELECT * FROM OldStuff);  
     END;