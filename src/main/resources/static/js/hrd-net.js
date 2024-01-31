

function SELECT_TRAINING_API_LIST(request_url)
{
    const srchTraStDt = $('#srchTraStDt').val();
    const srchTraEndDt = $('#srchTraEndDt').val();

    const name = $("#userName").val();

    const request_data = {
        "srchTraStDt" : srchTraStDt,
        "srchTraEndDt" : srchTraEndDt,
    };

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/SELECT_TRAINING_ALL_LIST",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(request_data), //전송 데이터

        success: function (data_list) {

            console.log(data_list);

            let write_html = "";

            $('#training_result').html("");

            for(let i = 0; i < data_list.length; i++)
            {
                let temp = data_list[i];

                createTrainingCard(temp);

                const trprId = temp.trprId;
                const titleLink = temp.titleLink;

                const button_id = 'training_button_' + trprId;

                $("#" + button_id).click(function() {
                    onClickTraining(trprId, titleLink);
                });
            }


        },
        error:function(e){

        }
    };


    $.ajax(request);
}

function SELECT_TRAINING_DOING_LIST()
{
    SELECT_TRAINING_API_LIST("http://localhost:8080/SELECT_TRAINING_DOING_LIST");
}

function SELECT_TRAINING_ALL_LIST()
{
    SELECT_TRAINING_API_LIST("http://localhost:8080/SELECT_TRAINING_ALL_LIST");
}

function SELECT_TRAINING_RECRUIT_LIST()
{
    SELECT_TRAINING_API_LIST("http://localhost:8080/SELECT_TRAINING_RECRUIT_LIST");
}

function SELECT_TRAINING_DONE_LIST()
{
    SELECT_TRAINING_API_LIST("http://localhost:8080/SELECT_TRAINING_DONE_LIST");
}

function createTrainingCard(temp)
{
    const title = temp.title;
    const traStartDate = temp.traStartDate;
    const traEndDate = temp.traEndDate;
    const titleIcon = temp.titleIcon;
    const realMan = temp.realMan;
    const trprId = temp.trprId;
    const trainTarget = temp.trainTarget;
    const stateCode = temp.stateCode;

    //console.log(titleIcon);

    let recruit_state_str = "진행중";
    let class_state_str = "doing";

    switch (stateCode)
    {
        case 1:
            recruit_state_str = "모집중";
            class_state_str = "recruiting";
            break;
            
        case 2:
            recruit_state_str = "훈련종료";
            class_state_str = "done";
            break;
    }

    console.log("훈련과정 코드 " + trprId);

    const button_id = 'training_button_' + trprId;

    let html = "<div class='training_card'>";
    html += "<h3>" + title + " </h3>";
    html += "<div class='" + class_state_str  +"'>" + recruit_state_str  + "</div>";
    html += "<h5> 시작일: " + traStartDate +  " ~ 종료일:" + traEndDate   + "</h5>";
    html += "<p>훈련대상: " + trainTarget  +"</p>";
    html += "<p>수강비: " + realMan  +"원</p>";
    html += "<input class='detail_button' type='button' id='" + button_id + "' value='과정상세보기 + '> ";
    html += "</div>";

    $('#training_result').append(html);
}

function SELECT_TRAINING_DETAIL_ONE()
{
    const trprId_value = $("#trprId").val();

    console.log("trprId : " + trprId_value);

}

function onClickTraining(trprId, titleLink)
{
    console.log(trprId + "을 누르셨습니다.");

    $("#trprId").val(trprId);


    window.open(titleLink);
    //SELECT_TRAINING_DETAIL_ONE();

}

