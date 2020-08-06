package com.outfit7.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Class contains tests related to feeding Tom.
 */
class FoodTest extends DefaultTestEnvironment {

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
    }
}
