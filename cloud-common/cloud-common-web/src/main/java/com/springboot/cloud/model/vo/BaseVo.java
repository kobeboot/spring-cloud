package com.springboot.cloud.model.vo;

import com.springboot.cloud.model.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {

    private Long id;

}
