package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@Author Dejuan Carson

class SettingsTest {

    private Settings settings;

    @BeforeEach
    void setUp() {
        settings = new Settings();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, settings.getNotifications(), "Default notifications should be 0 (off)");
        assertEquals(1, settings.getLightMode(), "Default light mode should be 1 (on)");
        assertEquals(0, settings.getTextToSpeech(), "Default text-to-speech should be 0 (off)");
        assertEquals(10, settings.getFontSize(), "Default font size should be 10");
    }

    @Test
    void testSetAndGetNotifications() {
        settings.setNotifications(1);
        assertEquals(1, settings.getNotifications(), "Notifications should be set to 1 (on)");
        
        settings.setNotifications(0);
        assertEquals(0, settings.getNotifications(), "Notifications should be set to 0 (off)");
    }

    @Test
    void testSetAndGetLightMode() {
        settings.setLightMode(0);
        assertEquals(0, settings.getLightMode(), "Light mode should be set to 0 (off)");
        
        settings.setLightMode(1);
        assertEquals(1, settings.getLightMode(), "Light mode should be set to 1 (on)");
    }

    @Test
    void testSetAndGetTextToSpeech() {
        settings.setTextToSpeech(1);
        assertEquals(1, settings.getTextToSpeech(), "Text-to-speech should be set to 1 (on)");
        
        settings.setTextToSpeech(0);
        assertEquals(0, settings.getTextToSpeech(), "Text-to-speech should be set to 0 (off)");
    }

    @Test
    void testSetAndGetFontSize() {
        settings.setFontSize(12);
        assertEquals(12, settings.getFontSize(), "Font size should be set to 12");
        
        settings.setFontSize(8);
        assertEquals(8, settings.getFontSize(), "Font size should be set to 8");
        
        settings.setFontSize(-5);
        assertEquals(8, settings.getFontSize(), "Font size should remain 8 when setting a negative value");
    }

    @Test
    void testToString() {
        String expected = "Settings:\nNotifications: 0\nLight Mode: 1\nText-To-Speech: 0\nFont Size: 10";
        assertEquals(expected, settings.toString(), "Default toString output should match expected format");
        
        settings.setNotifications(1);
        settings.setLightMode(0);
        settings.setTextToSpeech(1);
        settings.setFontSize(15);

        expected = "Settings:\nNotifications: 1\nLight Mode: 0\nText-To-Speech: 1\nFont Size: 15";
        assertEquals(expected, settings.toString(), "Updated toString output should match expected format");
    }
}
