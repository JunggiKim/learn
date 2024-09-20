package io.dodn.springboot.core.test.example;

import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Component
@Slf4j
public class DatabaseClean {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    private DataSource dataSource;

    private List<String> findDatabaseTableNames() {
        return jdbcTemplate
                .query("SHOW TABLES", (rs, rowNum) -> rs.getString(1))
                .stream().toList();
    }

    public void all() {
        var tableNames = findDatabaseTableNames();
        if (tableNames.isEmpty()) {
            findDatabaseTableNames();
        }
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        for (String tableName : tableNames) {
            log.info(tableName + " = 삭제 중  ");
            jdbcTemplate.execute(String.format("TRUNCATE TABLE %s", tableName));
        }
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }



//    public void all() {
//        List<String> tables = entityManager.getMetamodel()
//                .getEntities()
//                .stream().map(EntityType::getName)
//                .toList();
////        entityManager.getMetamodel().
//
//
//
//        tables.forEach(table -> {
//            log.info(table + " : data 삭제 중");
//            jdbcTemplate.execute("TRUNCATE table " + table);
//        });
//
//    }

}
