package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;




import java.time.Duration;

/**
 * Manages WebDriver instances for parallel test execution
 */
public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Get WebDriver instance for current thread
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Initialize WebDriver based on browser configuration
     */
    public static void initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        WebDriver webDriver;

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (ConfigReader.isHeadless()) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                webDriver.manage().window().maximize();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (ConfigReader.isHeadless()) {
                    edgeOptions.addArguments("--headless=new");
                }
                webDriver = new EdgeDriver(edgeOptions);
                webDriver.manage().window().maximize();
                break;

            case "safari":
                org.openqa.selenium.safari.SafariOptions safariOptions =
                        new org.openqa.selenium.safari.SafariOptions();
                safariOptions.setAutomaticInspection(false);
                safariOptions.setAutomaticProfiling(false);
                webDriver = new org.openqa.selenium.safari.SafariDriver(safariOptions);
                webDriver.manage().window().maximize();
                break;

            case "brave":
                WebDriverManager.chromedriver().setup();

                ChromeOptions braveOptions = new ChromeOptions();
                braveOptions.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
               // WebDriver driver = new ChromeDriver(braveOptions);
                //driver.manage().window().maximize();

                // Essential options
                braveOptions.addArguments("--disable-features=DialMediaRouteProvider");
                braveOptions.addArguments("--start-maximized");
                braveOptions.addArguments("--disable-notifications");
                braveOptions.addArguments("--remote-allow-origins=*");
                braveOptions.addArguments("--disable-blink-features=AutomationControlled");

                if (ConfigReader.isHeadless()) {
                    braveOptions.addArguments("--headless=new");
                }

                webDriver = new ChromeDriver(braveOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Timeouts (applies to ALL browsers)
        webDriver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        driver.set(webDriver); // ✅ ThreadLocal assignment
    }

    /**
     * Quit WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}