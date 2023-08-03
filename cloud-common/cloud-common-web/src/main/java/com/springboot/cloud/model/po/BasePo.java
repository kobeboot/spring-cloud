package com.springboot.cloud.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class BasePo implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Long version;

    @TableLogic
    private boolean removed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePo)) return false;
        BasePo basePo = (BasePo) o;
        return isRemoved() == basePo.isRemoved() && Objects.equals(getId(), basePo.getId()) && Objects.equals(getCreateUser(), basePo.getCreateUser()) && Objects.equals(getCreateTime(), basePo.getCreateTime()) && Objects.equals(getUpdateUser(), basePo.getUpdateUser()) && Objects.equals(getUpdateTime(), basePo.getUpdateTime()) && Objects.equals(getVersion(), basePo.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateUser(), getCreateTime(), getUpdateUser(), getUpdateTime(), getVersion(), isRemoved());
    }

    @Override
    public String toString() {
        return "BasePo{" +
                "id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", removed=" + removed +
                '}';
    }

}
