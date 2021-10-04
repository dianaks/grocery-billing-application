# grocery-billing-application
Mini project SE-EP Java training

To run App on terminal
```
$ mvn compile
$ mvn exec:java -Dexec.mainClass=com.tokopedia.GroceryBillingApplication.App
```

To run Test on terminal
```
$ mvn test
```

App Test Cases
- Non member with single item transaction
- Non member with multiple item transaction
- Member with flat discount transaction (value >= $100)
- Member with percentage discount transaction (value < $100)
- New Member with flat discount transaction (value >= $100)
- New Member with percentage discount transaction (value < $100)
