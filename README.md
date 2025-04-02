# 📌 Selenium YouTube Automation with TestNG

[![XPath](https://img.shields.io/badge/XPath-Query%20Selector-orange)](https://developer.mozilla.org/en-US/docs/Web/XPath)
[![TestNG](https://img.shields.io/badge/TestNG-Testing%20Framework-blue)](https://testng.org/doc/)
[![Selenium](https://img.shields.io/badge/Selenium-Web%20Automation-brightgreen)](https://www.selenium.dev/)
[![Java 11](https://img.shields.io/badge/Java-11-red)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![License](https://img.shields.io/badge/License-Apache%202.0-green)](https://opensource.org/licenses/Apache-2.0)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen)](#)

## 🚀 Project Overview
This project automates various YouTube functionalities using **Selenium WebDriver** and **TestNG**. The test cases perform actions such as:
- Navigating to YouTube and validating URLs.
- Clicking on tabs like Movies, Music, and News.
- Extracting and printing key information from different sections.
- Performing searches and analyzing video view counts.

## 🛠️ Tech Stack
- **Programming Language**: Java
- **Testing Framework**: TestNG
- **Automation Tool**: Selenium WebDriver
- **Data Handling**: Apache POI
- **Logging & Assertions**: TestNG Assertions

## 📂 Project Structure
```
📦 demo
 ┣ 📜 TestCases.java             # Main test cases for YouTube automation
 ┣ 📂 utils                      # Utility classes
 ┃ ┣ 📜 ExcelDataProvider.java   # Reads test data from Excel
 ┃ ┗ 📜 ExcelReaderUtil.java     # Helper methods for Excel handling
 ┣ 📂 wrappers                   # Wrapper methods for Selenium operations
 ┃ ┗ 📜 Wrappers.java            # Commonly used Selenium functions
 ┗ 📜 logging.properties         # Logging configuration
```

## ⚙️ Setup Instructions
### Prerequisites
- **Java 11+** installed
- **Gradle** installed (if using dependency management)
- **Chrome Browser** installed
- **ChromeDriver** setup (Not needed if Selenium Manager is used)

### Clone Repository
```sh
git clone https://github.com/Gourab-Pal/youtube-automation.git
cd youtube-automation
```

### Install Dependencies
If using **Gradle**:
```sh
gradle build
```

### Run Test Cases
using Gradle:
```sh
./gradlew test
```

## 📝 Test Cases Overview
| Test Case | Description |
|-----------|-------------|
| `testCase01` | Opens YouTube homepage, verifies URL, clicks on 'About', and prints the About section details. |
| `testCase02` | Navigates to 'Movies' tab, scrolls through 'Top Selling' section, and verifies movie category. |
| `testCase03` | Goes to 'Music' tab, selects 'India's Biggest Hits' playlist, and prints playlist details. |
| `testCase04` | Opens 'News' tab, extracts details from 'Latest news posts', and prints first 3 news articles. |
| `testCase05` | Performs a YouTube search using data from Excel and calculates total view counts. |

## 🌟 Features
✅ Uses **TestNG** for testcase management.
✅ **Hard/soft Assertions** to prevent test failure interruptions.
✅ **Logging support** for detailed test execution logs.
✅ **Excel-based DataProvider** for dynamic test data.

## 📌 Author
👤 **Gourab Pal**  
📧 [gourab.pal.gpal@gmail.com](mailto:gourab.pal.gpal@gmail.com)  
🔗 [LinkedIn](http://www.linkedin.com/in/gourab-pal-0327801a4)

## 📜 License
This project is free to use.

