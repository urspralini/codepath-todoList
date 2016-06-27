package com.codepath.simpletodo.custom.serializers;

import com.activeandroid.serializer.TypeSerializer;
import com.codepath.simpletodo.models.Status;

/**
 * Created by pbabu on 6/26/16.
 */
public class StatusSerializer extends TypeSerializer {
    @Override
    public Object deserialize(Object data) {
        if(data == null) {
            return null;
        }else {
            return Status.fromValue((Integer)data);
        }

    }

    @Override
    public Class<?> getDeserializedType() {
        return Status.class;
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
            return ((Status)data).getValue();
        }
    }
}
