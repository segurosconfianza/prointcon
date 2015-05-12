<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, FRM_TRANSACCION_ALL, TRANSACCION_READ">
	<div class="row">
      <div class="col-md-6" ng-controller="FrmTransaccionController"><!-- Division grid maestro -->      
      		<h3>&nbsp;</h3>	                
      		<h3>{{ventanaTitulo}}</h3>
      		<div class="gridStyle" ng-grid="gridOptions"></div>  	    
      </div>
      <div class="col-md-6" ><!-- Division grid detalle -->
      	<tabset>		    
		    <tab heading="{{ventanaTitulo}}" ng-controller="FrmLogController" ng-click="fixGridRendering()">
		      	<h3>{{ventanaTitulo}}&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalLog"> Ver <span class="glyphicon glyphicon-eye-open"> </span></button></h3>	                
      			<div class="gridStyle" ng-grid="gridOptions"></div>      			
		    </tab>
		    <tab heading="{{ventanaTitulo}}" ng-controller="FrmAuditoriaController" ng-click="fixGridRendering()">
		      	<h3>{{ventanaTitulo}}&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalAudito"> Ver <span class="glyphicon glyphicon-eye-open"> </span></button></h3>	                
      			<div class="gridStyle" ng-grid="gridOptions"></div>
		    </tab>
		    <tab heading="{{ventanaTitulo}}" ng-controller="SopMotivoController" ng-click="fixGridRendering()">
		      	<h3>{{ventanaTitulo}}&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalMotivo"> Ver <span class="glyphicon glyphicon-eye-open"> </span></button></h3>	                
      			<div class="gridStyle" ng-grid="gridOptions"></div>
		    </tab>
		</tabset>      	            			
      </div>	    	       
    </div>
</sec:authorize>      