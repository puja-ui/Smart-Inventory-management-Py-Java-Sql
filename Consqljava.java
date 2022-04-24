import java.sql.*;
import java.util.*;
// java Database Connectivity
/*
 * 1. import
 * 2. load and register the driver com.mysql.jdbc.Driver
 * 3. Create Connection, create object of Connection interface
 * 4. Create Statement
 * 5. execute the query [DDL, DML, DQL]###
 * 6. process the result
 * 7. close connection
 * 
 * ###
 * DDL = Data Definition Language (change the structure of db)
 * 		like - Create, Alter, Drop, truncate, comment, rename
 * DML = Data Manipulation Language (change data)
 * 		like - insert, update, delete
 * 		ResultSet res = st.executeUpdate(qry) ... returns rows effected
 * DQL = Data Query Language (get data)
 * 		like - select
 * 		ResultSet res = st.executeQuery(qry)
 * DCL = Data Control Language (deals with rights and permissions and other controls)
 * 		like - grant, revoke
 * TCL = Transaction Control Language
 * 		like - commit, rollback, savepoint, set transaction
 * */
public class Consqljava {
	public static Scanner input = new Scanner(System.in);
	
	public static void showall(Connection con) throws SQLException {
		String qry = "SELECT * FROM records";
		Statement st = con.createStatement();		//getConnection is the method which gives the instance 
		ResultSet res = st.executeQuery(qry);		//for the "Connection" interface, we need this method because we cannot
													//create object of a interface directly
		while(res.next()) {
			System.out.print("Id: "+ res.getInt("id"));
			System.out.print("; Item: "+ res.getString("item"));
			System.out.print("; quantity: "+ res.getInt("quantity"));
			System.out.println("; price: "+ res.getInt("price"));
		}
		st.close();
	}
	
	public static void insert(Connection con) throws SQLException {
		String qry = "INSERT INTO records values (default, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(qry);
		System.out.print("Enter Item: ");
		input.nextLine();
		String item = input.nextLine();
		st.setString(1, item);
		System.out.print("Enter Quantity: ");
		int qty = input.nextInt();
		st.setInt(2, qty);
		System.out.print("Enter Price: ");
		int pr = input.nextInt();
		st.setInt(3, pr);
		int rowcount = st.executeUpdate();
		System.out.println(rowcount + " item(s) inserted");
		st.close();
	}
	
	public static void delete(Connection con) throws SQLException {
		String qry = "DELETE FROM records WHERE item = ?";
		PreparedStatement st = con.prepareStatement(qry);
		System.out.print("Enter the item you want to delete: ");
		input.nextLine();
		String itemin = input.nextLine();
		st.setString(1, itemin);
		int rowcount = st.executeUpdate();
		System.out.println(rowcount + " item(s) deleted");
		st.close();
	}
	
	public static void update(Connection con) throws SQLException {
		System.out.print("Enter the item You want to update: ");
		String qry;
		input.nextLine();
		String itemin = input.nextLine();
		qry = "SELECT * FROM records WHERE item = ?";
		PreparedStatement st = con.prepareStatement(qry);
		st.setString(1, itemin);
		ResultSet res = st.executeQuery();
		if(res.next() == false) {
			System.out.println("Item not available");
			return;
		}
		System.out.println("What do you want to update?");
		System.out.println("1. quantity");
		System.out.println("2. price");
		System.out.print("Enter your choice: ");
		int c = input.nextInt();
		if(c==1) {
			qry = "UPDATE records SET quantity = ? WHERE item = ?";
			System.out.print("Enter new quantity: ");
			int qty = input.nextInt();
			PreparedStatement st1 = con.prepareStatement(qry);
			st1.setInt(1, qty);
			st1.setString(2, itemin);
			int rowcount = st1.executeUpdate();
			System.out.println(rowcount + " quantity updated");
			st1.close();
		}
		else if(c==2) {
			qry = "UPDATE records SET price = ? WHERE item = ?";
			System.out.print("Enter new price: ");
			int pr = input.nextInt();
			PreparedStatement st1 = con.prepareStatement(qry);
			st1.setInt(1, pr);
			st1.setString(2, itemin);
			int rowcount = st1.executeUpdate();
			System.out.println(rowcount + " price updated");
			st1.close();
		}
		else System.out.println("Invalied choice");
	}
	
	public static void report(Connection con) throws SQLException {
		String qry = "SELECT COUNT(id) FROM records";
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery(qry);
		res.next();
		System.out.println("There are " + res.getInt(1)+ " items in yout inventory");
		st.close();
	}
	
	public static void search(Connection con) throws SQLException {
		String qry = "SELECT * FROM records WHERE item = ?";
		System.out.print("enter the item you want to search: ");
		input.nextLine();
		String itemin = input.nextLine();
		PreparedStatement st = con.prepareStatement(qry,ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //ResultSet will be scrollable, we can do next() and previous() both
		st.setString(1, itemin);
		ResultSet res = st.executeQuery();
		if(res.next() == false) {
			System.out.println("Item not available");
			return;
		}
		res.previous();
		while(res.next()) {
			System.out.println("Item = " + res.getString("item")+"; Quantity = "
								+ res.getInt("quantity") + "; Price = " + res.getInt("price"));
		}
		st.close();
	}
	
	public static void main(String args[]) throws Exception {
		String url = "jdbc:mysql://localhost:3306/inventory"; //jdbc:mysql://localhost:PortNumber/DBname
		String usname = "root";
		String pw = "Mysql";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, usname, pw);
		int go_on = 1;
		while(go_on==1) {
			System.out.println("Welcome to the Inventory Management Tool");
			System.out.println("1. Show inventory.");
			System.out.println("2. insert into inventory.");
			System.out.println("3. delete from inventory.");
			System.out.println("4. update records in inventory.");
			System.out.println("5. report of inventory.");
			System.out.println("6. search into inventory");
			System.out.print("Enter your choice: ");
			int ch = input.nextInt();
			if(ch == 1) {
				showall(con);
			}
			else if(ch == 2) {
				insert(con);
			}
			else if(ch == 3) {
				delete(con);
			}
			else if(ch == 4) {
				update(con);
			}
			else if(ch == 5) {
				report(con);
			}
			else if(ch == 6) {
				search(con);
			}
			else {
				System.out.println("Invalied choice");
			}
			System.out.println("Do you want to continue(1/0)? ");
			go_on = input.nextInt();
		}
		con.close();
	}
}
