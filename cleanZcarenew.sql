-- Full Database for ClenZCare

-- 1. Create database
CREATE DATABASE IF NOT EXISTS ClenZCare_db;
USE ClenZCare_db;

-- 2. Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('customer', 'service_provider', 'admin') DEFAULT 'customer',
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Service Categories Table
CREATE TABLE service_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Services Table
CREATE TABLE services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    service_name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    duration VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES service_categories(category_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 5. Service Providers Table
CREATE TABLE service_providers (
    provider_id INT PRIMARY KEY,
    experience_years INT,
    skills TEXT,
    rating DECIMAL(2,1) DEFAULT 0,
    availability BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (provider_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 6. Bookings Table
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    provider_id INT NOT NULL,
    service_id INT NOT NULL,
    booking_date DATE NOT NULL,
    booking_time TIME NOT NULL,
    address TEXT,
    status ENUM('pending', 'accepted', 'rejected', 'completed') DEFAULT 'pending',
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status ENUM('pending', 'paid', 'failed') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES service_providers(provider_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 7. Payments Table
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    invoice_number VARCHAR(100) UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50) DEFAULT 'Cash', -- Cash, Bank Transfer, etc.
    status ENUM('pending', 'paid', 'failed') DEFAULT 'pending',
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 8. Reviews Table
CREATE TABLE reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    customer_id INT NOT NULL,
    provider_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    review_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES service_providers(provider_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Database Complete 
