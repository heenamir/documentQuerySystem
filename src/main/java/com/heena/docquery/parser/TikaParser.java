package com.heena.docquery.parser;

import com.heena.docquery.exception.DocumentParsingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TikaParser implements DocumentParser {

    private final AutoDetectParser parser = new AutoDetectParser();

    /**
     * Extracts full text from the incoming MultipartFile using Tika.
     * Streams file content; avoids holding whole file in heap.
     */
    @Override
    public String extractText(MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            // BodyContentHandler with no write limit
            ContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();
            parser.parse(in, handler, metadata);
            return handler.toString();
        } catch (Exception e) {
            log.error("Failed to extract text from file [{}]: {}", file.getOriginalFilename(), e.getMessage(), e);
            throw new DocumentParsingException("Failed to extract text", e);
        }
    }

    /**
     * Extract metadata such as filename, content type, size and page count (when available).
     */
    @Override
    public ParsedMetadata extractMetadata(MultipartFile file) {
        Map<String, Object> extra = new HashMap<>();
        String fileName = Optional.ofNullable(file.getOriginalFilename()).orElse("unknown");
        String contentType = file.getContentType();
        long fileSize = file.getSize();

        // Try to parse metadata using a quick Tika metadata extraction (no full content)
        try (InputStream in = file.getInputStream()) {
            Metadata metadata = new Metadata();
            // use a small handler to populate metadata only
            parser.parse(in, new BodyContentHandler(0), metadata);
            extra.put("tika.metadata", metadata.names());
            // Common keys for page count
            Integer pageCount = extractPageCount(metadata);
            if (pageCount != null) {
                extra.put("pageCount", pageCount);
            }
        } catch (Exception e) {
            log.debug("Metadata extraction warning for file [{}]: {}", fileName, e.getMessage());
            // continue — metadata is optional
        }

        return ParsedMetadata.builder()
                .fileName(fileName)
                .fileType(contentType)
                .fileSize(fileSize)
                .extra(extra)
                .build();
    }

    private Integer extractPageCount(Metadata metadata) {
        String[] keys = {"xmpTPg:NPages", "meta:page-count", "pdf:pages", "pages"};
        for (String k : keys) {
            String v = metadata.get(k);
            if (v != null) {
                try {
                    return Integer.valueOf(v.trim());
                } catch (NumberFormatException ignored) { }
            }
        }
        return null;
    }
}
