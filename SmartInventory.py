from sys import exit
import mysql.connector
db = mysql.connector.connect(
  host="localhost",
  user="root",
  password="Mysql",
  database="inventory"
)

cr = db.cursor()

def mainMenu():
    
    print('')
    print('= Inventory Management Menu =')
    print('(1) Add New Item to Inventory')
    print('(2) Remove Item from Inventory')
    print('(3) Update Inventory')
    print('(4) Search Item in Inventory')
    print('(5) Print Inventory Report')
    print('(0) Quit')
    inputChoice = int(input("Enter choice: "))
    
    return inputChoice
    
def addItem():      
    choice = int(input("Enter 1 to continue or 0 to exit: "))
    if(choice == 1):
        it = input("enter item name: ")
        quty = input("enter quantity: ")
        price = input("enter price per unit: ")
        qry = "INSERT INTO records (item, quantity, price) VALUES (%s, %s, %s)"
        val = (it, quty, price)
        cr.execute(qry, val)
        db.commit()
        print(cr.rowcount, "inserted")
    if(choice == 0):
        exit(0)
    return

def removeItem():    
    choice = int(input("Enter 1 to continue or 0 to exit: "))
    if(choice == 1):
        it = input("enter the item you want to delete: ")
        qry = "DELETE FROM records WHERE item = %s"
        val = (it ,)
        cr.execute(qry, val)
        db.commit()
        if(cr.rowcount == 0):
            print("the we do not have this item")
        else:
            print(cr.rowcount, "deletd")
    if(choice == 0):
        exit(0)
    return

def updateInventory():
    choice = int(input("Enter 1 to continue or 0 to exit: "))
    if(choice == 1):
        print("what do you wnat to update?")
        print("1. quantity")
        print("2. price")
        upd = int(input("enter your choice: "))
        if(upd == 1):
            it = input("the item: ")
            newqty = input("update quantity to: ")
            qry = "UPDATE records SET quantity = %s WHERE item = %s"
            val = (newqty, it)
            cr.execute(qry, val)
            db.commit()
        if(upd == 2):
            it = input("the item: ")
            newpr = input("update price to: ")
            qry = "UPDATE records SET price = %s WHERE item = %s"
            val = (newpr, it)
            cr.execute(qry, val)
            db.commit()     
    if(choice == 0):
        exit(0)
    return

def searchItem():    
    choice = int(input("Enter 1 to continue or 0 to exit: "))
    if(choice == 1):
        it = input("enter the item you want to search: ")
        qry = "SELECT * FROM records WHERE item = %s"
        val = (it ,)
        cr.execute(qry, val)
        res = cr.fetchall()
        for x in res:
            print(x)
        if(cr.rowcount == 0):
            print("we do not have that item")
    if(choice == 0):
        exit(0)
    return

def printReport():    
    choice = int(input("Enter 1 to continue or 0 to exit: "))
    if(choice == 1):
        print("total types of item in your shop: ")
        cr.execute("SELECT COUNT(*) FROM records")
        res = cr.fetchall()
        for x in res:
            print(x)
        allpr = input("do you want to see all records?(y/n): ")
        if(allpr == 'y'):
            cr.execute("SELECT * FROM records")
            res = cr.fetchall()
            for x in res:
                print(x)
    if(choice == 0):
        exit(0)
    return

def main():
    
    while(True):

        choice = mainMenu()
    
        if(choice == 1):
            addItem()
            
        elif(choice == 2):
            removeItem()
            
        elif(choice == 3):
            updateInventory()
            
        elif(choice == 4):
            searchItem()
            
        elif(choice == 5):
            printReport()
            
        elif(choice == 0):
            exit(0)
        
        else:
            print("!!!INVALID INPUT!!!")
            print("Please Try Again")
            continue
    
main()
