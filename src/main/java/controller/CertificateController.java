package controller;

import service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateCertificate(
            @RequestParam String studentName,
            @RequestParam String courseTitle,
            @RequestParam String semester) throws IOException {

        byte[] pdfBytes = certificateService.generateCertificate(studentName, courseTitle, semester).readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}