CREATE TABLE Item(
  ItemId          INT           NOT NULL,
  Name            VARCHAR(255)  ,
  Currently       FLOAT         ,
  BuyPrice        FLOAT                 ,
  FirstBid        FLOAT         ,
  NumberOfBids    INT           ,
  Location        VARCHAR(255)  ,
  Country         VARCHAR(255)  ,
  Started         DATETIME      ,
  Ends            DATETIME      ,
  Description     TEXT          ,
  PRIMARY KEY (ItemId)
);

CREATE TABLE User(
  UserId          VARCHAR(255)  NOT NULL,
  Rating          INT           NOT NULL,
  Location        VARCHAR(255)          ,
  Country         VARCHAR(255)	        ,
  PRIMARY KEY (UserId)
);


CREATE TABLE Category(
  CategoryId      INT           NOT NULL,
  Category        VARCHAR(255)  ,
  PRIMARY KEY (CategoryId)
);

CREATE TABLE ItemCategory(
  CategoryId      INT           NOT NULL,
  ItemId          INT           NOT NULL,
  /*PRIMARY KEY (ItemId, CategoryId),*/
  FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId),
  FOREIGN KEY (ItemId) REFERENCES Item(ItemId)
);

CREATE TABLE Bid(
  BidId           INT           NOT NULL,
  Bidder          VARCHAR(255)  NOT NULL,
  Time            DATETIME      ,
  Amount          FLOAT         ,
  PRIMARY KEY (BidId),
  FOREIGN KEY (Bidder) REFERENCES User(UserId)
);

CREATE TABLE ItemBid(
  ItemId          INT           NOT NULL,
  BidId           INT           NOT NULL,
  FOREIGN KEY (ItemId) REFERENCES Item(ItemId),
  FOREIGN KEY (BidId) REFERENCES Bid(BidId)
);


