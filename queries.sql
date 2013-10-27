select count(*) from User;
select count(*) from (select distinct u.UserId from User u join Item i on u.UserId = i.Seller where u.location = 'New York' collate utf8_bin) as t;
select count(*) from (select count(*) as count from Item i join ItemCategory c on i.ItemId = c.ItemId group by i.itemId) as t where t.count = 4;
select t.ItemId from (select i.ItemId, b.Amount from Item i join Bid b on i.ItemId = b.BidIdwhere i.Ends > '2001-12-20 00:00:01' order by b.amount desc limit 1) as t;
select count(*) from (select distinct u.UserId from User u join Item i on u.UserId = i.Seller where u.Rating > 1000) as t;
select count(*) from (select distinct b.Bidder from Item i join Bid b where i.Seller = b.Bidder) as t;
select count(*) from (select distinct c.Category from Item i join ItemCategory c on i.ItemId = c.ItemId where (select max(b.amount) from Bid b where b.ItemId = i.ItemId) > 100) as t;
