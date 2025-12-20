-- Tạo các database nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS product_db;
CREATE DATABASE IF NOT EXISTS inventory_db;
CREATE DATABASE IF NOT EXISTS auth_db;
CREATE DATABASE IF NOT EXISTS payment_db;
-- Cấp quyền (Optional - Docker MySQL image thường đã xử lý root, nhưng thêm cho chắc chắn với user thường)
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';
FLUSH PRIVILEGES;