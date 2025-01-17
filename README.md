# 📊 Employee Performance Application

The Employee Performance Application is a Spring Boot project designed to manage employee performance appraisals. It evaluates employees based on predefined rating categories and appraisal ranges, calculates deviations from the standard distribution, and provides suggestions for performance adjustments.


# 🚀 Features
	•	Manage employee details and their performance ratings.
	•	Generate a bell curve for performance appraisal.
	•	Calculate deviations between actual and standard ratings.
	•	Suggest rating adjustments for balanced appraisals.
	•	Extensible architecture for future requirements.


# 🛠️ Technologies Used
	•	Java 17
	•	Spring Boot 
	•	MySQL for data storage
	•	Hibernate (JPA) for ORM
	•	Maven for project management
	•	RESTful APIs for communication

 # 📄 API Endpoints
<img width="483" alt="Screenshot 2025-01-18 at 2 47 05 AM" src="https://github.com/user-attachments/assets/bdf4c451-2737-43e8-a9cb-e4e70bf37b0e" />

# 🗄️ Database Structure
  ## Tables
  	1.employees:
    	•id: Primary Key
    	•name: Employee Name
    	•rating: Performance Rating (A, B, C, D, E)
  	2.ratings:
    	•category: Rating Category
    	•standard_percentage: Expected Distribution
    	•actual_percentage: Calculated Distribution
# 📂 Project Structure
```
src/main/java/com
├── controller        # REST controllers
├── service           # Business logic
├── repository        # Database access
├── model             # Entity and DTO classes
├── dto               # Utility classes 
└── EmployeePerformanceApplication.java  # Main class
