<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<%@ include file="../include/tag.jsp"%>

    <title>妈宝共读管理后台登陆</title>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">请登录</h3>
                    </div>
                    <div class="panel-body">
                        <form id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="电话" name="phone" type="phone" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住状态
                                    </label>
                                </div>
                                <a href="javaScript:void(0);" onclick="submit()" class="btn btn-lg btn-success btn-block">登录</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
	<script type="text/javascript">
		function submit(){
			$("#loginForm").submit();
		}
	</script>
</html>
