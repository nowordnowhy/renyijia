package com.renyijia.common.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-01-28
 * @email : zhou_wenya@163.com
 */
public class MysqlGenerator {
    private static final String PACKAGE_NAME = "com.renyijia";
    private static final String MODULE_NAME = "modules";
    private static final String OUT_PATH = "/Users/zwy/IdeaProjects/renyijia/user-server-parent/user-server-service/src/main/java";
    private static final String MAPPER_PATH = "/Users/zwy/IdeaProjects/renyijia/user-server-parent/user-server-service/src/main/resources";

    private static final String AUTHOR = "zhouwenya";

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/renyijia?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
//
//    private static final String DRIVER = "com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://rm-2zern29k51653xjef.mysql.rds.aliyuncs.com:3306/dam_resource_s?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
//    private static final String USER_NAME = "dam_product";
//    private static final String PASSWORD = "0A7TJbGwR0v";

//    private static final String URL = "jdbc:mysql://59.110.174.60:3306/app_store?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
//    private static final String USER_NAME = "root";
//    private static final String PASSWORD = "quntum888";

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig().setOutputDir(OUT_PATH)// 输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(false)// XML columList
                        .setAuthor(AUTHOR)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setXmlName("%sMapper").setMapperName("%sMapper")
                // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceDiy")
                // .setControllerName("%sAction")
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig().setDbType(DbType.MYSQL)// 数据库类型
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public DbColumnType processTypeConvert(String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                 if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                                 return DbColumnType.BOOLEAN;
                                 }
                                return super.processTypeConvert(fieldType);
                            }
                        }).setDriverName(DRIVER).setUsername(USER_NAME).setPassword(PASSWORD).setUrl(URL))
                .setStrategy(
                        // 策略配置
                        new StrategyConfig()
                                 .setCapitalMode(true)// 全局大写命名
                                .setDbColumnUnderline(true)// 全局下划线命名
                                // .setTablePrefix(new String[]{"unionpay_"})// 此处可以修改为您的表前缀
                                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                                 .setInclude(new String[] {"information"}) // 需要生成的表
                                // .setExclude(new String[]{"test"}) // 排除生成的表
                                // 自定义实体，公共字段
                                // .setSuperEntityColumns(new String[]{"test_id"})
                                .setTableFillList(tableFillList)
                                // 自定义实体父类
//                                 .setSuperEntityClass("com.baomidou.demo.common.base.BsBaseEntity")
                                // // 自定义 mapper 父类
                                 .setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
                                // // 自定义 service 父类
                                 .setSuperServiceClass("com.baomidou.mybatisplus.service.IService")
                                // // 自定义 service 实现类父类
                                 .setSuperServiceImplClass("com.baomidou.mybatisplus.service.impl.ServiceImpl")
                                // 自定义 controller 父类
//                                 .setSuperControllerClass("com.quntum.Controller")
                                // 【实体】是否生成字段常量（默认 false）
                                // public static final String ID = "test_id";
//                                .setEntityColumnConstant(true)
                                // 【实体】是否为构建者模型（默认 false）
                                // public User setName(String name) {this.name = name; return this;}
//                                .setEntityBuilderModel(true)
                                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                                .setEntityLombokModel(true)
                        // Boolean类型字段是否移除is前缀处理
                         .setEntityBooleanColumnRemoveIsPrefix(true)
                         .setRestControllerStyle(true)
                         .setControllerMappingHyphenStyle(true)
                ).setPackageInfo(
                        // 包配置
                        new PackageConfig().setModuleName(MODULE_NAME).setParent(PACKAGE_NAME)// 自定义包路径
                                .setController("controller")// 这里是控制器包名，默认 web
                                .setXml("mapper").setMapper("mapper")

                ).setCfg(
                        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                        new InjectionConfig() {
                            @Override
                            public void initMap() {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                                this.setMap(map);
                            }
                        }.setFileOutConfigList(
                                Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                                    // 自定义输出文件目录
                                    @Override
                                    public String outputFile(TableInfo tableInfo) {
                                        return MAPPER_PATH + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                                    }
                                })))
                .setTemplate(
                        // 关闭默认 xml 生成，调整生成 至 根目录
                        new TemplateConfig().setXml(null)
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
//                         .setController("...")
//                         .setEntity("...")
//                         .setMapper("...")
//                         .setXml("...")
//                         .setService("...")
//                         .setServiceImpl("...")
                );

        // 执行生成
        mpg.execute();
    }


}
