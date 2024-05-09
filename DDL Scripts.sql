CREATE DATABASE `ordermanagementsystem` ;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `return_score` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ;
CREATE TABLE `returnorders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL,
  `return_order_condition` varchar(10) DEFAULT NULL,
  `refundmode` varchar(20) DEFAULT NULL,
  `return_order_status` varchar(255) DEFAULT 'Pending',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`)
) ;

CREATE TABLE `fraudulentreturnorders` (
  `OrderID` int NOT NULL,
  `CustomerID` varchar(255) DEFAULT NULL,
  `ProductID` varchar(255) DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `ProductCategory` varchar(50) DEFAULT NULL,
  `QuantityReturned` int DEFAULT NULL,
  `PurchaseAmount` decimal(10,2) DEFAULT NULL,
  `ReasonForReturn` varchar(50) DEFAULT NULL,
  `CustomerAccountAge` int DEFAULT NULL,
  `PreviousReturns` int DEFAULT NULL,
  `PreviousFraudReports` int DEFAULT NULL,
  `DeliveryType` varchar(50) DEFAULT NULL,
  `ReturnCondition` varchar(50) DEFAULT NULL,
  `RefundIssued` decimal(10,2) DEFAULT NULL,
  `IsFraud` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ;