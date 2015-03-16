<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, FRM_TRANSACCION_ALL, TRANSACCION_READ">
	<div class="row">
      <div class="col-md-6" ng-controller="FrmTransaccionController"><!-- Division grid maestro -->      
      		<h3>{{ventanaTitulo}}</h3>	                
      		<div class="gridStyle" ng-grid="gridOptions"></div>  	    
      </div>
      <div class="col-md-6" ><!-- Division grid detalle -->
      	                 				
      </div>	    	       
    </div>
</sec:authorize>      