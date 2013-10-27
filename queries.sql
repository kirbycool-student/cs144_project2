select count(*) from User;
select * from User u join Item i on u.UserId = i.Seller where u.location = 'New York'; 
