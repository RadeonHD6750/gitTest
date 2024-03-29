
// MQTT client
var mqttClient= null;

//MQTT info
// 각자 상황에 맞는 host, port, topic 을 사용합니다.
const mqtt_host = "nextit.or.kr";
const mqtt_port = "29001";
const mqtt_clientId = "client-" + parseInt(Math.random() * 100);        // 랜덤 클라이언트 ID
const mqtt_topic = "/IoT/Sensor/#";

// MQTT 클라이언트 연결
function StartMqtt()
{
    mqttClient = new Paho.MQTT.Client(mqtt_host, Number(mqtt_port), mqtt_clientId);

    mqttClient.onConnectionLost = onConnectionLost;
    mqttClient.onMessageArrived = onMessageArrived;

    mqttClient.connect({
        onSuccess : onConnect
        , onFailure : onFailure
    });
}

// 연결 성공 시 실행되는 function
function onConnect()
{
    console.log("Connect to " + mqtt_host + " is OK!!");
    console.log("subscribe to " + mqtt_topic);
    mqttClient.subscribe(mqtt_topic);
}

// 연결 실패 시 실행되는 function
function onFailure()
{
    console.log("connet : onFailure..");
}



function onConnectionLost(responseObject)
{
    if (responseObject.errorCode !== 0)
    {
        console.log("onConnectionLost : " + responseObject.errorMessage);

        // 연결 재시도
        StartMqtt();
    }
}

// 메시지 받는 부분
function onMessageArrived(message)
{
    const received_data = JSON.parse(message.payloadString);
    console.log(received_data);

    try
    {
        const today = NowDate();

        const analog = received_data.analog;
        const temp = received_data.temp;
        const hum = received_data.hum;

        const analogRemap = map(analog, 0, 1023, 0, 40);

        if(isReady)
        {
            addData(temp, today);
            addHumData(hum, today);
        }

    }
    catch (e)
    {

    }

}

function map(x, in_min, in_max, out_min, out_max) {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}



// 메시지 보내기
// 각 화면에서 메시지를 보내려면 각 화면에서 아래 function 선언하여 사용
function fncMqttDoSend(sendMsg)
{
    mqttClient.send(mqtt_topic, sendMsg);
}
