package com.ticketbot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String TOKEN;
    public static String SUPPORT_CHANNEL_ID;
    public static String GUILD_ID;
    public static String TICKET_CATEGORY_ID;

    public static void loadConfig() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/resources/config.properties")) {
            prop.load(input);
            TOKEN = prop.getProperty("token");
            SUPPORT_CHANNEL_ID = prop.getProperty("support_channel_id");
            GUILD_ID = prop.getProperty("guild_id");
            TICKET_CATEGORY_ID = prop.getProperty("ticket_category_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
