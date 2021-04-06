# Revature-Project-0
My Project 0 for Revature

Class Descriptions:
* Main Driver - Contains the only main method that instantiates a BankCore object 'bank'. The data for the system is loaded from .txt files into the bank, then the system engine is started with that bank object. When the engine is done running the data is saved back to .txt files and the system concludes executuion.
* BankCore - Serves as the database for this project. Holds a HashSet for Customers, Employees, and Accounts.
* User - Parent class of Customer and Employee. Holds basic user information.
* Customer - Child class of User. A customer is associated with and account through the account's owners field.
* Employee - Child class of User. Holds employee specific information.
* Account - An object for storing accounts in the bank system. Accounts and customers are not directly linked.
* UserInterface - Handles all system input and output, and holds three engines.
  >- startEngine() is the first and last screen a user will see. It gives the user to create or sign into an account. Calls the corresponding engine based on the user type, customer or employee.
  >- customerEngine() is the customers source of access to thier accounts.
  >- employeeEngine() is the employees tool to perform all tasks necessary to thier role. Admin employees have access to more options and basic empmloyees.
* Service - Handles operations between the user interface (customers, and employees) and the bank core (database). *WiP*


Noteable Known Weaknesses: 
  - The Service class is weaker than intended. As I started to get crunched for time I spent less time on encapsulation access. As I continue to iterate over the project I will adjust the sensitive data fields access modifiers and implement more corresponding methods in this class.
  - The Customer class was intended to hold more customer only data fields but to focus on implementing the Project requirements I have not yet come up with any. Flag was the only customer specific data field but I decided to implement password reset through the flag field so I moved this field to the user class so that employees would also have access.
  - JUnit testing is not yet implemented.
  - Loggging is not yet implemented.
