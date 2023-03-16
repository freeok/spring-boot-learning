package com.pcdd.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author pcdd
 * @date 2021-12-28 14:24:36
 */

public class CodeGenerate {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/jpa_test?" +
            "serverTimezone=Asia/Shanghai" +
            "&useUnicode=true" +
            "&characterEncoding=utf-8" +
            "&useSSL=true";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "root";

    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL, DB_USER, DB_PASS)
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
