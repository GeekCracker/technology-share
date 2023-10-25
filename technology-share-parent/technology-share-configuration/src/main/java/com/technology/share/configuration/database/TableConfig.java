package com.technology.share.configuration.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class TableConfig implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(TableConfig.class);

    @Resource
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedReader reader = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            //reader = new BufferedReader(new InputStreamReader(new FileInputStream(ResourceUtils.getFile("classpath:sql/createTable.sql"))));
            reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("sql/createTable.sql")));
            StringBuilder sb = new StringBuilder("");
            String line = null;
            String tableName = "";
            while ((line = reader.readLine()) != null) {
                // 如果当前行存在drop语句
                if (line.toLowerCase().contains("drop")) {
                    // 如果是false 表示不删除旧的表 则不拼接删除的sql
                    if ("false".equals("false")) {
                        continue;
                    }
                }
                sb.append(line + "\n");
                if (line.contains("CREATE TABLE")) {
                    tableName = line.toLowerCase().replaceAll(" ", "").replaceAll("createtableifnotexists", "")
                            .replaceAll("`", "").replaceAll("\\(", "");
                }
                if (line.endsWith(";")) {
                    System.out.println(sb.toString());
                    preparedStatement = connection.prepareStatement(sb.toString());
                    preparedStatement.executeUpdate();
                    logger.info("创建" + tableName + "表成功");
                    sb = new StringBuilder("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
