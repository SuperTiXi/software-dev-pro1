/**
 * Created by Amy on 2018/8/9.
 */
$(function () {
    isLoginFun();
    header();
    $("#ctl01_lblUserName").text(getCookie('userName'));
    var oTable = new TableInit();
    oTable.Init();
});

//回车事件
$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        getUserList();
    }
});

$('#userManager').on("keydown", function (event) {
    var keyCode = event.keyCode || event.which;
    if (keyCode == "13") {
        //console.log("1111")
        event.preventDefault();
    }
});

function getUserList() {
    $("#userTable").bootstrapTable('refresh');
}

function TableInit() {

    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#userTable').bootstrapTable({
            url: httpRequestUrl + '/admin/queryUserList',         //请求后台的URL（*）
            method: 'POST',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortOrder: "asc",                   //排序方式
            queryParamsType: '',
            dataType: 'json',
            paginationShowPageGo: true,
            showJumpto: true,
            pageNumber: 1, //初始化加载第一页，默认第一页
            queryParams: queryParams,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            pageSize: 10,//单页记录数
            pageList: [10, 20, 30, 40],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列

            columns: [{
                checkbox: true,
                visible: false
            }, {
                field: 'id',
                title: '序号',
                align: 'center',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },
                {
                    field: 'username',
                    title: '用户账号',
                    align: 'center',
                    width: '230px'
                },
                {
                    field: 'password',
                    title: '用户密码',
                    align: 'center'
                }, {
                    field: 'startTime',
                    title: '开始时间',
                    align: 'center'
                }, {
                    field: 'endTime',
                    title: '结束时间',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    events: operateEvents,//给按钮注册事件
                    formatter: addFunctionAlty//表格中增加按钮
                }],
            responseHandler: function (res) {
                console.log(res.toString());
                if(res.code == "666"){
                    var userInfo = res.data.list;
                    // var userInfo=JSON.parse('[{"password":"1","startTime":"2022-05-12T10:09:28","id":"1","endTime":"2022-05-12T10:09:30","username":"aa","status":"1"},{"password":"123","startTime":"2022-05-12T12:10:37","id":"290e08f3ea154e33ad56a18171642db1","endTime":"2022-06-11T12:10:37","username":"aaa","status":"1"},{"password":"1","startTime":"2018-10-24T09:49:00","id":"8ceeee2995f3459ba1955f85245dc7a5","endTime":"2025-11-24T09:49:00","username":"admin","status":"1"},{"password":"aa","startTime":"2022-05-16T12:01:54","id":"a6f15c3be07f42e5965bec199f7ebbe6","endTime":"2022-06-15T12:01:54","username":"aaaaa","status":"1"}]');
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'id': '',
                                "username": '',
                                'password': '',
                                "startTime": '',
                                'endTime': '',
                                'status': ''
                            };

                            dataNewObj.id = userInfo[i].id;
                            dataNewObj.username = userInfo[i].username;
                            dataNewObj.password = userInfo[i].password;
                            dataNewObj.startTime = userInfo[i].startTime;
                            dataNewObj.endTime = userInfo[i].endTime;
                            dataNewObj.status = userInfo[i].status;
                            NewData.push(dataNewObj);
                        }
                        //console.log(NewData)
                    }
                    var data = {
                        total: res.data.total,
                        rows: NewData
                    };

                    return data;
                }

            }

        });
    };

    // 得到查询的参数
    function queryParams(params) {
        var userName = $("#keyWord").val();
        //console.log(userName);
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            userName: userName
        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}


window.operateEvents = {
    //编辑
    'click #btn_count': function (e, value, row, index) {
        id = row.id;
        $.cookie('questionId', id);
    }
};


// 表格中按钮
function addFunctionAlty(value, row, index) {
    var btnText = '';

    btnText += "<button type=\"button\" id=\"btn_look\" onclick=\"resetPassword(" + "'" + row.id + "'" + ")\" style='width: 77px;' class=\"btn btn-default-g ajax-link\">重置密码</button>&nbsp;&nbsp;";

    btnText += "<button type=\"button\" id=\"btn_look\" onclick=\"editUserPage(" + "'" + row.id + "')\" class=\"btn btn-default-g ajax-link\">编辑</button>&nbsp;&nbsp;";

    if (row.status == "1") {
        btnText += "<button type=\"button\" id=\"btn_stop" + row.id + "\" onclick=\"changeStatus(" + "'" + row.id + "'" + ")\" class=\"btn btn-danger-g ajax-link\">关闭</button>&nbsp;&nbsp;";
    } else if (row.status == "0") {
        btnText += "<button type=\"button\" id=\"btn_stop" + row.id + "\" onclick=\"changeStatus(" + "'" + row.id + "'" + ")\" class=\"btn btn-success-g ajax-link\">开启</button>&nbsp;&nbsp;"
    }
    btnText += "<button type=\"button\" id=\"btn_stop" + row.id + "\" onclick=\"deleteUser(" + "'" + row.id + "'" + ")\" class=\"btn btn-danger-g ajax-link\">删除</button>&nbsp;&nbsp;";

    return btnText;
}

//重置密码
function resetPassword(id) {
    deleteCookie("userTitle");
    setCookie("userTitle", "重置密码");
    if (id != '') {
        deleteCookie("userId");
        setCookie("userId", id);
        console.log(id);
    }
    window.location.href = 'createNewUser.html';
}

// 打开创建用户页
function openCreateUserPage(id, value) {

    deleteCookie("userTitle");
    setCookie("userTitle", value);
    if (id != '') {
        deleteCookie("userId");
        setCookie("userId", id);
    }
    window.location.href = 'createNewUser.html';
}

function editUserPage(id) {
    deleteCookie("userTitle");
    setCookie("userTitle", "编辑用户");
    if (id != '') {
        deleteCookie("userId");
        setCookie("userId", id);
        console.log(id);
    }
    window.location.href = 'createNewUser.html';
}
// 修改用户状态（禁用、开启）
function changeStatus(index) {
    var url = '/admin/modifyUserStatus';
    var data ={
      'id':index
    };
    commonAjaxPost(true,url,data,function (result) {
        if (result.code=='666'){
            window.location.reload();
            alert("状态修改成功");

        }
    });
}

//删除用户
function deleteUser(id) {
        var url = '/admin/deleteUserInfoById';
        console.log(url);
        var data = {
            'id': id,
            "username": '',
            'password': '',
            "startTime": '',
            'endTime': '',
            'status': ''
        };
        console.log(data);
        commonAjaxPost(true, url, data, function (result) {

            if (result.code=='666'){
                window.location.reload();
                alert("删除成功");
            }else if (result.code=='333') {
                window.location.href = 'login.html';

            }else{
                alert("删除失败");
            }
        });
}
//导出为Excel
function exportExcel(tableId,fileName) {
    var table = document.getElementById(tableId);
    var excelContent = table.innerHTML;
    var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
    excelFile += "<body><table>";
    excelFile += excelContent;
    excelFile += "</table></body>";
    excelFile += "</html>";
    var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
    var a = document.createElement("a");
    a.download = fileName+".xls";
    a.href = link;
    a.click();
}

function base64 (content) {
    return window.btoa(unescape(encodeURIComponent(content)));
}

function importExcel() {
    document.getElementById("image").click();
}

//导入表格
function fileInput(e) {
    //debugger;
    var files = e.files;
    var fileReader = new FileReader();
    fileReader.onload = function (ev) {
        console.log(e);
        try {
            var data = ev.target.result,
                workbook = XLSX.read(data, {
                    type: 'binary'
                })// 以二进制流方式读取得到整份excel表格对象
            persons = []; // 存储获取到的数据
        } catch (exp) {
            console.log(exp);
            layer.msg('文件类型不正确');
            return;
        }
        // 表格的表格范围，可用于判断表头是否数量是否正确
        var fromTo = '';
        // 遍历每张表读取
        for (var sheet in workbook.Sheets) {
            if (workbook.Sheets.hasOwnProperty(sheet)) {
                fromTo = workbook.Sheets[sheet]['!ref'];
                persons = persons.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
                break; // 如果只取第一张表，就取消注释这行
            }
        }
        if (persons.length != 0) {
            $("#userTable").bootstrapTable('removeAll');
            //传入参数
            for (var i = 0; i < persons.length; i++) {
                let url = "/admin/addUserInfo";
                let data = {
                    "username": persons[i].用户账号,
                    "password": persons[i].用户密码,
                    "startTime": persons[i].开始时间,
                    "stopTime": persons[i].结束时间
                }
                commonAjaxPost(false, url, data)
            }
            window.open("userManage.html")
        }
    };
    // 以二进制方式打开文件
    fileReader.readAsBinaryString(files[0]);
    //window.location.reload();
}

