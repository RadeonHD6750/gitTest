const hostUrl = location.host;
const requestBaseUrl = "http://" + location.host;


function login()
{
    const loginData = getLoginFormData();
    let redirectUrl = "";
    //const token = $("meta[name='_csrf']").attr("content")
    //const header = $("meta[name='_csrf_header']").attr("content");

    console.log("login data");
    console.log(loginData);

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/session-login/login",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(loginData),
        beforeSend : function(xhr) {
            //xhr.setRequestHeader(header, token);
        },
        success: function(data, status, xhr) {
            // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
            // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
            console.log("success");
            console.log(data);

            alert(data.message);
            redirectUrl = data.redirectUrl;
        },
        error: function(xhr, status, error) {
            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에
            // error 콜백이 호출될 수 있습니다.
            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
            // 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
            console.log("error");
            console.log(xhr);
        },
        complete: function(xhr, status) {
            // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
            // try - catch - finally의 finally 구문과 동일합니다.
            console.log("complete");
            console.log(xhr);

            location.href = redirectUrl;
        }
    }


    $.ajax(request);
}

function logout()
{
    const loginData = getLoginFormData();

    let redirectUrl = "";

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/session-login/logout",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(loginData),
        success: function(data, status, xhr) {
            // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
            // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
            console.log("success");
            console.log(data);
            redirectUrl = data.redirectUrl;
            alert(data.message);
        },
        error: function(xhr, status, error) {
            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에
            // error 콜백이 호출될 수 있습니다.
            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
            // 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
            console.log("error");
            console.log(xhr);
        },
        complete: function(xhr, status) {
            // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
            // try - catch - finally의 finally 구문과 동일합니다.
            console.log("complete");
            console.log(xhr);

            location.href = redirectUrl;
        }
    }


    $.ajax(request);
}

function getLoginFormData()
{

    const userId = $('#userId').val();
    const password = $('#password').val();

    const data = {
        "userId" : userId,
        "password" : password
    };

    console.log(data);

    return data;
}