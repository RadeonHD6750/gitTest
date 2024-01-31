package com.jimin.springboot.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

//센서 모니터링용
public class SensorMonitoring implements MqttCallback {

    private MQTTClient client;
    private String broker;
    private String clientID;

    public SensorMonitoring(String broker, String clientID)
    {
        client = new MQTTClient(this, broker, clientID);
        client.connect();
        client.subscribe("/IoT/Sensor/jimin", 0);
    }


    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message arrived : " + new String(mqttMessage.getPayload(), "UTF-8"));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
