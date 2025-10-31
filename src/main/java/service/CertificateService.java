package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class CertificateService {

    public ByteArrayInputStream generateCertificate(String studentName, String courseTitle, String semester) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph title = new Paragraph("Course Completion Certificate", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n\nThis is to certify that " + studentName +
                    " has successfully completed the course \"" + courseTitle +
                    "\" during the semester " + semester + ".", bodyFont));

            document.add(new Paragraph("\n\nDate: " + java.time.LocalDate.now(), bodyFont));
            document.add(new Paragraph("\n\nSignature: ____________________", bodyFont));

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Could not generate PDF certificate", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}