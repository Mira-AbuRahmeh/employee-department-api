CREATE TABLE IF NOT EXISTS `Department` (
  `department_Id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL UNIQUE,
  `email` varchar(150) NOT NULL UNIQUE,
  `head_id` int DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP ,
  `updated_by` varchar(50) DEFAULT NULL

);

CREATE TABLE IF NOT EXISTS `Employee` (
  `employee_Id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL UNIQUE,
  `salary`  decimal(10,2) NOT NULL,
  `department_id` int DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP ,
  `updated_by` varchar(50) DEFAULT NULL,
  FOREIGN KEY(department_id) REFERENCES Department(department_Id)
);

ALTER TABLE Department
ADD FOREIGN KEY (head_id) REFERENCES Employee(employee_Id);