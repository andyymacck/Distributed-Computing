import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/file_sharing_db");
        dataSource.setUsername("Fredrick");
        dataSource.setPassword("Fre534ddy");
        return dataSource;
    }

    @Bean
    public CommandLineRunner databaseInitializer(UserRepository userRepository, SharedFilesRepository sharedFilesRepository) {
        return args ->
        };
    
}
