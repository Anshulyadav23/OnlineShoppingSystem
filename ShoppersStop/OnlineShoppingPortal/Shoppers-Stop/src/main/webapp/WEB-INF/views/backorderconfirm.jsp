<%@include file= "./shared/sidebar.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- column to display personal details -->
<div class="row">
<div class = "col-sm-6">
<div class ="panel panel-primary">
<div class = "panel-heading">
<h4>Items Purchased</h4>
 </div>
 <div class="panel-body">
 <div class="text-center">
 <!-- It will redirect to cart controller -->
 
 <form action="confirmbackorder">
 <input type="hidden" name="user" value="${user }">
  <h2>Confirm your order or not </h2> 
  <input type="submit" class="btn btn-primary"  value="Yes">
  <a href="${pageContext.request.contextPath}/index" class="btn btn-primary">No</a>
  </form>
  
  
  <form action="cancelOrder">
  <input type="hidden" name="user" value="${user }">
  <input type="submit" class="btn btn-primary" name="cancelorder" value="Cancel order">
   </form>
  
  </div>
 <div class="panel-footer">
 <!-- anchor to display the edit of address -->
 
</div>
</div>
</div>
</div>
</div>
<!-- to provide the confirm button after displaying the details -->


<%@include file= "./shared/footer.jsp" %>



