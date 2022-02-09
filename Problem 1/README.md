# sdet_testarch_examples

## Problem Statement 1:

Automate the validation of a book e-commerce platform. A database contains book purchases information. The organization has decided to store Asian users books purchase transactions data in a seperate database by running a daily job. The new database data is exposed through an API. Till now manual tester validated this system by reading Asian records from original database, creates json files, put these json as payload to API and verify the same data present in new database.

### Approach

1. Create a database, table

```bash
docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:latest
```

```sql
CREATE TABLE Business.CustomerInfo (
	CourseName varchar(100) NULL,
	PurchaseDate DATE NULL,
	Amount DOUBLE NULL,
	Location varchar(100) NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

```
Insert data info CustomerInfo table

```sql
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Eudyptula minor', CURRENT_DATE(), '3961.34', 'Antarctica');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Aegypius occipitalis', CURRENT_DATE(), '3081.81', 'Australia');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Acrantophis madagascariensis', CURRENT_DATE(), '881.38', 'Asia');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Macropus rufogriseus', CURRENT_DATE(), '1384.70', 'Africa');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Coluber constrictor', CURRENT_DATE(), '4983.69', 'South America');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Genetta genetta', CURRENT_DATE(), '123.75', 'Asia');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Pseudoleistes virescens', CURRENT_DATE(), '3846.79', 'Asia');
insert into MOCK_DATA (CourseName, PurchaseDate, Amount, Location) values ('Libellula quadrimaculata', CURRENT_DATE(), '3749.52', 'Africa');
```