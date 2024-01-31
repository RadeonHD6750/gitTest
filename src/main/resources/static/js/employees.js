/**
서비스 요청 함수
**/
//사용자 조회하기
function SELECT_EMPLOYEE_ONE()
{
    const employee_id_value = $("#IsUserInput").val();

    console.log("employeeId : " + employee_id_value);

    const send_data = {
        "employeeId" : Number(employee_id_value)
    };
    //
    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/emp/one",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(send_data), //전송 데이터

        success: function (data) {
            //console.log("result : " + data);
            console.log(data);

            const employee_id = data.employeeId;
            const emp_name = data.empName;
            const email = data.email;
            const phone_number = data.phoneNumber;
            const hire_date = data.hireDate;
            const salary = data.salary;
            const department_id = data.departmentId;
            const manager_id = data.managerId;
            const create_date = data.createDate;
            const update_date = data.updateDate;

            $('#employee_id').val(employee_id);
            $('#emp_name').val(emp_name);
            $('#email').val(email);
            $('#phone_number').val(phone_number);
            $('#hire_date').val(hire_date);
            $('#salary').val(salary);
            $('#department_id').val(department_id);
            $('#manager_id').val(manager_id);

            $('#create_date').val(create_date);
            $('#update_date').val(update_date);

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+
            $('#result_area').empty();
            $('#result_area').append(e);
        }
    };


    $.ajax(request);
}

//부서에 딸린 사원들 조회하기
function SELECT_EMPLOYEE_LIST()
{
    const department_id = $("#dept_no").val();

    console.log("dept : " + department_id);

    const send_data = {
        "departmentId" : Number(department_id)
    };

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/emp/list",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(send_data), //전송 데이터

        success: function (data) {
            console.log("result : " + data);
            console.log(data);

            let user_table = document.getElementById('user_list');  //행을 추가할 테이블

            user_table.innerHTML = "";

            console.log("user_table");
            console.log(user_table);

            for(let i = 0; i < data.length; i++)
            {
                //console.log(data[i]);


                const  employee_id = data[i].employeeId;
                const  emp_name = data[i].empName;
                const  department_name = data[i].departmentName;
                const  parent_department_name = data[i].parentDepartmentName;


                let temp_row = user_table.insertRow(user_table.rows.length);

                //console.log("insert row");
                //console.log(temp_row);

                let employee_id_cell = temp_row.insertCell(0);
                let emp_name_cell = temp_row.insertCell(1);
                let department_name_cell = temp_row.insertCell(2);
                let parent_department_name_cell = temp_row.insertCell(3);
                let working_cell = temp_row.insertCell(4);

                const button_id = 'emp_button_' + employee_id;

                employee_id_cell.innerHTML = "<td>"+ employee_id  + "</td>";
                emp_name_cell.innerHTML = "<td>"+ emp_name  + "</td>";
                department_name_cell.innerHTML = "<td>"+ department_name  + "</td>";
                parent_department_name_cell.innerHTML = "<td>"+ parent_department_name  + "</td>";
                working_cell.innerHTML = "<input type='button' value='상세보기' id='" + button_id + "'> ";


                $("#" + button_id).click(function() {

                    //alert(button_id + "을 선택");

                    onClickEmployees(employee_id);
                });
                //추가한 행
            }

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+

        }
    };


    $.ajax(request);
}

function INSERT_EMPLOYEE_ONE()
{

    const insert_data = getEmployeesJSON();

    console.log("INSERT_EMPLOYEE_ONE");
    console.log(insert_data);

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/emp/insert",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(insert_data), //전송 데이터
        success : function (data){

            alert(data.result_data);
            console.log(data);

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+
            alert("에러 발생");
            console.log("INSERT_EMPLOYEE_ONE");
            console.log(e);
        }
    }


    $.ajax(request);


    SELECT_EMPLOYEE_LIST();
}

function UPDATE_EMPLOYEE_ONE()
{
    const update_data = getEmployeesJSON();

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/emp/update",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(update_data), //전송 데이터
        success : function (data){

            if(data.result_code == 200)
            {
                alert(data.result_data);
            }

            console.log("UPDATE_EMPLOYEE_ONE");
            console.log(data);

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+
            alert("에러 발생");
            console.log("UPDATE_EMPLOYEE_ONE ERROR");
            console.log(e);
        }
    }


    $.ajax(request);


    SELECT_EMPLOYEE_LIST();
}



function DELETE_EMPLOYEE_ONE()
{
    const delete_data = getEmployeesJSON();

    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/emp/delete",
        dataType: "JSON",
        async:false,
        data: JSON.stringify(delete_data), //전송 데이터
        success : function (data){

            if(data.result_code == 200)
            {
                alert(data.result_data);
            }

            console.log("DELETE_EMPLOYEE_ONE");
            console.log(data);

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+
            alert("에러 발생");
            console.log("UPDATE_EMPLOYEE_ONE");
            console.log(e);
        }
    }


    $.ajax(request);


    SELECT_EMPLOYEE_LIST();
}



/**
 프론트엔드 함수
 **/
function onClickEmployees(employee_id)
{
    console.log(employee_id + "을 누르셨습니다.");

    $("#IsUserInput").val(employee_id);

    SELECT_EMPLOYEE_ONE();
}

function getEmployeesJSON()
{
    const employee_id = $('#employee_id').val();
    const emp_name = $('#emp_name').val();
    const email = $('#email').val();
    const phone_number = $('#phone_number').val();
    const hire_date = $('#hire_date').val();
    const salary = $('#salary').val();
    const department_id = $('#department_id').val();
    const manager_id = $('#manager_id').val();

    const create_date = $('#create_date').val();
    const update_date = $('#update_date').val();

    const data = {
        "employeeId" : employee_id,
        "empName" : emp_name,
        "email" : email,
        "phoneNumber" : phone_number,
        "hireDate" : hire_date,
        "salary" : salary,
        "departmentId" : department_id,
        "managerId" : manager_id,
        "createDate" : create_date,
        "updateDate" :update_date
    };

    return data;
}
