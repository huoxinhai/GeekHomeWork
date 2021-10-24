package com.geek.homework.week05.bean.convert;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 编辑器
 */
public class DatePropertyEditor extends PropertyEditorSupport {

    /**
     *  重写将 String 转为 bean 的方法
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            setValue(parse);
        }
    }
}
