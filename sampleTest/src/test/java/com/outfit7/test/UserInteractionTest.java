package com.outfit7.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
     * Clicks on "Food" button, feeds a cake to Tom, checks for cake animation and makes a screenshot.
     *
     * @throws IOException
     *         thrown if there is a problem with saving a screenshot
     */
    @Test
    void feedCakeMakeScreenshotTest() throws IOException {
        WebDriverWait waitForApp = new WebDriverWait(driver, 1);

        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton")).click();
        waitForApp.until(ExpectedConditions.elementToBeClickable(By.id("com.outfit7.talkingtom:id/foodItemCake")));
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodItemCake")).click();

        // TODO: verify that the cake animation has started/is playing

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,
                new File(String.format("%s\\%s", System.getProperty("user.dir"), "eatingTom.png")));

        // TODO: wait until the animation is finished
    }

    /**
     * Swipes on a screen and checks if Tom is enjoying the pets.
     *
     * @throws IOException
     *         thrown if an I/O error occurs at the swipe execution start
     * @throws InterruptedException
     *         thrown if the waiting for the swipe execution terminating is interrupted
     */
    @Test
    void petAndPokeTest() throws IOException, InterruptedException {
        sendShellCommandAndWaitForItsTermination(
                String.format("adb shell input swipe %1$s %2$s %3$s %4$s", windowSize.width * 0.5,
                        windowSize.height * 0.5, windowSize.width * 0.5, windowSize.height * 0.7));

        // TODO: assert that an animation is playing: Tom is purring/has a pleased face

        sendShellCommandAndWaitForItsTermination(
                String.format("adb shell input tap %1$s %2$s", windowSize.width * 0.5, windowSize.height * 0.5));

        // TODO: assert that an animation is playing: Tom is kicked/poked
    }
}
