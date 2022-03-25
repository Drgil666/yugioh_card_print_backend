package project.yugioh.card_print.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * @author Gilbert
 * @date 2022/2/24 15:54
 */
@Configuration
public class GridFsConfig {
    @Value("${grid.fs.database}")
    String dbName;

    @Bean
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory databaseFactory, MongoConverter converter) {
        return new GridFsTemplate(databaseFactory, converter, dbName);
    }
}

