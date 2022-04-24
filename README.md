# Smart-Inventory-management-Py-Java-Sql
Same Project with Python and Java connected to same SQL DB

CLI application connected to DB named "inventory" having table "records".
performing basic database operation such as read, update, delete, search, count

# Java JDBC application
used library java.sql and java.util with scrollable ResultSet.

# python application
used library mysql.connector

# output
```
Welcome to the Inventory Management Tool
1. Show inventory.
2. insert into inventory.
3. delete from inventory.
4. update records in inventory.
5. report of inventory.
6. search into inventory
Enter your choice: 1
Id: 2; Item: soap; quantity: 8; price: 6
Id: 3; Item: car; quantity: 7; price: 56
Id: 5; Item: cream; quantity: 6; price: 50
Id: 6; Item: sugar; quantity: 5; price: 30
Id: 7; Item: paste; quantity: 7; price: 12
Id: 10; Item: CD; quantity: 90; price: 10
Id: 11; Item: facewash; quantity: 1; price: 56
Id: 12; Item: mouse; quantity: 6; price: 45
Id: 14; Item: amul; quantity: 6; price: 30
Do you want to continue(1/0)? 
1
Welcome to the Inventory Management Tool
1. Show inventory.
2. insert into inventory.
3. delete from inventory.
4. update records in inventory.
5. report of inventory.
6. search into inventory
Enter your choice: 4
Enter the item You want to update: paste
What do you want to update?
1. quantity
2. price
Enter your choice: 1
Enter new quantity: 8
1 quantity updated
Do you want to continue(1/0)? 
1
Welcome to the Inventory Management Tool
1. Show inventory.
2. insert into inventory.
3. delete from inventory.
4. update records in inventory.
5. report of inventory.
6. search into inventory
Enter your choice: 4
Enter the item You want to update: teddy
Item not available
Do you want to continue(1/0)? 
0
```
