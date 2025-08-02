# Gym Manager - Gym Management System

## Project Description

Gym Manager is a comprehensive gym management system with Angular frontend and Spring Boot backend. The system allows tracking member activities, managing personal information, creating workout plans, and analyzing progress.

## System Architecture

### Backend (Server)
- **Framework**: Spring Boot 3.5.4
- **Language**: Java 24
- **Database**: MySQL with JPA/Hibernate
- **Architecture**: RESTful API with layered architecture
  - Controller → Service → Repository → Entity
- **Dependencies**: Spring Web, Spring Data JPA, MySQL Connector, Lombok

### Frontend (Client)
- **Framework**: Angular 20.0.5
- **Language**: TypeScript
- **Architecture**: Component-based with services

## 🎯 Core Features

### 1. User Management
- Register and manage member information
- Track membership types and expiration dates
- Manage detailed personal information

### 2. Activity Tracking
- Record workout activities
- Classify workout types (Cardio, Strength Training, etc.)
- Track start/end times
- Automatic calorie calculation

### 3. Workout Planning
- Create workout plans for members
- Track equipment usage
- Notes and intensity assessment

### 4. Progress Analytics
- Activity statistics over time
- Workout trend analysis
- Progress reports for each member

### 5. Security
- JWT login support (in development)
- Role & access control middleware
- Sensitive data encryption (planned)

## 📊 Database Structure

## 🔌 API Endpoints

### Activities Management
```
POST   /manager/activity                    # Create new activity
GET    /manager/activity/{id}              # Get activity by ID
GET    /manager/activities/user/{userId}   # Get user activities
GET    /manager/activities                 # Get all activities
PUT    /manager/activity/{id}              # Update activity
DELETE /manager/activity/{id}              # Delete activity
```

### Request/Response Examples

#### Create New Activity
```json
{
  "userId": 1,
  "startTime": "2024-01-15T10:00:00",
  "endTime": "2024-01-15T11:00:00",
  "activityType": "CARDIO",
  "activityName": "Running",
  "equipment": "Treadmill",
  "intensityLevel": "HIGH",
  "notes": "Great workout session"
}
```

#### Response
```json
{
  "id": 1,
  "userId": 1,
  "startTime": "2024-01-15T10:00:00",
  "endTime": "2024-01-15T11:00:00",
  "activityType": "CARDIO",
  "activityName": "Running",
  "duration": 60,
  "caloriesBurned": 450,
  "equipment": "Treadmill",
  "intensityLevel": "HIGH",
  "notes": "Great workout session"
}
```

### Environment Variables
Create `.env` file or use environment variables:
```bash
# Database
DB_URL=jdbc:mysql://localhost:3306/gym_manager
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# JWT Secret
JWT_SECRET=your_jwt_secret_key

# Server Configuration
SERVER_PORT=8080
```

## 🚀 Installation and Setup

### System Requirements
- Java 24
- Node.js 18+
- MySQL 8.0+
- Maven 3.6+
- Angular CLI 20.0.5+

### 1. Clone repository
```bash
git clone https://github.com/Lakeserl/gym-manager.git
cd gym-manager
```

### 2. Setup and run Backend

```bash
cd server

# Install dependencies
mvn clean install

# Configure database
# Edit application.properties with your MySQL information
# Example: spring.datasource.url=jdbc:mysql://localhost:3306/gym_manager

# Run database migration
mysql -u [your_username] -p [your_database] < database_migration.sql

# Run application
mvn spring-boot:run
```

Backend will run at: `http://localhost:8080`

### 3. Setup and run Frontend

```bash
cd client

# Install dependencies
npm install

# Run application
ng serve
```

Frontend will run at: `http://localhost:4200`

## 🛠️ Technologies Used

### Backend
- **Spring Boot 3.5.4**: Main framework
- **Spring Data JPA**: ORM and database access
- **MySQL**: Database
- **Lombok**: Reduce boilerplate code
- **Maven**: Build tool and dependency management

### Frontend
- **Angular 20.0.5**: Main framework
- **TypeScript**: Programming language
- **Angular CLI**: Development tools
- **CSS3**: Styling

## 📈 Advanced Features

### Business Logic
- **Automatic Calorie Calculation**: Calculate calories based on activity type and intensity
- **Duration Calculation**: Automatically calculate time from start/end time
- **Validation**: Input data validation
- **Error Handling**: Comprehensive error handling

### Data Management
- **User-Activity Relationship**: Link activities to specific members
- **Activity Classification**: Clear classification of workout types
- **Equipment Tracking**: Track equipment usage
- **Progress Tracking**: Notes and progress assessment

## 🔧 Development

### Backend Development
```bash
cd server
mvn spring-boot:run
```

### Frontend Development
```bash
cd client
ng serve
```

### Database Migration
```bash
mysql -u [your_username] -p [your_database] < server/database_migration.sql
```

## 📝 Testing

### Backend Tests
```bash
cd server
mvn test
```

### Frontend Tests
```bash
cd client
ng test
```

## 🚀 Deployment

### Production Checklist
- [ ] Configure production database
- [ ] Change JWT secret keys
- [ ] Configure CORS for production domain
- [ ] Use HTTPS
- [ ] Configure logging and monitoring
- [ ] Database backup strategy
- [ ] Load balancing (if needed)

### Docker Deployment (Optional)
```dockerfile
# Backend Dockerfile
FROM openjdk:24-jdk-slim
COPY target/server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

