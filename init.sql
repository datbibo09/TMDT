-- Tạo các database
CREATE DATABASE IF NOT EXISTS product_db;
CREATE DATABASE IF NOT EXISTS inventory_db;
CREATE DATABASE IF NOT EXISTS auth_db;
CREATE DATABASE IF NOT EXISTS payment_db;
CREATE DATABASE IF NOT EXISTS orders_db;

-- Bước 1: Tạo user nếu chưa tồn tại
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password_cua_ban';

-- Bước 2: Cấp quyền (Sử dụng cú pháp chuẩn của MySQL 8)
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;

-- Bước 3: Áp dụng thay đổi
FLUSH PRIVILEGES;