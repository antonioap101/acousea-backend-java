package com.acousea.backend;

import com.acousea.backend.core.shared.domain.crc.CRCUtils;

import java.util.HexFormat;

public class MainTests {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        // 2043000103001a52080000000067530fd6500a010100000200000000005402000035d0
        byte[] bytes = HexFormat.of().parseHex("20730200030018420264004c084080000040800000520800000000386d43bd");
        short crc = CRCUtils.calculate16BitCRC(bytes);
        byte[] crcBytes = new byte[2];
        crcBytes[0] = (byte) ((crc >> 8) & 0xFF);
        crcBytes[1] = (byte) (crc & 0xFF);
        System.out.println("CRC: " + crc);
        // Encode the CRC as a hex string
        String crcHex = HexFormat.of().formatHex(crcBytes);

        System.out.println("CRC Hex: " + crcHex);

    }
}
