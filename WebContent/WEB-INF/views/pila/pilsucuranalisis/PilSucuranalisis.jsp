<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, PIL_USUA_ALL, PIL_USUA_READ">	
		
	<div class="row">   	
	      <div class="col-md-6" data-ng-controller="PilSucuranalisisController" ng-init="init()"><!-- Division grid maestro -->      	
	        <div class="well well-sm">				
				<button type="button" class="btn btn-default btn-sm"><a href="#"> Ayuda <span class="glyphicon glyphicon-info-sign"> </span></a></button>	
			</div>
			<h3>{{ventanaTitulo}}</h3>
	    	<custom-grid cols="columnDefs" selected-items="selectedItems" custom-options="gridOptions" evento="gridEvento" data-ng-if="directiveGrid"></custom-grid>
	      </div> 
	      <div class="col-md-6"  ng-controller="PilUsuasucuranalisisController" ng-click="fixGridRendering()"><!-- Division grid detalle -->
        	<div class="well well-sm">
				<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_CREATE"><button type="button" class="btn btn-primary btn-sm" data-ng-click="createRecordForm()"> Nuevo <span class="glyphicon glyphicon-file"> </span></button></sec:authorize>
				<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_UPDATE"><button type="button" class="btn btn-success btn-sm" data-ng-click="loadDatatoForm()"  > Editar <span class="glyphicon glyphicon-edit"> </span></button></sec:authorize>
				<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_DELETE"><button type="button" class="btn btn-danger btn-sm"  data-ng-click="deleteRecordForm()" > Inactivar <span class="glyphicon glyphicon-trash"> </span></button></sec:authorize>
				<button type="button" class="btn btn-default btn-sm"><a href="#"> Ayuda <span class="glyphicon glyphicon-info-sign"> </span></a></button>	
			</div>
			<h3>{{ventanaTitulo}}</h3>
	    	<custom-grid cols="columnDefs" selected-items="selectedItems" custom-options="gridOptions" evento="gridEvento" data-ng-if="directiveGrid"></custom-grid>
	    	<!-- ventana modal -->
	    	<!-- Modal New -->
			<div class="modal fade" id="myModalNewChild" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
			      </div>
			      <div class="modal-body">
			        <form name="formInsert" class="form-horizontal" role="form">		        							
						<div class="form-group">
							<label for="usuaunit" class="col-sm-2 control-label">{{ whatClassIsIt("ussuusua") }}</label>
							<div class="col-sm-10">
								<select class="form-control" name ="ussuusua" id="ussuusua" ng-model="ussuusua" ng-options="opt.value as opt.label for opt in optionsAnalistas" data-ng-required="true" data-ng-disabled="buttonDelete"></select>
							</div>
						</div>
						<div class="form-group">
							<label for="usuaunit" class="col-sm-2 control-label">{{ whatClassIsIt("ussusucu") }}</label>
							<div class="col-sm-10">
								<input style="width:100%;" type="text" name ="sucunomb" id="sucunomb" ng-model="sucunomb" ng-readonly="true">
							</div>
						</div> 							 	
						<div class="form-group" data-ng-show="!buttonDelete">
							<label for="ussuesta" class="col-sm-2 control-label">{{ whatClassIsIt("ussuesta") }}</label>
							<div class="col-sm-10">
								<select class="form-control" name ="ussuesta" id="ussuesta" ng-model="ussuesta" ng-options="opt.value as opt.label for opt in optionsEstados" data-ng-disabled="buttonDelete"></select>
							</div>
						</div> 											
		  			</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			        <button type="button" class="btn btn-primary" data-ng-click="insertRecord()" data-ng-show="buttonNew">Registrar <span class="glyphicon glyphicon-floppy-disk"></span></button>
			        <button type="button" class="btn btn-success" data-ng-click="updateRecord()" data-ng-show="buttonEdit">Guardar Cambios <span class="glyphicon glyphicon-floppy-disk"></span></button>
			        <button type="button" class="btn btn-danger" data-ng-click="deleteRecord()" data-ng-show="buttonDelete">Inactivar <span class="glyphicon glyphicon-trash"> </span></button>
			      </div>
			    </div>
			  </div>
			</div>
			<custom-alert name-modal="myModalError" label-error="Ninguno"></custom-alert>      	   		
        </div>
	</div>         	    	        
</sec:authorize>  
