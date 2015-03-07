<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR, SOPORTE_ALL, SOPORTE_READ">
	<div class="row">
      <div class="col-md-6" ng-controller="SoporteController"><!-- Division grid maestro -->      	
        
        <!-- Nested list template -->
	    <script type="text/ng-template" id="form_renderer.jsp">  
			  <label for="{{column.paralabe}}" class="col-sm-3 control-label"><label ng-if="column.pararequ == 1"><font color="red">*</font></label>{{column.paralabe}}</label>
			  <div class="col-sm-3" ng-if="column.paratida == 'S'">			
				<input ng-pattern="/^[a-zA-Z0-9]*$/" style="width:100%;" type="text" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'I' || column.paratida == 'L'">				
				<input ng-pattern="/^[0-9]*$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'F' || column.paratida == 'O'">				
				<input ng-pattern="/^[0-9]*$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ" step="any">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'D'">			
              	<input style="width:100%;" type="text" datepicker-popup="dd/MM/yyyy" ng-model="Params[column.paranomb]" is-open="campoDate" ng-click="campoDate=true" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="column.pararequ" ui-date ui-date-format="dd/MM/yyyy" close-text="Cerrar" current-text="Hoy" clear-text="Limpiar"/>
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'CS' || column.paratida == 'CI'">
				<select class="form-control" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-options="opt.value as opt.label for opt in options[column.paracomb]" ng-required="column.pararequ"></select>
	  	      </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'TA' || column.paratida == 'TL'">
				<textarea name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ" rows="20" cols="50%"></textarea>
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
        		<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,SOPORTE_ALL,SOPORTE_READ"><button type="button" class="btn btn-primary btn-lg active" ng-click="loadRecord()" ng-show="Boton">Consultar Datos <span class="glyphicon glyphicon-search"></span></button></sec:authorize>
		      </div>		      
			</form>				
		</div>			
		
		<div class="panel" align="center" ng-if="BotonLoader">
			<button class="btn btn-lg btn-warning"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Cargando...</button>
		</div>				
		
		<div class="alert alert-success" role="alert" ng-if="trans" align="center"><h4><b>Transacci&oacute;n:</b> {{transaccion}}</h4></div>
		<div class="alert alert-danger" role="alert" ng-if="Error" align="center"><h4><b>Error:</b> {{EROR}}</h4></div>
		
		<div align="left" ng-show="Result">		    		    		    
		    <form name="formData2" class="form-horizontal" role="form">
			   <div class="form-group" ng-repeat="column in columns | filter: {paratipo: 'S'}" ng-include="'form_renderer.jsp'"></div>
			  
			   <div class="form-group">
				  <label for="file" class="col-sm-3 control-label"><font color="red">*</font>Seleccione el/los adjunto(s): </label>
				  <div class="col-sm-3">			
					<input type="file" ng-file-select ng-model="picFile" name="file" multiple="multiple" required>
				  </div>
			   </div>
			   
			   <div class="form-group">
				  <label for="motivo" class="col-sm-3 control-label">Ingrese el motivo: </label>
				  <div class="col-sm-3">			
					<input type="text" ng-model="Motivo" name="Motivo" required>
				  </div>
			   </div>			  			  
			  
			   <div align="center">	
	        		<button type="reset" class="btn btn-default btn-lg active" >Limpiar Datos</button>		        	
	        		<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,SOPORTE_ALL,SOPORTE_ALL"><button type="button" class="btn btn-success btn-lg active" ng-click="updateRecord(picFile, Motivo)" ng-show="Boton">Modificar Datos <span class="glyphicon glyphicon-floppy-disk"></span></button></sec:authorize>
		      </div>	
			</form>				
		</div>						
		
      </div>
      <div class="col-md-6" ng-controller="SoporteChildController"><!-- Division grid detalle -->
      	 <script type="text/ng-template" id="form_renderer_children.jsp">  
			  <label for="{{column.paralabe}}" class="col-sm-3 control-label"><label ng-if="column.pararequ == 1"><font color="red">*</font></label>{{column.paralabe}}</label>
			  <div class="col-sm-3" ng-if="column.paratida == 'S'">			
				<input ng-pattern="/^[a-zA-Z0-9]*$/" style="width:100%;" type="text" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'I' || column.paratida == 'L'">				
				<input ng-pattern="/^[0-9]*$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'F' || column.paratida == 'O'">				
				<input ng-pattern="/^[0-9]*$/" style="width:100%;" type="number" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-required="column.pararequ" step="any">
			  </div>
			  <div class="col-sm-3" ng-if="column.paratida == 'D'">			
              	<input style="width:100%;" type="text" datepicker-popup="dd/MM/yyyy" ng-model="Params[column.paranomb]" is-open="campoDate" ng-click="campoDate=true" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="column.pararequ" ui-date ui-date-format="dd/MM/yyyy" close-text="Cerrar" current-text="Hoy" clear-text="Limpiar"/>
			  </div>
              <div class="col-sm-3" ng-if="column.paratida == 'CS' || column.paratida == 'CI'">
				<select class="form-control" name ="{{column.paracons}}" id="{{column.paracons}}" ng-model="Params[column.paranomb]" ng-options="opt.value as opt.label for opt in options[column.paracomb]" ng-required="column.pararequ"></select>
	  	      </div>
        </script> 
        <script type="text/ng-template" id="data_renderer.jsp">
			<td width="20%"><b ng-bind-html="column |to_trusted"></b>: </td><td width="80%" ng-bind-html="Data[column] | nl2br">&nbsp;</td>	
        </script>
         
        <div align="left">
		    <h2><label ng-bind-html="title |to_trusted"></label></h2><br/>		    
		    
		    <h4><label ng-bind-html="description | to_trusted"></label></h4>
		    <h4>Por favor, ingrese los siguientes datos para consultar la informaci&oacute;n. (<font color="red">*</font>) Campos obligatorios.<h4><br/>
		    		    
		    <form name="formData" class="form-horizontal" role="form">
			   <div class="form-group" ng-repeat="column in columns | filter: {paratipo: 'E'}" ng-include="'form_renderer_children.jsp'"></div>			 			 
			  
			   <div align="center">	
	        		<button type="reset" class="btn btn-default btn-lg active" >Limpiar Datos</button>		        	
	        		<sec:authorize ifAnyGranted="ADMINISTRATOR_ADMINISTRATOR,SOPORTE_ALL,SOPORTE_READ"><button type="button" class="btn btn-primary btn-lg active" ng-click="loadRecord()" ng-show="Boton">Consultar Datos <span class="glyphicon glyphicon-search"></span></button></sec:authorize>
		      </div>		      
			</form>				
		</div>	 
      	<div class="panel" align="center" ng-if="BotonLoader">
			<button class="btn btn-lg btn-warning"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Cargando...</button>
		</div>
		
		<div class="panel" align="left" ng-if="Result">			
			<table class="table table-condensed" border=0 width="80%">						  
				<tr ng-repeat="column in Camp" ng-include="'data_renderer.jsp'">			   			   		 
			   	</tr>
			</table>			   		
		</div>
      </div>	    	       
    </div>
</sec:authorize>      