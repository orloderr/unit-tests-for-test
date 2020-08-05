package com.outfit7.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomTest extends Base {

    @Test
    void customTest() throws IOException, InterruptedException {
        Assertions.assertFalse(driver.getPageSource().contains("purr"), "Tom is unexpectedly purring!");

        sendShellCommandAndWaitForItsTermination(
                String.format("adb shell input swipe %1$s %2$s %3$s %4$s", windowSize.width * 0.5,
                        windowSize.height * 0.5, windowSize.width * 0.5, windowSize.height * 0.7));

        Assertions.assertTrue(driver.getPageSource().contains("purr"), "There is no cake scene at all!");
    }
}
