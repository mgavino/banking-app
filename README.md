# Banking REST app

##DDBB
We need to run a PosgreSQL on localhost:5432 with a created database called _banking_. User and password have to be postgres/postgres

##REST services

###USER

#### Signup
```
POST /user
{
  "email": "user@mgavino.com",
  "password": "password"
}
```

###BANK

#### Create bank account
```
POST /bank
{
  "user": userId,
  "name": "Account Name"
}
```

#### Get bank accounts by user
```
GET /bank
{
  "user: userId
}

#### Get bank account by id
```
GET /bank/{bankAccountId}
```

#### Get bank movements by id and date range
```
GET /bank/{bankAccountId}/movements
{
  "from": "2019-02-01",
  "to": "2019-02-10"
}
```

#### Deposit money into bank account
```
POST /bank/{bankAccountId}/deposit
{
  "concept": "Cash deposit",
  "amount": "50.00"
}
```

####
```
POST /bank/{bankAccountId}/withdraw
{
  "concept": "Payment",
  "amount": "50.00"
}
```
