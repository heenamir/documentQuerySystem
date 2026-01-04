package com.heena.docquery.mapper;

import com.heena.docquery.dto.DocumentResponseDTO;
import com.heena.docquery.model.DocumentMetadata;
import com.heena.docquery.model.DocumentType;
import com.heena.docquery.parser.ParsedMetadata;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentMetadata toDomainMetadata(ParsedMetadata parsedMetadata) {
        if (parsedMetadata == null) {
            return null;
        }

        DocumentMetadata metadata = new DocumentMetadata();
        metadata.setFileName(parsedMetadata.getFileName());
        metadata.setFileType(parsedMetadata.getFileType());
        metadata.setFileSize(parsedMetadata.getFileSize());
        metadata.setExtra(parsedMetadata.getExtra());

        return metadata;
    }

    public DocumentResponseDTO toResponse(
            String content,
            DocumentMetadata metadata,
            DocumentType type
    ) {
        return DocumentResponseDTO.builder()
                .fileName(metadata.getFileName())
                .type(type)
                .content(content)
                .metadata(metadata)
                .build();
    }
}
