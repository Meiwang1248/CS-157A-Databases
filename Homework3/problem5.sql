/* a */
 No, the view is not updatable since it is constructed from two different relations. 

/* b */
CREATE  TRIGGER  NewPCInsert 
INSTEAD  OF  INSERT  ON  NewPC 
REFERENCING  NEW ROW  AS  NewRow 
    FOR EACH ROW 
    (INSERT  INTO  Product  VALUES(NewRow.maker, NewRow.model, ‘pc’)) 
    (INSERT  INTO  PC  VALUES(NewRow.model, NewRow.speed, NewRow.ram, NewRow.hd, NewRow.price)); 

/*c*/
CREATE  TRIGGER  PriceUpdate 
INSTEAD  OF  UPDATE  NewPC 
REFERENCING  NEW  AS  new 
             OLD  AS  old 
    FOR EACH ROW 
    (UPDATE Product SET maker=new.maker, model=new.model WHERE model=old.model
     UPDATE PC SET model=new.model, speed=new.speed, ram=new.ram, hd=new.hd, price=new.price WHERE model=old.model); 


/* d */
CREATE  TRIGGER  TupleDeletion 
INSTEAD  OF  DELETE  FROM  NewPC 
REFERENCING  OLD  AS  old 
    FOR EACH ROW 
    (DELETE FROM Product WHERE model=old.model
     DELETE FROM PC WHERE model=old.model); 
