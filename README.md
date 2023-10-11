# Library Management System

This is a Library Management System project that aims to streamline the management of books, students, and library transactions.

## Table of Contents

1. [Introduction](#introduction)
2. [Objective](#objective)
3. [Features](#features)
4. [Modules Overview](#modules-overview)
5. [Getting Started](#getting-started)
6. [Installation](#installation)
7. [Usage](#usage)
8. [Contributing](#contributing)
9. [License](#license)

## Introduction

The Library Management System deals with the management of library records, including books, student information, book issuance, and returns. It replaces the traditional manual system with a computerized system to improve efficiency and accuracy.

## Objective

The primary objective of this system is to:

- Maintain data about books in the library.
- Save time by eliminating data re-entry.
- Arrange data logically for easy maintenance.
- Remove duplicate data.
- Record book issuance, return, and student fines.
- Provide information about book availability.
- Display due dates for issued books.
- Store data online for remote access.

## Features

- Add, update, and delete student details.
- Manage author, publisher, and book information.
- Record book issuance, return, and penalties.
- Display book availability and due dates.
- User-friendly interface with error messages.

## Modules Overview

### Administrative Login

- Grant access to administrative activity.
- Check and match username and password.
- Requires User Name and Password.

### Student Management

- Manage student-related data.
- Add, update, and delete student details.
- Disable Update and Delete buttons initially.
- Password is 'student name123' by default if empty.
- Requires Name, Password, Email, Address, City, and Phone No.

### Publisher

- Manage Publisher related data.
- Add, update, and delete Publisher details.
- Requires Name, Address, and Phone No.

### Author

- Manage Author related data.
- Add, update, and delete Author details.
- Requires Name, Address, and Phone No.

### Category

- Manage Category related data.
- Add, update, and delete Category details.
- Requires Name and Status.

### Books

- Manage Book related data.
- Add, update, and delete Book details.
- Requires Book Name, Author, Publisher, Category, Content, No. of Pages, Edition, and Quantity.

### Issue Books

- Manage Issued Books related data.
- Add, update, and delete Issued Book details.
- Requires Member ID, Book ID, Issue Date, and Return Date.

### Return Books

- Manage Returned Books related data.
- Add, update, and delete Returned Book details.
- Calculate late penalties (10 Rs/day).
- Requires Member ID and Book Selection.

### Log Out

- Log out from the system.

### Student Login

- Grant access to Student activity.
- Check and match username and password.

### View Books

- View a list of all available books.

### Password Change

- Change student password using the old password.
- Requires Old Password and New Password.

## Getting Started

Follow the steps below to get started with this project:

### Installation

1. Clone the repository: `git clone https://github.com/Quantumo0o/Library.git`
2. Install the required dependencies.
3. Set up your database and configure connection settings.

### Usage

1. Run the application.
2. Access the system through the provided URLs.
3. Use the administrative and student login interfaces to manage the library.

## Contributing

We welcome contributions from the open-source community. If you'd like to contribute to this project, please follow our [contribution guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE). See the [LICENSE](LICENSE) file for more details.
