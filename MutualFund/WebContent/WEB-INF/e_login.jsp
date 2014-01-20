<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<div class="row">
<div class="col-md-4"></div>
 <div class="col-md-4">
 <p>
	<form class="form-signin" method="post" action="admin_login.do">
        <h4 class="form-signin-heading">Dear employee, please sign in</h2>
        <input type="text" class="form-control" name="userName" placeholder="Email address" vale="${form.userName}" autofocus="">
        <br/>
        <input type="password" class="form-control" name ="password" placeholder="Password" value="">
        
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> <h5>Remember me</h5>
        </label>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
</p>
 </div>
  <div class="col-md-4"></div>

</div>

<jsp:include page="template-bottom.jsp" />
