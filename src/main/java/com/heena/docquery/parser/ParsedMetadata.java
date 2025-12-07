package com.heena.docquery.parser;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ParsedMetadata {
    private String fileName;
    private String fileType;
    private long fileSize;
    private Map<String, Object> extra; // optional details
}
