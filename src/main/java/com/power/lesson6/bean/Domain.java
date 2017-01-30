package com.power.lesson6.bean;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by shenli on 2017/1/31.
 */
public interface Domain {

    String getTableName();

    Field[] getFields();

    Serializable getIdField();

}
