<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }

        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }

        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }

        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px;
        }
    </style>
    <title>Register</title>
</head>
<body>
<%--<form action="/register/save" method="post" onsubmit="return formCheck(this)">--%>
<form:form modelAttribute="user">
    <div class="title">Register</div>
    <div id="msg" class="msg"><form:errors path="id"/> </div>
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr">
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" placeholder="2020/12/31">
    <label for="">취미</label>
    <input class="input-field" type="text" name="hobby">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <button>회원 가입</button>
</form:form>
<script>
    function formCheck(frm) {
        var msg ='';

        if(frm.id.value.length<3) {
            setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
            return false;
        }

        // if(frm.pwd.value.length<3) {
        //     setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
        //     return false;
        // }

        return true;
    }

    // ↓ 메시지 보여주는 함수.
    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;

        // 값을 잘못 입력했을때 잘못 입력한 값이 선택되게 하는 것.
        if(element) {
            element.select();
        }
    }
</script>
</body>
</html>