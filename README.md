<!--<p align="center">
    <a href="https://github.com/osiristape/hotel-booking-system/edit/main/README.md">EN</a>  |   
    <a href="https://github.com/osiristape/hotel-booking-system/edit/main/README-ES.md">ES</a>  |  
    <a href="https://github.com/osiristape/hotel-booking-system/edit/main/README-CN.md">中國官話</a> |  
    <a href="https://github.com/osiristape/hotel-booking-system/edit/main/README-JP.md">日本語</a>
</p>-->


# hotel-booking-system

## Overview
This project is a **Hotel Booking System** designed as a desktop application using **JavaFX** and **NetBeans**. It facilitates room and customer management, including features like check-ins, room availability, and generating receipts.

## Features

### Core Functionalities

- **Dashboard Overview**

  - Displays the following:
    - Total Available Rooms
    - Total Check-Ins Today
    - Total Income Today

- **Room Management**

  - Add, update, delete room information.
  - Manage room availability (vacant or occupied).
  - Import and display room images.

- **Customer Management**

  - Handle guest check-ins and check-outs.
  - Display and search for customer details.
  - Receipt generation (requires JasperReports).

### Reporting

- **JasperReports Integration**
  - Generates printable receipts (requires **Jasper Studio** for compatibility).

## Prerequisites

- **Java Development Kit (JDK)** 17 or above.
- **NetBeans IDE** 12 or later.
- **MySQL Database**
  - Tables: `customer`, `roomlisting`.
- **JasperReports Studio** (for editing `.jrxml` files).

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/hotel-booking-system.git
   ```
2. Import the project into NetBeans.
3. Configure the database connection in `database.connectDb()`.
   - Default credentials:
     ```java
     jdbc:mysql://localhost/hotelbooking
     User: root
     Password: ""
     ```
4. Ensure that MySQL tables are set up as per the queries in the code.
5. Run the application via NetBeans.

## Known Issues

- **Security Measures:**

  - As this project was developed by a student, **double-check security implementations** for database queries and user input validation.
  - Potential vulnerabilities include SQL injection and insufficient error handling.

- **Buggy or Redundant Code:**

  - Some code may be repetitive or contain logic flaws.

- **JasperReports Compatibility:**

  - **NetBeans 23** does not fully support JasperReports without additional configuration.
  - Receipt generation is **non-functional** unless **Jasper Studio** is installed.

## License

This project is open-source and available under the MIT License.

