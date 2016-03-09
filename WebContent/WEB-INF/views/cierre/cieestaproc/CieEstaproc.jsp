<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, CIEESTAPROC_ALL, CIEESTAPROC_READ">	
		
      <div ng-controller="CieEstaprocController" ng-init="init()"><!-- Division grid maestro -->      	
        <div class="well well-sm">
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,CIEESTAPROC_ALL,CIEESTAPROC_CREATE"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="createRecordForm()"> Nuevo <span class="glyphicon glyphicon-file"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,CIEESTAPROC_ALL,CIEESTAPROC_UPDATE"><button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="loadDatatoForm()"  > Editar <span class="glyphicon glyphicon-edit"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,CIEESTAPROC_ALL,CIEESTAPROC_DELETE"><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="deleteRecordForm()" > Borrar <span class="glyphicon glyphicon-trash"> </span></button></sec:authorize>
			<button type="button" class="btn btn-default btn-sm"><a href="#"> Ayuda <span class="glyphicon glyphicon-info-sign"> </span></a></button>	
		</div>
		<h3>{{ventanaTitulo}}</h3>
    	<custom-grid cols="columnDefs" selected-items="selectedItems" custom-options="gridOptions" evento="gridEvento" icons="iconForeesta" data-ng-if="directiveGrid"></custom-grid>
    	<!-- ventana modal -->
    	<!-- Modal New -->
		<div class="modal fade" id="myModalNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
		      </div>
		      <div class="modal-body">
		        <form name="formInsert" class="form-horizontal" role="form">

							<div class="form-group">
								<label for="esprcons" class="col-sm-2 control-label">{{ whatClassIsIt("esprcons") }}</label>
								<div class="col-sm-10">
									<input type="number" name ="esprcons" id="esprcons" style="width:100%;" ng-model="esprcons" value="{{esprcons}}" ng-pattern="/^(0|\-?[1-9][0-9]*)$/" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div>
		 
			
							<div class="form-group">
								<label for="esprnomb" class="col-sm-2 control-label">{{ whatClassIsIt("esprnomb") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{esprnomb}}" id="{{esprnomb}}" ng-model="esprnomb" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="esprporc" class="col-sm-2 control-label">{{ whatClassIsIt("esprporc") }}</label>
								<div class="col-sm-10">
									<input type="number" name ="esprporc" id="esprporc" style="width:100%;" ng-model="esprporc" value="{{esprporc}}" ng-pattern="/^(0|\-?[1-9][0-9]*)$/" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div>
		 
			
							<div class="form-group">
								<label for="esprdesc" class="col-sm-2 control-label">{{ whatClassIsIt("esprdesc") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{esprdesc}}" id="{{esprdesc}}" ng-model="esprdesc" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="espreror" class="col-sm-2 control-label">{{ whatClassIsIt("espreror") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{espreror}}" id="{{espreror}}" ng-model="espreror" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="espruser" class="col-sm-2 control-label">{{ whatClassIsIt("espruser") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{espruser}}" id="{{espruser}}" ng-model="espruser" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="espresta" class="col-sm-2 control-label">{{ whatClassIsIt("espresta") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{espresta}}" id="{{espresta}}" ng-model="espresta" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="esprduho" class="col-sm-2 control-label">{{ whatClassIsIt("esprduho") }}</label>
								<div class="col-sm-10">
									<input type="number" name ="esprduho" id="esprduho" style="width:100%;" ng-model="esprduho" value="{{esprduho}}" ng-pattern="/^(0|\-?[1-9][0-9]*)$/" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div>
		 
			
							<div class="form-group">
								<label for="esprfein" class="col-sm-2 control-label">{{ whatClassIsIt("esprfein") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{esprfein}}" id="{{esprfein}}" ng-model="esprfein" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
			
							<div class="form-group">
								<label for="esprfefi" class="col-sm-2 control-label">{{ whatClassIsIt("esprfefi") }}</label>
								<div class="col-sm-10">
									<input style="width:100%;" type="text" name ="{{esprfefi}}" id="{{esprfefi}}" ng-model="esprfefi" ng-required="true" data-ng-readonly="buttonDelete">
								</div>
							</div> 
							
	  			</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
		        <button type="button" class="btn btn-primary" ng-click="insertRecord()" ng-show="buttonNew">Registrar <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-success" ng-click="updateRecord()" ng-show="buttonEdit">Guardar Cambios <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-danger" ng-click="deleteRecord()" ng-show="buttonDelete">Borrar <span class="glyphicon glyphicon-trash"> </span></button>
		      </div>
		    </div>
		  </div>		  			
		</div>
		
		<custom-alert labelerror="Ninguno"></custom-alert>									
      </div>          	    	        
</sec:authorize>  
