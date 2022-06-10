package com.icarus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: icarus
 * @since: 2022/6/5
 */
@SpringBootTest
public class CodeGenerator {
    private  String projectPath = System.getProperty("user.dir");

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://localhost:3306/marker?useUnicode=true&useSSL=false&characterEncoding=utf8",
            "root",
            "123456")
            .typeConvert(new MySqlTypeConvert());

    @Test
    public void generatorCode(){
        FastAutoGenerator
                .create(DATA_SOURCE_CONFIG)
                //全局配置
                .globalConfig(builder -> {
                    builder.author("icarus") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 执行完毕不打开文件夹
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.icarus") // 设置父包名
                            .controller("controller") //生成controller层
                            .entity("entity") //生成实体层
                            .service("service") //生成服务层
                            .mapper("mybatis/mapper"); //生成mapper层
                    // .moduleName("mybatisplus");
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("m_user","m_blog") // 设置需要生成的表名
                            .addTablePrefix("tbl_")// 设置过滤表前缀
                            .serviceBuilder() //开启service策略配置
                            .formatServiceFileName("%sService") //取消Service前的I
                            .controllerBuilder() //开启controller策略配置
                            .enableRestStyle() //配置restful风格
                            .enableHyphenStyle() //url中驼峰转连字符
                            .entityBuilder() //开启实体类配置
                            .enableLombok() //开启lombok
                            .enableChainModel(); //开启lombok链式操作

                })
                //模板配置
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //执行
                .execute();
    }
}

