# 👗 Style with Clothes

_Style with Clothes_ is a **clothing-only e-commerce web application** built with Java and Spring Boot. The platform offers users the ability to browse, shop, and manage clothing items categorized by gender (👨 Male / 👩 Female). It also provides robust features for both customers and admins to streamline order and inventory management.

---

## 📜 Table of Contents

- [📌 Project Description](#-project-description)
- [🛠️ Technologies Used](#-technologies-used)
- [✨ Features](#-features)
  - [🧑‍💼 Admin]
  - [🧍 User]
- [📁 Project Structure](#-project-structure)

---

## 📌 Project Description

**Style with Clothes** is a web-based e-commerce system dedicated to selling clothes online. The store is divided into two primary categories:
- 👨 Men's Wear
- 👩 Women's Wear

Users can browse collections, manage carts, place orders, and admins can manage inventory and orders effectively.

---

## 🛠️ Technologies Used

| Layer        | Technology            |
|--------------|------------------------|
| 👨‍💻 Backend   | Java, Spring Boot, Spring Security |
| 🗄️ Database  | MySQL                 |
| 🔐 Security  | Spring Security (Role-based) |

---

## ✨ Features

### 🧑‍💼 Admin

- 🔐 Admin registration and login
- ➕ Add new product
- ✏️ Update existing product details
- ❌ Delete a product
- 📦 View all orders from all users
- 🚚 Update order status (e.g., Pending ➝ Shipped ➝ Delivered)

### 🧍 User

- 📝 Register, login, and update profile
- 👀 View all available products
- 🛒 Add/remove products to/from cart
- ✅ Place an order
- ❌ Cancel an order

---

## 📁 Project Structure

StyleWithClothes/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/stylewithclothes/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ └── security/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── static/
├── pom.xml
└── README.md


