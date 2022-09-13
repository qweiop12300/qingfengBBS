package com.chenbaolu.qflt.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * 描述 :
 * 创建时间 : 2022/9/13 19:38
 * 作者 : 23128
 */
public class DateDeserializer implements JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Long myDate = je.getAsLong();
        return new Timestamp(myDate);
    }
}
