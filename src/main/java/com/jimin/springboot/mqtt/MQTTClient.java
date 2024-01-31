package com.jimin.springboot.mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MQTTClient{

    private String broker;
    private String clientID;
    private String userName;
    private String password;
    private MqttAsyncClient client;
    private MemoryPersistence persistence;

    private MqttCallback callback;

    private MqttConnectOptions connOpts;

    public MQTTClient(MqttCallback callback, String broker, String clientID)
    {
        this.callback = callback;
        this.broker = broker;
        this.clientID = clientID;
        this.persistence = new MemoryPersistence();
    }

    public MQTTClient(MqttCallback callback, String broker, String clientID, String userName, String password)
    {
        this.callback = callback;
        this.broker = broker;
        this.clientID = clientID;
        this.userName = userName;
        this.password = password;
        this.persistence = new MemoryPersistence();
    }

    public void connect()
    {
        try
        {
            client = new MqttAsyncClient(this.broker, this.clientID, this.persistence);
            client.setCallback(this.callback);

            connOpts = new MqttConnectOptions();
         
            //connOpts.setUserName(this.userName); //필요시에만
            //connOpts.setPassword(this.password.toCharArray()); //필요시에만
     
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+ this.broker);

            client.connect(connOpts);

            System.out.println("Connected");
        }
        catch (MqttException me)
        {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
        }
    }

    public void disconnect()
    {
        try
        {
            client.disconnect();
            client.close();
        }
        catch (MqttException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void subscribe(String topic, int qos)
    {
        try
        {
            client.subscribe(topic,qos);
        }
        catch (MqttException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
