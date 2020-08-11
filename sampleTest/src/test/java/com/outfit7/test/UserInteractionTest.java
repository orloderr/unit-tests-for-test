package com.outfit7.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This test class contains all three tests. This way the @BeforeAll method is executed only once at the beginning,
 * preparing the test environment. Cuts down the test execution time.
 */
class UserInteractionTest extends DefaultTestEnvironment {

    /**
     * Clicks on "Info" button, then on "How To Play" button and checks presence of a given text.
     */
    @Test
    void checkHowToPlayTextInfoAndCloseTest() {
        driver.findElement(By.id("com.outfit7.talkingtom:id/buttonInfo")).click();
        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonHowToPlay")).click();

        Assertions.assertTrue(driver.getPageSource().contains("Talk to Tom and he repeats with a funny voice"),
                "There is no given text.");

        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonClose")).click();
    }

    /**
     * Clicks on "Food" button, feeds a cake to Tom, checks for HUD absence/existence and makes a screenshot.
     *
     * @throws IOException
     *         thrown if there is a problem with saving a screenshot
     */
    @Test
    void feedCakeMakeScreenshotTest() throws IOException {
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton")).click();
        waitForApp.until(ExpectedConditions.elementToBeClickable(By.id("com.outfit7.talkingtom:id/foodItemCake")));
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodItemCake")).click();

        // When food animation is played, the game HUD is hidden. Check if the food container is removed form the
        // screen when cake animation is requested.
        Assertions.assertTrue(waitForApp.until(ExpectedConditions
                .invisibilityOf(driver.findElement(By.id("com.outfit7.talkingtom:id/foodItemsContainer")))));

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,
                new File(String.format("%s\\%s", System.getProperty("user.dir"), "eatingTom.png")));

        // The game HUD comes back after the food animation. Assert its element returns to the screen.
        // Awaitility is used instead of WebDriverWait because `driver.findElement` immediately throws an exception
        // and fails the test.
        Awaitility.await().atMost(15, TimeUnit.SECONDS).untilAsserted(() -> Assertions
                .assertTrue(driver.getPageSource().contains("com.outfit7.talkingtom:id/foodItemsContainer")));
    }

    /**
     * Records a video and checks if a menu with sharing options appear.
     */
    @Test
    void recordVideoCheckSharingWindowAndCloseTest() {
        driver.findElement(By.id("com.outfit7.talkingtom:id/recorderButton")).click();
        driver.findElement(By.id("com.outfit7.talkingtom:id/recorderButton")).click();

        Assertions.assertTrue(driver.findElement(By.id("com.outfit7.talkingtom:id/recorderMenuMainMenu")).isDisplayed(),
                "There is no requested menu.");
        Assertions
                .assertTrue(driver.findElement(By.id("com.outfit7.talkingtom:id/recorderMenuButtonPlay")).isDisplayed(),
                        "There is no requested button.");

        driver.findElement(By.id("com.outfit7.talkingtom:id/recorderMenuButtonClose")).click();
    }
}
