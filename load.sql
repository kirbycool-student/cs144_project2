LOAD DATA INFILE "item.csv"          INTO TABLE Item
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
LOAD DATA INFILE "user.csv"          INTO TABLE User
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
LOAD DATA INFILE "category.csv"      INTO TABLE Category
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
LOAD DATA INFILE "itemCategory.csv"  INTO TABLE ItemCategory
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
LOAD DATA INFILE "bid.csv"           INTO TABLE Bid
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
LOAD DATA INFILE "itembid.csv"       INTO TABLE ItemBid
  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
