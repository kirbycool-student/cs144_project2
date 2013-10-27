select count(*) from User;
select count(*) from (select distinct u.UserId from User u join Item i on u.UserId = i.Seller where u.location = 'New York' collate utf8_bin) as t;
select count(*) from (select count(*) as count from Item i join ItemCategory c on i.ItemId = c.ItemId group by i.itemId) as t where t.count = 4;
