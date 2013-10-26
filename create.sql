CREATE TABLE Item(
  ItemId          INT           NOT NULL,
  Name            VARCHAR(255)  NOT NULL,
  Currently       FLOAT         NOT NULL,
  BuyPrice        FLOAT                 ,
  FirstBid        FLOAT         NOT NULL,
  NumberOfBids    INT           NOT NULL,
  Location        VARCHAR(255)  NOT NULL,
  Country         VARCHAR(255)  NOT NULL,
  Started         DATETIME      NOT NULL,
  Ends            DATETIME      NOT NULL,
  Description     TEXT          NOT NULL,
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
  Category        VARCHAR(255)  NOT NULL,
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
  Time            DATETIME      NOT NULL,
  Amount          FLOAT         NOT NULL,
  PRIMARY KEY (BidId),
  FOREIGN KEY (Bidder) REFERENCES User(UserId)
);

CREATE TABLE ItemBid(
  ItemId          INT           NOT NULL,
  BidId           INT           NOT NULL,
  FOREIGN KEY (ItemId) REFERENCES Item(ItemId),
  FOREIGN KEY (BidId) REFERENCES Bid(BidId)
);


