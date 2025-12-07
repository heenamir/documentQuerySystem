package com.heena.docquery.parser;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentParser {
    /**
     * Extracts raw textual content from the given file.
     *
     * @param file the uploaded document
     * @return extracted text content
     */
    String extractText(MultipartFile file);

    /**
     * Extracts metadata such as file type, size, page count (if applicable).
     *
     * @param file the uploaded document
     * @return metadata in key-value format
     */
    default ParsedMetadata extractMetadata(MultipartFile file) {
        return ParsedMetadata.builder().build(); // optional subclass overrides
    }
}
