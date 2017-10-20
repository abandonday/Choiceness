package com.zank.choiceness.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Zank on 2017/10/19.
 *
 * @Entity schema：告知GreenDao当前实体属于哪个schema
 * active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法
 * nameInDb：在数据中使用的别名，默认使用的是实体的类名
 * indexes：定义索引，可以跨越多个列
 * createInDb：标记创建数据库表
 * @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
 * @Unique：向数据库列添加了一个唯一的约束
 */
@Entity
public class User {
    /**
     * @Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
     * @Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb="name")
     * @NotNul：设置数据库表当前列不能为空
     * @Transient ：添加次标记之后不会生成数据库表的列
     * @ToOne：定义与另一个实体（一个实体对象）的关系
     * @ToMany：定义与多个实体对象的关系
     */
    @Id
    private Long id;
    private String name;
    @Transient
    private int tempUsageCount; // not persisted

    @Generated(hash = 873297011)
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
