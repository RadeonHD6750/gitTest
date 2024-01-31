
function selectDepartmentList()
{
    const request = {
        contentType: 'application/json',
        type: "POST",
        url: requestBaseUrl + "/dept/list",
        dataType: "JSON",

        success: function (data) {
            console.log("result : " + data);
            console.log(data);

            let dept_table = document.getElementById('dept_list');  //행을 추가할 테이블

            dept_table.innerHTML = "";

            console.log("dept_table");
            console.log(dept_table);

            for(let i = 0; i < data.length; i++)
            {
                console.log(data[i]);


                const  departmentId = data[i].departmentId;
                const  departmentName = data[i].departmentName;
                const  parentDepartmentName = data[i].parentDepartmentName;
                const  parentId = data[i].parentId;
                const  managerId = data[i].managerId;
                const  createDate = data[i].createDate;
                const  updateDate = data[i].updateDate;



                let temp_row = dept_table.insertRow(dept_table.rows.length);

                console.log("insert row");
                console.log(temp_row);

                let department_id_cell = temp_row.insertCell(0);
                let department_name_cell = temp_row.insertCell(1);
                let parent_department_name_cell = temp_row.insertCell(2);
                let working_cell = temp_row.insertCell(3);

                const button_id = 'dept_button_' + departmentId + '';

                department_id_cell.innerHTML = "<td>"+ departmentId  + "</td>";
                department_name_cell.innerHTML = "<td>"+ departmentName  + "</td>";
                parent_department_name_cell.innerHTML = "<td>"+ parentDepartmentName  + "</td>";
                working_cell.innerHTML = "<input type='button' value='선택하기' id='" + button_id + "'> ";

               
                $("#" + button_id).click(function() {

                    onClickDepartment(departmentId);
                });
                
            }

        },
        error:function(e){
            //에러가 났을 경우 실행시킬 코드+

        }
    };


    $.ajax(request);
}

function onClickDepartment(department_id)
{
    console.log(department_id + "을 누르셨습니다.");
    //alert(department_id + "을 누르셨습니다.");

    $("#dept_no").val(department_id);

    SELECT_EMPLOYEE_LIST();
}