package com.geek.homework.week05.bean.convert;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Date;

public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //将date类型的字段date设置到propertyEditor中去
        registry.registerCustomEditor(Date.class,"createTime",new DatePropertyEditor());
        registry.registerCustomEditor(Date.class,"modifyTime",new DatePropertyEditor());
    }
}
