package lab9;

import lab9.common.dto.CityDto;
import lab9.common.dto.CountryDto;
import lab9.common.dto.ContinentDto;
import lab9.common.repository.CityRepositoryInterface;
import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.repository.ContinentRepositoryInterface;
import lab9.common.factory.AbstractRepositoryFactory;
import lab9.common.factory.RepositoryFactoryProvider;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    public static void main(String[] args) {
        configureLogging();
        
        LOGGER.info("Application starting with AbstractFactory pattern");
        System.out.println("Application starting with AbstractFactory pattern");
        
        try {
            AbstractRepositoryFactory factory = RepositoryFactoryProvider.getFactory();
            LOGGER.info("Using factory: " + factory.getClass().getSimpleName());
            System.out.println("Using factory: " + factory.getClass().getSimpleName());
            
            ContinentRepositoryInterface continentRepo = factory.createContinentRepository();
            CountryRepositoryInterface countryRepo = factory.createCountryRepository();
            CityRepositoryInterface cityRepo = factory.createCityRepository();
            
            demonstrateFactoryPattern(continentRepo, countryRepo, cityRepo);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Application error: " + e.getMessage(), e);
        } finally {
            // Close resources if needed
            LOGGER.info("Application completed");
            System.out.println("Application completed");
        }
    }
    

    private static void demonstrateFactoryPattern(ContinentRepositoryInterface continentRepo, CountryRepositoryInterface countryRepo, CityRepositoryInterface cityRepo) {
        
        System.out.println("=== TESTING REPOSITORY OPERATIONS ===");
        
        // Create a continent using DTO
        ContinentDto europeDto = new ContinentDto(0, "Europe");
        ContinentDto savedContinent = continentRepo.create(europeDto);
        System.out.println("Created continent: " + savedContinent.getName());
        
        // Create a country using DTO  
        CountryDto franceDto = new CountryDto(0, "France", 33, savedContinent.getName());
        CountryDto savedCountry = countryRepo.create(franceDto);
        System.out.println("Created country: " + savedCountry.getName());
        
        // Create a city using DTO
        CityDto parisDto = new CityDto(
            0,                                    
            "Paris",                              
            savedCountry.getName(),               
            true,                                 
            new BigDecimal("48.8566"),           
            new BigDecimal("2.3522"),            
            2161000                              
        );
        
        CityDto savedCity = cityRepo.create(parisDto);
        System.out.println("Created city: " + savedCity.getName() + 
                   " (population: " + savedCity.getPopulation() + ")");
        
        testFindOperations(continentRepo, countryRepo, cityRepo);
        testSearchOperations(cityRepo);
    }
    
    private static void testFindOperations(ContinentRepositoryInterface continentRepo, CountryRepositoryInterface countryRepo, CityRepositoryInterface cityRepo) {
        
        System.out.println("=== TESTING FIND OPERATIONS ===");
        
        // Find continent by ID
        ContinentDto foundContinent = continentRepo.findById(1);
        if (foundContinent != null) {
            System.out.println("Found continent: " + foundContinent.getName());
        }
        
        // Find country by ID
        CountryDto foundCountry = countryRepo.findById(1);
        if (foundCountry != null) {
            System.out.println("Found country: " + foundCountry.getName() + 
                       " (code: " + foundCountry.getCode() + ")");
        }
        
        // Find city by ID
        CityDto foundCity = cityRepo.findById(1);
        if (foundCity != null) {
            System.out.println("Found city: " + foundCity.getName() + 
                       " in " + foundCity.getCountryName() + 
                       " (population: " + foundCity.getPopulation() + ")");
        }
    }
    
    private static void testSearchOperations(CityRepositoryInterface cityRepo) {
        System.out.println("=== TESTING SEARCH OPERATIONS ===");
        
        // Search cities by name pattern
        List<CityDto> citiesStartingWithP = cityRepo.findByName("P");
        System.out.println("Cities starting with 'P': " + citiesStartingWithP.size());
        
        for (CityDto city : citiesStartingWithP) {
            System.out.println("  - " + city.getName() + " in " + city.getCountryName() + 
                       " (pop: " + city.getPopulation() + ")");
        }

    }

    private static void configureLogging() {
        try {
            new java.io.File("logs").mkdirs();
            
            InputStream configFile = App.class.getClassLoader()
                .getResourceAsStream("logging.properties");
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
                LOGGER.info("Logging configured from properties file");
            } else {
                System.err.println("Warning: logging.properties not found");
            }
        } catch (IOException e) {
            System.err.println("Error configuring logging: " + e.getMessage());
            e.printStackTrace();
        }
    }
}