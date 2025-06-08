package lab13.app;

import lab13.com.DisplayLocales;
import lab13.com.Info;
import lab13.com.SetLocale;

import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplorer {
    private SetLocale setLocaleCommand;
    private DisplayLocales displayLocalesCommand;
    private Info infoCommand;
    private Scanner scanner;
    private ResourceBundle messages;
    
    public LocaleExplorer() {
        this.setLocaleCommand = new SetLocale();
        this.displayLocalesCommand = new DisplayLocales();
        this.scanner = new Scanner(System.in);
        updateCommands();
    }
    
    private void updateCommands() {
        this.messages = setLocaleCommand.getMessages();
        this.infoCommand = new Info(setLocaleCommand.getCurrentLocale(), messages);
    }
    
    public void run() {
        System.out.println("=== Internationalization and Localization Explorer ===");
        System.out.println("Available commands:");
        System.out.println("  locales - Display available locales");
        System.out.println("  set <locale> - Set current locale (e.g., 'set ro_RO' or 'set en_US')");
        System.out.println("  info [locale] - Display information about current or specified locale");
        System.out.println("  quit - Exit the application");
        System.out.println();
        
        while (true) {
            try {
                System.out.print(messages.getString("prompt") + " ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                    break;
                }
                
                processCommand(input);
                System.out.println();
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Goodbye!");
    }
    
    private void processCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : null;
        
        switch (command) {
            case "locales":
                System.out.println(messages.getString("locales"));
                displayLocalesCommand.execute();
                break;
                
            case "set":
                setLocaleCommand.execute(argument);
                updateCommands(); 
                break;
                
            case "info":
                infoCommand.execute(argument);
                break;
                
            default:
                System.out.println(messages.getString("invalid"));
                break;
        }
    }
    
    public static void main(String[] args) {
        new LocaleExplorer().run();
    }
}