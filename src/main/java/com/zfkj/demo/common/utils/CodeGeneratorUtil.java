package com.zfkj.demo.common.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lijunlin
 * @description:自动代码生成器
 * @create: 2020-07-13 14:10
 **/
public class CodeGeneratorUtil {
    public static void main(String[] args) {

        // ================= 必须修改的配置 start =================

        // 数据源配置
        String jdbcUrl = "jdbc:mysql://124.70.150.250:3306/zhifei_wisdom_campus?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String jdbcUsername = "root";
        String jdbcPassword = "ZFKJ#kfb@2021";

        // 父级包名配置
        String parentPackage = "com.zfkj.demo";

        // 生成代码的 @author 值 谁生成代码这里填谁的名字
        String author = "lijunlin";

        // 要生成代码的表名配置
        String[] tables = {
                "base_attachment",
                "sys_auth",
                "sys_base_dict",
                "sys_log",
                "sys_role",
                "sys_role_auth",
                "sys_user",
                "sys_user_role"
        };

        // ================= 必须修改的配置 end =================


        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");// 获取用户目录
        gc.setOutputDir(projectPath + "/codegenerator/src/main/java");// 设置输出目录
        gc.setAuthor(author);// 设置作者名字
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setServiceName("%sService"); // 去掉Service中的I前缀
        gc.setIdType(IdType.AUTO); // 设置主键生成策略
        // 生成完毕后是否打开输出目录
        gc.setOpen(true);
        // 为true时生成entity将继承Model类，单类即可完成基于单表的业务逻辑操作，按需开启
        gc.setActiveRecord(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(jdbcDriver);
        dsc.setUsername(jdbcUsername);
        dsc.setPassword(jdbcPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父级包名，按需修改
        pc.setParent(parentPackage);
        // 设置模块名, 会在parent包下生成一个指定的模块包
        pc.setModuleName(null);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/codegenerator/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(false);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns("id");
        // Controller驼峰连字符，如开启，则requestMapping由 helloWorld 变为 hello-world 默认false
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 开启后将使用lombok注解代替set-get方法，false则生成set-get方法
        strategy.setEntityLombokModel(true);
        // 在实体类中移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
