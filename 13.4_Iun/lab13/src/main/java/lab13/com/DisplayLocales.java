package lab13.com;

import java.util.Locale;

public class DisplayLocales {
    public void execute() {
        System.out.println("Available locales:");
        Locale[] availableLocales = Locale.getAvailableLocales();
        
        int count = 0;
        for (Locale locale : availableLocales) {
            if (count >= 20) break;
            System.out.println("  " + locale.toString() + " - " + locale.getDisplayName());
            count++;
        }
        System.out.println("... (" + availableLocales.length + " total locales available)");
    }
}