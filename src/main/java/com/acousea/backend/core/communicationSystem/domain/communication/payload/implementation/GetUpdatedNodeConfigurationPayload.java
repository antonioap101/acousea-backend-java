package com.acousea.backend.core.communicationSystem.domain.communication.payload.implementation;

import com.acousea.backend.core.communicationSystem.domain.communication.payload.Payload;
import com.acousea.backend.core.communicationSystem.domain.communication.tags.TagType;
import com.acousea.backend.core.communicationSystem.domain.exceptions.InvalidPacketException;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetUpdatedNodeConfigurationPayload implements Payload {
    private List<TagType> tagTypes;

    public GetUpdatedNodeConfigurationPayload(List<TagType> tagTypes) {
        this.tagTypes = tagTypes;
    }

    @Override
    public String encode() {
        StringBuilder builder = new StringBuilder();
        tagTypes.forEach(tagType -> builder.append(tagType.getValue()));
        return builder.toString();
    }

    @Override
    public int getFullLength() {
        return tagTypes.size();
    }

    @Override
    public byte[] toBytes() {
        byte[] bytes = new byte[tagTypes.size()];
        for (int i = 0; i < tagTypes.size(); i++) {
            bytes[i] = (byte) tagTypes.get(i).getValue();
        }
        return bytes;
    }

    public static Payload fromBytes(ByteBuffer buffer) {
        List<TagType> tagTypes = new ArrayList<>();
        while (buffer.hasRemaining()) {
            try {
                tagTypes.add(TagType.fromValue(buffer.get()));
            } catch (InvalidPacketException e) {
                System.out.println("Invalid tag type: " + e.getMessage());
            }
        }
        return new GetUpdatedNodeConfigurationPayload(tagTypes);
    }
}