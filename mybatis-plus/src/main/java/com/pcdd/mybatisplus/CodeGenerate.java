package com.pcdd.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author pcdd
 * @date 2021-12-28 14:24:36
 */
public class CodeGenerate {

    public static final String DB_NAME = "blog_system";
    public static final String DB_URL = STR."jdbc:mysql://localhost:3306/\{DB_NAME}?serverTimezone=Asia/Shanghai";
    public static final String DB_USR = "root";
    public static final String DB_PWD = "root";

    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL, DB_USR, DB_PWD)
                .globalConfig(builder -> {
                    builder.author("pcdd") // 设置作者
                            // .enableSwagger()
                            // .outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .outputDir("D://MPCodeGenerate"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    if (metaInfo.getJdbcType().TYPE_CODE == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.pcdd.mybatis") // 设置父包名
                            // .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper"))
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://MPCodeGenerate")); // 设置 mapperXml 生成路径
                })
                .strategyConfig(builder -> {
                    // Entity 策略配置
                    builder.entityBuilder()
                            .enableFileOverride()
                            .enableLombok(); // 生成 Lombok 注解
                    // Controller 策略配置
                    builder.controllerBuilder() // 生成 @RestController
                            .enableFileOverride()
                            .enableRestStyle();
                    // Service 策略配置
                    builder.serviceBuilder()
                            .enableFileOverride();
                    // Mapper 策略配置
                    builder.mapperBuilder()
                            .enableFileOverride();
                })
                // 使用 Freemarker 引擎模板，默认是 Velocity 引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
