CREATE TABLE User(
  UserId          VARCHAR(255)  NOT NULL,
  Rating          INT           NOT NULL,
  Location        VARCHAR(255)          ,
  Country         VARCHAR(255)	        ,
  PRIMARY KEY (UserId)
);

CREATE TABLE Item(
  ItemId          INT           NOT NULL,
  Name            VARCHAR(255)          ,
  Currently       DECIMAL(8,2)                ,
  BuyPrice        DECIMAL(8,2)                 ,
  FirstBid        DECIMAL(8,2)                 ,
  NumberOfBids    INT                   ,
  Location        VARCHAR(255)          ,
  Country         VARCHAR(255)          ,
  Started         DATETIME              ,
  Ends            DATETIME              ,
  Seller          VARCHAR(255)          ,
  Description     VARCHAR(4000)         ,
  PRIMARY KEY (ItemId),
  FOREIGN KEY (Seller) REFERENCES User(UserId)
);


CREATE TABLE ItemCategory(
  Category        VARCHAR(255)  NOT NULL,
  ItemId          INT           NOT NULL,
  /*PRIMARY KEY (ItemId, CategoryId),*/
  FOREIGN KEY (ItemId) REFERENCES Item(ItemId)
);

CREATE TABLE Bid(
  ItemId          INT           NOT NULL,
  Bidder          VARCHAR(255)  NOT NULL,
  Time            DATETIME      ,
  Amount          DECIMAL(8,2)         ,
  FOREIGN KEY (Bidder) REFERENCES User(UserId),
  FOREIGN KEY (ItemId) REFERENCES Item(ItemId)
);


