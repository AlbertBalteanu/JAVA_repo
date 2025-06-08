package lab13.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    private Locale currentLocale;
    private ResourceBundle messages;
    
    public Info(Locale locale, ResourceBundle messages) {
        this.currentLocale = locale;
        this.messages = messages;
    }
    
    public void execute(String localeTag) {
        Locale targetLocale = currentLocale;
        
        if (localeTag != null && !localeTag.trim().isEmpty()) {
            try {
                targetLocale = Locale.forLanguageTag(localeTag);
                if (targetLocale.getLanguage().isEmpty()) {
                    String[] parts = localeTag.split("_");
                    if (parts.length == 2) {
                        targetLocale = new Locale.Builder()
                            .setLanguage(parts[0])
                            .setRegion(parts[1])
                            .build();
                    } else if (parts.length == 1) {
                        targetLocale = new Locale.Builder()
                            .setLanguage(parts[0])
                            .build();
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid locale tag, using current locale");
            }
        }
        
        displayLocaleInfo(targetLocale);
    }
    
    private void displayLocaleInfo(Locale locale) {
        try {
            String infoMessage = messages.getString("info");
            System.out.println(java.text.MessageFormat.format(infoMessage, locale.toString()));
            System.out.println();
            
            // Country information
            System.out.println("Country: " + locale.getDisplayCountry() + 
                             " (" + locale.getCountry() + ")");
            
            // Language information
            System.out.println("Language: " + locale.getDisplayLanguage() + 
                             " (" + locale.getLanguage() + ")");
            
            // Currency information
            try {
                Currency currency = Currency.getInstance(locale);
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
                System.out.println("Currency: " + currency.getDisplayName() + 
                                 " (" + currency.getCurrencyCode() + ")");
                System.out.println("Currency Symbol: " + currency.getSymbol(locale));
                System.out.println("Example: " + currencyFormat.format(123.45));
            } catch (Exception e) {
                System.out.println("Currency: Not available for this locale");
            }
            
            // Date format symbols
            DateFormatSymbols dfs = new DateFormatSymbols(locale);
            
            // Week days
            String[] weekdays = dfs.getWeekdays();
            System.out.print("Week Days: ");
            for (int i = 1; i < weekdays.length; i++) {
                if (i > 1) System.out.print(", ");
                System.out.print(weekdays[i].toLowerCase());
            }
            System.out.println();
            
            // Months
            String[] months = dfs.getMonths();
            System.out.print("Months: ");
            for (int i = 0; i < months.length - 1; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(months[i].toLowerCase());
            }
            System.out.println();
            
            // Today's date
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
            System.out.println("Today: " + dateFormat.format(new Date()));
            
        } catch (Exception e) {
            System.out.println("Error displaying locale information: " + e.getMessage());
        }
    }
}