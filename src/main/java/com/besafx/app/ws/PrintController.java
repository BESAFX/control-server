package com.besafx.app.ws;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class PrintController {

    @MessageMapping("/print")
    public void printBarcode(final String data) {
        JOptionPane.showMessageDialog(null, "Please wait until process is done.", "Control Server", JOptionPane.INFORMATION_MESSAGE);
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                final PDDocument document = PDDocument.load(Base64.getDecoder().decode(data.getBytes()));
                final PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(document));
                job.print();
                JOptionPane.showMessageDialog(null, "File printed successfully", "Control Server", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | PrinterException e) {
                e.printStackTrace();
            }
        });
    }
}
