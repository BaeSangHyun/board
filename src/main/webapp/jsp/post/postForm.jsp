<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Jsp</title>

    <%@include file="/commonJsp/basicLib.jsp"%>
    <script src="${cp}/SE2/js/HuskyEZCreator.js"></script>
    <%--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript" language="javascript"></script>--%>
    <script src="${cp}/js/multifile-master/jquery.MultiFile.min.js"> </script>
    <script src="${cp}/js/multifile-master/ext/jquery.form.js"></script>

    <style>
        .form-control {
            width: 770px;
        }
        #afile3-list {
            width: 770px;
        }
    </style>

    <script type="text/javascript">
        var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
        var fileCnt;

        $(document).ready(function() {
            // Editor Setting
            nhn.husky.EZCreator.createInIFrame({
                oAppRef : oEditors, // 전역변수 명과 동일해야 함.
                elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
                sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
                fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
                htParams : {
                    // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseToolbar : true,
                    // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseVerticalResizer : true,
                    // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseModeChanger : true,
                }
            });

            // 전송버튼 클릭이벤트
            $("#savebutton").click(function(){
                if(confirm("저장하시겠습니까?")) {
                    // id가 smarteditor인 textarea에 에디터에서 대입
                    oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

                    // 이부분에 에디터 validation 검증
                    if(validation()) {
                        $("#frm").submit();
                        /*var fileInput = document.getElementById("files");
                        var files = fileInput.files;
                        for(var i = 0; i < files.length; i++) {
                            file = files[i];
                            alert(file);
                        }*/
                    }
                }
            });
            fileCnt = $('#fileList label').length;
            if(fileCnt >= 5) {
                $('#files').prop("disabled", true);
            }

            $('#files').MultiFile({
                max: 5 - fileCnt, //업로드 최대 파일 갯수 (지정하지 않으면 무한대)
                accept: 'jpg|png|gif', //허용할 확장자(지정하지 않으면 모든 확장자 허용)
                maxfile: 1024, //각 파일 최대 업로드 크기
                maxsize: 3024,  //전체 파일 최대 업로드 크기
                STRING: { //Multi-lingual support : 메시지 수정 가능
                    remove: "<span class=\"glyphicon glyphicon-remove del\"></span>", //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
                    duplicate: "$file 은 이미 선택된 파일입니다.",
                    denied: "$ext 는(은) 업로드 할수 없는 파일확장자입니다.",
                    selected: '$file 을 선택했습니다.',
                    toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)",
                    toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
                    toobig: "$file 은 크기가 매우 큽니다. (max $size)"
                },
                list: "#afile3-list"
            });
        });
        
        function delFile(idx) {
            $.ajax({
                url : '/delFile',
                type : 'post',
                data : { 'fileId' : idx },
                dataType : 'json',
                success : function (data) {
                    alert(data.res);
                    $('#file'+idx).empty();
                    location.reload();
                }, error : function (xhr) {
                    alert("상태 : " + xhr.status);
                }
            })
        }

        // 필수값 Check
        function validation(){
            var contents = $.trim(oEditors[0].getContents());
            if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음.
                alert("내용을 입력하세요.");
                oEditors.getById['smarteditor'].exec('FOCUS');
                return false;
            }

            return true;
        }

    </script>
</head>

<body>
<%@include file="/commonJsp/header.jsp"%>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="/commonJsp/left.jsp"%>
        </div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <div class="blog-header">
            <h1 class="blog-title">게시글 등록</h1>
        </div>

        <div class="row">

            <div class="col-sm-8 blog-main">
                <form action="${cp}/postForm" method="post" id="frm" role="form" enctype="multipart/form-data">
                    <input type="hidden" name="boardId" value="${param.boardId}">
                    <input type="hidden" name="postId" value="${param.postId}">
                    <input type="hidden" name="pa_postId" value="${pa_postId}">

                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" class="form-control" value="${post.TITLE}"><br>
                    <textarea name="cont" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">${post.CONT}</textarea>
                    <%--<input multiple="multiple" type="file" class="form-control" name="fileName[]" maxlength="5"><br>--%>
                    <%--<input type="file" name="files" class="multi with-preview" maxlength="5" multiple/>--%>
                    <div id="fileList">
                        <c:forEach items="${postFile}" var="file">
                            <div id="file${file.FILEID}">
                                <span onclick="delFile(${file.FILEID})" class="glyphicon glyphicon-remove del"></span>
                                <label id="post_cont" class="control-label">${file.FILENM}</label><br>
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                    <div>
                    <input type="file" id="files" name="files" class="afile3 with-preview" multiple/>
                    <div id="afile3-list" style="border:2px solid #c9c9c9;min-height:50px"></div>
                    </div>
                    <%--<input type="file" name="files" multiple="multiple">--%>
                </form>
                <br>
                <button type="button" id="savebutton" class="btn btn-primary">등록</button>
            </div>
            <!-- /.blog-main -->
        </div>	</div>
    </div>
</div>
</body>
</html>
