package com.unicorn.sg.demo.view.service;

import com.unicorn.sg.demo.domain.Event;
import com.unicorn.sg.demo.domain.EventStaus;
import com.unicorn.sg.demo.view.service.examples.ExampleCodes;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    public static List<Event> getOKEvents() {
        List<Event> events = new ArrayList<>();
        events.add(Event.builder().name("Calculate intervals").overviewMessage("").description("" +
                "<start>2024-10-16T00:00Z</start>\n" +
                "<end>2024-10-17T00:00Z</end>").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Call TSDA").overviewMessage("Loaded 2 Timeseries").description("silo:32624:444309:Calc").code(ExampleCodes.TSDA_RESPONSE).status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Validate inputs").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Merge TS").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Send to Entsoe").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Wait for response").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Validate ACK").overviewMessage("A01 - Document was accepted").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Archive data").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        return events;
    }

    public static List<Event> getFailedEvents() {
        List<Event> events = new ArrayList<>();
        events.add(Event.builder().name("Call TSDA").overviewMessage("Loaded 2 Timeseries").description("silo:32624:444309:Calc").code(ExampleCodes.TSDA_RESPONSE).status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Validate inputs").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Merge TS").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Send to Entsoe").overviewMessage("").description("").code(ExampleCodes.MAPPED_XML).status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Wait for response").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        events.add(Event.builder().name("Validate ACK").overviewMessage("A02 - Document was rejected").description("""
                Data insertion not allowed - Value delivered in Resolution element is not correct. Submission resolution
                                      does not match the configuration. Expected resolution: PT60M Delivered resolution: PT15M
                                        Data Item: Actual Total Load [6.1.A] Dimension: BZN|CH Data provider: SWISSGRID Time Interval: 2014-09-03T22:00:00.000Z/2014-09-04T22:00:00.000Z"""
        ).code(ExampleCodes.ACK_FAILED).status(EventStaus.FAILED).build());
        events.add(Event.builder().name("Archive data").overviewMessage("").description("").code("").status(EventStaus.SUCCESS).build());
        return events;
    }
}
