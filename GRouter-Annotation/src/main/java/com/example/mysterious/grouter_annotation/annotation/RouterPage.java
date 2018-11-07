package com.example.mysterious.grouter_annotation.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解的元注解：
@Target(ElementType.TYPE)
//两个元注解 @Target定义了注解的使用目标
//CONSTRUCTOR：构造器的声明
//FIELD：域声明（包括enum实例）
//LOCAL_VARIABLE：局部变量声明
//METHOD：方法声明
//PACKAGE：包声明
//PARAMETER：参数声明
//TYPE：类、接口（包括注解类型）或enum声明
//ANNOTATION_TYPE：注解声明（应用于另一个注解上）
//TYPE_PARAMETER：类型参数声明（1.8新加入）
//TYPE_USE：类型使用声明（1.8新加入）
@Retention(RetentionPolicy.CLASS)
// @Retention 定义了注解生存期
//注解的生存期在 RetentionPolicy中枚举
//-SOURCE 存在源码中，编译阶段被丢弃
//-CLASS 默认级别，存在类文件中，VM加载时将丢弃
//-RUNTIME 运行时，存在于运行时，可以通过反射API获取
public @interface RouterPage {
    /**
     * path
     */
    String[] path();

    /**
     * 要添加的interceptors
     */
    Class[] interceptors() default {};
}
