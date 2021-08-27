package com.besafx.app.ws;

import lombok.extern.slf4j.Slf4j;
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
    public String getOs() {
        return System.getProperties().get("os.name").toString();
    }

    @MessageMapping("/info/printer/list")
    @SendTo("/topic/info")
    public List<String> listAllPrinters() {
        return Arrays.asList(PrintServiceLookup.lookupPrintServices(null, null))
                .stream()
                .map(PrintService::getName)
                .collect(Collectors.toList());
    }
}
