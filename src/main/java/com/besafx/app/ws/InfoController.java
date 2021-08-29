package com.besafx.app.ws;

import com.besafx.app.model.MessageResponse;
import com.besafx.app.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class InfoController {

    @MessageMapping("/info/getOS")
    @SendTo("/topic/info")
    public MessageResponse getOs() {
        final String osName = System.getProperties().get("os.name").toString();
        log.debug("OS: {}", osName);
        return MessageResponse.builder()
                .content(osName)
                .type(MessageType.GET_OS)
                .dateCreate(DateTime.now().toDate())
                .build();
    }

    @MessageMapping("/info/printer/list")
    @SendTo("/topic/info")
    public MessageResponse listAllPrinters() {
        final List<String> printers = Arrays.asList(PrintServiceLookup.lookupPrintServices(null, null))
                .stream()
                .map(PrintService::getName)
                .collect(Collectors.toList());
        log.debug("Printers: {}", printers);
        return MessageResponse.builder()
                .content(printers)
                .type(MessageType.LIST_PRINTERS)
                .dateCreate(DateTime.now().toDate())
                .build();
    }
}
