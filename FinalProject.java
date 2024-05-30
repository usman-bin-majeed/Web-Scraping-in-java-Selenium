package day1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.*;
import java.util.concurrent.TimeUnit;


public class FinalProject {
    public static String[] mobilePriceScraper (String name) throws Exception {
        Scanner console = new Scanner(System.in);
        WebElement finalTag;
        String price = "none" , currentURL  = "none" , tages;
        String[] returns = new String[3];
        int productChoice , availbeTagesCounter = 0;
        WebElement[] tage = new WebElement[5];
        String[] tageName = new String[5];

        WebDriver driver = null;  // Initialize the WebDriver with ChromeOptions

        name = "mobile "+ name ;


        try{
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless");  // Set up Chrome options to hide
            options.addArguments("--disable-gpu");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://priceoye.pk");
        }catch(WebDriverException e){
            driver.quit();
            throw new WebDriverException("NO INTERNET");
        }


        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search-term']"));

        // Send the Enter key to the element
        searchBox.sendKeys(name);
        searchBox.sendKeys(Keys.RETURN);
        String status = driver.findElement(By.xpath("//*[@id=\"page-search\"]/div[1]/section/header/h2")).getText().trim();

        if (status.compareToIgnoreCase("No Search Results - Try Again! :)") == 0){
            driver.quit();
            throw new Exception("No Search Results - Try Again! :) \nNO DATA FOUND AGAINST ENTERED NAME");

        }





        for(int i = 1 ; i <= 4 ; i++) {
            try {

                tage[i] = driver.findElement(By.cssSelector(STR.".productBox:nth-child(\{i}) .p-title")); // Use the appropriate locator strategy
                tageName[i] = tage[i].getText();
                availbeTagesCounter++;

            } catch (Exception e) {
                //System.out.println("got error");/checking statemnt
                break;
            }
        }

        while (true) {
            for (int i = 1; i <= availbeTagesCounter; i++)
                System.out.printf("PRESS %d FOR  %s \n",i,tageName[i]);
            System.out.print("-->");
            try {
                productChoice = console.nextInt();
                console.nextLine();   // clear the leftover line

                if (productChoice > 0 && productChoice <= availbeTagesCounter) {
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                console.next();
            }
        }

        finalTag = tage[productChoice];
        name = finalTag.getText();
        finalTag.click();

        try {
            // Locate the element using the specified CSS selector
            finalTag = driver.findElement(By.cssSelector("#product-summary > div.po-product-info > div > div.po-price-content > div > span.summary-price.text-black.price-size-lg.bold"));
            price = finalTag.getText();
            currentURL = driver.getCurrentUrl();


        } catch (Exception e) {
            driver.quit();
            throw new WebDriverException("THIS IS OUT DATED SERIES");



        }

        driver.quit();
        returns[0] = price;
        returns[1] = name;
        returns[2] = currentURL;
        return returns;

    }







    public static String[] mobilePriceScraper2 (String name) throws Exception {


        String price  , currentURL = "none" , productName = name;
        String[] returns = new String[2];
        WebDriver driver = null;
        WebElement searchBox = null;
        try{


            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            // Set up Chrome options to run in headless mode
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless");    // for hiding browser
            options.addArguments("--disable-gpu");


            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://www.shophive.com/");
        }catch(WebDriverException e){
            driver.quit();
            throw new Exception("NO INTERNET");
        }


        try{
            searchBox = driver.findElement(By.id("search"));

            // Send the Enter key to the element
            searchBox.sendKeys(name);}
        catch (Exception e) {
            driver.quit();
            throw new Exception("WebElement searchBox not found");
        }
        //searchBox.sendKeys(Keys.RETURN);
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            searchBox.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement firstScroll = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/form/div[2]/div/div[2]/div/div[1]/div[2]/ul/li[1]/a/div[2]/div[1]/span"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            firstScroll.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            driver.quit();
            throw new Exception("NO DATA FOUND");
        }


        try {
            try{
                try
                {
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    price = driver.findElement(By.xpath("/html/body/div[2]/main/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[1]/span[1]/span/span[2]/span")).getText();
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    currentURL = driver.getCurrentUrl();

                }
                catch (Exception e){
                    price = driver.findElement(By.xpath("/html/body/div[2]/main/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[1]/span[1]/span/span[2]/span")).getText();
                    currentURL = driver.getCurrentUrl();
                }
            }catch(Exception e) {
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                price = driver.findElement(By.xpath("/html/body/div[2]/main/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[1]/span/span/span")).getText();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                currentURL = driver.getCurrentUrl();

            }

            //System.out.println("ok3");
        }catch (Exception e){
            System.out.println("NO DATA FOUND  1");
            price = "NO DATA FOUND ";

        }



        driver.quit();
        returns[0] = price;
        returns[1] = currentURL;

        return returns;
    }




    public static String[] mobilePriceScraper3 (String name) throws Exception{
        String price  , currentURL = "none" ;// webPage url jahn sy price uthayi hai

        String[] returns = new String[2]; // yea price, aur url hold kry ga.

        WebElement finalSearchScroll;
        WebElement searchBox;

        WebDriver driver = null; // control the browser
        try{
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // setting up the path and the driver to be controlled by the java code
            // Set up Chrome options to run in headless mode
            ChromeOptions options = new ChromeOptions(); // kis fashion main browser load krna hai

            options.addArguments("--headless");    // for hiding browser
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize(); // maximize the browser window

            driver.get("https://www.daraz.pk/");

        }catch(Exception e){
            driver.quit(); // Closing the browser
            throw new Exception("NO INTERNET");
        }

        try {


            WebElement filter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div/div/div/div/div/ul/li[7]/a/span[2]"));
            filter.click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // to let the files get loaded.

            filter = driver.findElement(By.cssSelector("#J_5022174600 > div > ul > ul.lzd-site-menu-sub.Level_1_Category_No1 > li:nth-child(1) > a > span"));
            filter.click();
            searchBox = driver.findElement(By.id("q")); // search bar ki html id

            // Send the Enter key to the element

            searchBox.sendKeys(name);

        } catch(WebDriverException e) {
            driver.quit();
            throw new Exception("WebElement searchBox not found");
        }

        try{
            try {
                WebElement searchScroll1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[3]/div/a[1]"));
                WebElement searchScroll2 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[3]/div/a[2]"));
                WebElement searchScroll3 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[3]/div/a[3]"));
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                String firstInScrollList = searchScroll1.getText();
                String secondInScrollList = searchScroll2.getText();
                String thirdInScrollList = searchScroll3.getText();

                // Checking the closest value against the searched value.

                if ((firstInScrollList).compareToIgnoreCase(name) == 0) {
                    finalSearchScroll = searchScroll1;
                    finalSearchScroll.click();
                } else if ((secondInScrollList).compareToIgnoreCase(name) == 0) {
                    finalSearchScroll = searchScroll2;
                    finalSearchScroll.click();
                } else if ((thirdInScrollList).compareToIgnoreCase(name) == 0) {
                    finalSearchScroll = searchScroll3;
                    finalSearchScroll.click();
                } else {
                    //System.out.println("not ok 1"); // check statment
                    driver.quit();
                    returns[0] = "NO DATA FOUND";
                    returns[1] = currentURL;
                    return returns;
                }
            }catch (Exception e){
                //System.out.println("not ok 2"); // check statment
                WebElement searchScroll1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[3]/div/a[1]"));
                finalSearchScroll = searchScroll1;
                String firstInScrollList = searchScroll1.getText();
            }

        }catch (Exception e){
            //System.out.println("not ok 3"); //check statment
            driver.quit();
            returns[0] = "NO DATA FOUND";
            returns[1] = currentURL;
            return returns;
        }

        try {

            try{
                WebElement productNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div/div[1]/div[2]/div[1]/div/a/div[1]/img"));
                productNameElement.click();
                price = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/div/div[1]/div[7]/div/div/span")).getText();
                currentURL = driver.getCurrentUrl();
            }catch (Exception e){
                try{
                    //System.out.println("not ok 4"); // check statment
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    searchBox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[1]/input[1]"));
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    searchBox.sendKeys(Keys.CONTROL + "a");

                    searchBox.sendKeys(Keys.DELETE);
                    searchBox.sendKeys(name);
                    searchBox.sendKeys(Keys.RETURN);
                    WebElement productNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div/div[1]/div[2]/div[1]/div/a/div[1]/img"));
                    productNameElement.click();
                    price = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/div/div[1]/div[7]/div/div/span")).getText();
                    currentURL = driver.getCurrentUrl();
                }catch (Exception ex){
                    //System.out.println("not ok 5");
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    searchBox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[2]/div/div[2]/form/div/div[1]/input[1]"));
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    searchBox.sendKeys(Keys.CONTROL + "a");
                    searchBox.sendKeys(Keys.DELETE);
                    searchBox.sendKeys(name);
                    searchBox.sendKeys(Keys.RETURN);
                    WebElement productNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div/div[1]/div[2]/div[1]/div/a/div[1]/img"));
                    productNameElement.click();
                    price = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/div/div[1]/div[7]/div/div/span")).getText();
                    currentURL = driver.getCurrentUrl();
                }
            }


        }catch(Exception e){

            driver.quit();
            returns[0] = "NO DATA FOUND";
            returns[1] = currentURL;

            return returns;
        }


        driver.quit();
        returns[0] = price;
        returns[1] = currentURL;

        return returns;
    }







    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] firstWebsiteDetails = new String[3];
        String[] secondWebsiteDetails = new String[2];
        String[] thirdWebsiteDetails = new String[2];
        String userEnteredProductName , firstPrice = "none" , secondPrice = "none" , thirdPrice = "none" , name = "none" , url1 = "none" , url2 = "none" , url3 = "none";
        int categorySelection ;

        while(true) {
            while (true) {
                System.out.print("SELECT THE CATEGORY \nPRESS 1 : TO SEARCH MOBILE  \nPRESS 0 : TO EXIT\n--> ");
                try {
                    categorySelection = console.nextInt();
                    console.nextLine();   // clear the leftover line

                    if (categorySelection == 1 || categorySelection == 0) {
                        break; // Exit the loop if the input is valid
                    } else {
                        System.out.println("Invalid input. Please enter only 1, 2 or 0.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    console.nextLine();   // clear the leftover line
                }
            }


            if (categorySelection == 1) {
                System.out.print("Enter the name of mobile : ");
                userEnteredProductName = console.nextLine();
                name = userEnteredProductName;
                try {
                    System.out.println("\nEXECUTING FIRST WEBSITE ........");
                    firstWebsiteDetails = mobilePriceScraper(userEnteredProductName);
                    firstPrice = firstWebsiteDetails[0];
                    name = firstWebsiteDetails[1];
                    url1 = firstWebsiteDetails[2];
                    System.out.println("\nFIRST WEBSITE SUCCESSFULLY EXECUTED");
                } catch (Exception e) {
                    System.out.println(e.getMessage());


                }
                try {
                    System.out.println("\nEXECUTING SECOND WEBSITE ........");
                    secondWebsiteDetails = mobilePriceScraper2(name);
                    secondPrice = secondWebsiteDetails[0];
                    url2 = secondWebsiteDetails[1];
                    System.out.println("\nSECOND WEBSITE SUCCESSFULLY EXECUTED");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
                try {
                    System.out.println("\nEXECUTING THIRD WEBSITE ........");
                    thirdWebsiteDetails = mobilePriceScraper3(name);
                    thirdPrice = thirdWebsiteDetails[0];
                    url3 = thirdWebsiteDetails[1];
                    System.out.println("\nTHIRD WEBSITE SUCCESSFULLY EXECUTED");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }


            } else if (categorySelection == 0) {
                System.out.print("EXITING THE APPLICATION.......");
                System.exit(1);
            }


            System.out.printf("\n\n %-40s %-30s %-15s %s\n %-40s %-30s %-15s %s\n %-40s %-30s %-15s %s\n %-40s %-30s %-15s %s\n\n", "MOBILE NAME", "MOBILE PRICE", "WEBSITE", "URL LINK", name, firstPrice, "PRICE OYE", url1, name, secondPrice, "SHOPHIVE.COM", url2, name, thirdPrice, "DARAZ.PK.COM", url3);
        }



    }
}