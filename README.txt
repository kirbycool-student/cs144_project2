*: primary key
#: foreign key

Item(itemId*, name, category, currently, buy_price, first_bid#, number_of_bids, started, ends, seller#, description)

Bid(bidId*, bidder#, time, amount)

ItemBid(itemId#, bidId#)

User(userId*, rating, location#)

Location(location*, country)
