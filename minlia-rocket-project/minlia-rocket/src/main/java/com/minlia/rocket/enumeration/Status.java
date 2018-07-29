package com.minlia.rocket.enumeration;

import com.baomidou.mybatisplus.enums.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "Status",description = "Entity status for business usage")
public enum Status implements IEnum {

    @ApiModelProperty(value = "Disabled")
    DISABLED,//(2,"Disabled"),

    @ApiModelProperty(value = "Enabled")
    ENABLED;//(3,"Enabled");


//    private int ordinal;
//    private String name;
//
//    Status(final int ordinal, final String name) {
//        this.ordinal = ordinal;
//        this.name = name;
//    }

    @Override
    public Serializable getValue() {
        return this.name();
    }

//    public String getName(){
//        return this.name;
//    }

}
