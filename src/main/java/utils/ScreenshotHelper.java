package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots
 */
public class ScreenshotHelper {

    /**
     * Capture screenshot and save to file
     * @param driver WebDriver instance
     * @param screenshotName name for the screenshot
     * @return path to saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = "target/screenshots/" + fileName;

        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File("target/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);

            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot captured: " + screenshotPath);

            return screenshotPath;
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot and attach to Allure report
     * @param driver WebDriver instance
     * @return screenshot as byte array
     */
    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static byte[] captureScreenshotForAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Capture screenshot with custom name for Allure
     * @param driver WebDriver instance
     * @param name custom screenshot name
     * @return screenshot as byte array
     */
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] captureScreenshotWithName(WebDriver driver, String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}