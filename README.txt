Partner: Kirby Cool 703-892-301

*: primary key
#: foreign key

Item(itemId*, name, currently, buy_price, first_bid, number_of_bids, location,
country, started, ends, description)

ItemCategory(category*, itemID*#)

Bid(itemId*#, bidder*#, time*, amount*)

User(userId*, rating, location, country)
