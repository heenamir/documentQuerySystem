package com.heena.docquery.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Builder
public class DocumentMetadata {

    private String fileName;
    private String fileType;
    private long fileSize;
    private Map<String, Object> extra;

    public  DocumentMetadata() {}
}
