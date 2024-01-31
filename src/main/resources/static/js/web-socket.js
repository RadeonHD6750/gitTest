//const socketHostUrl = "ws://" + hostUrl + "/socket";
const socketHostUrl = "ws://192.168.0.100:29001"
const socket = new WebSocket(socketHostUrl);

const ackData = {
"msg" : "ok"
};

// 커넥션이 제대로 생성되었을 때
socket.onopen = function(e)
{
    alert(socketHostUrl + "에 접속완료");
    socket.send(JSON.stringify(ackData));
};


socket.onmessage = function(event) {
    console.log(`[message] 서버로부터 전송받은 데이터`);
    console.log(event.data);
};

socket.onclose = function(event) {
    if (event.wasClean)
    {
        alert(`[close] 커넥션이 정상적으로 종료되었습니다(code=${event.code} reason=${event.reason})`);
    }
    else
    {
        // 예시: 프로세스가 죽거나 네트워크에 장애가 있는 경우
        // event.code가 1006이 됩니다.
        alert('[close] 커넥션이 죽었습니다.');
    }
};

socket.onerror = function(error) {
    alert(`[error]`);
};