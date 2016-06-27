package com.codepath.simpletodo.custom.serializers;

import com.activeandroid.serializer.TypeSerializer;
import com.codepath.simpletodo.models.Priority;

/**
 * Created by pbabu on 6/26/16.
 */
public class PrioritySerializer extends TypeSerializer {
    @Override
    public Object deserialize(Object data) {
        if(data == null) {
            return null;
        }else {
            return Priority.fromValue((Integer)data);
        }

    }

    @Override
    public Class<?> getDeserializedType() {
        return Priority.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return Integer.class;
    }

    @Override
    public Object serialize(Object data) {
        if(data == null) {
            return null;
        }else {
            return ((Priority)data).getValue();
        }
    }
}
