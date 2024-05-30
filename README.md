## Author : Usman Majeed
#### contact : fa23-bds-042@isbstudent.comsats.edu.pk


# Title :  Web-Scraping-using-Selenium-in-Java
The Mobile Price Scraper is a Java-based web scraping application designed to fetch the price of mobile phones from the Daraz.pk e-commerce platform. Utilizing the Selenium WebDriver, the application automates the process of searching for a specified mobile phone and retrieves its price along with the product URL.



# Mobile Price Scraper

The **Mobile Price Scraper** is a Java-based web scraping application designed to fetch the price of mobile phones from the Daraz.pk e-commerce platform. By utilizing the Selenium WebDriver, this application automates the process of searching for a specified mobile phone and retrieves its price along with the product URL. This price engine will go over the three e-commerce websites, mainly daraz , priceoye and shophive. These are some famous e-commerce platform in Pakistan. 

## Features

- **Web Scraping**: The application automatically extracts information from the Daraz.pk website, including product prices and URLs.
- **Selenium WebDriver**: Interacts with the website just like a human would, including clicking buttons, filling out forms, and navigating between pages.
- **Customized XPath/CSS Locators**: To ensure robust scraping, the project uses customized locators to identify elements in the DOM structure.
- **Maven Integration**: The project is managed using Maven, which handles dependencies, project generation, packaging, and more.

## Prerequisites

Before you get started, ensure that the following are installed on your system:

- **Java**: Make sure you have Java installed. You can check by running `java -version` in your terminal.
- **Maven**: Install Maven if you haven't already. You can verify its installation with `mvn -version`.
- An IDE (e.g., Eclipse): Choose your preferred IDE for Java development.

## Installation

1. Clone this repository to your local machine using `git clone <repository_url>`.
2. Open the project in your preferred IDE (e.g., Eclipse).
3. Ensure that the required dependencies (such as TestNG, Selenium, and Apache POI) are correctly configured in your `pom.xml` file.

## Usage

1. Customize the search parameters in the code (e.g., specify the mobile phone model or brand you want to scrape).
2. Run the application.
3. The scraped data (price and product URL) will be displayed in the console.

## Important Note

Remember to check the `robots.txt` file of any website you intend to scrape to ensure compliance with scraping policies. Respect the website's terms of use.

## Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request if you find any improvements or want to add new features.


