package com.unicorn.sg.demo.rest;

public class ProcessUtils {

    public static final String TEST_PROCESS_XML = """
                <?xml version="1.0" encoding="UTF-8"?>
                <Unavailability_MarketDocument
                        xmlns="urn:iec62325.351:tc57wg16:451-6:outagedocument:3:0"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xsi:schemaLocation="urn:iec62325.351:tc57wg16:451-6:outagedocument:3:0 iec62325-451-6-outage.xsd">
                
                    <mRID>2015-000001</mRID>
                    <revisionNumber>2</revisionNumber>
                    <type>A78</type>
                    <process.processType>A26</process.processType>
                    <createdDateTime>2015-08-17T17:00:00Z</createdDateTime>
                    <sender_MarketParticipant.mRID codingScheme="A01">10XCH-SWISSGRIDC</sender_MarketParticipant.mRID>
                    <sender_MarketParticipant.marketRole.type>A04</sender_MarketParticipant.marketRole.type>
                    <receiver_MarketParticipant.mRID codingScheme="A01">10X1001A1001A450</receiver_MarketParticipant.mRID>
                    <receiver_MarketParticipant.marketRole.type>A32</receiver_MarketParticipant.marketRole.type>
                
                    <unavailability_Time_Period.timeInterval>
                        <start>2015-09-01T08:00Z</start>
                        <end>2015-09-02T10:00Z</end>
                    </unavailability_Time_Period.timeInterval>
                
                    <TimeSeries>
                
                        <mRID>1</mRID>
                        <businessType>A53</businessType>
                
                        <in_Domain.mRID codingScheme="A01">10YCH-SWISSGRIDZ</in_Domain.mRID>
                        <out_Domain.mRID codingScheme="A01">10YAT-APG------L</out_Domain.mRID>
                
                        <start_DateAndOrTime.date>2015-09-01</start_DateAndOrTime.date>
                        <start_DateAndOrTime.time>08:00:00Z</start_DateAndOrTime.time>
                
                        <end_DateAndOrTime.date>2015-09-02</end_DateAndOrTime.date>
                        <end_DateAndOrTime.time>10:00:00Z</end_DateAndOrTime.time>
                
                        <quantity_Measure_Unit.name>MAW</quantity_Measure_Unit.name>
                
                        <curveType>A03</curveType>
                
                        <Asset_RegisteredResource>
                            <mRID codingScheme="A01">10T-AT-CH-00003T</mRID>
                        </Asset_RegisteredResource>
                
                        <Available_Period>
                            <timeInterval>
                                <start>2015-09-01T08:00Z</start>
                                <end>2015-09-01T10:00Z</end>
                            </timeInterval>
                            <resolution>PT60M</resolution>
                            <Point>
                                <position>1</position>
                                <quantity>100</quantity>
                            </Point>
                            <Point>
                                <position>2</position>
                                <quantity>100</quantity>
                            </Point>
                        </Available_Period>
                
                        <Available_Period>
                            <timeInterval>
                                <start>2015-09-02T08:00Z</start>
                                <end>2015-09-02T10:00Z</end>
                            </timeInterval>
                            <resolution>PT60M</resolution>
                            <Point>
                                <position>1</position>
                                <quantity>100</quantity>
                            </Point>
                            <Point>
                                <position>2</position>
                                <quantity>100</quantity>
                            </Point>
                        </Available_Period>
                
                        <Reason>
                            <code>B24</code>
                        </Reason>
                
                    </TimeSeries>
                
                    <TimeSeries>
                
                        <mRID>2</mRID>
                
                        <businessType>A53</businessType>
                
                        <in_Domain.mRID codingScheme="A01">10YAT-APG------L</in_Domain.mRID>
                        <out_Domain.mRID codingScheme="A01">10YCH-SWISSGRIDZ</out_Domain.mRID>
                
                        <start_DateAndOrTime.date>2015-09-01</start_DateAndOrTime.date>
                        <start_DateAndOrTime.time>08:00:00Z</start_DateAndOrTime.time>
                
                        <end_DateAndOrTime.date>2015-09-02</end_DateAndOrTime.date>
                        <end_DateAndOrTime.time>10:00:00Z</end_DateAndOrTime.time>
                
                        <quantity_Measure_Unit.name>MAW</quantity_Measure_Unit.name>
                
                        <curveType>A03</curveType>
                
                        <Asset_RegisteredResource>
                            <mRID codingScheme="A01">10T-AT-CH-00003T</mRID>
                        </Asset_RegisteredResource>
                
                        <Available_Period>
                            <timeInterval>
                                <start>2015-09-01T08:00Z</start>
                                <end>2015-09-01T10:00Z</end>
                            </timeInterval>
                            <resolution>PT60M</resolution>
                            <Point>
                                <position>1</position>
                                <quantity>100</quantity>
                            </Point>
                            <Point>
                                <position>2</position>
                                <quantity>100</quantity>
                            </Point>
                        </Available_Period>
                
                        <Available_Period>
                            <timeInterval>
                                <start>2015-09-02T08:00Z</start>
                                <end>2015-09-02T10:00Z</end>
                            </timeInterval>
                            <resolution>PT60M</resolution>
                            <Point>
                                <position>1</position>
                                <quantity>100</quantity>
                            </Point>
                            <Point>
                                <position>2</position>
                                <quantity>100</quantity>
                            </Point>
                        </Available_Period>
                
                        <Reason>
                            <code>B24</code>
                        </Reason>
                
                    </TimeSeries>
                
                    <Reason>
                        <code>B19</code>
                    </Reason>
                
                </Unavailability_MarketDocument>
                """;

    public static String createTestProcess() {
        return TEST_PROCESS_XML;
    }
}
