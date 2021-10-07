package com.example;

import java.util.Scanner;

import com.example.dao.AdminDaoImpl;
import com.example.dao.DBConnection;
import com.example.dao.EmployeeDaoImpl;
import com.example.dao.PersonDaoImpl;
import com.example.dao.UserDaoImpl;
import com.example.model.Person;
import com.example.model.User;
import com.example.service.UserService;

public class MainDriver {

	static String curUser = "";
	static String curUserType = "";
	
	static Person me = null;
	
	static DBConnection con = new DBConnection();
	
	static PersonDaoImpl pDao = new PersonDaoImpl(con);
	
	static UserDaoImpl uDao = new UserDaoImpl(con);
	static UserService uServ = new UserService(uDao);
	
	static EmployeeDaoImpl eDao = new EmployeeDaoImpl(con);
	
	static AdminDaoImpl aDao = new AdminDaoImpl(con);
	/* LOGIC FOR SCREENS
	 * Hello! Who are you?
	 * 		insert person 
	 * 			check if person exists
	 * 			return user id
	 * New user or returning user?
	 * 		New
	 * 			insert user with ssn
	 * 				login
	 * 		Returning
	 * 			login
	 * 		Employee
	 * 		Admin	
	 * 
	 * after login
	 * get users
	 * deposit
	 * withdraw
	 * transfer
	 * 
	 * logout
	 */
	
	public static void main(String[] args) {
		
		print("Do you need to create a person?");
		print("Please type 'y' for yes, or 'n' for no...");
		
		PersonCreator();
		StartScreen();
		
	}
	
	static void PersonCreator() {
		
		//String personYN = getInput();
		switch (getInput()) {
			case "y":
				//System.out.println("in y block");
				String fName;
				String lName;
				print("Please enter your first name...");
				fName = getInput();
				print("Please enter your last name...");
				lName = getInput();
				if (!(fName.isBlank()) && !(lName.isBlank())) {
					pDao.insert_person(fName, lName);
					int temp = pDao.getPersonsNumber(fName, lName);
					print("" + temp + " is your id number. REMEMBER IT!");
					print("...");
					me = pDao.getPersonWithNumber(temp);
				}
				else {
					print("Neither your first or last name can be blank! Please try again.");
					PersonCreator();
				}
				break;
				
			case "n" :
				//System.out.println("in n block");
				print("...");
				print("What is your id number?");
				Integer temp;
				temp = Integer.parseInt(getInput());
				if (temp > 0) {
					me = pDao.getPersonWithNumber(temp);
				}
				else {
					print("Please enter a valid number");
				}
				break;
				
			default :
				print("Please enter a valid input. -person");
				PersonCreator();
				
		}
	}
	
	static void StartScreen() {
		
		print("Hello, " + me.getFirstName() + " " + me.getLastName() +"!");
		print("Are you a returning user? \nPlease type 'y' for yes, or 'n' for no...");
		switch(getInput().toLowerCase()) {
		case "y":
			//logging in
			print("Please log in...");
			print("Type your username: ");
			curUser = getInput();
			print("Type your password: ");
			if (uDao.checkLoggedInUser(curUser, getInput())) {
				print("Welcome to your account, " + curUser + "!");
				BankMenu();
			}
			else {
				print("OH NO! Something went wrong when you tried to log in to your account \"" + curUser + "\" \n		Please try again.");
				StartScreen();
			}
			break;
			
		case "n" :
			//creating account
			print("Please create a username: ");
			String uTemp = getInput();
			print("Please create a password: ");
			String pTemp = getInput();
			print("Please enter your ID number: ");
			Integer check = Integer.parseInt(getInput());
			if (check.equals(pDao.getPersonsNumber(me.getFirstName(), me.getLastName()))) {
				print("Creating account...");
				uDao.insertUser(uTemp, pTemp, check);
				print("Please restart and log in");
			}
			else {
				print("What a terrible mistake! It looks like you have not remembered your ID number! Please contact an admin!");
				StartScreen();
			}
			break;
		
		case "employee":
			print("Hello Employee! Please log in...");
			EmployeeLogin();
			break;
			
		case "admin":
			print("Hello Admin! Please log in...");
			AdminLogin();
			break;
			
		default :
			print("Please enter a valid input.");
			StartScreen();
		}
	}
	
	static void AdminLogin() {
		
		print("Type your username: ");
		curUser = getInput();
		print("Type your password: ");
		if (uDao.checkLoggedInUser(curUser, getInput())) {
			//System.out.println("user " + curUser + " userType is " + uDao.getByUsername(curUser).getUser_type());
			if(uDao.getByUsername(curUser).getUser_type().equals("Admin")) {
				
				print("Welcome to your admin account, " + curUser);
				curUserType = uDao.getByUsername(curUser).getUser_type();
				BankMenu();
			}
			else {
				print("OH NO! Something went wrong when you tried to log in to your account \"" + curUser + "\" \n		Please try again. second");
				StartScreen();
			}
		}
		else {
			print("OH NO! Something went wrong when you tried to log in to your account \"" + curUser + "\" \n		Please try again. first");
			StartScreen();
		}
		//curUserType = "Admin";
	}
	
	static void EmployeeLogin() {
		
		print("Type your username: ");
		curUser = getInput();
		print("Type your password: ");
		if (uDao.checkLoggedInUser(curUser, getInput())) {
			//System.out.println("user " + curUser + " userType is " + uDao.getByUsername(curUser).getUser_type());
			if(uDao.getByUsername(curUser).getUser_type().equals("Employee")) {
				
				print("Welcome to your employee account, " + curUser);
				curUserType = uDao.getByUsername(curUser).getUser_type();
				BankMenu();
			}
			else {
				print("OH NO! Something went wrong when you tried to log in to your account \"" + curUser + "\" \n		Please try again. ..");
				StartScreen();
			}
		}
		else {
			print("OH NO! Something went wrong when you tried to log in to your account \"" + curUser + "\" \n		Please try again. .");
			StartScreen();
		}
		//curUserType = "Employee";
	}
	
	static void BankMenu() {
		//I know this is some terrible recursion, but I didn't realize what I was doing wrong until I had too much implemented. 
		//If I were to do it again, I would do it differently.
		print("-------------------------");
		curUserType = uDao.getByUsername(curUser).getUser_type();
		//System.out.println("curUser is " + curUser);
		switch (curUserType) {
		
		case "Unapproved":
			print("You are an unapproved user!");
			print("Please contact an employee or admin about approving your account.");
			print("What would you like to do?");
			print("1. Go look at the fish.");
			print("2. Sit around in the waiting area.");
			print("3. Rob the bank.");
			print("4. Log out.");
			switch(getInput()) {
			case "1":
				print("The fish are pretty cool.");
				BankMenu();
				break;
			case "2":
				print("The chairs are uncomfortable.");
				BankMenu();
				break;
			case "3":
				print("You walk up to the teller and try and rob the bank. They have alerted the authorities.");
				logout();
				break;
			case "4":
				logout();
				break;
				
			default :
				print("That is not a valid input.");
				BankMenu();
				break;
			}
			break;
			
		case "Customer":
			print("What would you like to do?");
			print("1. Go look at the fish.");
			print("2. Sit around in the waiting area.");
			print("3. Rob the bank.");
			print("4. Check balance.");
			print("5. Make a withdraw.");
			print("6. Make a deposit.");
			print("7. Make a transfer.");
			print("8. Log out.");
			switch(getInput()) {
			case "1":
				print("The fish are pretty cool.");
				BankMenu();
				break;
			case "2":
				print("The chairs are uncomfortable.");
				BankMenu();
				break;
			case "3":
				print("You walk up to the teller and try and rob the bank. They have alerted the authorities.");
				logout();
				break;
				//additional function
			case "4":
				print("");
				//check balance
				print("Your current balance is $"+ uDao.balance(curUser));
				BankMenu();
				break;
			case "5":
				print("");
				//withdraw
				print("Enter how much would you like to withdraw...");
				double temp5 = Double.parseDouble(getInput());
				
				if (uDao.withdraw(curUser, temp5)) {
					//print("Withdrawal successful!");
					BankMenu();
				}
				else {
					print("There was a problem with the withdrawl.");
					BankMenu();
				}
				break;
			case "6":
				//deposit
				print("Enter how much would you like to deposit...");
				double temp6 = Double.parseDouble(getInput());
				
				if (uDao.deposit(curUser, temp6)) {
					//print("Deposit successful!");
					BankMenu();
				}
				else {
					print("There was a problem with the deposit.");
					BankMenu();
				}
				break;
			case "7":
				//transfer
				print("Enter how much would you like to transfer...");
				double temp7 = Double.parseDouble(getInput());
				
				print("Enter the username of the account to transfer to...");
				String sTemp = getInput();
				
				if (uDao.transfer(curUser, temp7, sTemp)) {
					print("Transfer successful!");
					BankMenu();
				}
				else {
					print("There was a problem with the transfer.");
					BankMenu();
				}
				break;
				
			case "8":
				logout();
				break;
				
			default :
				print("That is not a valid input.");
				BankMenu();
				break;
			}
			break;
			
		case "Employee":
			print("What would you like to do?");
			print("1. Get unapproved users.");
			print("2. Approve a user.");
			print("3. Get user info.");
			print("4. Log out.");
			switch(getInput()) {
			case "1": 
				//get unapproved users
				for (User u : eDao.getUnapprovedUsers()) {
					print("Username: " + u.getUsername().toString() + " has assets of $" + u.getBalance() + " and is a user of type " + u.getUser_type().toString() + "");
				}
				BankMenu();
				break;
				
			case "2": 
				//approve user
				print("Enter the username to approve...");
				eDao.approveUser(getInput());
				BankMenu();
				break;
				
			case "3": 
				//get user info
				print("Enter the username to get info on...");
				User u1 = eDao.getUserInfo(getInput());
				print("Username: " + u1.getUsername() + 
						" and has assets $" + u1.getBalance() + 
						" and is of type " + u1.getUser_type());
				BankMenu();
				break;
				
			case "4": 
				//logout
				logout();
				break;
			}
			break;
			
		case "Admin":
			
			print("What would you like to do?");
			print("1. Get unapproved users.");
			print("2. Approve a user.");
			print("3. Get user info.");
			print("4. Get all users info.");
			print("5. Get sensative user info.");
			print("6. Get all sensative users info.");
			print("7. Promote a user.");
			print("8. Update a user.");
			print("9. Delete a user.");
			print("0. Log out.");
			
			switch(getInput()) {
			case "1": 
				//get unapproved users
				for (User u : eDao.getUnapprovedUsers()) {
					print("Username: " + u.getUsername().toString() + 
							" has assets of $" + u.getBalance() + 
							" and is a user of type " + u.getUser_type().toString());
				}
				BankMenu();
				break;
				
			case "2": 
				//approve user
				print("Enter the username to approve...");
				aDao.approveUser(getInput());
				BankMenu();
				break;
				
			case "3": 
				//get user info
				print("Enter a username to view information on...");
				User u1 = eDao.getUserInfo(getInput());
				print("Username: " + u1.getUsername() + 
						" and has assets $" + u1.getBalance() + 
						" and is of type " + u1.getUser_type());
				BankMenu();
				break;
				
			case "4": 
				//get all users info
				print("Getting all user info...");
				for (User u : aDao.getAllUserInfo()) {
					print("Username: " + u.getUsername() + 
							" and has assets $" + u.getBalance() + 
							" and is of type " + u.getUser_type());
				}
				BankMenu();
				break;
				
			case "5": 
				//get sensative user info
				print("Enter a username to get information from...");
				User us1 = aDao.getThisUserSensativeInfo(getInput());
				print("Username: " + us1.getUsername() + 
						" with password " + us1.getPassword() + 
						" has assets $" + us1.getBalance() + 
						", name is " + us1.getPerson_name() + 
						" and id number is " + us1.getSsn());
				BankMenu();
				break;
				
			case "6": 
				//get all sensative users info
				print("Getting all information...");
				for (User us : aDao.getSensativeUserInfo()) {
					print("Username: " + us.getUsername() + 
							" with password " + us.getPassword() + 
							" has assets $" + us.getBalance() + 
							", name is " + us.getPerson_name() + 
							" and id number is " + us.getSsn());
				}
				BankMenu();
				break;
				
			case "7": 
				//promote user
				print("Enter a username to promote to Employee");
				aDao.promoteUser(getInput());
				BankMenu();
				break;
				
			case "8": 
				//update user
				print("Enter a username to update...");
				String temps1 = getInput();
				print("Enter the new user type... (Types are: Unapproved, Customer, Employee, Admin)");
				aDao.updateUser(temps1, getInput());
				BankMenu();
				break;
			
			case "9": 
				//delete user
				print("Enter the username to delete...");
				aDao.deleteUser(getInput());
				BankMenu();
				break;
				
			case "0": 
				//get user info
				logout();
				break;
			}
			break;
			
		default :
			break;
		}
	}
	
	static void logout() {
		print("Logging out...");
		print("Goodbye.");
		System.exit(1);
	}
	
	public static String getInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	static void print(String s) {
		System.out.println(">" + s);
	}
}