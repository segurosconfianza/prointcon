<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, PIL_USUA_ALL, PIL_USUA_READ">	
		
      <div data-ng-controller="PilUsuaController" ng-init="init()"><!-- Division grid maestro -->      	
        <div class="well well-sm">
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_CREATE"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalNew" data-ng-click="createRecordForm()"> Nuevo <span class="glyphicon glyphicon-file"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_UPDATE"><button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModalNew" data-ng-click="loadDatatoForm()"  > Editar <span class="glyphicon glyphicon-edit"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,PIL_USUA_ALL,PIL_USUA_DELETE"><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModalNew" data-ng-click="deleteRecordForm()" > Inactivar <span class="glyphicon glyphicon-trash"> </span></button></sec:authorize>
			<button type="button" class="btn btn-default btn-sm"><a href="#"> Ayuda <span class="glyphicon glyphicon-info-sign"> </span></a></button>	
		</div>
		<h3>{{ventanaTitulo}}</h3>-{{buttonDelete}}
    	<custom-grid cols="columnDefs" selected-items="selectedItems" custom-options="gridOptions" evento="gridEvento" data-ng-if="directiveGrid"></custom-grid>
    	<!-- ventana modal -->
    	<!-- Modal New -->
		<div class="modal fade" id="myModalNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">{{ventanaTitulo}}</h4>
		        <h6>Por favor, ingrese los siguientes datos. (<font color="red">*</font>) Campos obligatorios.<h6>
		      </div>
		      <div class="modal-body">
		        <form name='formData' class="form-horizontal" role="form">		        	
					<div class="form-group">
						<label for="usuacons" class="col-sm-6 control-label">{{ whatClassIsIt("usuacons") }}</label>
						<div class="col-sm-6">
							<input type="number" name ="usuacons" id="usuacons" style="width:100%;" data-ng-model="usuacons" value="{{usuacons}}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="usuaunit" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuaunit") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuaunit}}" id="{{usuaunit}}" data-ng-model="usuaunit" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuadive" class="col-sm-6 control-label">{{ whatClassIsIt("usuadive") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuadive}}" id="{{usuadive}}" data-ng-model="usuadive" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuatiin" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuatiin") }}</label>
						<div class="col-sm-6">
							<select class="form-control" id="usuatiin" data-ng-model="usuatiin" data-ng-options="opt.value as opt.label for opt in optionsUsuatiin" data-ng-required="true" data-ng-disabled="buttonDelete"></select>
						</div>
					</div> 
					<div class="form-group">
						<label for="usuarazo" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuarazo") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuarazo}}" id="{{usuarazo}}" data-ng-model="usuarazo" ng-change="usuarazo=usuarazo.toUpperCase();" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuanomb" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuanomb") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuanomb}}" id="{{usuanomb}}" data-ng-model="usuanomb" ng-change="usuanomb=usuanomb.toUpperCase();" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuaapel" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuaapel") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuaapel}}" id="{{usuaapel}}" data-ng-model="usuaapel" ng-change="usuaapel=usuaapel.toUpperCase();" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuaemai" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuaemai") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="email" name ="{{usuaemai}}" id="{{usuaemai}}" data-ng-model="usuaemai" data-ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuatele" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuatele") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuatele}}" id="{{usuatele}}" data-ng-model="usuatele" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuapeco" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuapeco") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuapeco}}" id="{{usuapeco}}" data-ng-model="usuapeco" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuausua" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuausua") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="text" name ="{{usuausua}}" id="{{usuausua}}" data-ng-model="usuausua" data-ng-required="true" data-ng-readonly="buttonDelete">
						</div>
					</div> 
					<div class="form-group">
						<label for="usuapass" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuapass") }}</label>
						<div class="col-sm-6">
							<input style="width:100%;" type="password" name ="{{usuapass}}" id="{{usuapass}}" data-ng-model="usuapass" data-ng-required="buttonNew" data-ng-readonly="buttonDelete">
						</div>
					</div> 					
					<div class="form-group">
						<label for="usuasucu" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuasucu") }}</label>
						<div class="col-sm-6">
							<select class="form-control" id="usuasucu" data-ng-model="usuasucu" data-ng-options="opt.value as opt.label for opt in optionsSucursales" data-ng-required="true" data-ng-disabled="buttonDelete"></select>
						</div>
					</div>
					<div class="form-group" data-ng-show="buttonNew || buttonEdit">
						<label for="usuaesta" class="col-sm-6 control-label"><font color="red">*</font>{{ whatClassIsIt("usuaesta") }}</label>
						<div class="col-sm-6">
							<select class="form-control" id="usuaesta" data-ng-model="usuaesta" data-ng-options="opt.value as opt.label for opt in optionsUsuaesta" data-ng-required="true" data-ng-disabled="buttonDelete"></select>
						</div>
					</div> 
	  			</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
		        <button type="button" class="btn btn-primary" data-ng-click="insertRecord()" data-ng-show="buttonNew">Registrar <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-success" data-ng-click="updateRecord()" data-ng-show="buttonEdit">Guardar Cambios <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-danger" data-ng-click="deleteRecord()" data-ng-show="buttonDelete">Inactivar<span class="glyphicon glyphicon-trash"> </span></button>
		      </div>
		    </div>
		  </div>
		</div>
		<custom-alert name-modal="myModalError" label-error="Ninguno"></custom-alert>						
      </div>          	    	        
</sec:authorize>  
