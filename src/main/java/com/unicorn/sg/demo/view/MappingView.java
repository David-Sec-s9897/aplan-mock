package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.transparency_portal.TransparencyPortalRestService;
import com.unicorn.sg.demo.client.transparency_portal.requests.TransformRequest;
import com.unicorn.sg.demo.domain.LogRecord;
import com.unicorn.sg.demo.domain.anotations.Loggable;
import com.unicorn.sg.demo.repository.LogRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class MappingView implements Serializable {
    public static final Logger LOGGER = Logger.getLogger(MappingView.class.getName());

    @RestClient
    transient TransparencyPortalRestService restService;

    String tsdaData;
    String masterData;
    String mapping;
    String mappedResult;

    public static final String TEST_DATA = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><tsDocument><period.timeInterval><start>2024-05-01T22:00Z</start><end>2024-05-02T22:00Z</end></period.timeInterval><revisionNumber>1.0.0</revisionNumber><timeSeries><id>silo:33165:315745:Calc</id><period><timeInterval><start>2024-05-01T22:00Z</start><end>2024-05-02T22:00Z</end></timeInterval><resolution>P1D</resolution><point><DatumZeitUTC>2024-05-01T22:00:00</DatumZeitUTC><position>1</position><quantity>0.0</quantity></point></period></timeSeries><timeSeries><id>silo:33609:343912:Calc</id><period><timeInterval><start>2024-05-01T22:00Z</start><end>2024-05-02T22:00Z</end></timeInterval><resolution>P1D</resolution><point><DatumZeitUTC>2024-05-01T22:00:00</DatumZeitUTC><position>1</position><quantity>0.0</quantity></point></period></timeSeries></tsDocument>";
    public static final String TEST_MASTER_DATA = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Balancing_MarketDocument xmlns=\"urn:iec62325.351:tc57wg16:451-6:balancingdocument:3:0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:iec62325.351:tc57wg16:451-6:balancingdocument:3:0 iec62325-451-6-balancing.xsd\"><mRID>silo_33165_315745_raw_001</mRID><revisionNumber>2</revisionNumber><type>A89</type><process.processType>A34</process.processType><sender_MarketParticipant.mRID codingScheme=\"A01\">10XCH-SWISSGRIDC</sender_MarketParticipant.mRID><sender_MarketParticipant.marketRole.type>A04</sender_MarketParticipant.marketRole.type><receiver_MarketParticipant.mRID codingScheme=\"A01\">10X1001A1001A450</receiver_MarketParticipant.mRID><receiver_MarketParticipant.marketRole.type>A32</receiver_MarketParticipant.marketRole.type><createdDateTime>2001-12-17T09:30:47Z</createdDateTime><docStatus><value>A06</value></docStatus><controlArea_Domain.mRID codingScheme=\"A01\">10YCH-SWISSGRIDZ</controlArea_Domain.mRID><TimeSeries><mRID>silo:33165:315745:raw</mRID><businessType>A98</businessType><type_MarketAgreement.type>A02</type_MarketAgreement.type><mktPSRType.psrType>A04</mktPSRType.psrType><flowDirection.direction>A02</flowDirection.direction><currency_Unit.name>EUR</currency_Unit.name><price_Measure_Unit.name>MAW</price_Measure_Unit.name><curveType>A01</curveType></TimeSeries><TimeSeries><mRID>silo:33609:343912:raw</mRID><businessType>A98</businessType><type_MarketAgreement.type>A02</type_MarketAgreement.type><mktPSRType.psrType>A04</mktPSRType.psrType><flowDirection.direction>A01</flowDirection.direction><currency_Unit.name>EUR</currency_Unit.name><price_Measure_Unit.name>MAW</price_Measure_Unit.name><curveType>A01</curveType></TimeSeries></Balancing_MarketDocument>";
    public static final String TEST_MAPPING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"2.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:core=\"http://www.altova.com/MapForce/UDF/core\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:fn=\"http://www.w3.org/2005/xpath-functions\" exclude-result-prefixes=\"core xs fn\"><xsl:template name=\"core:firstCharacter\"><xsl:param name=\"value\" select=\"()\"/><xsl:param name=\"default\" select=\"()\"/><xsl:choose><xsl:when test=\"(fn:string-length($value) = xs:integer('0'))\"><xsl:sequence select=\"$default\"/></xsl:when><xsl:otherwise><xsl:variable name=\"var1_resultof_cast\" as=\"xs:double\" select=\"xs:double(xs:integer('1'))\"/><xsl:sequence select=\"fn:substring($value, $var1_resultof_cast, $var1_resultof_cast)\"/></xsl:otherwise></xsl:choose></xsl:template><xsl:output method=\"xml\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"/\"><xsl:variable name=\"var1_tsDocument\" as=\"node()?\" select=\"tsDocument\"/><Balancing_MarketDocument xmlns=\"urn:iec62325.351:tc57wg16:451-6:balancingdocument:3:0\" xmlns:cl=\"urn:entsoe.eu:wgedi:codelists\"><xsl:for-each select=\"$var1_tsDocument\"><xsl:variable name=\"var2_resultof_first\" as=\"node()\" select=\"*:period.timeInterval[fn:namespace-uri() eq '']\"/><period.timeInterval><start><xsl:sequence select=\"fn:string($var2_resultof_first/*:start[fn:namespace-uri() eq ''])\"/></start><end><xsl:sequence select=\"fn:string($var2_resultof_first/*:end[fn:namespace-uri() eq ''])\"/></end></period.timeInterval></xsl:for-each><xsl:for-each select=\"$var1_tsDocument/*:timeSeries[fn:namespace-uri() eq '']\"><TimeSeries><mRID><xsl:sequence select=\"fn:string(*:id[fn:namespace-uri() eq ''])\"/></mRID><xsl:for-each select=\"*:period[fn:namespace-uri() eq '']\"><xsl:variable name=\"var3_resultof_first\" as=\"node()\" select=\"*:timeInterval[fn:namespace-uri() eq '']\"/><Period><timeInterval><start><xsl:sequence select=\"fn:string($var3_resultof_first/*:start[fn:namespace-uri() eq ''])\"/></start><end><xsl:sequence select=\"fn:string($var3_resultof_first/*:end[fn:namespace-uri() eq ''])\"/></end></timeInterval><resolution><xsl:sequence select=\"xs:string(xs:duration(fn:string(*:resolution[fn:namespace-uri() eq ''])))\"/></resolution><xsl:for-each select=\"*:point[fn:namespace-uri() eq '']\"><Point><position><xsl:sequence select=\"xs:string(xs:integer(fn:string(*:position[fn:namespace-uri() eq ''])))\"/></position><xsl:variable name=\"var4_resultof_first\" as=\"xs:string\"><xsl:call-template name=\"core:firstCharacter\"><xsl:with-param name=\"value\" select=\"'.'\" as=\"xs:string\"/><xsl:with-param name=\"default\" select=\"'.'\" as=\"xs:string\"/></xsl:call-template></xsl:variable><xsl:variable name=\"var5_resultof_first\" as=\"xs:string\"><xsl:call-template name=\"core:firstCharacter\"><xsl:with-param name=\"value\" select=\"','\" as=\"xs:string\"/><xsl:with-param name=\"default\" select=\"','\" as=\"xs:string\"/></xsl:call-template></xsl:variable><procurement_Price.amount><xsl:sequence select=\"xs:string(xs:decimal(fn:translate(format-number(xs:decimal(xs:float(fn:string(*:quantity[fn:namespace-uri() eq '']))), '#,##0.00'), '.,', fn:concat($var4_resultof_first, $var5_resultof_first))))\"/></procurement_Price.amount></Point></xsl:for-each></Period></xsl:for-each></TimeSeries></xsl:for-each></Balancing_MarketDocument></xsl:template></xsl:stylesheet>\n";



    @PostConstruct
    public void init() {
        tsdaData = TEST_DATA;
        masterData = TEST_MASTER_DATA;
        mapping = TEST_MAPPING;
        mappedResult = "";
    }

    public void submitMapping(){
        TransformRequest transformRequest = TransformRequest.builder().data(tsdaData).masterData(masterData).mapping(mapping).build();
        Response response = restService.xsltTransformation(transformRequest);
        mappedResult = "ahoj";
        int statusCode = response.getStatusInfo().getStatusCode();
        LOGGER.log(Level.INFO, "statusCode: " + statusCode);
        //mappedResult = (String) response.getEntity();
    }


    public String getTsdaData() {
        return tsdaData;
    }

    public void setTsdaData(String tsdaData) {
        this.tsdaData = tsdaData;
    }

    public String getMasterData() {
        return masterData;
    }

    public void setMasterData(String masterData) {
        this.masterData = masterData;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getMappedResult() {
        return mappedResult;
    }

    public void setMappedResult(String mappedResult) {
        this.mappedResult = mappedResult;
    }
}