package com.heena.docquery.service.impl;

import com.heena.docquery.dto.DocumentResponseDTO;
import com.heena.docquery.mapper.DocumentMapper;
import com.heena.docquery.model.DocumentMetadata;
import com.heena.docquery.model.DocumentType;
import com.heena.docquery.parser.ParsedMetadata;
import com.heena.docquery.service.DocumentDetector;
import com.heena.docquery.parser.DocumentParser;
import com.heena.docquery.service.DocumentService;
import com.heena.docquery.service.DocumentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentValidator validator;
    private final DocumentDetector detector;
    private final DocumentParser parser;
    private final DocumentMapper mapper;

    @Override
    public DocumentResponseDTO process(MultipartFile file) {
        log.info("Starting document processing: {}", file.getOriginalFilename());

        // 1. Basic validation (size, empty, unsupported)
        validator.validate(file);

        // 2. Detect document type
        DocumentType type = detector.detect(file);
        log.info("Detected document type: {}", type);

        // 3. Extract text (and later: classify → vectorize → store)
        String content = parser.extractText(file);
        ParsedMetadata parsedMetadata = parser.extractMetadata(file);

        // 4. Convert parser metadata → domain metadata
        DocumentMetadata metadata = mapper.toDomainMetadata(parsedMetadata);

        // 5. Build response DTO
        return mapper.toResponse(content, metadata, type);
    }
}
