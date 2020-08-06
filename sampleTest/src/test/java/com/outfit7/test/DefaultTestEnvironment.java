package com.outfit7.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * Base class that prepares am main scene in "Talking Tom" application as a test environment.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultTestEnvironment {
    private static final Logger LOG = Logger.getLogger("DefaultTestEnvironmentLog");
    protected AppiumDriver<MobileElement> driver;
    protected boolean isWindows;
    protected Dimension windowSize;

    @BeforeAll
    public void prepareTestEnvironment() throws Exception {
        startAppiumConnectionAndLoadApplication();
        prepareApplicationMainRoom();
    }

    /**
     * Establishes an Appium connection to the device and loads an application.
     *
     * @throws MalformedURLException
     *         thrown when a given Appium server address is wrong
     */
    private void startAppiumConnectionAndLoadApplication() throws MalformedURLException {
        // Set driver capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("fullReset", "true");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability("platformName", "Android");
        File file = new File("src/main/resources/base.apk");
        String absolutePath = file.getAbsolutePath();
        capabilities.setCapability("app", absolutePath);

        // Create new android driver, the default port for Appium is 4723
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        // Default appium timeout for searching elements
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    /**
     * Passes through all preparation dialogues and stays in the main room of the application.
     *
     * @throws IOException
     *         if an I/O error occurs at the command execution start
     * @throws InterruptedException
     *         if the waiting for the command execution terminating
     */
    private void prepareApplicationMainRoom() throws IOException, InterruptedException {
        isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        windowSize = driver.manage().window().getSize();

        // Wait for first element so we can continue
        driver.findElement(By.id("com.outfit7.talkingtom:id/sharingAgeScreeningButtonOk"));

        // Swipe to get to some age
        sendShellCommandAndWaitForItsTermination(
                String.format("adb shell input swipe %1$s %2$s %3$s %4$s", windowSize.width * 0.5,
                        windowSize.height * 0.5, windowSize.width * 0.5, windowSize.height * 0.7));

        // Click button ok to continue
        driver.findElement(By.id("com.outfit7.talkingtom:id/sharingAgeScreeningButtonOk")).click();

        // Sometimes we get asked for special permission
        MobileElement allowPermissionButton = driver
                .findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
        if (allowPermissionButton != null) {
            allowPermissionButton.click();
        }

        // Pass gdpr screen
        driver.findElement(By.id("com.outfit7.talkingtom:id/btnYes")).click();

        // Check if we are in main room
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton"));
        LOG.log(Level.INFO, "### We are in main room ###");
    }

    /**
     * Sends a given command to a command shell: Command Line (on Windows) or Bash (on Linux). Waits for the command
     * execution termination.
     *
     * @param command
     *         command to execute
     * @throws IOException
     *         if an I/O error occurs at the execution start
     * @throws InterruptedException
     *         if the waiting for the execution terminating
     */
    protected void sendShellCommandAndWaitForItsTermination(String command) throws IOException, InterruptedException {
        if (isWindows) {
            new ProcessBuilder("cmd", "/c", command).inheritIO().start().waitFor();
        } else {
            // TODO: needs testing
            new ProcessBuilder("sh", "-c", command).inheritIO().start().waitFor();
        }
    }
}
