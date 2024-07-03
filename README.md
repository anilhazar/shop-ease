# ShopEase

## Introduction

ShopEase is a comprehensive e-commerce application designed to provide seamless shopping experiences. Built using Java Spring Boot, the application supports various functionalities including user registration, product management, shopping cart handling, order processing, and more. ShopEase ensures efficient stock management and secure customer authentication, making it a robust solution for modern online shopping needs.

## Technologies Used

- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **MySQL**: Database for storing application data
- **BCrypt**: For password encryption
- **Maven**: For project management and dependency management

## Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK 8 or higher
- Maven 3.1.1 or higher
- MySQL Server

## How It Works / How It Designed to Work

Imagine a user named Anil, who has just discovered ShopEase. He decides to register an account to start shopping. With a few simple steps, Anil's personal details are securely saved, withhispassword encrypted using BCrypt to ensure maximum security.

Once logged in, Anil explores a variety of products. The product catalog is managed by the ShopEase admin, who can add, update, or soft delete products from the inventory. This means that even if a product is removed from the storefront, its data remains intact for future reference or auditing purposes.

Anil selects a few items He likes and adds them tohisshopping cart. The cart functionality is designed to handle these operations smoothly. If Anil decides to buy more of an item already inhiscart, the application updates the quantity instead of adding a duplicate entry, ensuring a user-friendly experience.

When Anil is ready to checkout, He places an order. ShopEase processes this order by confirming the availability of the products, updating stock levels, and calculating the total price. Anil can viewhisorder history and track the status ofhiscurrent orders throughhisaccount.

The backend system of ShopEase is robust, handling various tasks such as validating stock before adding a product, ensuring no redundant database queries, and managing soft deletes effectively. The system’s architecture is designed to provide a seamless experience for both customers and administrators, maintaining data integrity and performance throughout.

## Installation and Usage

### Installation

1. **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/shopease.git
    cd shopease
    ```

2. **Set up the database**
    ```sqlscript.sql
    All the Configurations you needed embed in the file
    ```

3. **Configure application properties**
    Update the `application.yml` file with your MySQL database credentials.

4. **Build the project**
    ```bash
    mvn clean install
    ```

5. **Run the application**
    ```bash
    mvn spring-boot:run
    ```

### Usage

Once the application is running, you can access the endpoints using Postman or any other API testing tool. Detailed API documentation is available via the Postman link below.

## Postman Documentation

Access the detailed API documentation and test the endpoints using [this Postman collection](https://documenter.getpostman.com/view/29648549/2sA3dxCqkK).

## Contact

For any inquiries or support, please contact:

Ali Anil Hazar  
Email: [alianilhazar@gmail.com](mailto:alianilhazar@gmail.com)

