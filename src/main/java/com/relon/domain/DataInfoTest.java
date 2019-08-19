package com.relon.domain;

import com.google.protobuf.InvalidProtocolBufferException;

public class DataInfoTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student.Builder studentBuilder = DataInfo.Student.newBuilder();
        studentBuilder.setName("张三");
        studentBuilder.setAge(20);
        studentBuilder.setAddress("北京");
        DataInfo.Student student =
                studentBuilder.build();
        byte[] studentByte = student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(studentByte);
        System.out.println(student1);

    }
}
