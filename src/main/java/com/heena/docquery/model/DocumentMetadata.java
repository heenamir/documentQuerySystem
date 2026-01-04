package com.heena.docquery.model;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadata {

    private String fileName;
    private String fileType;
    private long fileSize;
    private Map<String, Object> extra;
}
