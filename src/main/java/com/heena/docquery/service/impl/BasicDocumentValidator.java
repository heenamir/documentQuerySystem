package com.heena.docquery.service.impl;

import com.heena.docquery.exception.DocumentValidationException;
import com.heena.docquery.model.DocumentType;
import com.heena.docquery.service.DocumentDetector;
import com.heena.docquery.service.DocumentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BasicDocumentValidator implements DocumentValidator {

    private final DocumentDetector detector;

    @Override
    public void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new DocumentValidationException("File is empty");
        }

        DocumentType type = detector.detect(file);

        if (type == DocumentType.UNKNOWN) {
            throw new DocumentValidationException("Unsupported file type");
        }

        // add more checks later
        // - max size
        // - allowed formats
        // - financial schema validation (for XMLs)
    }
}
