<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, CIERRECARTERACUADRE_ALL, CIERRECARTERACUADRE_READ">
	
      <div ng-controller="CierreCarteraCuadreController" ng-init="init()"><!-- Division grid maestro -->      	
        
        <!-- Nested list template -->
	    <script type="text/ng-template" id="form_renderer.jsp">  
			  <label for="{{column.paralabe}}" class="col-sm-3 control-label"><label ng-if="column.pararequ == 1"><font color="red">*</font></label>{{column.paralabe}}</label>
			  <div class="col-sm-3" ng-if="column.paratida == 'S'">			
				<input style="width:100%;" type="text" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'I' || column.paratida == 'L'">				
				<input ng-pattern="/^(0|\-?[1-9][0-9]*)$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'F' || column.paratida == 'O'">				
				<input ng-pattern="/^(0|\-?{0,9}\.\d{1,9})$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ" step="any">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'D'">			
              	<input style="width:100%;" type="text" datepicker-popup="dd/MM/yyyy" ng-model="Params[column.paranomb]" is-open="campoDate" ng-click="campoDate=true" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="column.pararequ" ui-date ui-date-format="dd/MM/yyyy" close-text="Cerrar" current-text="Hoy" clear-text="Limpiar"/>
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'CS' || column.paratida == 'CI'">
				<select class="form-control" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-options="opt.value as opt.label for opt in options[column.paracomb]" ng-required="column.pararequ"></select>
	  	      </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'TA' || column.paratida == 'TL'">
				<textarea name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ" rows="20" cols="50%" ng-trim="false"></textarea>
	  	      </div>
        </script>
        	    
	    <div align="left">
		    <h2><label ng-bind-html="title |to_trusted"></label></h2><br/>		    
		    
		    <h4><label ng-bind-html="description | to_trusted"></label></h4>
		    <h4>Por favor, ingrese los siguientes datos para consultar la informaci&oacute;n. (<font color="red">*</font>) Campos obligatorios.<h4><br/>
		    		    
		    <form name="formData" class="form-horizontal" role="form">
			   <div class="form-group" ng-repeat="column in columns | filter: {paratipo: 'E'}" ng-include="'form_renderer.jsp'"></div>			 			 
			  
			   <div align="center">	
        		<button type="reset" class="btn btn-default btn-lg active" >Limpiar Datos</button>		        	
        		<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,CIERRECARTERACUADRE_ALL,CIERRECARTERACUADRE_ALL"><button type="button" class="btn btn-success btn-lg active" ng-click="ExecuteProcess()" ng-show="Boton">Ejecutar Proceso<span class="glyphicon glyphicon-floppy-disk"></span></button></sec:authorize>
		      </div>		      
			</form>				
		</div>			
		
		<div class="panel" align="center" ng-if="BotonLoader">
			<button class="btn btn-lg btn-warning"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Cargando...</button>
		</div>				
		
		<div class="alert alert-success" role="alert" ng-if="trans" align="center"><h4><label ng-bind-html="transaccion |to_trusted"></label></h4></div>
		<div class="alert alert-danger" role="alert" ng-if="Error" align="center"><h4><b>Error:</b> <label ng-bind-html="DescripcionError |to_trusted"></label></h4></div>									
		
      </div>          	      
    
</sec:authorize>      