# grocery-billing-application
Mini project SE-EP Java training

Requirement: https://docs.google.com/document/d/19c_HJiNcklFqtq9he4DvtMqP8VBoq8AS50DdtFNa7X0/edit?pli=1

## How to run

To run App on terminal
```
$ mvn clean install
$ mvn compile
$ mvn exec:java -Dexec.mainClass=com.tokopedia.GroceryBillingApplication.App
```

To run Test on terminal
```
$ mvn clean install
$ mvn test
```

## App Test Cases
- Non member, single item, and payment cash
- Non member, multiple item, and payment cash
- Non member, single item, and payment credit card
- Non member, multiple item, and payment credit card
- Member, single item, flat discount, and payment cash
- Member, single item, percentage discount, and payment cash
- Member, single item, flat discount, and payment credit card
- Member, single item, percentage discount, and payment credit card
- Member, multiple item, flat discount, and payment cash
- Member, multiple item, percentage discount, and payment cash
- Member, multiple item, flat discount, and payment credit card
- Member, multiple item, percentage discount, and payment credit card
- New Member, single item, flat discount, and payment cash
- New Member, single item, percentage discount, and payment cash
- New Member, single item, flat discount, and payment credit card
- New Member, single item, percentage discount, and payment credit card
- New Member, multiple item, flat discount, and payment cash
- New Member, multiple item, percentage discount, and payment cash
- New Member, multiple item, flat discount, and payment credit card
- New Member, multiple item, percentage discount, and payment credit card

## Input & Output Example:
```
===========================
GROCERY BILLING APPLICATION
by: Elisabeth Diana
===========================
ITEM QUANTITY 

Carrot:
1
------> $2.0
Onion:
1
------> $3.0
Cabbage:
1
------> $4.0
Broccoli:
1
------> $4.0
Pumpkin:
1
------> $5.0
Sweet potato:
2
------> $4.4
===========================
MEMBER ID: 
7
---------------------------
Member not found!
===========================
Register as a member and enjoy 5% discount every transaction.
Registration fee $100 (y/N):
y
===========================
name:
buyer
phone:
085271721828
---------------------------
Congrats, you're a member now!
===========================
Choose payment method (CASH/credit card):
credit card
===========================
HERE IS YOUR BILLING ... 

Original Price: $22.4
Membership Fee: $100
Discount Price: $1.12
Transaction Fee: $2.43
---------------------------
Total Price: $123.71
```
