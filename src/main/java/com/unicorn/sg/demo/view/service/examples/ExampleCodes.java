package com.unicorn.sg.demo.view.service.examples;

public class ExampleCodes {
    public static final String TSDA_RESPONSE = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <tsDocument>
                <period.timeInterval>
                    <start>2024-10-16T00:00Z</start>
                    <end>2024-10-17T00:00Z</end>
                </period.timeInterval>
                <revisionNumber>1.0.0</revisionNumber>
                <timeSeries>
                    <id>silo:32624:444309:Calc</id>
                    <period>
                        <timeInterval>
                            <start>2024-10-16T00:00Z</start>
                            <end>2024-10-17T00:00Z</end>
                        </timeInterval>
                        <resolution>P1D</resolution>
                        <point>
                            <DatumZeitUTC>2024-10-16T00:00:00</DatumZeitUTC>
                            <position>1</position>
                            <quantity>0.0</quantity>
                        </point>
                    </period>
                </timeSeries>
                <timeSeries>
                    <id>silo:32624:444306:Calc</id>
                    <period>
                        <timeInterval>
                            <start>2024-10-16T00:00Z</start>
                            <end>2024-10-17T00:00Z</end>
                        </timeInterval>
                        <resolution>P1D</resolution>
                        <point>
                            <DatumZeitUTC>2024-10-16T00:00:00</DatumZeitUTC>
                            <position>1</position>
                            <quantity>0.0</quantity>
                        </point>
                    </period>
                </timeSeries>
            </tsDocument>
            """;
    public static final String MAPPED_XML = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <ns2:Publication_MarketDocument xmlns:cl="urn:entsoe.eu:wgedi:codelists" xmlns:ns2="urn:iec62325.351:tc57wg16:451-3:publicationdocument:7:0">
                <ns2:mRID>silo_32624_444309_202410170715</ns2:mRID>
                <ns2:revisionNumber>20</ns2:revisionNumber>
                <ns2:type>A61</ns2:type>
                <ns2:sender_MarketParticipant.mRID codingScheme="A01">10XCH-SWISSGRIDC</ns2:sender_MarketParticipant.mRID>
                <ns2:sender_MarketParticipant.marketRole.type>A04</ns2:sender_MarketParticipant.marketRole.type>
                <ns2:receiver_MarketParticipant.mRID codingScheme="A01">10X1001A1001A450</ns2:receiver_MarketParticipant.mRID>
                <ns2:receiver_MarketParticipant.marketRole.type>A32</ns2:receiver_MarketParticipant.marketRole.type>
                <ns2:createdDateTime>2001-12-17T09:30:47Z</ns2:createdDateTime>
                <ns2:period.timeInterval>
                    <ns2:start>2024-10-16T00:00Z</ns2:start>
                    <ns2:end>2024-10-17T00:00Z</ns2:end>
                </ns2:period.timeInterval>
                <ns2:TimeSeries>
                    <ns2:mRID>silo:32624:341252:Calc</ns2:mRID>
                    <ns2:businessType>A27</ns2:businessType>
                    <ns2:in_Domain.mRID codingScheme="A01">10YCH-SWISSGRIDZ</ns2:in_Domain.mRID>
                    <ns2:out_Domain.mRID codingScheme="A01">10Y1001A1001A82H</ns2:out_Domain.mRID>
                    <ns2:quantity_Measure_Unit.name>MAW</ns2:quantity_Measure_Unit.name>
                    <ns2:curveType>A01</ns2:curveType>
                    <ns2:Period>
                        <ns2:timeInterval>
                            <ns2:start>2024-10-16T00:00Z</ns2:start>
                            <ns2:end>2024-10-17T00:00Z</ns2:end>
                        </ns2:timeInterval>
                        <ns2:resolution>P1D</ns2:resolution>
                        <ns2:Point>
                            <ns2:position>1</ns2:position>
                            <ns2:quantity>0</ns2:quantity>
                        </ns2:Point>
                    </ns2:Period>
                </ns2:TimeSeries>
                <ns2:TimeSeries>
                    <ns2:mRID>silo:32624:312650:Calc</ns2:mRID>
                    <ns2:businessType>A27</ns2:businessType>
                    <ns2:in_Domain.mRID codingScheme="A01">10Y1001A1001A82H</ns2:in_Domain.mRID>
                    <ns2:out_Domain.mRID codingScheme="A01">10YCH-SWISSGRIDZ</ns2:out_Domain.mRID>
                    <ns2:quantity_Measure_Unit.name>MAW</ns2:quantity_Measure_Unit.name>
                    <ns2:curveType>A01</ns2:curveType>
                    <ns2:Period>
                        <ns2:timeInterval>
                            <ns2:start>2024-10-16T00:00Z</ns2:start>
                            <ns2:end>2024-10-17T00:00Z</ns2:end>
                        </ns2:timeInterval>
                        <ns2:resolution>P1D</ns2:resolution>
                        <ns2:Point>
                            <ns2:position>1</ns2:position>
                            <ns2:quantity>0</ns2:quantity>
                        </ns2:Point>
                    </ns2:Period>
                </ns2:TimeSeries>
            </ns2:Publication_MarketDocument>
            """;
    public static final String ACK_FAILED = """
            <?xml version="1.0" encoding="UTF-8"?>
            <Acknowledgement_MarketDocument
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns="urn:iec62325.351:tc57wg16:451-1:acknowledgementdocument:7:0"
                    xsi:schemaLocation="urn:iec62325.351:tc57wg16:451-1:acknowledgementdocument:7:0 iec62325-451-1-acknowledgement.xsd">
                <mRID>c9a0d3f0-8952-4</mRID>
                <createdDateTime>2014-11-07T10:15:07Z</createdDateTime>
            
                <sender_MarketParticipant.mRID codingScheme="A01">10X1001A1001A450</sender_MarketParticipant.mRID>
                <sender_MarketParticipant.marketRole.type>A32</sender_MarketParticipant.marketRole.type>
            
                <receiver_MarketParticipant.mRID codingScheme="NKG">10XCH-SWISSGRIDC</receiver_MarketParticipant.mRID>
                <receiver_MarketParticipant.marketRole.type>A04</receiver_MarketParticipant.marketRole.type>
            
                <received_MarketDocument.mRID>1415355283200</received_MarketDocument.mRID>
                <received_MarketDocument.revisionNumber>27</received_MarketDocument.revisionNumber>
            
                <received_MarketDocument.createdDateTime>2014-11-07T10:15:06Z</received_MarketDocument.createdDateTime>
            
            
                <Reason>
                    <code>A02</code>
                </Reason>
                <Reason>
                    <code>A41</code>
                    <text>Data insertion not allowed - Value delivered in Resolution element is not correct. Submission resolution
                        does not match the configuration.
                        Expected resolution: PT60M
                        Delivered resolution: PT15M
                        Data Item: Actual Total Load [6.1.A]
                        Dimension: BZN|CH
                        Data provider: SWISSGRID
                        Time Interval: 2014-09-03T22:00:00.000Z/2014-09-04T22:00:00.000Z
                    </text>
                </Reason>
                <Reason>
                    <code>A41</code>
                    <text>Data insertion not allowed - Value delivered in Resolution element is not correct. Submission resolution
                        does not match the configuration.
                        Expected resolution: PT60M
                        Delivered resolution: PT15M
                        Data Item: Actual Total Load [6.1.A]
                        Dimension: BZN|CH
                        Data provider: SWISSGRID
                        Time Interval: 2014-09-04T22:00:00.000Z/2014-09-05T22:00:00.000Z
                    </text>
                </Reason>
                <Reason>
                    <code>A41</code>
                    <text>Data insertion not allowed - Value delivered in Resolution element is not correct. Submission resolution
                        does not match the configuration.
                        Expected resolution: PT60M
                        Delivered resolution: PT15M
                        Data Item: Actual Total Load [6.1.A]
                        Dimension: BZN|CH
                        Data provider: SWISSGRID
                        Time Interval: 2014-09-03T22:00:00.000Z/2014-09-04T22:00:00.000Z
                    </text>
                </Reason>
                <Reason>
                    <code>A41</code>
                    <text>Data insertion not allowed - Value delivered in Resolution element is not correct. Submission resolution
                        does not match the configuration.
                        Expected resolution: PT60M
                        Delivered resolution: PT15M
                        Data Item: Actual Total Load [6.1.A]
                        Dimension: BZN|CH
                        Data provider: SWISSGRID
                        Time Interval: 2014-09-04T22:00:00.000Z/2014-09-05T22:00:00.000Z
                    </text>
                </Reason>
            
            
            </Acknowledgement_MarketDocument>
            """;

}

