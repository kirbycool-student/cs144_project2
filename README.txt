*: primary key
#: foreign key

Item(itemId*, name, currently, buy_price, first_bid, number_of_bids, started, ends, seller#, description)

Category(catId*, category)

ItemCategory(itemId#*, catId#*)

Bid(bidId*, bidder#, time, amount)

ItemBid(itemId#, bidId#)

User(userId*, rating, location#)

Location(location*, country)
