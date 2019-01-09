# Personal-loans-project

## Build and Run project:

**1. mvn clean install**

**2. In target directory "java -jar assignment-0.0.1-SNAPSHOT.jar"**

## How to use:

**1. Open "Postman", HTTP method POST , Body -> RAW -> JSON(application/json)**

http://localhost:8080/registration

and add few clients in this format:

[
{
	"name" : "Sherlock",
	"address" : [
			"London",
			"Baker str.",
			"221B"
		],
		"clientInfo": {
			"Loan" : 0,
			"Term" : 0
		}
},
{
	"name" : "Harry",
	"address" : [
			"Great Britain",
			"Hogwarts School"
		],
		"clientInfo": {
			"Loan" : 0,
			"Term" : 0
		}
}
]

**2. To add a loan, HTTP method POST. Pass name, ammount of money and term, like:**

http://localhost:8080/addLoanToAccount/Harry/200/4

You can add loans only 2 times a day from the same IP address and only from 06:00h to 00:00h, or else you get:

"We are sorry, but you exceeded your loan limit for this day! Try after 24h!"

or

"We are sorry, it's  to risky to loan you between 00:00 and 06:00, try different hours!"

**3. To see full client history, HTTP method GET and pass the name of the client:**

http://localhost:8080//getClientsHistory/Harry 








