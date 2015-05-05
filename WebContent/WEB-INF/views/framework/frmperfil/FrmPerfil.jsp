<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, FRM_PERFIL_ALL, FRM_PERFIL_READ">	
	
	<div class="row">
      <div class="col-md-6" ng-controller="FrmPerfilController"><!-- Division grid maestro -->      	
        <div class="well well-sm">
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFIL_ALL,FRM_PERFIL_CREATE"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="createRecordForm()"> Nuevo <span class="glyphicon glyphicon-file"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFIL_ALL,FRM_PERFIL_UPDATE"><button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="loadDatatoForm()"  > Editar <span class="glyphicon glyphicon-edit"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFIL_ALL,FRM_PERFIL_DELETE"><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModalNew" ng-click="deleteRecordForm()" > Borrar <span class="glyphicon glyphicon-trash"> </span></button></sec:authorize>
			<a href="#"><span class="badge"><span class="glyphicon glyphicon-info-sign"> </span></span></a>	
		</div>
		<h3>{{ventanaTitulo}}</h3>
    	<div class="gridStyle" ng-grid="gridOptions"></div>
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
					  <label for="peficons" class="col-sm-2 control-label">{{ whatClassIsIt("peficons") }}</label>
					  <div class="col-sm-10">
					  	<input type="number" name ="peficons" id="peficons" ng-model="peficons" readonly="readonly" value="{{peficons}}">
	  			  	  </div>
					</div>
	   				<div class="form-group">
					  <label for="pefinomb" class="col-sm-2 control-label">{{ whatClassIsIt("pefinomb") }}</label>
					  <div class="col-sm-10">
					  	<input type="text" name ="pefinomb" id="pefinomb" ng-model="pefinomb" min="1" max="99" required value="{{pefinomb}}">
					  </div>
					</div>							                 				
					<div class="form-group">
					  <label for="pefidesc" class="col-sm-2 control-label">{{ whatClassIsIt("pefidesc") }}</label>
					  <div class="col-sm-10">
					  	<textarea class="form-control" rows="3" name ="pefidesc" id="pefidesc" ng-model="pefidesc">{{pefidesc}}</textarea>
					  </div>
					</div>
					<div class="form-group">
					  <label for="pefifecr" class="col-sm-2 control-label">{{ whatClassIsIt("pefifecr") }}</label>
					  <div class="col-sm-10">
					  	<input type="text" name ="pefifecr" id="pefifecr" ng-model="pefifecr" min="1" max="99" readonly="readonly" value="{{pefifecr}}">
					  </div>
					</div>
					<div class="form-group">
					  <label for="pefiesta" class="col-sm-2 control-label">{{ whatClassIsIt("pefiesta") }}</label>
					  <div class="col-sm-10">
					  	<input type="text" name ="pefiesta" id="pefiesta" ng-model="pefiesta" readonly="readonly" value="{{pefiesta}}">
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
      </div>
      <div class="col-md-6" ng-controller="FrmPerfmoduController"><!-- Division grid detalle -->      	
        <div class="well well-sm">
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFMODU_ALL,FRM_PERFMODU_CREATE"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalChildren" ng-click="createRecordFormChildren()"> Nuevo <span class="glyphicon glyphicon-file"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFMODU_ALL,FRM_PERFMODU_UPDATE"><button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModalChildren" ng-click="loadDatatoFormChildren()"> Editar <span class="glyphicon glyphicon-edit"> </span></button></sec:authorize>
			<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,FRM_PERFMODU_ALL,FRM_PERFMODU_DELETE"><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModalChildren" ng-click="deleteRecordFormChildren()"> Borrar <span class="glyphicon glyphicon-trash"> </span></button></sec:authorize>
			<a href="#"><span class="badge"><span class="glyphicon glyphicon-info-sign"> </span></span></a>
		</div>
		<h3>{{ventanaTituloChildren}}</h3>
    	<div class="gridStyle" ng-grid="gridOptionsChildren"></div>
    	<!-- ventana modal -->
    	<!-- Modal New -->
		<div class="modal fade" id="myModalChildren" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">{{ventanaTituloChildren}}</h4>{{tituloError}}
		      </div>
		      <div class="modal-body">
		        <form name="formInsert" class="form-horizontal" role="form">
		        	<div class="form-group">
					  <label for="peficons" class="col-sm-2 control-label">{{ whatClassIsItChildren("pemopefi") }}</label>
					  <div class="col-sm-10">
					  	{{pefinomb}}
	  			  	  </div>					  
					</div>
		        	<div class="form-group">
					  <label for="peficons" class="col-sm-2 control-label">{{ whatClassIsItChildren("pemocons") }}</label>
					  <div class="col-sm-10">
					  	<input type="number" name ="pemocons" id="pemocons" ng-model="pemocons" readonly="readonly" value="{{pemocons}}">
	  			  	  </div>
					</div>
					<div class="form-group">
					  <label for="pemomoro" class="col-sm-2 control-label">{{ whatClassIsItChildren("pemomoro") }}</label>
					  <div class="col-sm-10">					  	
					  	<select class="form-control" id="pemomoro" ng-model="pemomoro" ng-options="opt.value as opt.label for opt in optionsMoro"></select>
	  			  	  </div>
					</div>					
	  			</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
		        <button type="button" class="btn btn-primary" ng-click="insertRecordChildren()" ng-show="buttonNewChildren">Registrar <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-success" ng-click="updateRecordChildren()" ng-show="buttonEditChildren">Guardar Cambios <span class="glyphicon glyphicon-floppy-disk"></span></button>
		        <button type="button" class="btn btn-danger" ng-click="deleteRecordChildren()" ng-show="buttonDeleteChildren">Borrar <span class="glyphicon glyphicon-trash"> </span></button>
		      </div>
		    </div>
		  </div>
		</div> 
		<script type="text/ng-template" id="myModalContent.html">       	
		  	<div class="modal-header">
            	<h3 class="modal-title">I'm a modal! {{tituloError}}</h3>
	        </div>
    	    <div class="modal-body">            
        	    Selected: <b>{{ error }}</b>
        	</div>
        	<div class="modal-footer">
            	<button class="btn btn-danger" data-dismiss="modal">Cancel</button>
        	</div>
    	</script>	   	
      </div>
    </div>	
    	        
</sec:authorize>  