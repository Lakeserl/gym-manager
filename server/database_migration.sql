-- Database Migration Script for Gym Management System
-- Run this script to fix the schema issues

-- Step 1: Drop existing foreign key constraints if they exist
SET FOREIGN_KEY_CHECKS = 0;

-- Step 2: Clean up old activities data that has invalid datetime values
DELETE FROM activities WHERE date = '0000-00-00 00:00:00' OR date IS NULL;

-- Step 3: Add new columns with default values
ALTER TABLE activities 
ADD COLUMN start_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
ADD COLUMN end_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
ADD COLUMN activity_type VARCHAR(50) DEFAULT 'CARDIO',
ADD COLUMN activity_name VARCHAR(255) DEFAULT 'General Activity',
ADD COLUMN duration INT DEFAULT 30,
ADD COLUMN equipment VARCHAR(255),
ADD COLUMN notes TEXT,
ADD COLUMN workout_plan VARCHAR(255),
ADD COLUMN intensity_level VARCHAR(20) DEFAULT 'MODERATE',
ADD COLUMN user_id BIGINT;

-- Step 4: Create users table if it doesn't exist
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    address TEXT,
    date_of_birth DATETIME(6),
    membership_type VARCHAR(20) NOT NULL DEFAULT 'BASIC',
    membership_start_date DATETIME(6),
    membership_end_date DATETIME(6),
    is_active BOOLEAN DEFAULT TRUE
);

-- Step 5: Insert a default user for existing activities
INSERT INTO users (username, full_name, email, phone_number, membership_type) 
VALUES ('default_user', 'Default User', 'default@gym.com', '0000000000', 'BASIC')
ON DUPLICATE KEY UPDATE id = id;

-- Step 6: Update existing activities to use the default user
UPDATE activities SET user_id = (SELECT id FROM users WHERE username = 'default_user' LIMIT 1);

-- Step 7: Convert old date column to new datetime columns
UPDATE activities 
SET start_time = COALESCE(date, CURRENT_TIMESTAMP(6)),
    end_time = COALESCE(DATE_ADD(date, INTERVAL 30 MINUTE), CURRENT_TIMESTAMP(6))
WHERE start_time IS NULL OR end_time IS NULL;

-- Step 8: Make user_id NOT NULL after setting default values
ALTER TABLE activities MODIFY COLUMN user_id BIGINT NOT NULL;

-- Step 9: Add foreign key constraint
ALTER TABLE activities 
ADD CONSTRAINT FK_activities_user 
FOREIGN KEY (user_id) REFERENCES users(id);

-- Step 10: Drop old date column
ALTER TABLE activities DROP COLUMN date;

-- Step 11: Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Step 12: Verify the migration
SELECT 'Migration completed successfully' as status; 