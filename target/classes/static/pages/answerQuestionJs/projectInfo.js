/**
 * Created by Amy on 2018/8/7.
 */
$(function () {
    isLoginFun();
    header();
    $("#ctl01_lblUserName").text(getCookie('userName'));
    getProjectInfo();

});

// 查看项目详细信息
function getProjectInfo() {
    var projectId = getCookie('projectId');

    var url = '/queryProjectById';
    var data = {
        "id": projectId
    };
    commonAjaxPost(true, url, data, getProjectInfoSuccess);

}

// 查看项目详细信息成功
function getProjectInfoSuccess(result) {
    console.log(result)
    if (result.code == "666") {

        var projectInfo = result.data;
        $("#projectNameSpan").text(projectInfo.projectName);
        $("#createTimeSpan").text(projectInfo.creationDate);
        $("#adminSpan").text(projectInfo.createdBy);
        $("#projectContentSpan").text(projectInfo.projectContent);

        var url = "/queryQuestionnaireByProId"
        var data = {
          'projectId':projectInfo.id
        };
        commonAjaxPost(true,url,data,function (result) {
            console.log(result);
            if(result.code =="666") {
                $("#questTableBody").empty();
                if (result.data == null) {
                    var text = "";
                    text += "<tr>";
                    text += "    <td style=\"text-align: center;color: #d9534f\" colspan=\"4\">暂无调查问卷</td>";
                    text += "<tr>";
                    $("#questTableBody").append(text);
                }
                for (var i = 0; i < result.data.length; i++) {
                    var questionnaire = result.data[i];
                    var text = "";
                    text += "<tr>";
                    text += "    <td>"+(i+1)+"</td>";
                    text += "    <td>" + questionnaire.questionName + "</td>";
                    text += "    <td>" + questionnaire.releaseTime + "</td>";
                    text += "<td>";
                    text += "    <a href=\"javascript:void(0)\" id=\"projcetShow" + i + "\" class=\"pull-left copy-items\" onclick=\"editQuest(" + "'" + questionnaire.id + "'" + "," + "'" + questionnaire.questionName + "'" + "," + "'" + questionnaire.questionContent + "'" + "," + "'" + questionnaire.endTime+ "'" + "," + "'" + questionnaire.creationDate + "'" + "," + "'" + questionnaire.dataId + "'" + ")\"><i class=\"icon copy-icon\"></i>修改</a>";
                    text += "</td>";
                    text += "</tr>";
                    $("#questTableBody").append(text);
                }
            }
        })

        // var text = "";
        //     text += "<tr>";
        //     text += "    <td style=\"text-align: center;color: #d9534f\" colspan=\"4\">暂无调查问卷</td>";
        //     text += "</tr>";
        // $("#questTableBody").empty();
        // $("#questTableBody").append(text)

    } else if (result.code == "333") {
        layer.msg(result.message, {icon: 2});
        setTimeout(function () {
            window.location.href = 'login.html';
        }, 1000)
    } else {
        layer.msg(result.message, {icon: 2})
    }
}

//编辑问卷
function editQuest(id, name, content, endTime, creationDate, dataId) {
    var data = {
        "id": id
    };
    commonAjaxPost(true, '/selectQuestionnaireStatus', data, function (result) {
        // if (result.code == "666") {
        //     if (result.data != "5") {
        //         layer.msg('问卷已发布，不可修改', {icon: 2});
        //     } else if (result.data == "5") {
        //         deleteCookie("questionId");
        //         deleteCookie("questionName");
        //         deleteCookie("questionContent");
        //         deleteCookie("endTime");
        //         setCookie("questionId", id);
        //         setCookie("questionName", name);
        //         setCookie("questionContent", content);
        //         setCookie("endTime", endTime);
        //         setCookie("creationDate", creationDate);
        //         setCookie("dataId", dataId);
        //         window.location.href = 'editQuestionnaire.html'
        //     }
        // }
        if (result.code == "666") {
            // if (result.data == "1") {
            //     if ($("#operationAll" + m + n).children("a:first-child").text() == '开启') {
            //         judgeIfChangeStatus(m, n);
            //     }
            //     layer.msg('问卷运行中，不可修改', {icon: 2});
            // } else
            if (result.data != "5") {
                layer.msg('问卷已发布，不可修改', {icon: 2});
            } else {
                // if (result.data != "1") {
                commonAjaxPost(true, '/selectQuestSendStatus', {id: id}, function (result) {
                    //发送过问卷
                    if (result.code == "40003") {
                        setCookie("ifEditQuestType", "false");
                    } else if (result.code == "666") {         //未发送过问卷
                        setCookie("ifEditQuestType", "true");
                    }
                });
                deleteCookie("questionId");
                deleteCookie("questionName");
                deleteCookie("questionContent");
                deleteCookie("endTime");
                setCookie("questionId", id);
                setCookie("questionName", name);
                setCookie("questionContent", content);
                setCookie("endTime", endTime);
                setCookie("creationDate", creationDate);
                setCookie("dataId", dataId);
                window.location.href = 'editQuestionnaire.html'
                // }
            }
        }
        else if (result.code == "333") {
            layer.msg(result.message, {icon: 2});
            setTimeout(function () {
                window.location.href = 'login.html';
            }, 1000)
        } else {
            layer.msg(result.message, {icon: 2})
        }
    });
}

