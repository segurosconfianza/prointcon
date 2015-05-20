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
      			
      			<!-- Definition of the model Log-->
				<div class="modal fade" id="myModalLog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
				      </div>
				      <div class="modal-body">
				        <form name="formInsert" class="form-horizontal" role="form">
				        	<div class="form-group">
							  <label for="slogcons" class="col-sm-2 control-label">{{ whatClassIsIt("slogcons") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="slogcons" id="slogcons" ng-model="slogcons" readonly="readonly" value="{{slogcons}}">
			  			  	  </div>
							</div>
			   				<div class="form-group">
							  <label for="slogtran" class="col-sm-2 control-label">{{ whatClassIsIt("slogtran") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="slogtran" id="slogtran" ng-model="slogtran" readonly="readonly" value="{{slogtran}}">
							  </div>
							</div>							                 				
							<div class="form-group">
							  <label for="slogtabl" class="col-sm-2 control-label">{{ whatClassIsIt("slogtabl") }}</label>
							  <div class="col-sm-10">
							  	<input type="text" name ="slogtabl" id="slogtabl" ng-model=slogtabl readonly="readonly" value="{{slogtabl}}">
							  </div>
							</div>
							<div class="form-group">
							  <label for="slogacci" class="col-sm-2 control-label">{{ whatClassIsIt("slogacci") }}</label>
							  <div class="col-sm-10">
							  	<input type="text" name ="slogacci" id="slogacci" ng-model="slogacci" readonly="readonly" value="{{slogacci}}">
							  </div>
							</div>
							<div class="form-group">
							  <label for="slogregi" class="col-sm-2 control-label">{{ whatClassIsIt("slogregi") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="slogregi" id="slogregi" ng-model="slogregi" readonly="readonly">{{slogregi}}</textarea>
							  </div>
							</div>
			  			</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				      </div>
				    </div>
				  </div>
				</div>      			
		    </tab>
		    <tab heading="{{ventanaTitulo}}" ng-controller="FrmAuditoriaController" ng-click="fixGridRendering()">
		      	<h3>{{ventanaTitulo}}&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalAudito"> Ver <span class="glyphicon glyphicon-eye-open"> </span></button></h3>	                
      			<div class="gridStyle" ng-grid="gridOptions"></div>
      			
      			<!-- Definition of the model Log-->
				<div class="modal fade" id="myModalAudito" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
				      </div>
				      <div class="modal-body">
				        <form name="formInsert" class="form-horizontal" role="form">
				        	<div class="form-group">
							  <label for="audicons" class="col-sm-2 control-label">{{ whatClassIsIt("audicons") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="audicons" id="audicons" ng-model="audicons" readonly="readonly" value="{{audicons}}">
			  			  	  </div>
							</div>
			   				<div class="form-group">
							  <label for="auditran" class="col-sm-2 control-label">{{ whatClassIsIt("auditran") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="auditran" id="auditran" ng-model="auditran" readonly="readonly" value="{{auditran}}">
							  </div>
							</div>							                 				
							<div class="form-group">
							  <label for="auditabl" class="col-sm-2 control-label">{{ whatClassIsIt("auditabl") }}</label>
							  <div class="col-sm-10">
							  	<input type="text" name ="auditabl" id="auditabl" ng-model=auditabl readonly="readonly" value="{{auditabl}}">
							  </div>
							</div>
							<div class="form-group">
							  <label for="audicopk" class="col-sm-2 control-label">{{ whatClassIsIt("audicopk") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="audicopk" id="audicopk" ng-model="audicopk" readonly="readonly">{{audicopk}}</textarea>
							  </div>
							</div>
							<div class="form-group">
							  <label for="audicamp" class="col-sm-2 control-label">{{ whatClassIsIt("audicamp") }}</label>
							  <div class="col-sm-10">
							  	<input type="text" name ="audicamp" id="audicamp" ng-model="audicamp" readonly="readonly" value="{{audicamp}}">
							  </div>
							</div>
							<div class="form-group">
							  <label for="audivaan" class="col-sm-2 control-label">{{ whatClassIsIt("audivaan") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="audivaan" id="audivaan" ng-model="audivaan" readonly="readonly">{{audivaan}}</textarea>
							  </div>
							</div>
							<div class="form-group">
							  <label for="audivanu" class="col-sm-2 control-label">{{ whatClassIsIt("audivanu") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="audivanu" id="audivanu" ng-model="audivanu" readonly="readonly">{{audivanu}}</textarea>
							  </div>
							</div>
			  			</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				      </div>
				    </div>
				  </div>
				</div>  
		    </tab>
		    <tab heading="{{ventanaTitulo}}" ng-controller="SopMotivoController" ng-click="fixGridRendering()">
		      	<h3>{{ventanaTitulo}}&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalMotivo"> Ver <span class="glyphicon glyphicon-eye-open"> </span></button></h3>	                
      			<div class="gridStyle" ng-grid="gridOptions"></div>
      			
      			<!-- Definition of the model Log-->
				<div class="modal fade" id="myModalMotivo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
				      </div>
				      <div class="modal-body">
				        <form name="formInsert" class="form-horizontal" role="form">
				        	<div class="form-group">
							  <label for="moticons" class="col-sm-2 control-label">{{ whatClassIsIt("moticons") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="moticons" id="moticons" ng-model="moticons" readonly="readonly" value="{{moticons}}">
			  			  	  </div>
							</div>
			   				<div class="form-group">
							  <label for="motitran" class="col-sm-2 control-label">{{ whatClassIsIt("motitran") }}</label>
							  <div class="col-sm-10">
							  	<input type="number" name ="motitran" id="motitran" ng-model="motitran" readonly="readonly" value="{{motitran}}">
							  </div>
							</div>		
							<div class="form-group">
							  <label for="motidesc" class="col-sm-2 control-label">{{ whatClassIsIt("motidesc") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="motidesc" id="motidesc" ng-model="motidesc" readonly="readonly">{{motidesc}}</textarea>
							  </div>
							</div>
							<div class="form-group">
							  <label for="adjunomb" class="col-sm-2 control-label">{{ whatClassIsIt("adjunomb") }}</label>
							  <div class="col-sm-10">
							  	<textarea class="form-control" rows="3" name ="adjunomb" id="adjunomb" ng-model="adjunomb" readonly="readonly">{{adjunomb}}</textarea>
							  </div>
							</div>												                 			
							<div class="form-group">
							  <label for="adjuuser" class="col-sm-2 control-label">{{ whatClassIsIt("adjuuser") }}</label>
							  <div class="col-sm-10">
							  	<input type="text" name ="adjuuser" id="adjuuser" ng-model=adjuuser readonly="readonly" value="{{adjuuser}}">
							  </div>
							</div>							
			  			</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>				       
				        <a href="{{urlDownload}}adjuarch={{adjuarch}}&adjunomb={{adjunomb}}"><button type="button" class="btn btn-primary btn-sm">Descargar <span class="glyphicon glyphicon-download"></span></button></a>
				      </div>
				    </div>
				  </div>
				</div>  
		    </tab>
		</tabset>      	
		
      </div>	    	       
    </div>
</sec:authorize>      