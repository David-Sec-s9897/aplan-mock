package com.unicorn.sg.demo.client.transparency_portal.requests;

import java.nio.charset.StandardCharsets;

public class TransformRequest {
    private byte[] data;
    private byte[] mapping;
    private byte[] masterData;
    private String mRID;
    private String revisionNumber;

    public TransformRequest() {
        //default constructor for jaxB
    }

    public TransformRequest(byte[] data, byte[] mapping, byte[] masterData, String mRID, String revisionNumber){
        this.data = data;
        this.mapping = mapping;
        this.masterData = masterData;
        this.mRID = mRID;
        this.revisionNumber = revisionNumber;
    }

    public static TransformRequestBuilder builder() {
        return new TransformRequestBuilder();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public byte[] getMapping() {
        return mapping;
    }

    public void setMapping(byte[] mapping) {
        this.mapping = mapping;
    }

    public byte[] getMasterData() {
        return masterData;
    }

    public String getmRID() {
        return mRID;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setMasterData(byte[] masterData) {
        this.masterData = masterData;
    }

    public static class TransformRequestBuilder {
        private byte[] data;
        private byte[] mapping;
        private byte[] masterData;
        String mRID;
        String revisionNumber;

        public TransformRequestBuilder data(byte[] data) {
            this.data = data;
            return this;
        }

        public TransformRequestBuilder data(String data) {
            this.data = data.getBytes(StandardCharsets.UTF_8);
            return this;
        }

        public TransformRequestBuilder mapping(byte[] mapping) {
            this.mapping = mapping;
            return this;
        }

        public TransformRequestBuilder mapping(String mapping) {
            this.mapping = mapping.getBytes(StandardCharsets.UTF_8);
            return this;
        }

        public TransformRequestBuilder masterData(byte[] masterData) {
            this.masterData = masterData;
            return this;
        }
        public TransformRequestBuilder masterData(String masterData) {
            this.masterData = masterData.getBytes(StandardCharsets.UTF_8);
            return this;
        }

        public TransformRequestBuilder mRID(String mRID) {
            this.mRID = mRID;
            return this;
        }

        public TransformRequestBuilder revisionNumber(String revisionNumber) {
            this.revisionNumber = revisionNumber;
            return this;
        }

        public TransformRequest build() {
            return new TransformRequest(data, mapping, masterData, mRID, revisionNumber);
        }
    }
}
