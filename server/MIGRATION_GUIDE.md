# Hướng dẫn khắc phục lỗi Database Migration

## **Vấn đề:**
Hibernate không thể tự động cập nhật schema vì:
1. Dữ liệu cũ có datetime không hợp lệ (`'0000-00-00 00:00:00'`)
2. Thiếu foreign key constraint cho user_id
3. Schema cũ không tương thích với entity mới

## **Giải pháp:**

### **Bước 1: Dừng ứng dụng**
```bash
# Dừng Spring Boot application nếu đang chạy
```

### **Bước 2: Chạy script migration**
```bash
# Kết nối vào MySQL và chạy script
mysql -u Lakeserl -p myGym < database_migration.sql
```

Hoặc copy nội dung từ file `database_migration.sql` và chạy trong MySQL Workbench.

### **Bước 3: Kiểm tra kết quả**
```sql
-- Kiểm tra bảng activities
DESCRIBE activities;

-- Kiểm tra bảng users
DESCRIBE users;

-- Kiểm tra dữ liệu
SELECT * FROM activities LIMIT 5;
SELECT * FROM users LIMIT 5;
```

### **Bước 4: Khôi phục cấu hình**
Sau khi migration thành công, đổi lại trong `application.properties`:
```properties
spring.jpa.hibernate.ddl-auto=update
```

### **Bước 5: Khởi động ứng dụng**
```bash
# Chạy lại Spring Boot application
./mvnw spring-boot:run
```

## **Nếu vẫn gặp lỗi:**

### **Option 1: Xóa và tạo lại database**
```sql
DROP DATABASE myGym;
CREATE DATABASE myGym;
```

Sau đó đổi `ddl-auto=create` trong application.properties để tạo schema mới.

### **Option 2: Backup và restore**
```bash
# Backup dữ liệu cũ
mysqldump -u Lakeserl -p myGym > backup_old.sql

# Tạo database mới
mysql -u Lakeserl -p -e "CREATE DATABASE myGym_new"

# Restore với schema mới
mysql -u Lakeserl -p myGym_new < backup_old.sql
```

## **Kiểm tra sau migration:**

1. **API Test:**
```bash
# Test tạo activity mới
curl -X POST http://localhost:8080/manager/activity \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "startTime": "2024-01-01T10:00:00",
    "endTime": "2024-01-01T11:00:00",
    "activityType": "CARDIO",
    "activityName": "Running",
    "duration": 60,
    "steps": 5000,
    "distance": 5.0,
    "caloriesBurned": 300,
    "equipment": "Treadmill",
    "notes": "Good session",
    "workoutPlan": "Cardio Plan",
    "intensityLevel": "HIGH"
  }'
```

2. **Kiểm tra logs:**
- Không có lỗi Hibernate
- Application khởi động thành công
- API endpoints hoạt động

## **Lưu ý quan trọng:**
- Backup database trước khi migration
- Test trên môi trường dev trước
- Kiểm tra dữ liệu sau migration
- Cập nhật frontend nếu cần 