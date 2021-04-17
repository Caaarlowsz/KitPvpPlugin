package com.sgpvp.GameData;

import com.sgpvp.main;
import jdk.nashorn.internal.runtime.ECMAException;
import org.bukkit.ChatColor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameLog {
    private static main plugin = GameVariables.plugin;
    public static File logsFolder; // Create File

    public static void setupLogFolder() {
        try {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        } catch (Exception e) {

        }

        logsFolder = new File(plugin.getDataFolder(), "logs");

        if (!logsFolder.exists()) logsFolder.mkdirs();
    }

    public static void logToFile(String file, String message) {
        try {
            File dataFolder = plugin.getDataFolder(); // Sets file to the plugins/<pluginname> folder
            if (!dataFolder.exists()) { // Check if logs folder exists
                dataFolder.mkdir(); // if not then create it
            }
            File saveTo = new File(plugin.getDataFolder() + "/logs/", file + ".log"); // Sets the path of the new log file
            if (!saveTo.exists()) { // Check if logs folder exists
                saveTo.createNewFile(); // if not then create it
            }
            FileWriter fw = new FileWriter(saveTo, true); // Create a FileWriter to edit the file
            PrintWriter pw = new PrintWriter(fw); // Create a PrintWriter
            pw.println(message); // This is the text/message you will be writing to the file
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace(); // If theres any errors in this process it will print the error in console
        }
    }

    public static void saveError(Exception e) {
        StringWriter sw = new StringWriter(); // Create StringWriter
        e.printStackTrace(new PrintWriter(sw)); // Set the StringWriter we just made to the StackTrace
        String fullStackTrace = sw.toString(); // Create a String
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"); // Set the Time Format
        LocalDateTime now = LocalDateTime.now(); // Get the time
        logToFile(dtf.format(now).toString(), fullStackTrace); // Now we finally get to save the file!
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Error saved to log"); // Also send a message to console saying there was an error saved
    }
    public static void saveEvent(String event) {
        logToFile("GameLog#" + GameVariables.gameID, event);
    }
}
