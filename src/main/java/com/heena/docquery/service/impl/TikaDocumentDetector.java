package com.heena.docquery.service.impl;

import com.heena.docquery.model.DocumentType;
import com.heena.docquery.service.DocumentDetector;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class TikaDocumentDetector implements DocumentDetector {

    private final Tika tika = new Tika();

    @Override
    public DocumentType detect(MultipartFile file) {
        try {
            String mime = tika.detect(file.getInputStream());
            log.info("Detected MIME type: {}", mime);

            if (mime.equals("application/pdf")) return DocumentType.PDF;
            if (mime.equals("application/json")) return DocumentType.JSON;
            if (mime.startsWith("application/xml") || mime.equals("text/xml")) return DocumentType.XML;
            if (mime.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                return DocumentType.DOCX;
            if (mime.equals("application/msword")) return DocumentType.DOC;
            if (mime.contains("excel")) return DocumentType.XLSX;
            if (mime.startsWith("text/")) return DocumentType.TEXT;

            return DocumentType.UNKNOWN;

        } catch (Exception e) {
            log.error("Failed to detect document type: {}", e.getMessage());
            return DocumentType.UNKNOWN;
        }
    }
}
