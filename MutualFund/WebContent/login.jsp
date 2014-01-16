<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<div class="row">
<div class="col-md-4"></div>
 <div class="col-md-4">
 <p>
	<form class="form-signin">
        <h4 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Email address" required="" autofocus="">
        <br/>
        <input type="password" class="form-control" placeholder="Password" required="">
        
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
