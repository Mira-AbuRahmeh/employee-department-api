

# 📘 API Documentation

### 🧾 Table of Contents

1. [Base URL](#base-url)
2. [Authentication](#authentication)
3. [Employee APIs](#employee-apis)
4. [Department APIs](#department-apis)

---

Base URL

The base URL varies depending on the environment:

| Environment | Base URL Example                |
| ----------- | ------------------------------- |
| Development | `http://localhost:8080/company` |
| Production  | `http://localhost:8081/company` |

---

### 🔐 Authentication

Most endpoints require authentication.

* **Type**: Basic Auth
* **For ADMIN role**

  * Username: `admin`
  * Password: `1234`
* **For USER Role**

  * Username: `user`
  * Password: `1234`
* Use tools like **Postman** to set Basic Auth in the **Authorization tab**.

---

### 👤 Employee APIs

#### 📍 POST `/company/employees/add`

**Create a new employee**

* **Authentication**: Required with ADMIN role
* **Request Header**:

  * `Content-Type: application/json`
* **Constraint** :

  * name and email should not be blank
  * email should be well-formatd and unique per employee
  * role should be either ADMIN or USER
  * All fields are required except department\_id.
* **Request Body**:

```json
{
  "name": "John Doe",
  "email": "joh.doe@example.com",
  "salary": 5000,
  "role":"USER",
  "department_id":2
}
```

* **Responses**:

  * `201 Created`: Employee created successfully.

    * **Response Body**:

    ```json
     {
    "statusCode": "201",
    "statusMsg": "Employee added successfully"
     }
    ```
  * `400 Bad Request`: Validation failed (e.g., missing required fields or invalid data format).

    * **Response Body**:

    ```json
    {
    "status": "BAD_REQUEST",
    "message": "Request validation failed",
    "timeStamp": "2025-05-12",
    "errors": [
        "Name must be at least 3 characters long",
        "Name must not be blank"
    ]
    }
    ```
  * `409 Conflict`: Email already exists in the system (duplicate email).

    * **Response Body**:

    ```json
    {
    "status": "CONFLICT",
    "message": "A database error occurred. Please check your input.",
    "timeStamp": "2025-05-12",
    "errors": [
        "Database Integrity Violation"
    ]
    }
    ```

### 📍 `GET /company/employees/viewAll`

**Description:** Retrieve a list of all employees.

* **Authentication:** Required
* **Response:**

  * `200 OK`
  * Returns: JSON array of employee objects
  * Example:

    ```json
    [
      {
        "employeeId": 1,
        "name": "Alice Johnson",
        "role": "ADMIN",
        "email": "alice@company.com",
        "salary": 85000.00,
        "department": {
          "name": "HR"
        }
      },
      {
        "employeeId": 2,
        "name": "Bob Smith",
        "role": "USER",
        "email": "bob@company.com",
        "salary": 65000.00,
        "department": {
          "name": "Engineering"
        }
      }
    ]
    ```

---

### 📍 `GET /company/employees/view/{id}`

**Description:** Get employee details by their ID.

* **Authentication:** Required
* **Path Parameters:**

  * `id` (integer) – Employee ID
* **Response:**

  * `200 OK`
  * Returns: JSON object of the employee
  * Example:

    ```json
    {
        "employeeId": 2,
        "name": "Bob Smith",
        "role": "USER",
        "email": "bob@company.com",
        "salary": 65000.00,
        "department": {
          "name": "Engineering"
        }
      }
    ```

    * `404 Not Found`  : No employee with the specified id
    * **Response body:**

    ```json
    {
      
      "status": "NOT FOUND",
      "message": "Employee with id 90 not found",
      "timeStamp": "2025-05-13",
      "errors": [
          "Entity not found"
      ]
    }
    ```

---

### 📍 `DELETE /company/employees/delete/{id}`

**Description:** Delete an employee by their ID.

* **Authentication:** Required with ADMIN role
* **Path Parameters:**

  * `id` (integer) – Employee ID
* **Response:**

  * `200 OK`
  * Returns:

    ```json
    {
      "statusCode": "200",
      "statusMsg": "Employee deleted successfully"
    }
    ```

    * `404 Not Found`  : No employee with the specified id

      * **Response body:**

      ```json
      {
        
        "status": "NOT FOUND",
        "message": "Employee with id 90 not found",
        "timeStamp": "2025-05-13",
        "errors": [
            "Entity not found"
        ]
      }
      ```

---

### 📍 `PATCH /company/employees/update/{id}`

**Description:** Update employee information.

* **Authentication:** Required with ADMIN role

* **Path Parameters:**

  * `id` (integer) – Employee ID

* **Request Body:** JSON object of updated fields

  ```json
  {
  "name":"Ahmed"
  }
  ```

* **Headers:**

  * `Content-Type: application/json`

* **Response:**

  * `200 OK`
  * Returns:

    ```json
    {
      "statusCode": "200",
      "statusMsg": "Employee info updated successfully"
    }
    ```

    * `400 Bad Request`: Validation failed(e.g. pass an empty name)

* **Response Body:**

```json
 {
   "status": "BAD_REQUEST",
   "message": "Request validation failed",
   "timeStamp": "2025-05-13",
   "errors": [
       "Name must not be blank"
   ]
}
```

* `409 Conflict`: duplicate email value.

* **Response Body:**

```json
 {
   "status": "CONFLICT",
   "message": "A database error occurred. Please check your input.",
   "timeStamp": "2025-05-13",
   "errors": [
       "Database Integrity Violation"
   ]
}
```

---

### 📍 `GET /company/employees/search/{keyword}`

**Description:** Search for employees by keyword in name or email.

* **Authentication:** Required
* **Path Parameters:**

  * `keyword` (string) – Name, email
* **Response:**

  * `200 OK`
  * Returns: JSON array of matched employees
  * Example:
    GET //company/employees/search/Ah

    ```json
    [
       {
        "employeeId": 5,
        "name": "Ahmed",
        "role": "USER",
        "email": "ehmed@company.com",
        "salary": 71000.00,
        "department": {
            "name": "Engineering"
        }
    }
    ]
    ```

---

### 📍 `GET /company/employees/filter`

**Description:** Filter employees using query parameters.

* **Authentication:** Required
* **Query Parameters:** *(All optional)*

  * `depID` (integer): Filter by department ID
  * `minSalary` (decimal): Minimum salary
  * `sortBy` (string): Field to sort by (e.g., `salary`, `name`)
  * `order` (string): `asc` or `desc`
* **Response:**

  * `200 OK`
  * Returns: JSON array of filtered/sorted employees
  * Example:
    GET /company/employees/filter?minSalary=80000\&sortBy=name\&order=desc

    ```json
    [
     
    {
        "employeeId": 9,
        "name": "Isla Brown",
        "role": "USER",
        "email": "isla@company.com",
        "salary": 80000.00,
        "department": {
            "name": "Marketing"
        }
    },
    {
        "employeeId": 8,
        "name": "Henry Zhang",
        "role": "USER",
        "email": "henry@company.com",
        "salary": 90000.00,
        "department": {
            "name": "Finance"
        }
    },
    {
        "employeeId": 1,
        "name": "Alice Johnson",
        "role": "USER",
        "email": "alice@company.com",
        "salary": 85000.00,
        "department": {
            "name": "HR"
        }
    }
    ]
    ```

    * `400 Bad Request `: Type Mismatch (e.g., passing a string value to minSalary) or Property not found (e.g., passing a not valid property to sortBy)

      * Example: /company/employees/filter?minSalary=a800\&sortBy=name\&order=desc

      ```json
      {
        "status": "BAD_REQUEST",
        "message": "Parameter minSalary must be of type BigDecimal",
        "timeStamp": "2025-05-13",
        "errors": [
            "Method parameter minSalary failed to convert value type"
        ]
      }

      ```

---

## 🏢 Department APIs

### 📍 `POST /company/departments/add`

**Description:** Add a new department.

* **Authentication:** Required with ADMIN role
* **Headers:**

  * `Content-Type: application/json`
* **Request Body:**

  ```json
  {
    "name": "Engineering",
    "email":"eng@org.com"
  }
  ```
* **Response:**

  * `201 Created`: Employee created successfully.

    * **Response Body**:

  ```json
  {
    "statusCode": "201",
    "statusMsg": "Department added successfully"
  }
  ```

  ## 📍 `GET /company/departments/viewAll`

**Description:** Retrieve a list of all departments.

* **Authentication:** Required

* **Response:**

  * `200 OK`

* **Response Body :**

  ```json
  [
    {
        "name": "HR",
        "email": "hr@company.com",
        "head": {
            "employeeId": 1,
            "name": "Alice Johnson",
            "email": "alice@company.com"
        }
    },
    {
        "name": "Engineering",
        "email": "eng@company.com",
        "head": {
            "employeeId": 2,
            "name": "Bob Smith",
            "email": "bob@company.com"
        }
    },
  ]
  ```

---

## 📍 `GET /company/departments/view/{id}`

**Description:** Get a specific department by its ID.

* **Authentication:** Required

* **Path Parameters:**

  * `id` (integer): ID of the department to retrieve

* **Response:**

  * `200 OK`

* **Response body:**

  ```json
  {
    "id": 1,
    "name": "Engineering"
  }

  ```

  * `404 Not Found`  : No department with the specified id
  * **Response body:**

  ```json
  {
    
    "status": "NOT FOUND",
    "message": "Department with id 90 not found",
    "timeStamp": "2025-05-13",
    "errors": [
        "Entity not found"
    ]
  }
  ```

  ---

 ## 📍 `DELETE /company/departments/delete/{id}`
  **Description:** Delete a department by ID.

* **Authentication:** Required

* **Path Parameters:**

  * `id` (integer): ID of the department to delete

* **Response:**

  * `200 OK`

* **Body:**

  ```json
  {
    "statusCode": "200",
    "statusMsg": "Department deleted successfully"
  }
  ```

  * `404 Not Found`  : No department with the specified id
  * **Response body:**

  ```json
  {
    
    "status": "NOT FOUND",
    "message": "Department with id 90 not found",
    "timeStamp": "2025-05-13",
    "errors": [
        "Entity not found"
    ]
  }
  ```

---

## 📍 `PATCH /company/departments/updateName/{id}`

**Description:** Update the name of a specific department.

* **Authentication:** Required

* **Path Parameters:**

  * `id` (integer): ID of the department to update

* **Headers:**

  * `Content-Type: application/json`

* **Request Body:**

  ```json
  {
    "name": "New Name"
  }
  ```

  * **Response:**
  * `200 OK`

* **Response Body:**

  ```json
  {
    "statusCode": "200",
    "statusMsg": "Department name updated successfully"
  }
  ```

  * `400 Bad Request`: Validation failed(e.g. pass an empty name)

* **Response Body:**

```json
 {
   "status": "BAD_REQUEST",
   "message": "Request validation failed",
   "timeStamp": "2025-05-13",
   "errors": [
       "Name must not be blank"
   ]
}
```

* `409 Conflict`: Name already exists in the system (duplicate name).

* **Response Body:**

```json
 {
   "status": "CONFLICT",
   "message": "A database error occurred. Please check your input.",
   "timeStamp": "2025-05-13",
   "errors": [
       "Database Integrity Violation"
   ]
}
```

---

## How to Run

1. Clone the repository or download the source code files.
2. Open the project in your preferred Java IDE.
3. Run the code using mvn spring-boot\:run -Dspring-boot.run.profiles= command to define the active profile (dev,prod)

---

## Authentication credential for testing purpose

* **For admin role:**

  * `username`: admin
  * `password`: 1234

* **For user role:**

  * `username`: user
  * `password`: 1234
