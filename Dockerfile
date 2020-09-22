FROM openjdk:8-alpine

LABEL image=scala-exercise

ARG SPARK_VERSION
ARG HADOOP_VERSION
ARG SCALA_VERSION
ARG SBT_VERSION

ENV SPARK_VERSION=${SPARK_VERSION:-2.4.5}
ENV HADOOP_VERSION=${HADOOP_VERSION:-2.7}
ENV SCALA_VERSION ${SCALA_VERSION:-2.11.12}
ENV SBT_VERSION ${SBT_VERSION:-1.2.8}

RUN apk --update add wget tar bash

#Download spark binaries
WORKDIR /
RUN wget --no-verbose https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

#Untar the downloaded binaries
RUN tar -xzf /spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} spark && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

#Set environment variable
ENV PATH="/spark/bin:${PATH}"

WORKDIR /scala_exercise
RUN mkdir -p /scala_exercise/data

ADD src/. /scala_exercise/src/
ADD build.sbt /scala_exercise/
ADD project/. /scala_exercise/project/
ADD target/scala-2.11/spark_exercise.jar /scala_exercise/jars/

#Run jar compilation
#RUN sbt clean assembly
