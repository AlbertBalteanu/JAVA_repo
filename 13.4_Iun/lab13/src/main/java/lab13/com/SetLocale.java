package lab13.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private Locale currentLocale;
    private ResourceBundle messages;
    
    public SetLocale() {
        this.currentLocale = Locale.getDefault();
        loadResourceBundle();
    }
    
    public void execute(String localeTag) {
        try {
            if (localeTag == null || localeTag.trim().isEmpty()) {
                System.out.println("Current locale: " + currentLocale.toString() + 
                                 " (" + currentLocale.getDisplayName() + ")");
                return;
            }
            
            Locale newLocale = Locale.forLanguageTag(localeTag);
            if (newLocale.getLanguage().isEmpty()) {
                // Try to parse as language_country format
                String[] parts = localeTag.split("_");
                if (parts.length == 2) {
                    newLocale = new Locale.Builder()
                        .setLanguage(parts[0])
                        .setRegion(parts[1])
                        .build();
                } else if (parts.length == 1) {
                    newLocale = new Locale.Builder()
                        .setLanguage(parts[0])
                        .build();
                }
            }
            
            this.currentLocale = newLocale;
            Locale.setDefault(newLocale);
            loadResourceBundle();
            
            String message = messages.getString("locale.set");
            System.out.println(java.text.MessageFormat.format(message, currentLocale.toString()));
            
        } catch (Exception e) {
            System.out.println("Error setting locale: " + e.getMessage());
        }
    }
    
    private void loadResourceBundle() {
        try {
            messages = ResourceBundle.getBundle("lab13.res.Messages", currentLocale);
        } catch (Exception e) {
            messages = ResourceBundle.getBundle("lab13.res.Messages", Locale.ENGLISH);
        }
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    public ResourceBundle getMessages() {
        return messages;
    }
}