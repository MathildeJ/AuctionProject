Group Members:
David Ahmad ZOnneveld Michel
Guillermo Reyes Guzman
Djamila Baroudi 
Mathilde Jean
Poonam Kumari

Before deploying te project:

1) Create a Database called "auction" with username: root, password:root
2) Create a connection Pool to that database with the name "AuctionPool"
3) Create a resource called "jdbc/auctionResource" using that pool
4) Create a Connection Factory  with the name "jms/OrderConnectionFactory", ResourceType "javax.jms.TopicConnectionFactory"
5) Create an DestinationRequestQueue with the name "OrderRequestQueue", Resource type "Queue"