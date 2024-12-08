package com.acousea.backend.core.communicationSystem.domain.nodes.extModules.rtc;

import com.acousea.backend.core.communicationSystem.domain.communication.serialization.SerializableModule;
import com.acousea.backend.core.communicationSystem.domain.communication.serialization.ModuleCode;
import com.acousea.backend.core.communicationSystem.domain.nodes.extModules.ExtModule;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
public class RTCModule extends SerializableModule implements ExtModule {
    public static final String name = "rtc";
    private LocalDateTime currentTime;

    public RTCModule(LocalDateTime currentTime) {
        super(ModuleCode.RTC, serialize(currentTime));
        this.currentTime = currentTime;
    }

    public static RTCModule createDefault() {
        return new RTCModule(LocalDateTime.now());
    }

    private static byte[] serialize(LocalDateTime currentTime) {
        long epochSecond = currentTime.toEpochSecond(ZoneOffset.UTC);
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(epochSecond);
        return buffer.array();
    }

    public static RTCModule fromBytes(ByteBuffer buffer) {
        if (buffer.remaining() < getMinSize()) {
            throw new IllegalArgumentException("Invalid byte array for RTCModule");
        }
        long epochSecond = buffer.getLong();
        LocalDateTime time = LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC);
        return new RTCModule(time);
    }

    @Override
    public int getFullSize() {
        return getMinSize();
    }

    public static int getMinSize() {
        return Long.BYTES; // 8 bytes for epoch seconds
    }

    @Override
    public String toString() {
        return "RTCModule{" +
                "currentTime=" + currentTime +
                '}';
    }
}
