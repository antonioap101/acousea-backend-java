package com.acousea.backend.core.communicationSystem.domain.communication.tags.implementation;

import com.acousea.backend.core.communicationSystem.domain.communication.tags.Tag;
import com.acousea.backend.core.communicationSystem.domain.communication.tags.TagType;
import com.acousea.backend.core.communicationSystem.domain.nodes.extModules.ambient.AmbientModule;

import java.nio.ByteBuffer;

public class AmbientTag extends Tag {
    public AmbientTag(byte[] value) {
        super(TagType.TEMPERATURE, value
        );
    }

    public static AmbientTag fromAmbientModule(AmbientModule module) {
        return new AmbientTag(
                ByteBuffer.allocate(Integer.BYTES * 2)
                        .putInt(module.getTemperature())
                        .putInt(module.getHumidity())
                        .array());
    }

    public AmbientModule toAmbientModule() {
        ByteBuffer buffer = ByteBuffer.wrap(this.VALUE);
        return new AmbientModule(buffer.getInt(), buffer.getInt());
    }

    public static AmbientTag fromBytes(ByteBuffer buffer) {
        if (buffer.remaining() < Integer.BYTES * 2) {
            throw new IllegalArgumentException("Invalid byte array for AmbientTag");
        }
        int temperature = buffer.getInt();
        int humidity = buffer.getInt();
        return fromAmbientModule(new AmbientModule(temperature, humidity));
    }
}
