package com.outfit7.test;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Class contains tests related to petting/touching Tom.
 */
class TouchTest extends DefaultTestEnvironment {

    /**
     * Swipes on a screen and checks if Tom is enjoying the pets. Taps on a screen and checks if Tom is kicked.
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
