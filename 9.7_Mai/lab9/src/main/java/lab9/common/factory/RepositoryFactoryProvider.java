package lab9.common.factory;

import lab9.JDBC.factory.JDBCRepositoryFactory;
import lab9.JPA.factory.JPARepositoryFactory;
import java.io.InputStream;
import java.util.Properties;

public class RepositoryFactoryProvider {
    
    public static AbstractRepositoryFactory getFactory() {
        try {
            Properties props = new Properties();
            InputStream input = RepositoryFactoryProvider.class
                .getClassLoader().getResourceAsStream("app.properties");
            
            if (input != null) {
                props.load(input);
                String type = props.getProperty("repository.type", "JPA");
                
                if ("JDBC".equalsIgnoreCase(type)) {
                    return new JDBCRepositoryFactory();
                }
            }
            
            return new JPARepositoryFactory();
            
        } catch (Exception e) {
            // daca ceva, je pe a
            return new JPARepositoryFactory();
        }
    }
}
