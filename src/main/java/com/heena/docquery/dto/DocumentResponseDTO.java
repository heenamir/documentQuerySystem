package com.heena.docquery.dto;

import com.heena.docquery.model.DocumentType;
import com.heena.docquery.parser.ParsedMetadata;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponseDTO {
    private String fileName;
    private DocumentType type;
    private String content;
    private ParsedMetadata metadata;
}
