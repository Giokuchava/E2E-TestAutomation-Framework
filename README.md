# End-to-End Test Automation Framework
## Automation Exercise - UI & API Testing

### 📋 Project Overview
This project is a comprehensive test automation framework covering both UI and API testing for [Automation Exercise](https://automationexercise.com).

**Framework Stack:**
- Java
- Selenium WebDriver
- TestNG
- RestAssured
- Maven
- Allure Reporting
- Page Object Model (POM)

---

### 👥 Group Members

| Name | Email | Test Cases Automated |
|------|-------|---------------------|
| Member 1 | email1@example.com | UI: TC01, TC02, TC03, TC04 / API: API01, API02 |
| Member 2 | email2@example.com | UI: TC05, TC06, TC07 / API: API03, API04, API05 |
| Member 3 | email3@example.com | UI: TC08, TC09 / API: API06, API07, API08 |
| Member 4 | email4@example.com | UI: TC10 / API: API09, API10 |

---

### 📁 Project Structure

```
automation-exercise-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/          # Page Object Model classes
│   │       ├── api/            # API client classes
│   │       └── utils/          # Utility classes
│   └── test/
│       ├── java/
│       │   ├── ui/             # UI test cases
│       │   └── api/            # API test cases
│       └── resources/
│           ├── config.properties
│           └── testng.xml
├── pom.xml
├── .gitignore
└── README.md
```

---

### 🧪 Test Cases Coverage

#### UI Test Cases (10)
1. **TC01** - Register User
2. **TC02** - Login User with correct email and password
3. **TC03** - Login User with incorrect email and password
4. **TC04** - Logout User
5. **TC05** - Register User with existing email
6. **TC06** - Contact Us Form
7. **TC08** - Verify All Products and product detail page
8. **TC09** - Search Product
9. **TC15** - Place Order: Register before Checkout
10. **TC25** - Verify Scroll Up using 'Arrow' button and Scroll Down functionality

#### API Test Cases (10)
1. **API 01** - GET All Products List
2. **API 02** - POST To All Products List
3. **API 03** - GET All Brands List
4. **API 04** - PUT To All Brands List
5. **API 05** - POST To Search Product
6. **API 06** - POST To Search Product without search_product parameter
7. **API 07** - POST To Verify Login with valid details
8. **API 08** - POST To Verify Login without email parameter
9. **API 09** - DELETE To Verify Login
10. **API 11** - POST To Create/Register User Account

---

### 🚀 Getting Started

#### Prerequisites
- Java JDK 11 or higher
- Maven 3.6+
- Chrome/Firefox browser
- Git

#### Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/automation-exercise-framework.git
cd automation-exercise-framework
```

2. Install dependencies:
```bash
mvn clean install
```

---

### ▶️ Running Tests

#### Run All Tests
```bash
mvn clean test
```

#### Run UI Tests Only
```bash
mvn clean test -Dtest=ui.**
```

#### Run API Tests Only
```bash
mvn clean test -Dtest=api.**
```

#### Run Specific Test Class
```bash
mvn clean test -Dtest=UserRegistrationTest
```

#### Run Tests in Parallel
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

### 📊 Generating Allure Reports

1. Run tests and generate Allure results:
```bash
mvn clean test
```

2. Generate and open Allure report:
```bash
mvn allure:serve
```

Or generate report to a folder:
```bash
mvn allure:report
```

The report will be generated in `target/site/allure-maven-plugin` directory.

---

### 🔧 Configuration

Edit `src/test/resources/config.properties` to configure:
- Base URLs
- Browser settings
- Timeout values
- API endpoints

```properties
base.url=https://automationexercise.com
api.base.url=https://automationexercise.com/api
browser=chrome
implicit.wait=10
explicit.wait=15
```

---

### 📝 TestNG Configuration

The `testng.xml` file supports parallel execution:
- Thread count: 5
- Parallel mode: methods
- Separate suites for UI and API tests

---

### 🏗️ Framework Features

- ✅ Page Object Model design pattern
- ✅ Parallel test execution support
- ✅ Explicit waits (no hard-coded delays)
- ✅ Screenshot capture on test failure
- ✅ API request/response logging
- ✅ Comprehensive Allure reporting
- ✅ Reusable utility methods
- ✅ Configuration management
- ✅ Cross-browser support

---

### 📸 Reporting Features

Allure reports include:
- Test execution timeline
- Detailed test steps
- Screenshots for failed UI tests
- API request and response bodies
- Error logs and stack traces
- Test categorization
- Test history and trends

---

### 🤝 Contributing

Each team member should:
1. Create a feature branch for their test cases
2. Commit regularly with meaningful messages
3. Create pull requests for review
4. Follow Java coding standards

---

### 📧 Contact

For questions or issues, please contact:
- Project Lead: [email@example.com]
- GitHub Issues: [Repository Issues Page]

---

### 📅 Project Timeline

- **Start Date:** [Date]
- **Deadline:** February 12, 2026
- **Submission:** GitHub repository URL via email

---

### ⚠️ Important Notes

- No ZIP files or screenshots allowed
- Repository must be public
- All dependencies managed via Maven
- Follow POM design pattern strictly
- No hard-coded waits permitted
