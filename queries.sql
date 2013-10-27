select count(*) from User;
select count(*) from User u join Item i on u.UserId = i.ItemId where u.location = 'New York'; 
