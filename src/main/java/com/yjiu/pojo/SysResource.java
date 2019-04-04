package com.yjiu.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 
 * </p>
 *
 * @author yjiu123
 * @since 2019-04-03
 */
@TableName("sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;
    @TableField("name")
    private String name;
    @TableField("type")
    private String type;
    @TableField("parent_id")
    private Integer parentId;
    @TableField("permission")
    private String permission;
    @TableField("available")
    private String available;
    @TableField("open")
    private Integer open;


    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "SysResource{" +
        ", sId=" + sId +
        ", name=" + name +
        ", type=" + type +
        ", parentId=" + parentId +
        ", permission=" + permission +
        ", available=" + available +
        ", open=" + open +
        "}";
    }
}
